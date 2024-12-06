package com.irctcClone.RailwayManager.service;

import com.irctcClone.RailwayManager.entity.Train;
import com.irctcClone.RailwayManager.repository.TrainRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TrainService {
    @Autowired
    private TrainRepository trainRepository;

    public Train addTrain(Train train) {
        return trainRepository.save(train);
    }

    public List<Train> getTrains(String source, String destination) {
        return trainRepository.findBySourceAndDestination(source, destination);
    }
}

