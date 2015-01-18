package com.example.mray.mhacksvglass.datasources;

/**
 * Created by Aaron Barber on 17/01/15.
 */
public class BankInfo {

    public static BankInfo Sample = new BankInfo(17343.25, "01/02/15", "01/31/15");

    private double balance;
    private String recent_transition;
    private String upcoming_payment;

    public BankInfo(){
        this(0, "", "");
    }

    public BankInfo(double balance, String recent_transition, String upcoming_payment){
        this.balance = balance;
        this.recent_transition = recent_transition;
        this.upcoming_payment = upcoming_payment;
    }

    public void setBalance(double balance){
        this.balance = balance;
    }

    public void setRecentTransition(String recentTransition){
        this.recent_transition = recentTransition;
    }

    public void setUpcomingPayment(String upcomingPayment){
        this.upcoming_payment = upcomingPayment;
    }

    public double getBalance(){
        return balance;
    }

    public String getRecentTransition(){
        return recent_transition;
    }

    public String getUpcomingPayment(){
        return upcoming_payment;
    }
}
