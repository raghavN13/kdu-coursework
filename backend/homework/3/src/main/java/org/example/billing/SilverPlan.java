package org.example.billing;

public class SilverPlan extends HealthInsurancePlan{

    public SilverPlan(){
        setCoverage(0.7);
    }
    public double monthlyPremium(double salary, int age , boolean smoking){
        return 0.06*salary + getOfferedBy().monthlyPremium(this,age,smoking);
    }
}
