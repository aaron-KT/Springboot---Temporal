package com.postgresql.demo.activities;

import com.postgresql.demo.dto.HospitalRequestDTO;
import com.postgresql.demo.dto.BloodBankDTO;
import com.postgresql.demo.model.Hospital;
import com.postgresql.demo.services.HospitalPersistenceService;
import org.springframework.stereotype.Component;
import java.util.List;

@Component  // ✅ Fix: Use @Component instead of @ActivityImpl
public class HospitalActivitiesImpl implements HospitalActivities {

    private final HospitalPersistenceService hospitalPersistenceService;

    public HospitalActivitiesImpl(HospitalPersistenceService hospitalPersistenceService) {
        this.hospitalPersistenceService = hospitalPersistenceService;
    }

    @Override
    public Hospital saveHospital(HospitalRequestDTO hospitalDTO) {
        return hospitalPersistenceService.saveHospital(hospitalDTO);
    }

    @Override
    public void addBloodBanks(Long hospitalId, List<BloodBankDTO> bloodBankDTOs) {
        hospitalPersistenceService.addBloodBanks(hospitalId, bloodBankDTOs);
    }

    @Override
    public void sendConfirmation(String email) {
        System.out.println("📩 Email sent to: " + email);
    }
}
