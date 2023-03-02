package ru.ancap.hexagon.walkthrough.classic;

import lombok.AllArgsConstructor;
import ru.ancap.hexagon.walkthrough.StepResult;
import ru.ancap.hexagon.walkthrough.WalkthroughData;
import ru.ancap.hexagon.walkthrough.WalkthroughOperator;

import java.util.function.Predicate;

@AllArgsConstructor
public class RegionWalkthrough<NODE> implements WalkthroughOperator<NODE, Void> {
    
    private final Predicate<NODE> regionPredicate;

    @Override
    public StepResult<Void> step(NODE target, WalkthroughData<NODE, Void> previous) {
        if (!this.regionPredicate.test(target)) return StepResult.DENY;
        return StepResult.Allow.VOID;
    }
}
