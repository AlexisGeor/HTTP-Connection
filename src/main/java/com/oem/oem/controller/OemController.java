package com.oem.oem.controller;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OemController {

    @GetMapping(value = "/r")
    public String getAutomotor2() {
        return "HOLAAAAAAAAAAAAAAAAAAAA";
    }

}
