package com.postgresql.demo.services;

import com.postgresql.demo.dto.HospitalRequestDTO;
import com.postgresql.demo.dto.BloodBankDTO;
import com.postgresql.demo.model.Hospital;
import com.postgresql.demo.model.BloodBank;
import com.postgresql.demo.workflow.HospitalWorkflow;
import io.temporal.client.WorkflowClient;
import io.temporal.client.WorkflowOptions;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class HospitalService {

    private final WorkflowClient workflowClient;

    public HospitalService(WorkflowClient workflowClient) {
        this.workflowClient = workflowClient;
    }

    public void startHospitalWorkflow(HospitalRequestDTO hospitalRequestDTO) {
        String workflowId = "hospital-workflow-" + UUID.randomUUID();
    
        HospitalWorkflow workflow = workflowClient.newWorkflowStub(
            HospitalWorkflow.class,
            WorkflowOptions.newBuilder()
                .setWorkflowId(workflowId)
                .setTaskQueue("HOSPITAL_TASK_QUEUE")
                .build()
        );
    
        // Pass the DTO directly instead of converting to entity
        WorkflowClient.start(workflow::registerHospital, hospitalRequestDTO);
    }
}
