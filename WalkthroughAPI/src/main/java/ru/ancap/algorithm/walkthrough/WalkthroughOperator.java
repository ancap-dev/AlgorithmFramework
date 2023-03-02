package ru.ancap.hexagon.walkthrough;

public interface WalkthroughOperator<NODE, CUSTOM_DATA> {
    
    StepResult<CUSTOM_DATA> step(NODE target, WalkthroughData<NODE, CUSTOM_DATA> previous);

    /**
     * You may want to receive not null, but something else for the first call in chain of calls, and 
     * this method is for that situation, by default it returns null, but you can override it
     */
    default CUSTOM_DATA initialData(NODE ignoredByDefaultValue) {
        return null;
    }

}