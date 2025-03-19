package com.postgresql.demo.dto;

import java.io.Serializable;

import lombok.Data;

@Data
public class BloodBankDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    private String name;
    private String bloodType;
    private int availableUnits;
}
