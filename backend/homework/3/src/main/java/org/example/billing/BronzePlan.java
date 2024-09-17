package org.example.billing;

public class BronzePlan extends HealthInsurancePlan{
    public BronzePlan(){
        setCoverage(0.6);
    }
    public double monthlyPremium(double salary, int age , boolean smoking){
        return 0.05*salary + getOfferedBy().monthlyPremium(this,age,smoking);
    }
}
