package com.sree.movie;

import com.sree.movie.dto.BookingResponseDto;
import com.sree.movie.dto.ShowBrowseDto;
import com.sree.movie.entity.Booking;
import com.sree.movie.entity.Movie;
import com.sree.movie.entity.Show;
import com.sree.movie.entity.Theatre;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;

public class TestDataUtil {

    public static Show createShow(Long id, int seats) {
        Movie movie = new Movie();
        movie.setName("Inception");

        Theatre theatre = new Theatre();
        theatre.setName("PVR");

        Show show = new Show();
        show.setId(id);
        show.setMovie(movie);
        show.setTheatre(theatre);
        show.setAvailableSeats(seats);
        show.setTicketPrice(BigDecimal.valueOf(200));
        show.setShowDate(LocalDate.of(2026, 1, 25));
        show.setShowTime(LocalTime.of(10, 0));
        return show;
    }

    public static Booking createBooking(Show show) {
        Booking booking = new Booking();
        booking.setId(1L);
        booking.setCustomerName("Srinivas");
        booking.setSeatCount(3);
        booking.setTotalAmount(BigDecimal.valueOf(400));
        booking.setShow(show);
        return booking;
    }

    public static ShowBrowseDto createShowBrowseDto() {
        return new ShowBrowseDto(
                1L, "Inception", "PVR", "Bangalore",
                LocalDate.of(2026, 1, 25),
                LocalTime.of(10, 0),
                BigDecimal.valueOf(200), 50
        );
    }

    public static BookingResponseDto createBookingResponseDto() {
        return new BookingResponseDto(
                1L, "Srinivas", 3, BigDecimal.valueOf(400),
                1L, "Inception", "PVR",
                LocalDate.of(2026, 1, 25),
                LocalTime.of(10, 0)
        );
    }
}