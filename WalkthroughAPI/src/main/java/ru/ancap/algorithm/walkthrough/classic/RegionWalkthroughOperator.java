package ru.ancap.algorithm.walkthrough.classic;

import lombok.AllArgsConstructor;
import ru.ancap.algorithm.walkthrough.StepResult;
import ru.ancap.algorithm.walkthrough.WalkthroughData;
import ru.ancap.algorithm.walkthrough.WalkthroughOperator;

import java.util.function.Predicate;

@AllArgsConstructor
public class RegionWalkthroughOperator<NODE> implements WalkthroughOperator<NODE, Void> {
    
    private final Predicate<NODE> regionPredicate;

    @Override
    public StepResult<Void> step(NODE target, WalkthroughData<NODE, Void> previous) {
        if (!this.regionPredicate.test(target)) return StepResult.DENY;
        return StepResult.Allow.VOID;
    }
    
}
