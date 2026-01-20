package com.sree.movie.pricing;

import com.sree.movie.entity.Show;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalTime;
import java.util.List;

@Component
@RequiredArgsConstructor
public class DiscountCalculator {

    private final List<DiscountRule> rules;

    public BigDecimal calculate(Show show, int seats) {

        BigDecimal amount =
                show.getTicketPrice().multiply(BigDecimal.valueOf(seats));

        for (DiscountRule rule : rules) {
            if (rule.isApplicable(show, seats)) {
                amount = rule.apply(show, seats, amount);
            }
        }
        return amount;
    }

    public BigDecimal applyDiscount(Show show, int seats) {
        BigDecimal price = show.getTicketPrice()
                .multiply(BigDecimal.valueOf(seats));

        if (seats >= 3) {
            BigDecimal thirdTicket = show.getTicketPrice()
                    .multiply(BigDecimal.valueOf(0.5));
            price = price.subtract(thirdTicket);
        }

        if (show.getShowTime().isAfter(LocalTime.NOON)
                && show.getShowTime().isBefore(LocalTime.of(17, 0))) {
            price = price.multiply(BigDecimal.valueOf(0.8));
        }

        return price;
    }
}
