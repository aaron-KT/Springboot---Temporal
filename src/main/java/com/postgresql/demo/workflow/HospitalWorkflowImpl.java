package com.postgresql.demo.workflow;

import com.postgresql.demo.activities.HospitalActivities;
import com.postgresql.demo.dto.HospitalRequestDTO;
import com.postgresql.demo.model.Hospital;
import io.temporal.activity.ActivityOptions;
import io.temporal.workflow.Workflow;

import java.time.Duration;

public class HospitalWorkflowImpl implements HospitalWorkflow {

    private final HospitalActivities activities = Workflow.newActivityStub(
        HospitalActivities.class,
        ActivityOptions.newBuilder()
            .setStartToCloseTimeout(Duration.ofSeconds(10))
            .build()
    );

    @Override
    public String registerHospital(HospitalRequestDTO hospitalDTO) {
        // Save hospital
        Hospital savedHospital = activities.saveHospital(hospitalDTO);
        System.out.println("✅ Hospital saved with ID: " + savedHospital.getId());
    
        // Add blood banks
        activities.addBloodBanks(savedHospital.getId(), hospitalDTO.getBloodBanks());
    
        // Send confirmation email
        activities.sendConfirmation(savedHospital.getEmail());
    
        return "✅ Hospital registered successfully!";
    }
}
