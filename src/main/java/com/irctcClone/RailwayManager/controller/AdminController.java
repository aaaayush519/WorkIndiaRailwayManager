package com.irctcClone.RailwayManager.controller;


import com.irctcClone.RailwayManager.entity.Train;
import com.irctcClone.RailwayManager.service.TrainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private TrainService trainService;

    @GetMapping("/add-train")
    public String showAddTrainPage() {
        return "admin/add-train";
    }

    @PostMapping("/add-train")
    public String addTrain(@RequestHeader("API-KEY") String apiKey, @ModelAttribute Train train, Model model) {
        if (!"adminApiKey".equals(apiKey)) {
            model.addAttribute("error", "Invalid API Key");
            return "admin/add-train";
        }
        trainService.addTrain(train);
        model.addAttribute("message", "Train added successfully");
        return "admin/add-train";
    }
}
