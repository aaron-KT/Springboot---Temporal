package com.postgresql.demo.services;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.postgresql.demo.model.Hospital;
import com.postgresql.demo.model.BloodBank;
import com.postgresql.demo.repo.HospitalRepository;
import com.postgresql.demo.repo.BloodBankRepository;
import com.postgresql.demo.dto.HospitalRequestDTO;
import com.postgresql.demo.dto.BloodBankDTO;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class HospitalPersistenceService {

    private final HospitalRepository hospitalRepository;
    private final BloodBankRepository bloodBankRepository;

    public HospitalPersistenceService(HospitalRepository hospitalRepository, BloodBankRepository bloodBankRepository) {
        this.hospitalRepository = hospitalRepository;
        this.bloodBankRepository = bloodBankRepository;
    }

    @Transactional
    public Hospital saveHospital(HospitalRequestDTO hospitalDTO) {
        Hospital hospital = new Hospital();
        hospital.setName(hospitalDTO.getName());
        hospital.setEmail(hospitalDTO.getEmail());
        hospital.setLocation(hospitalDTO.getLocation());

        Hospital savedHospital = hospitalRepository.save(hospital);
        System.out.println("Saved Hospital: " + savedHospital);

        return savedHospital;
    }

    @Transactional
    public void addBloodBanks(Long hospitalId, List<BloodBankDTO> bloodBankDTOs) {
        Hospital hospital = hospitalRepository.findById(hospitalId)
                .orElseThrow(() -> new RuntimeException("Hospital not found with ID: " + hospitalId));

        List<BloodBank> bloodBanks = bloodBankDTOs.stream()
                .map(dto -> new BloodBank(dto.getName(), dto.getBloodType(), dto.getAvailableUnits(), hospital))
                .collect(Collectors.toList());

        bloodBankRepository.saveAll(bloodBanks);
        System.out.println("Blood Banks added for Hospital ID: " + hospitalId);
    }
}
