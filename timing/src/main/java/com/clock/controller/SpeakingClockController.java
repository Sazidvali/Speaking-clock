package com.clock.controller;

import com.clock.exception.InvalidTimeFormatException;
import com.clock.service.SpeakingClockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/time")
public class SpeakingClockController {

    private final SpeakingClockService  speakingClockService;

    @Autowired
    public SpeakingClockController(SpeakingClockService speakingClockService) {
        this.speakingClockService = speakingClockService;
    }

    @GetMapping ("/convert/{time}")
    public String convertToWords(@PathVariable  String time) {
        try {
            return speakingClockService.convertToWords(time);
        } catch (InvalidTimeFormatException e) {
            return "Invalid time format";
        }
    }
}
