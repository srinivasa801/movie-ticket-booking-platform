package com.sree.movie.repository;

import com.sree.movie.dto.ShowBrowseDto;
import com.sree.movie.entity.Show;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface ShowRepository extends JpaRepository<Show, Long> {
    @Query("""
SELECT new com.sree.movie.dto.ShowBrowseDto(
    s.id,
    m.name,
    t.name,
    t.city,
    s.showDate,
    s.showTime,
    s.ticketPrice,
    s.availableSeats
)
FROM Show s
JOIN s.movie m
JOIN s.theatre t
WHERE m.id = :movieId
AND t.city = :city
AND s.showDate = :date
""")
    List<ShowBrowseDto> browseShows(
            @Param("movieId") Long movieId,
            @Param("city") String city,
            @Param("date") LocalDate date);
}