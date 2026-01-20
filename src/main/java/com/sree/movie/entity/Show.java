package com.sree.movie.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Index;
import jakarta.persistence.Table;
import jakarta.persistence.Id;
import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.UniqueConstraint;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.FetchType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(
        name = "shows",
        indexes = {
                @Index(name = "idx_show_date", columnList = "show_date"),
                @Index(name = "idx_show_city_date", columnList = "show_date"),
                @Index(name = "idx_show_movie", columnList = "movie_id")
        },
        uniqueConstraints = {
                @UniqueConstraint(
                        name = "uc_theatre_movie_time",
                        columnNames = {"theatre_id", "movie_id", "show_date", "show_time"}
                )
        }
)
public class Show {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "movie_id", nullable = false)
    private Movie movie;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "theatre_id", nullable = false)
    private Theatre theatre;

    @Column(name = "show_date", nullable = false)
    private LocalDate showDate;

    @Column(name = "show_time", nullable = false)
    private LocalTime showTime;

    @Column(nullable = false)
    private BigDecimal ticketPrice;

    @Column(nullable = false)
    private int availableSeats;
}
