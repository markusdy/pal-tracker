package io.pivotal.pal.tracker;

import io.micrometer.core.instrument.AbstractDistributionSummary;
import io.micrometer.core.instrument.Clock;
import io.micrometer.core.instrument.DistributionSummary;
import io.micrometer.core.instrument.distribution.DistributionStatisticConfig;
import io.micrometer.core.instrument.distribution.HistogramSnapshot;

public class TimeEntryDistributionSummary extends AbstractDistributionSummary {


    protected TimeEntryDistributionSummary(Id id, Clock clock, DistributionStatisticConfig distributionStatisticConfig, double scale, boolean supportsAggregablePercentiles) {
        super(id, clock, distributionStatisticConfig, scale, supportsAggregablePercentiles);
    }

    @Override
    protected void recordNonNegative(double amount) {

    }

    @Override
    public long count() {
        return 0;
    }

    @Override
    public double totalAmount() {
        return 0;
    }

    @Override
    public double max() {
        return 0;
    }
}
