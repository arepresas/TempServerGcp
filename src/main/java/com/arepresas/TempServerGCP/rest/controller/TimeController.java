package com.arepresas.TempServerGCP.rest.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/time")
public class TimeController {

    @RequestMapping(value="", method=RequestMethod.GET, produces = "application/json")
    public LocalDateTime getLocalTime() {
        return LocalDateTime.now();
    }

}
