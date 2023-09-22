package ru.ancap.algorithm.walkthrough.classic;

import lombok.AllArgsConstructor;
import ru.ancap.algorithm.walkthrough.StepResult;
import ru.ancap.algorithm.walkthrough.WalkthroughData;
import ru.ancap.algorithm.walkthrough.WalkthroughOperator;

import java.util.function.Predicate;

@AllArgsConstructor
public class RegionWalkthroughOperator<NODE> implements WalkthroughOperator<NODE> {
    
    private final Predicate<NODE> regionPredicate;

    @Override
    public StepResult step(NODE target, WalkthroughData<NODE> previous) {
        if (!this.regionPredicate.test(target)) return StepResult.DENY;
        return StepResult.ALLOW;
    }
    
}
