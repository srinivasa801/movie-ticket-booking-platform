package com.sree.movie.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;

@Data
public class BookingResponseDto {

    private Long bookingId;
    private String customerName;
    private int seats;
    private BigDecimal totalAmount;

    private Long showId;
    private String movieName;
    private String theatreName;
    private LocalDate showDate;
    private LocalTime showTime;

    public BookingResponseDto(Long bookingId, String customerName, int seats,
                              BigDecimal totalAmount, Long showId,
                              String movieName, String theatreName,
                              LocalDate showDate, LocalTime showTime) {

        this.bookingId = bookingId;
        this.customerName = customerName;
        this.seats = seats;
        this.totalAmount = totalAmount;
        this.showId = showId;
        this.movieName = movieName;
        this.theatreName = theatreName;
        this.showDate = showDate;
        this.showTime = showTime;
    }
}