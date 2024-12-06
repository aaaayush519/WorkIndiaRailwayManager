package com.irctcClone.RailwayManager.dto;

import lombok.Data;

@Data
public class TrainDto {
    private String source;
    private String destination;
    private Integer totalSeats;
    private Integer availableSeats;
}

