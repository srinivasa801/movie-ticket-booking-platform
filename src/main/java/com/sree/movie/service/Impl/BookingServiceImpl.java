package com.sree.movie.service.Impl;

import com.sree.movie.dto.BookingResponseDto;
import com.sree.movie.entity.Booking;
import com.sree.movie.entity.Show;
import com.sree.movie.exception.BusinessException;
import com.sree.movie.exception.ResourceNotFoundException;
import com.sree.movie.pricing.DiscountCalculator;
import com.sree.movie.repository.BookingRepository;
import com.sree.movie.repository.ShowRepository;
import com.sree.movie.service.BookingService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
@Slf4j
public class BookingServiceImpl implements BookingService {

    private final ShowRepository showRepository;
    private final BookingRepository bookingRepository;
    private final DiscountCalculator discountCalculator;

    @Override
    @Transactional
    public BookingResponseDto bookTicket(
            Long showId, int seats, String customerName) {

        log.info("Booking started | showId={} seats={} customer={}",
                showId, seats, customerName);

        Show show = showRepository.findById(showId)
                .orElseThrow(() -> {
                    log.warn("Show not found | showId={}", showId);
                    return new ResourceNotFoundException("Show not found");
                });

        log.debug("Available seats before booking | showId={} available={}",
                showId, show.getAvailableSeats());

        if (show.getAvailableSeats() < seats) {
            log.warn("Insufficient seats | showId={} requested={} available={}",
                    showId, seats, show.getAvailableSeats());
            throw new BusinessException("Insufficient seats available");
        }

        BigDecimal finalAmount =
                discountCalculator.calculate(show, seats);

        log.info("Final price calculated | showId={} amount={}",
                showId, finalAmount);

        show.setAvailableSeats(show.getAvailableSeats() - seats);

        Booking booking = new Booking();
        booking.setCustomerName(customerName);
        booking.setSeatCount(seats);
        booking.setTotalAmount(finalAmount);
        booking.setShow(show);

        Booking savedBooking = bookingRepository.save(booking);

        log.info("Booking successful | bookingId={} showId={} remainingSeats={}",
                savedBooking.getId(),
                showId,
                show.getAvailableSeats());

        return new BookingResponseDto(
                savedBooking.getId(),
                savedBooking.getCustomerName(),
                savedBooking.getSeatCount(),
                savedBooking.getTotalAmount(),
                show.getId(),
                show.getMovie().getName(),
                show.getTheatre().getName(),
                show.getShowDate(),
                show.getShowTime()
        );
    }

    @Override
    @Transactional
    public BookingResponseDto cancelBooking(Long bookingId) {

        log.info("Cancel booking started | bookingId={}", bookingId);

        Booking booking = bookingRepository.findById(bookingId)
                .orElseThrow(() -> {
                    log.warn("Booking not found | bookingId={}", bookingId);
                    return new ResourceNotFoundException("Booking not found");
                });

        Show show = booking.getShow();
        show.setAvailableSeats(
                show.getAvailableSeats() + booking.getSeatCount());

        bookingRepository.delete(booking);

        log.info("Booking cancelled | bookingId={} seatsReleased={}",
                bookingId, booking.getSeatCount());

        return new BookingResponseDto(
                bookingId,
                booking.getCustomerName(),
                booking.getSeatCount(),
                booking.getTotalAmount(),
                show.getId(),
                show.getMovie().getName(),
                show.getTheatre().getName(),
                show.getShowDate(),
                show.getShowTime()
        );
    }
}
