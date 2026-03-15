package com.parking.centers.controller;

import com.parking.centers.model.Car;
import com.parking.centers.model.ParkingOperation;
import com.parking.centers.service.ParkingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.util.List;
import java.util.Locale;
import java.util.Optional;

@RestController
@RequestMapping(value = "parkingCenters")
public class ParkingController {
    @Autowired
    private ParkingService parkingService;

    @Autowired
    MessageSource messages;

    @GetMapping("/node/{nodeName}")
    public ResponseEntity<List<ParkingOperation>> getByNodeName(
            @PathVariable String nodeName,
            @RequestHeader (value = "Accept-Language", required = false) Locale locale
    ) {
        List<ParkingOperation> operations = parkingService.getParkingOperationsByNode(nodeName, locale);
        return ResponseEntity.ok(operations);
    }

    @GetMapping("/getByPlaceNo/{placeNo}")
    public ResponseEntity<List<ParkingOperation>> getByPlaceNo(
            @PathVariable String placeNo
    ) {
        List<ParkingOperation> operations = parkingService.getParkingOperationByPlaceNo(placeNo);
        return ResponseEntity.ok(operations);
    }

    @GetMapping("/getByOperationID/{id}")
    public ResponseEntity<Optional<ParkingOperation>> getByOperationID(
            @PathVariable int id
    ) {
        Optional<ParkingOperation> operation = parkingService.getParkingOperationByID(id);
        return ResponseEntity.ok(operation);
    }

    @GetMapping("/findByCarRegNumber/{carRegNumber}")
    public ResponseEntity<List<ParkingOperation>> findByCarRegNumber(
            @PathVariable String carRegNumber
    ) {
        List<ParkingOperation> operations = parkingService.getParkingOperationsByCarRegNum(carRegNumber);
        return ResponseEntity.ok(operations);
    }

    @GetMapping("/active")
    public ResponseEntity<List<ParkingOperation>> getActiveParkings(
            @RequestHeader (value = "Accept-Language", required = false) Locale locale
    ) {
        List<ParkingOperation> operations = parkingService.getActiveParkings(locale);
        return ResponseEntity.ok(operations);
    }

    @GetMapping(value = "/{parkingNodeName}/{placeNo}/{id}/{carRegNum}")
    public ResponseEntity<ParkingOperation> getParkingOperation(
            @PathVariable("id") int regOperationNo,
            @PathVariable("carRegNum") String carRegNum,
            @PathVariable("parkingNodeName") String parkingNodeName,
            @PathVariable("placeNo") String placeNo,
            @RequestHeader (value = "Accept-Language", required = false) Locale locale
    ){
        ParkingOperation parkingOperation = parkingService.getParkingOperation(regOperationNo, parkingNodeName, placeNo, new Car(carRegNum));

        parkingOperation.add(linkTo(methodOn(ParkingController.class).getParkingOperation(regOperationNo, carRegNum, parkingNodeName,placeNo, locale)).withSelfRel());
        parkingOperation.add(linkTo(methodOn(ParkingController.class).createParkingOperation(parkingNodeName, placeNo, parkingOperation, null)).withRel(messages.getMessage("parkingSystem.create.link.message", null, locale)));
        parkingOperation.add(linkTo(methodOn(ParkingController.class).updateParkingOperation(parkingOperation,null)).withRel(messages.getMessage("parkingSystem.update.link.message", null, locale)));
        parkingOperation.add(linkTo(methodOn(ParkingController.class).deleteParkingOperation(regOperationNo, null)).withRel(messages.getMessage("parkingSystem.delete.link.message", null, locale)));

        return ResponseEntity.ok(parkingOperation);
    }

    @PostMapping("/{parkingNodeName}/{placeNo}")
    public ResponseEntity<String> createParkingOperation(
            @PathVariable ("parkingNodeName") String parkingNodeName,
            @PathVariable ("placeNo") String placeNo,
            @RequestBody ParkingOperation parkingOperation,
            @RequestHeader (value = "Accept-Language", required = false) Locale locale
    ){
        System.out.println((messages.getMessage("parkingSystem.create.message", null, locale)));
        System.out.println(locale.getLanguage().toString());
        return ResponseEntity.ok(parkingService.createParkingOperation(parkingOperation, parkingNodeName, placeNo, locale));
    }


    @PutMapping("/updParking")
    public ResponseEntity<String> updateParkingOperation(
            @RequestBody ParkingOperation parkingOperation,
            @RequestHeader(value = "Accept-Language", required = false) Locale locale
    ) {

        return ResponseEntity.ok(parkingService.updateParkingOperation(parkingOperation, locale));
    }

    @DeleteMapping("/deleteParkingOperation/{id}")
    public ResponseEntity<String> deleteParkingOperation(
            @PathVariable("id") int id,
            @RequestHeader (value = "Accept-Language", required = false) Locale locale
    ){
        return ResponseEntity.ok(parkingService.deleteParkingOperation(id, locale));
    }
}

