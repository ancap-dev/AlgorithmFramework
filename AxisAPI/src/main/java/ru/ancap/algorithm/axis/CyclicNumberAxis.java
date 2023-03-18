package ru.ancap.algorithm.axis;

public class CyclicNumberAxis implements NumberAxis {

    public static CyclicNumberAxis HEXAGONAL = new CyclicNumberAxis(6);

    private final int boundOffset;
    private final int period;

    public CyclicNumberAxis(int period) {
        this(0, period, null);
    }

    public CyclicNumberAxis(int start, int end) {
        this(start, end - start, null);
    }

    private CyclicNumberAxis(int boundOffset, int period, Object signature) {
        if (period < 0) throw new IllegalArgumentException("Period can not be smaller than 0");
        this.boundOffset = boundOffset;
        this.period = period;
    }

    public int offset(int base, int steps) {
        if (base+1 > this.period) throw new IllegalArgumentException("Base can not be outside the borders!");

        int offset = steps % this.period;

        int newBase = base + offset;
        if (newBase >= this.period) newBase -= this.period;
        else if (newBase < 0) newBase += this.period;

        return newBase + this.boundOffset;
    }

}
