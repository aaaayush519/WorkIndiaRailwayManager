package com.irctcClone.RailwayManager.service;

import com.irctcClone.RailwayManager.entity.Booking;
import com.irctcClone.RailwayManager.entity.Train;
import com.irctcClone.RailwayManager.repository.BookingRepository;
import com.irctcClone.RailwayManager.repository.TrainRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BookingService {
    @Autowired
    private BookingRepository bookingRepository;
    @Autowired
    private TrainRepository trainRepository;

    @Transactional
    public synchronized Booking bookSeat(Long trainId, Long userId, String source, String destination) {
        Train train = trainRepository.findById(trainId).orElseThrow(() -> new RuntimeException("Train not found"));
        if (train.getAvailableSeats() <= 0) throw new RuntimeException("No seats available");

        train.setAvailableSeats(train.getAvailableSeats() - 1);
        trainRepository.save(train);

        Booking booking = new Booking();
        booking.setUserId(userId);
        booking.setTrainId(trainId);
        booking.setSource(source);
        booking.setDestination(destination);

        return bookingRepository.save(booking);
    }
}

