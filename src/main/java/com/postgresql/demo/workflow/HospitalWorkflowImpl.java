package com.postgresql.demo.workflow;

import com.postgresql.demo.activities.HospitalActivities;
import com.postgresql.demo.model.Hospital;
import io.temporal.activity.ActivityOptions;
import io.temporal.workflow.Workflow;

import java.time.Duration;

public class HospitalWorkflowImpl implements HospitalWorkflow {

    private final HospitalActivities activities = Workflow.newActivityStub(
        HospitalActivities.class,
        ActivityOptions.newBuilder()
            .setStartToCloseTimeout(Duration.ofSeconds(10))  // ⏳ Activity timeout of 10 seconds
            .build()
    );

    @Override
    public String registerHospital(Hospital hospital) {
        Hospital savedHospital = activities.saveHospital(hospital);  // ✅ Ensure hospital is saved
    
        System.out.println("Hospital saved with ID: " + savedHospital.getId()); // 🛠️ Debugging log
    
        activities.addBloodBanks(savedHospital.getId());  // ✅ Now, the hospital ID exists
    
        activities.sendConfirmation(savedHospital.getEmail());
    
        return "Hospital registered successfully!";
    }
    
}

