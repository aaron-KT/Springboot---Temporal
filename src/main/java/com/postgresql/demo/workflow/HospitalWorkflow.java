package com.postgresql.demo.workflow;

import com.postgresql.demo.model.Hospital;
import io.temporal.workflow.WorkflowInterface;
import io.temporal.workflow.WorkflowMethod;
import com.postgresql.demo.dto.HospitalRequestDTO;

// In HospitalWorkflow interface
@WorkflowInterface
public interface HospitalWorkflow {
    @WorkflowMethod
    String registerHospital(HospitalRequestDTO hospitalRequestDTO);
}
