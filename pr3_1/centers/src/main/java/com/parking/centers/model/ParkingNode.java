package com.parking.centers.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ParkingNode {
    private String address;
    private int numOfParkingSpaces;
    private int numOfFreeSpaces;
}
