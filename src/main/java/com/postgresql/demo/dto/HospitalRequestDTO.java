package com.postgresql.demo.dto;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class HospitalRequestDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    
    private String name;
    private String email;
    private String location;
    private List<BloodBankDTO> bloodBanks;
}
