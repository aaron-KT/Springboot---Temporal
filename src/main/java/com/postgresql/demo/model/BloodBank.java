package com.postgresql.demo.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor  // ✅ Ensure there's a default constructor
@Entity
@Table(name = "blood_banks")
public class BloodBank {

    @Id
    @SequenceGenerator(name = "bloodbank_seq", sequenceName = "bloodbank_sequence", initialValue = 5000, allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "bloodbank_seq")
    private Long id;

    private String name;
    private String bloodType;
    private int availableUnits;

    @ManyToOne(fetch = FetchType.LAZY) // ✅ Lazy loading to avoid unnecessary queries
    @JoinColumn(name = "hospital_id", nullable = false)
    private Hospital hospital;

    public BloodBank(String name, String bloodType, int availableUnits, Hospital hospital) {
        this.name = name;
        this.bloodType = bloodType;
        this.availableUnits = availableUnits;
        this.hospital = hospital;
    }
}
