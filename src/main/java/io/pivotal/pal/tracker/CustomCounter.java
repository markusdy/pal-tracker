package io.pivotal.pal.tracker;

import io.micrometer.core.instrument.Counter;

public class CustomCounter implements Counter {

    private double count;
    @Override
    public void increment(double amount) {
        this.count += amount;
    }

    @Override
    public double count() {
        return this.count;
    }

    @Override
    public Id getId() {
        return new Id("73", null, null, null, Type.COUNTER);
    }
}
