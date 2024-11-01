package com.utn.airbnb.repositories;

import com.utn.airbnb.entities.Rental;
import com.utn.airbnb.entities.RentalCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RentalRepository extends JpaRepository<Rental, Long> {
    List<Rental> findByCategory(RentalCategory category);
}
