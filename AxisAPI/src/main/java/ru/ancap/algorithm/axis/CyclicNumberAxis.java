package ru.ancap.algorithm.axis;

public class CyclicNumberAxis implements NumberAxis {

    public static CyclicNumberAxis HEXAGONAL = new CyclicNumberAxis(6);

    private final double boundOffset;
    private final double period;

    public CyclicNumberAxis(double period) {
        this(0, period, null);
    }

    public CyclicNumberAxis(double start, double end) {
        this(start, end - start, null);
    }

    private CyclicNumberAxis(double boundOffset, double period, Object hack_to_add_other_method_with_the_same_signature) {
        if (period < 0) throw new IllegalArgumentException("Period can not be smaller than 0");
        this.boundOffset = boundOffset;
        this.period = period;
    }

    public double offset(double base, double steps) {
        if (base+1 > this.period) throw new IllegalArgumentException("Base can not be outside the borders!");

        double offset = steps % this.period;

        double newBase = base + offset;
        if (newBase >= this.period) newBase -= this.period;
        else if (newBase < 0) newBase += this.period;

        return newBase + this.boundOffset;
    }

}
