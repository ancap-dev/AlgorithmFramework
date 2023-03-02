package ru.ancap.algorithm.walkthrough;

public sealed interface StepResult<CUSTOM_DATA> permits StepResult.Allow, StepResult.Deny, StepResult.EndWalkthrough {


    record Allow<CUSTOM_DATA>(CUSTOM_DATA customData) implements StepResult<CUSTOM_DATA> {
        
        public static Allow<Void> VOID = new Allow<>(null);
        
    }

    static <CUSTOM_DATA> Deny<CUSTOM_DATA> DENY() {return new Deny<>();}
    record Deny<CUSTOM_DATA>() implements StepResult<CUSTOM_DATA> {}

    static <CUSTOM_DATA> EndWalkthrough<CUSTOM_DATA> END_WALKTHROUGH() {return new EndWalkthrough<>();}
    record EndWalkthrough<CUSTOM_DATA>() implements StepResult<CUSTOM_DATA> {}
}
