package com.sree.movie.repository;

import com.sree.movie.entity.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookingRepository extends JpaRepository<Booking, Long> {

    // Find all bookings for a show
    List<Booking> findByShowId(Long showId);

    // Find all bookings by customer name
    List<Booking> findByCustomerName(String customerName);

    // Count booked seats for a show (useful for reports)
    @Query("""
           SELECT COALESCE(SUM(b.seatCount), 0)
           FROM Booking b
           WHERE b.show.id = :showId
           """)
    int getTotalBookedSeats(@Param("showId") Long showId);

    // Check if booking exists for a customer & show
    boolean existsByShowIdAndCustomerName(Long showId, String customerName);
}
