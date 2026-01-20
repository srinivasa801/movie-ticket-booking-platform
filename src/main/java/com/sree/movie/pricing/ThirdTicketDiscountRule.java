package com.sree.movie.pricing;

import com.sree.movie.entity.Show;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class ThirdTicketDiscountRule implements DiscountRule {

    @Override
    public boolean isApplicable(Show show, int seats) {
        return seats >= 3;
    }

    @Override
    public BigDecimal apply(Show show, int seats, BigDecimal amount) {
        BigDecimal discount =
                show.getTicketPrice().multiply(BigDecimal.valueOf(0.5));
        return amount.subtract(discount);
    }
}
