package com.example.troytaylor.dailyexpense.Entities;

import java.io.Serializable;
import java.util.Calendar;

/**
 * Created by troytaylor on 4/28/16.
 */
public class Expense implements Serializable{

    private Calendar date; // need month day year & time set in this object
    private String description;
    private double amount;

    public Expense(Calendar date, String description, double amount){
        this.date = date;
        this.description = description;
        this.amount = amount;
    }

    //TODO: refactor modifier methods to return booleans

    public void setDate(Calendar cal){
        date = cal;
    }
    public Calendar getDate(){
        return this.date;
    }

    public void setDescription(String desc){
        this.description = desc;
    }
    public String getDescription(){
        return this.description;
    }

    public void setAmount(double amnt){
        this.amount = amnt;
    }
    public double getAmount(){
        return this.amount;
    }
}
