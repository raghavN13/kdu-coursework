package org.example.question3and4;

import org.example.billing.GoldPlan;
import org.example.billing.HealthInsurancePlan;
import org.example.billing.PlatinumPlan;
import org.example.billing.SilverPlan;
import org.example.hospitalmanagement.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BlueCrossBlueShield implements InsuranceBrand {
    private static final Logger logger = LoggerFactory.getLogger(BlueCrossBlueShield.class);
    public double monthlyPremium(HealthInsurancePlan insurancePlan, int age, boolean smoking) {

        double premium = 0.0;

        if (insurancePlan instanceof PlatinumPlan) {
            if (age > 55) {
                premium += 200.0;
            }
            if (smoking) {
                premium += 100.0;
            }
        } else if (insurancePlan instanceof GoldPlan) {
            if (age > 55) {
                premium += 150.0;
            }
            if (smoking) {
                premium += 90.0;
            }
        } else if (insurancePlan instanceof SilverPlan) {
            if (age > 55) {
                premium += 100.0;
            }
            if (smoking) {
                premium += 80.0;
            }
        } else {
            if (age > 55) {
                premium += 50.0;
            }
            if (smoking) {
                premium += 70.0;
            }
        }

        return premium;
    }


    public static void main(String[] args) {
        User employee = new User();
        InsuranceBrand insuranceCompany = new BlueCrossBlueShield();
        HealthInsurancePlan insurancePlan = new PlatinumPlan();
        insurancePlan.setOfferedBy(insuranceCompany);
        employee.setInsurancePlan(insurancePlan);

        double premium = insurancePlan.monthlyPremium(2000, 60, true);
        logger.info("Monthly Premium: {}", premium);

    }

}
