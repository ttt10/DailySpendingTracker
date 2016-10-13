package com.example.troytaylor.dailyexpense.Entities;

import com.example.troytaylor.dailyexpense.Constants.Categories;

import java.io.Serializable;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Calendar;

/**
 * Created by troytaylor on 4/28/16.
 */
public class Expense implements Serializable{

    private Calendar date; // need month day year & time set in this object
    private String description;
    private double amount;
    private Categories category;

    public Expense(Calendar date, String description, double amount, Categories category){
        this.date = date;
        this.description = description;
        this.category = category;
        NumberFormat format = new DecimalFormat("#.##");
        double amt = Double.parseDouble( format.format(amount));
        this.amount = amt;
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

    public void setCategory(Categories category){
        this.category = category;
    }
    public Categories getCategory() {
        return this.category;
    }
    //TODO: add compare method
}
