package com.sree.movie.controller;

import com.sree.movie.dto.BookingResponseDto;
import com.sree.movie.service.BookingService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;


@Tag(name = "Booking APIs", description = "Operations related to ticket booking")
@RestController
@RequestMapping("/api/bookings")
@RequiredArgsConstructor
@Slf4j
@Validated
public class BookingController {

    private final BookingService bookingService;

    /**
     * Book movie tickets
     */
    @Operation(summary = "Book seats for a show")
    @PostMapping
    public ResponseEntity<BookingResponseDto> bookTicket(
            @RequestParam @NotNull Long showId,
            @RequestParam @Min(1) int seats,
            @RequestParam @NotBlank String customerName) {

        log.info("Booking request showId={} seats={}", showId, seats);

        BookingResponseDto booking =
                bookingService.bookTicket(showId, seats, customerName);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(booking);
    }


    /**
     * Cancel booking
     */
    @Operation(summary = "cancel booking seats by booking id")
    @DeleteMapping("/{bookingId}")
    public ResponseEntity<BookingResponseDto> cancelBooking(
            @PathVariable Long bookingId) {

        return ResponseEntity.ok(
                bookingService.cancelBooking(bookingId)
        );
    }
}
