package com.postgresql.demo.activities;

import com.postgresql.demo.model.Hospital;
import org.springframework.stereotype.Service;
import com.postgresql.demo.services.HospitalPersistenceService;




@Service
public class HospitalActivitiesImpl implements HospitalActivities {

    private final HospitalPersistenceService hospitalPersistenceService;

    public HospitalActivitiesImpl(HospitalPersistenceService hospitalPersistenceService) {
        this.hospitalPersistenceService = hospitalPersistenceService;
        
    }

    @Override
    public Hospital saveHospital(Hospital hospital) {
        return hospitalPersistenceService.saveHospital(hospital);  // ✅ Returns the saved entity with ID
    }

    @Override
    public void addBloodBanks(Long hospitalId) {
        hospitalPersistenceService.addBloodBanks(hospitalId);
    }

    @Override
    public void sendConfirmation(String email) {
        System.out.println("Confirmation sent to " + email);
    }
}


