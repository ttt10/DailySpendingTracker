package com.example.troytaylor.dailyexpense.Services.Repository.Data.Entities;

import com.example.troytaylor.dailyexpense.Constants.Categories;

import java.io.Serializable;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Calendar;

/**
 *      Expense class
 *          - Data model for an Expense
 */

public class Expense implements Serializable{

    private long id;
    private Calendar date; // need month day year & time set in this object
    private String merchant;
    private double amount;
    private Categories category;
    private String description;

    public Expense(long _id, Calendar date, String merchant, double amount, Categories category, String description){
        this.id = _id;
        this.date = date;
        this.merchant = merchant;
        this.description= description;
        this.category = category;
        NumberFormat format = new DecimalFormat("#.##");
        double amt = Double.parseDouble( format.format(amount));
        this.amount = amt;
    }

    public long getId() { return id; }

    public Calendar getDate(){
        return this.date;
    }

    public String getMerchant() { return this.merchant; }

    public String getDescription(){
        return this.description;
    }

    public double getAmount(){
        return this.amount;
    }

    public Categories getCategory() {
        return this.category;
    }

    //TODO: add compare method
}
