package com.sree.movie.pricing;

import com.sree.movie.entity.Show;

import java.math.BigDecimal;

public interface DiscountRule {

    BigDecimal apply(Show show, int seats, BigDecimal currentAmount);

    boolean isApplicable(Show show, int seats);
}
