package com.parking.centers.repository;

import com.parking.centers.model.Car;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CarRepository extends CrudRepository<Car, String> {
    Optional<Car> findByRegNumber(String regNumber);
    boolean existsByRegNumber(String regNumber);

    void deleteByRegNumber(String regNumber);
}