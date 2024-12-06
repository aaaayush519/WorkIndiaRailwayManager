package com.irctcClone.RailwayManager.controller;

import com.irctcClone.RailwayManager.dto.BookingRequest;
import com.irctcClone.RailwayManager.entity.Train;
import com.irctcClone.RailwayManager.service.BookingService;
import com.irctcClone.RailwayManager.service.TrainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private TrainService trainService;

    @Autowired
    private BookingService bookingService;

    @GetMapping("/search-train")
    public String showSearchTrainPage() {
        return "user/search-train";
    }

    @PostMapping("/search-train")
    public String searchTrains(
            @RequestParam String source,
            @RequestParam String destination,
            Model model) {
        List<Train> trains = trainService.getTrains(source, destination);
        model.addAttribute("trains", trains);
        return "user/search-train";
    }

    @GetMapping("/book-seat/{trainId}")
    public String showBookSeatPage(@PathVariable Long trainId, Model model) {
        model.addAttribute("trainId", trainId);
        return "user/book-seat";
    }

    @PostMapping("/book-seat")
    public String bookSeat(
            @ModelAttribute BookingRequest bookingRequest,
            @RequestHeader("Authorization") String token,
            Model model) {
        // Assuming token validation is handled in the filter
        try {
            bookingService.bookSeat(
                    bookingRequest.getTrainId(),
                    bookingRequest.getUserId(),
                    bookingRequest.getSource(),
                    bookingRequest.getDestination()
            );
            model.addAttribute("message", "Seat booked successfully");
        } catch (Exception e) {
            model.addAttribute("error", "Error booking seat: " + e.getMessage());
        }
        return "user/book-seat";
    }
}
