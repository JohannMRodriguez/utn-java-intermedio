package com.utn.airbnb.repositories;

import com.utn.airbnb.entities.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookingRepository extends JpaRepository<Booking, Long> {
    Booking findByIdRental(Long id);
}
