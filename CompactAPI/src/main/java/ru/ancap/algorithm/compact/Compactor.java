package ru.ancap.algorithm.compact;

/**
 * Definitely supports packing range from Integer.MIN_VALUE + 1 to Integer.MAX_VALUE by specification. 
 * <b>May</b> support range from Integer.MIN_VALUE to Integer.MAX_VALUE.
 */
public interface Compactor {
    
    int MIN_VALUE = -2147483647;
    int MAX_VALUE =  2147483647;
    
    int[] unpack(long code);
    
    long pack(int first, int second);
    
    default long pack(long first, long second) {
        return this.pack((int) first, (int) second);
    }
    
}