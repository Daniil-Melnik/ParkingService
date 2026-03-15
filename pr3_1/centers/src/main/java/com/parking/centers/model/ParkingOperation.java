package com.parking.centers.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.FetchType;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.hateoas.RepresentationModel;

import java.util.Date;


@Getter
@Setter
@ToString

@Entity
@Table(name = "parkingoperation")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class ParkingOperation extends RepresentationModel<ParkingOperation> {

    @Id
    @Column(name="regnumofparkingact", nullable=false)
    private int regNumOfParkingAct;

    @Column(name="starttime", nullable=false)
    private Date startTime;

    @Column(name="endtime", nullable=true)
    private Date endTime;

    @Column(name="placeno", nullable=false)
    private String placeNo;

    @Column(name="parkingnodename", nullable=false)
    private String parkingNodeName;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "regnumber")
    private Car car;
}
