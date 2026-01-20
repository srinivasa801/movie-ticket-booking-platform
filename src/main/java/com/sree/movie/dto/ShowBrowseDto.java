package com.sree.movie.dto;



import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;

@Data
public class ShowBrowseDto {

    private Long showId;
    private String movieName;
    private String theatreName;
    private String city;
    private LocalDate showDate;
    private LocalTime showTime;
    private BigDecimal ticketPrice;
    private int availableSeats;

    public ShowBrowseDto(Long showId, String movieName, String theatreName,
                         String city, LocalDate showDate, LocalTime showTime,
                         BigDecimal ticketPrice, int availableSeats) {
        this.showId = showId;
        this.movieName = movieName;
        this.theatreName = theatreName;
        this.city = city;
        this.showDate = showDate;
        this.showTime = showTime;
        this.ticketPrice = ticketPrice;
        this.availableSeats = availableSeats;
    }
}
