package org.example.question3and4;

import org.example.billing.HealthInsurancePlan;

public interface InsuranceBrand {
    public double monthlyPremium(HealthInsurancePlan insurancePlan , int age , boolean smoke);
}
