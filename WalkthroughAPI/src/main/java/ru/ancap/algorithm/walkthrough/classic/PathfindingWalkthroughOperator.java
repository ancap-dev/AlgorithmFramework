package ru.ancap.algorithm.walkthrough.classic;

import lombok.AllArgsConstructor;
import ru.ancap.algorithm.walkthrough.StepResult;
import ru.ancap.algorithm.walkthrough.WalkthroughData;
import ru.ancap.algorithm.walkthrough.WalkthroughOperator;

import java.util.List;
import java.util.function.Consumer;

@AllArgsConstructor
public class PathfindingWalkthroughOperator<NODE> implements WalkthroughOperator<NODE, Void> {
    
    private final NODE target;
    private final Consumer<List<NODE>> pathConsumer;
    
    @Override
    public StepResult<Void> step(NODE target, WalkthroughData<NODE, Void> previous) {
        if (this.target.equals(target)) {
            this.pathConsumer.accept(previous.path().with(target));
            return StepResult.END_WALKTHROUGH();
        }
        return StepResult.Allow.VOID;
    }
    
}
