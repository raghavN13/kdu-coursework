package org.example.billing;

public class PlatinumPlan extends HealthInsurancePlan{

   public PlatinumPlan(){
        setCoverage(0.9);
    }

    public double monthlyPremium(double salary, int age , boolean smoking){
       return 0.08*salary + getOfferedBy().monthlyPremium(this,age,smoking);
    }
}
