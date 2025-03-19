package com.postgresql.demo.services;

import com.postgresql.demo.model.Hospital;
import com.postgresql.demo.workflow.HospitalWorkflow;

import io.temporal.api.common.v1.WorkflowExecution;
import io.temporal.client.WorkflowClient;
import io.temporal.client.WorkflowOptions;
import org.springframework.stereotype.Service;

import java.time.Duration;

@Service
public class HospitalService {

    private final WorkflowClient workflowClient;

    public HospitalService(WorkflowClient workflowClient) {
        this.workflowClient = workflowClient;
    }

    public String startHospitalWorkflow(Hospital hospital) {
        // Create a new workflow stub without setting a workflow ID
        HospitalWorkflow workflow = workflowClient.newWorkflowStub(
            HospitalWorkflow.class,
            WorkflowOptions.newBuilder()
                .setTaskQueue("HOSPITAL_TASK_QUEUE") // ✅ Let Temporal handle workflow ID
                .setWorkflowRunTimeout(Duration.ofMinutes(10))
                .build()
        );
    
        // Start the workflow asynchronously
        WorkflowExecution execution = WorkflowClient.start(workflow::registerHospital, hospital);
    
        // ✅ Return the automatically generated workflow ID in dev branch
        return execution.getWorkflowId();
    }
    
}
