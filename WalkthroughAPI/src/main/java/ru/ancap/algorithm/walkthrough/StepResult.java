package ru.ancap.algorithm.walkthrough;

public sealed interface StepResult<CUSTOM_DATA> permits StepResult.Allow, StepResult.Deny, StepResult.EndWalkthrough {


    record Allow<CUSTOM_DATA>(CUSTOM_DATA customData) implements StepResult<CUSTOM_DATA> {
        
        public static Allow<Void> VOID = new Allow<>(null);
        
    }

    Deny DENY = new Deny();
    record Deny() implements StepResult<Void> {}

    EndWalkthrough END_WALKTHROUGH = new EndWalkthrough();
    record EndWalkthrough() implements StepResult<Void> {}
}
