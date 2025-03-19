package com.postgresql.demo.controller;

import com.postgresql.demo.dto.HospitalRequestDTO;
import com.postgresql.demo.services.HospitalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/hospitals")
public class HospitalController {

    @Autowired
    private HospitalService hospitalService;

    @PostMapping("/register")
    public String registerHospital(@RequestBody HospitalRequestDTO hospitalRequestDTO) {
        hospitalService.startHospitalWorkflow(hospitalRequestDTO);
        return "Hospital registration workflow started!";
    }
}
