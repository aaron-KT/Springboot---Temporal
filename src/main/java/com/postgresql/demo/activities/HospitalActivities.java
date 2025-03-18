package com.postgresql.demo.activities;

import com.postgresql.demo.model.Hospital;
import io.temporal.activity.ActivityInterface;
import io.temporal.activity.ActivityMethod;

@ActivityInterface
public interface HospitalActivities {

    @ActivityMethod
    Hospital saveHospital(Hospital hospital);

    @ActivityMethod
    void addBloodBanks(Long hospitalId);

    @ActivityMethod
    void sendConfirmation(String email);
}
