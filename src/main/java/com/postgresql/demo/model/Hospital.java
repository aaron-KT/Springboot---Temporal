package com.postgresql.demo.model;

import jakarta.persistence.*;
import lombok.Data;
import java.util.List;

@Data
@Entity
@Table(name = "hospitals")
public class Hospital {
    
    @Id
    @SequenceGenerator(name = "hospital_seq", sequenceName = "hospital_sequence", initialValue = 1000, allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "hospital_seq")
    private Long id;

    private String name;

    @Column(unique = true)
    private String email;

    private String location;

    @OneToMany(mappedBy = "hospital", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<BloodBank> bloodBanks;
}
