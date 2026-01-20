package com.sree.movie;

import com.sree.movie.dto.BookingResponseDto;
import com.sree.movie.entity.Booking;
import com.sree.movie.entity.Show;
import com.sree.movie.exception.BusinessException;
import com.sree.movie.pricing.DiscountCalculator;
import com.sree.movie.repository.BookingRepository;
import com.sree.movie.repository.ShowRepository;
import com.sree.movie.service.Impl.BookingServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class BookingServiceImplTest {

    @Mock
    private ShowRepository showRepository;

    @Mock
    private BookingRepository bookingRepository;

    @Mock
    private DiscountCalculator discountCalculator;

    @InjectMocks
    private BookingServiceImpl bookingService;

    @Test
    void bookTicket_success() {

        // GIVEN
        Show show = TestDataUtil.createShow(1L, 50);
        Booking booking = TestDataUtil.createBooking(show);

        when(showRepository.findById(1L))
                .thenReturn(Optional.of(show));

        when(discountCalculator.calculate(show, 3))
                .thenReturn(BigDecimal.valueOf(400));

        when(bookingRepository.save(any(Booking.class)))
                .thenReturn(booking);

        // WHEN
        BookingResponseDto response =
                bookingService.bookTicket(1L, 3, "Srinivas");

        // THEN
        assertThat(response.getSeats()).isEqualTo(3);
        assertThat(response.getTotalAmount())
                .isEqualByComparingTo("400");

        verify(showRepository).findById(1L);
        verify(bookingRepository).save(any(Booking.class));
    }

    @Test
    void bookTicket_insufficientSeats_shouldThrowException() {

        Show show = TestDataUtil.createShow(1L, 1);

        when(showRepository.findById(1L))
                .thenReturn(Optional.of(show));

        assertThatThrownBy(() ->
                bookingService.bookTicket(1L, 3, "Test"))
                .isInstanceOf(BusinessException.class)
                .hasMessage("Insufficient seats available");
    }
}