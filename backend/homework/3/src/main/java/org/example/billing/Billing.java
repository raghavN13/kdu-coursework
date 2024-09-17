package org.example.billing;

import org.example.hospitalmanagement.Patient;

public class Billing {
    public static double[] computePaymentAmount(Patient patient , double amount){
        double[] payments = new double[2];
        HealthInsurancePlan patientInsurancePlan  = patient.getInsurancePlan();
        double paymentByInsurance = 0.0;

        if(patientInsurancePlan!=null){
            paymentByInsurance = amount* patientInsurancePlan.getCoverage();
            payments[0] = paymentByInsurance;
        }

        if (patientInsurancePlan instanceof PlatinumPlan) {
            payments[1] = amount - paymentByInsurance - 50;
        }
        else if (patientInsurancePlan instanceof GoldPlan) {
            payments[1] = amount - paymentByInsurance - 40;
        }
        else if (patientInsurancePlan instanceof SilverPlan) {
            payments[1] = amount - paymentByInsurance - 30;
        }
        else if (patientInsurancePlan instanceof BronzePlan) {
            payments[1] = amount - paymentByInsurance - 25;
        }
        else {
            payments[1] = amount - 20;
        }

        return payments;
    }
}
