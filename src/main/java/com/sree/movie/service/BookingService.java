package com.sree.movie.service;

import com.sree.movie.dto.BookingResponseDto;


public interface BookingService {

    BookingResponseDto bookTicket(
            Long showId,
            int seats,
            String customerName
    );

    BookingResponseDto cancelBooking(Long bookingId);
}
