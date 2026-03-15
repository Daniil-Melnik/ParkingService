package com.parking.centers.service;

import com.parking.centers.config.ServiceConfig;
import com.parking.centers.model.Car;
import com.parking.centers.model.ParkingOperation;
import com.parking.centers.repository.CarRepository;
import com.parking.centers.repository.ParkingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Optional;

@Service
public class ParkingService {
    @Autowired
    MessageSource messages;
    @Autowired
    private ParkingRepository parkingRepository;
    @Autowired
    private CarRepository carRepository;
    @Autowired
    ServiceConfig config;

    public List<ParkingOperation> getParkingOperationsByNode(String nodeName, Locale locale) {
        List<ParkingOperation> parkingOperations = parkingRepository.findByParkingNodeName(nodeName);
        if (null == parkingOperations) {
            throw new IllegalArgumentException(String.format(messages.getMessage("parkingSystem.search.error.message", null, null)));
        }

        return parkingOperations;
    }

    public List<ParkingOperation> getActiveParkings(Locale locale) {
        List<ParkingOperation> parkingOperations =  parkingRepository.findByEndTimeIsNull();
        if (null == parkingOperations) {
            throw new IllegalArgumentException(String.format(messages.getMessage("parkingSystem.search.error.message", null, null)));
        }
        return parkingOperations;
    }


    public ParkingOperation getParkingOperation(int id, String parkingNodeName, String placeNo, Car car){
        ParkingOperation parkingOperation = new ParkingOperation();

        parkingOperation.setRegNumOfParkingAct(id);
        parkingOperation.setParkingNodeName(parkingNodeName);
        parkingOperation.setStartTime(new Date());
        parkingOperation.setEndTime(null);
        parkingOperation.setPlaceNo(placeNo);
        parkingOperation.setCar(car);

        return parkingOperation;
    }

    public String createParkingOperation(ParkingOperation parkingOperation,
                                         String parkingCenterName,
                                         String parkingPlaceNo,
                                         Locale locale) {

        String responseMessage = "poor";

        if (parkingOperation != null) {
            // Сохраняем автомобиль
            Car car = parkingOperation.getCar();
            carRepository.save(car);

            // Устанавливаем параметры
            parkingOperation.setParkingNodeName(parkingCenterName);
            parkingOperation.setPlaceNo(parkingPlaceNo);
            parkingOperation.setCar(car);

            // Сохраняем в базу
            ParkingOperation saved = parkingRepository.save(parkingOperation);

            responseMessage = String.format(
                    messages.getMessage("parkingSystem.create.message", null, locale),
                    saved.toString()
            );
            System.out.println("NOT POOR - Created with ID: " + saved.getRegNumOfParkingAct());
        }

        return responseMessage;
    }

    public List<ParkingOperation> getParkingOperationByPlaceNo(String placeNo){
        List<ParkingOperation> parkingOperations =  parkingRepository.findByPlaceNo(placeNo);
        if (null == parkingOperations) {
            throw new IllegalArgumentException(String.format(messages.getMessage("parkingSystem.search.error.message", null, null)));
        }
        return parkingOperations;
    }

    public Optional<ParkingOperation> getParkingOperationByID(int id){
        Optional<ParkingOperation> parkingOperation = parkingRepository.findById(id);
        if (null == parkingOperation) {
            throw new IllegalArgumentException(String.format(messages.getMessage("parkingSystem.search.error.message", null, null)));
        }
        return parkingOperation;
    }

    public List<ParkingOperation> getParkingOperationsByCarRegNum(String regNum){
        List<ParkingOperation> parkingOperations =  parkingRepository.findByCar_RegNumber(regNum);
        if (null == parkingOperations) {
            throw new IllegalArgumentException(String.format(messages.getMessage("parkingSystem.search.error.message", null, null)));
        }
        return parkingOperations;
    }

    public String updateParkingOperation(ParkingOperation parkingOperation,
                                         Locale locale) {

        if (parkingOperation == null) {
            return messages.getMessage("parkingSystem.update.error.null", null, locale);
        }

        ParkingOperation existing = parkingRepository
                .findById(parkingOperation.getRegNumOfParkingAct())
                .orElse(null);

        if (existing == null) {
            return String.format(
                    messages.getMessage("parkingSystem.update.error.notfound", null, locale),
                    parkingOperation.getRegNumOfParkingAct()
            );
        }

        if (parkingOperation.getCar() != null &&
                parkingOperation.getCar().getRegNumber() != null &&
                !parkingOperation.getCar().getRegNumber().trim().isEmpty()) {

            String newRegNum = parkingOperation.getCar().getRegNumber();

            Car existingCar = carRepository.findById(newRegNum).orElse(null);
            if (existingCar == null) {
                Car newCar = new Car(newRegNum);
                carRepository.save(newCar);
                existing.setCar(newCar);
            } else {
                existing.setCar(existingCar);
            }
        }

        if (parkingOperation.getStartTime() != null) {
            existing.setStartTime(parkingOperation.getStartTime());
        }

        existing.setEndTime(parkingOperation.getEndTime());

        if (parkingOperation.getPlaceNo() != null) {
            existing.setPlaceNo(parkingOperation.getPlaceNo());
        }

        if (parkingOperation.getParkingNodeName() != null) {
            existing.setParkingNodeName(parkingOperation.getParkingNodeName());
        }

        if (existing.getStartTime() != null && existing.getEndTime() != null) {
            if (existing.getEndTime().before(existing.getStartTime())) {
                return messages.getMessage("parkingSystem.update.error.time", null, locale);
            }
        }

        if (existing.getStartTime() == null ||
                existing.getPlaceNo() == null || existing.getPlaceNo().trim().isEmpty() ||
                existing.getParkingNodeName() == null || existing.getParkingNodeName().trim().isEmpty()) {
            return messages.getMessage("parkingSystem.update.error.required", null, locale);
        }

        try {
            parkingRepository.save(existing);

            String responseMessage = String.format(
                    messages.getMessage("parkingSystem.update.message", null, locale),
                    existing.getRegNumOfParkingAct(),
                    existing.getCar() != null ? existing.getCar().getRegNumber() : "N/A",
                    existing.getStartTime(),
                    existing.getEndTime(),
                    existing.getPlaceNo(),
                    existing.getParkingNodeName()
            );

            return responseMessage;

        } catch (Exception e) {
            return String.format(
                    messages.getMessage("parkingSystem.update.error.save", null, locale),
                    e.getMessage()
            );
        }
    }

    public String deleteParkingOperation(int id, Locale locale) {
        parkingRepository.deleteById(id);

        return String.format(
                messages.getMessage("parkingSystem.delete.message", null, locale),
                id
        );
    }
}

