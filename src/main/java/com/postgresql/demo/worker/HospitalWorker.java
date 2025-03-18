package com.postgresql.demo.worker;

import com.postgresql.demo.activities.HospitalActivitiesImpl;
import com.postgresql.demo.services.HospitalPersistenceService; // ✅ Use this instead
import com.postgresql.demo.workflow.HospitalWorkflowImpl;
import io.temporal.client.WorkflowClient;
import io.temporal.serviceclient.WorkflowServiceStubs;
import io.temporal.worker.Worker;
import io.temporal.worker.WorkerFactory;
import org.springframework.stereotype.Component;

@Component
public class HospitalWorker {

    public HospitalWorker(HospitalPersistenceService hospitalPersistenceService) { // ✅ Inject Persistence Service
        WorkflowServiceStubs service = WorkflowServiceStubs.newInstance();
        WorkflowClient client = WorkflowClient.newInstance(service);
        WorkerFactory factory = WorkerFactory.newInstance(client);
        Worker worker = factory.newWorker("HOSPITAL_TASK_QUEUE");

        HospitalActivitiesImpl hospitalActivities = new HospitalActivitiesImpl(hospitalPersistenceService); // ✅ Corrected Constructor

        worker.registerWorkflowImplementationTypes(HospitalWorkflowImpl.class);
        worker.registerActivitiesImplementations(hospitalActivities);

        factory.start();
        System.out.println("🚀 Temporal Worker Started: Listening on HOSPITAL_TASK_QUEUE");
    }
}
