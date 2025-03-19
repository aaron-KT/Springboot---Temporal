package com.postgresql.demo.worker;

import com.postgresql.demo.activities.HospitalActivitiesImpl;
import com.postgresql.demo.services.HospitalPersistenceService; // ✅ Use this instead
import com.postgresql.demo.workflow.HospitalWorkflowImpl;
import io.temporal.client.WorkflowClient;
import io.temporal.serviceclient.WorkflowServiceStubs;
import io.temporal.worker.Worker;
import io.temporal.worker.WorkerFactory;
import jakarta.annotation.PostConstruct;

import org.springframework.stereotype.Component;

@Component
public class HospitalWorker {
    
    private final WorkerFactory factory;

    public HospitalWorker(HospitalPersistenceService hospitalPersistenceService) {
        WorkflowServiceStubs service = WorkflowServiceStubs.newInstance();
        WorkflowClient client = WorkflowClient.newInstance(service);
        this.factory = WorkerFactory.newInstance(client);
        Worker worker = factory.newWorker("HOSPITAL_TASK_QUEUE");

        HospitalActivitiesImpl hospitalActivities = new HospitalActivitiesImpl(hospitalPersistenceService);
        worker.registerWorkflowImplementationTypes(HospitalWorkflowImpl.class);
        worker.registerActivitiesImplementations(hospitalActivities);
    }

    @PostConstruct
    public void startWorker() {
        System.out.println("🚀 Temporal Worker Started: Listening on HOSPITAL_TASK_QUEUE");
        factory.start();
    }
}

