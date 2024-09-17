package org.example.hospitalmanagement;

import org.example.billing.Billing;
import org.example.billing.HealthInsurancePlan;
import org.example.billing.PlatinumPlan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Patient extends User {
    private static final Logger logger = LoggerFactory.getLogger(Patient.class);

    private long patientId;
    private boolean insured;
    private HealthInsurancePlan insurancePlan;

    public void setInsured(boolean insured) {
        this.insured = insured;
    }

    public HealthInsurancePlan getInsurancePlan() {
        return insurancePlan;
    }

    public void setInsurancePlan(HealthInsurancePlan insurancePlan) {
        this.insurancePlan = insurancePlan;
    }

    public static void main(String[] args) {
        Patient patient = new Patient();
        patient.setId(1);
        patient.setFirstName("Raghav");
        patient.setLastName("Nandwana");
        patient.setGender("Male");
        patient.setEmail("raghav.nandwana@kickdrumtech.com");
        patient.setInsured(true);

        PlatinumPlan platinumPlan = new PlatinumPlan();
        patient.setInsurancePlan(platinumPlan);

        double totalAmount = 2000.0;

        double[] payments = Billing.computePaymentAmount(patient, totalAmount);

        logger.info("Payment by Insurance: {}", payments[0]);
        logger.info("Patient's Remaining Payment: {}", payments[1]);
    }
}
