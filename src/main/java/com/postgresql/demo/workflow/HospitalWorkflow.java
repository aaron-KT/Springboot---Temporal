package com.postgresql.demo.workflow;

import com.postgresql.demo.model.Hospital;
import io.temporal.workflow.WorkflowInterface;
import io.temporal.workflow.WorkflowMethod;

@WorkflowInterface
public interface HospitalWorkflow {

    @WorkflowMethod
    String registerHospital(Hospital hospital);
}
