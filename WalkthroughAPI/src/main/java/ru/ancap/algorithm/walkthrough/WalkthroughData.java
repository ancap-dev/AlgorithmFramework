package ru.ancap.algorithm.walkthrough;

import lombok.With;
import ru.ancap.commons.list.with.WithList;

@With
public record WalkthroughData<NODE, CUSTOM_DATA>(NODE target, WithList<NODE> path, CUSTOM_DATA customData) {}