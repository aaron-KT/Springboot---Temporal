package com.postgresql.demo.controller;

import com.postgresql.demo.model.Hospital;
import com.postgresql.demo.services.HospitalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/hospitals")
public class HospitalController {

    @Autowired
    private HospitalService hospitalService;

    @PostMapping("/register")
    public String registerHospital(@RequestBody Hospital hospital) {
        hospitalService.startHospitalWorkflow(hospital);
        return "Hospital registration started!";
    }
    
}
