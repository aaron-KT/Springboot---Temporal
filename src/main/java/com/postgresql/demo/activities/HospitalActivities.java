package com.postgresql.demo.activities;

import com.postgresql.demo.dto.HospitalRequestDTO;
import com.postgresql.demo.dto.BloodBankDTO;
import com.postgresql.demo.model.Hospital;  // ✅ Import Hospital model
import io.temporal.activity.ActivityInterface;
import io.temporal.activity.ActivityMethod;
import java.util.List;

@ActivityInterface
public interface HospitalActivities {
    
    @ActivityMethod
    Hospital saveHospital(HospitalRequestDTO hospitalDTO);  // ✅ Now, Hospital is recognized

    @ActivityMethod
    void addBloodBanks(Long hospitalId, List<BloodBankDTO> bloodBankDTOs);  // ✅ Accept a list

    @ActivityMethod
    void sendConfirmation(String email);
}