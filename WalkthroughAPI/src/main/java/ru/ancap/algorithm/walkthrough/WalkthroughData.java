package ru.ancap.hexagon.walkthrough;

import lombok.With;
import ru.ancap.commons.withlist.WithList;

@With
public record WalkthroughData<NODE, CUSTOM_DATA>(NODE target, WithList<NODE> path, CUSTOM_DATA customData) {}