package com.sree.movie.pricing;

import com.sree.movie.entity.Show;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalTime;

@Component
public class AfternoonShowDiscountRule implements DiscountRule {

    @Override
    public boolean isApplicable(Show show, int seats) {
        return show.getShowTime().isAfter(LocalTime.NOON)
                && show.getShowTime().isBefore(LocalTime.of(17, 0));
    }

    @Override
    public BigDecimal apply(Show show, int seats, BigDecimal amount) {
        return amount.multiply(BigDecimal.valueOf(0.8));
    }
}