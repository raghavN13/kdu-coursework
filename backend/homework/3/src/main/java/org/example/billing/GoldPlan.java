package org.example.billing;

public class GoldPlan extends HealthInsurancePlan{

    public GoldPlan(){
        setCoverage(0.8);
    }
    public double monthlyPremium(double salary, int age , boolean smoking){
        return 0.07*salary + getOfferedBy().monthlyPremium(this,age,smoking);
    }


}
