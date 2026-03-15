package com.parking.centers.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Getter
@Setter
@ToString

@Entity
@Table(name = "car")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Car {

    @Id
    @Column(name="regnumber", nullable=false)
    private String regNumber;

    public Car(String regNumber){
        this.regNumber = regNumber;
    }

    public Car(){
        this(null);
    }
}
