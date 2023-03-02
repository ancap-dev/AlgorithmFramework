package ru.ancap.hexagon.walkthrough.classic;

import lombok.AllArgsConstructor;
import ru.ancap.hexagon.walkthrough.StepResult;
import ru.ancap.hexagon.walkthrough.WalkthroughData;
import ru.ancap.hexagon.walkthrough.WalkthroughOperator;

import java.util.List;
import java.util.function.Consumer;

@AllArgsConstructor
public class PathfindingWalkthrough<NODE> implements WalkthroughOperator<NODE, Void> {
    
    private final NODE target;
    private final Consumer<List<NODE>> pathConsumer;
    
    @Override
    public StepResult<Void> step(NODE target, WalkthroughData<NODE, Void> previous) {
        if (this.target.equals(target)) {
            this.pathConsumer.accept(previous.path().with(target));
            return StepResult.END_WALKTHROUGH;
        }
        return StepResult.Allow.VOID;
    }
    
}
