package ru.ancap.hexagon.walkthrough;

import lombok.AllArgsConstructor;
import ru.ancap.commons.withlist.ImmutableWithList;
import ru.ancap.commons.withlist.WithList;

import java.util.*;

@AllArgsConstructor
public class Walkthrough<NODE, CUSTOM_DATA> {

    private final NODE base;
    private final WalkthroughOperator<NODE, CUSTOM_DATA> walkthroughOperator;
    private final NodeMethodApplier<NODE> nodeNodeMethodApplier;
    
    public WalkthroughResult<NODE> walkthrough() {

        Stack<NODE> stack = new Stack<>();
        stack.push(this.base);
        Set<NODE> visited = new HashSet<>();
        Map<NODE, WithList<NODE>> toHexPaths = new HashMap<>();
        Map<NODE, CUSTOM_DATA> dataTargetingData = new HashMap<>();
        toHexPaths.put(this.base, new ImmutableWithList<>(List.of(this.base)));
        dataTargetingData.put(this.base, this.walkthroughOperator.initialData(this.base));

        while (!stack.isEmpty()) {
            NODE nodeUpperLevel = stack.pop();
            Set<NODE> neighbors = this.nodeNodeMethodApplier.neighbors(nodeUpperLevel);
            for (NODE nodeLowerLevel : neighbors) {
                if (!visited.contains(nodeLowerLevel)) {
                    
                    WithList<NODE> pathToLowerLevel = toHexPaths.get(nodeUpperLevel).with(nodeLowerLevel);
                    
                    StepResult<CUSTOM_DATA> stepResult = this.walkthroughOperator.step(nodeLowerLevel, new WalkthroughData<>(
                        nodeLowerLevel,
                        pathToLowerLevel,
                        dataTargetingData.get(nodeLowerLevel)
                    ));
                    
                    switch (stepResult) {
                        case StepResult.Allow<CUSTOM_DATA> allow -> {
                            stack.push(nodeLowerLevel);
                            visited.add(nodeLowerLevel);
                            toHexPaths.put(nodeLowerLevel, pathToLowerLevel);
                            dataTargetingData.put(nodeLowerLevel, allow.customData());
                        }
                        case StepResult.Deny ignoredInstance -> {}
                        case StepResult.EndWalkthrough ignoredInstance -> {return null;}
                    }
                    
                }
            }
        }
        return new WalkthroughResult<>(visited);
    }
    
}
