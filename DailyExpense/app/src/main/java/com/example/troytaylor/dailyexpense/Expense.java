package com.example.troytaylor.dailyexpense;

import java.util.Calendar;

/**
 * Created by troytaylor on 4/28/16.
 */
public class Expense {

    private Calendar date; // need month day year & time set in this object
    private String description;
    private double amount;

    public Expense(Calendar date, String description, double amount){
        this.date = date;
        this.description = description;
        this.amount = amount;
    }

    //TODO: create accessor/modifier methods


}
