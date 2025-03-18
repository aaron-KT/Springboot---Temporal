package com.postgresql.demo.services;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.postgresql.demo.model.Hospital;
import com.postgresql.demo.model.BloodBank;
import com.postgresql.demo.repo.HospitalRepository;
import com.postgresql.demo.repo.BloodBankRepository;

@Service
public class HospitalPersistenceService {

    private final HospitalRepository hospitalRepository;
    private final BloodBankRepository bloodBankRepository;

    public HospitalPersistenceService(HospitalRepository hospitalRepository, BloodBankRepository bloodBankRepository) {
        this.hospitalRepository = hospitalRepository;
        this.bloodBankRepository = bloodBankRepository;
    }

    @Transactional
    public Hospital saveHospital(Hospital hospital) {
        Hospital savedHospital = hospitalRepository.save(hospital);
        System.out.println("Saved Hospital: " + savedHospital);  // 🛠️ Debugging
        return savedHospital;
    }
    

    @Transactional
    public void addBloodBanks(Long hospitalId) {
        Hospital hospital = hospitalRepository.findById(hospitalId)
            .orElseThrow(() -> new RuntimeException("Hospital not found with ID: " + hospitalId));
    
        BloodBank bloodBank = new BloodBank("Central Blood Bank", "O+", 10, hospital);  // ✅ Correctly setting hospital
    
        System.out.println("Saving BloodBank for Hospital ID: " + hospital.getId()); // 🛠️ Debugging log
    
        bloodBankRepository.save(bloodBank);
    }
    
}


