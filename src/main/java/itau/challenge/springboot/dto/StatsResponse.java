package itau.challenge.springboot.dto;

import java.util.DoubleSummaryStatistics;

public record StatsResponse(
        long count,
        double sum,
        double avg,
        double min,
        double max
) {
    public StatsResponse(DoubleSummaryStatistics statistics) {
        this(
                statistics.getCount(),
                statistics.getSum(),
                statistics.getAverage(),
                statistics.getMin(),
                statistics.getMax()
        );
    }
}