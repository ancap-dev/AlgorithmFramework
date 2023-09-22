package ru.ancap.algorithm.walkthrough;

public interface WalkthroughOperator<NODE> {
    
    StepResult step(NODE target, WalkthroughData<NODE> previous);

}