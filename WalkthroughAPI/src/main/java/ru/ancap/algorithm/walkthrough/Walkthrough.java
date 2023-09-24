package ru.ancap.algorithm.walkthrough;

import lombok.AllArgsConstructor;

import java.util.HashSet;
import java.util.Set;
import java.util.Stack;

@AllArgsConstructor
public class Walkthrough<NODE> {

    private final NODE base;
    private final WalkthroughOperator<NODE> walkthroughOperator;
    private final NodeMethodApplier<NODE> nodeNodeMethodApplier;
    
    public WalkthroughResult<NODE> walkthrough() {

        Stack<NODE> stack = new Stack<>(); stack.push(this.base);
        Set<NODE> visited = new HashSet<>(); visited.add(this.base);

        collection: while (!stack.isEmpty()) {
            NODE nodeUpperLevel = stack.pop();
            Set<NODE> neighbors = this.nodeNodeMethodApplier.neighbors(nodeUpperLevel);
            for (NODE nodeLowerLevel : neighbors) {
                if (!visited.contains(nodeLowerLevel)) {
                    
                    StepResult stepResult = this.walkthroughOperator.step(nodeLowerLevel, new WalkthroughData<>(nodeLowerLevel));
                    
                    switch (stepResult) {
                        case ALLOW -> {
                            stack.push(nodeLowerLevel);
                            visited.add(nodeLowerLevel);
                        }
                        case DENY -> {}
                        case END_WALKTHROUGH -> {break collection;}
                    }
                    
                }
            }
        }
        return new WalkthroughResult<>(visited);
    }
    
}
