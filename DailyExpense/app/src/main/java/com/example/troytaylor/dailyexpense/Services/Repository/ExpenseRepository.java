package com.example.troytaylor.dailyexpense.Services.Repository;

import com.example.troytaylor.dailyexpense.Constants.Categories;
import com.example.troytaylor.dailyexpense.Entities.Expense;

import java.util.Calendar;
import java.util.List;

/**
 * Created by troytaylor on 8/19/16.
 *
 * The Expense Repository is responsible for CRUD operations on the SQLite Database and providing the application data
 *
 */
public class ExpenseRepository implements IRepository {

    private List<Expense> expenseList;
    private Calendar selectedDay;

    public ExpenseRepository(){}

    public boolean addExpense(Expense e){
        //TODO: return true when expense added to list
        return false;
    }

    public boolean removeExpense(Expense e){
        //TODO: return true when expense removed from list
        return false;
    }

    @Override
    public List<Expense> getExpenses(Calendar selectedDay) {
        return expenseList;
    }

    @Override
    public double getTotalDayAmount(Calendar day) {
        return 0;
    }

    @Override
    public double getTotalMonthAmount(Calendar month) {
        return 0;
    }

    public void setSelectedDay(Calendar day){
        this.selectedDay = day;
    }

    public Calendar getSelectedDay(){
        return selectedDay;
    }

}
