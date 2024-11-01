package com.utn.airbnb.repositories;

import com.utn.airbnb.entities.RentalCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RentalCategoryRepository extends JpaRepository<RentalCategory, Long> {
    List<RentalCategory> findByCalification(Integer calification);
}
