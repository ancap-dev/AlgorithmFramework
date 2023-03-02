package ru.ancap.algorithm.walkthrough;

import java.util.Set;

public interface NodeMethodApplier<NODE> {
    
    Set<NODE> neighbors(NODE applyTo);
    boolean equals(NODE applyTo, NODE compared);
    
}
