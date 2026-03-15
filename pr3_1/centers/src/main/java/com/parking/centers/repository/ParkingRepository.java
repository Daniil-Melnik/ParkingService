package com.parking.centers.repository;

import com.parking.centers.model.ParkingOperation;
import org.springframework.stereotype.Repository;
import org.springframework.data.repository.CrudRepository;
import java.util.List;
import java.util.Optional;

@Repository
public interface ParkingRepository extends CrudRepository<ParkingOperation, Integer> { // Integer, а не String
    List<ParkingOperation> findByParkingNodeName(String nodeName);

    Optional<ParkingOperation> findByCar_RegNumberAndPlaceNo(String regNumber, String placeNo);

    List<ParkingOperation> findByCar_RegNumber(String regNumber);

    List<ParkingOperation> findByPlaceNo(String placeNo);

    List<ParkingOperation> findByEndTimeIsNull(); // Активные парковки
}