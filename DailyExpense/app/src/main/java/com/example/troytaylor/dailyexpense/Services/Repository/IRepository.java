package com.example.troytaylor.dailyexpense.Services.Repository;

import com.example.troytaylor.dailyexpense.Entities.Expense;

import java.util.Calendar;
import java.util.List;

/**
 * Created by troytaylor on 4/28/16.
 */
public interface IRepository {

    /**
     * Adds an expense object to the repository
     * @param expense: new com.example.troytaylor.dailyexpense.Entities.Expense object to be added
     * @return: returns true if expense was added
     */
    boolean addExpense(Expense expense);

    /**
     *  Removes an com.example.troytaylor.dailyexpense.Entities.Expense object from the repository
     *  @param expense: the expense object that will be removed
     *  @return: returns true if expense was removed
     */
    boolean removeExpense(Expense expense);

    /**
     *  Gets a list of Expenses for a specified day
     * @param selectedDay: the day the user wants expenses for
     * @return: returns List of com.example.troytaylor.dailyexpense.Entities.Expense objects for a given day
     *
     */
    List<Expense> getExpenses(Calendar selectedDay);

    /**
     *  Gets the dollar amount spent on a given day
     *  @param day: Calendar day of expenses
     *  @return: dollar and cents amount spent on day
     */
    double getTotalDayAmount(Calendar day);

    /**
     *  Gets the dollar amount spent in a specified month
     *  @param month: month of year
     */
    double getTotalMonthAmount(Calendar month);

    /**
     *  sets the selected day
     */
    void setSelectedDay(Calendar day);

    /**
     *  returns the selected day
     */
    Calendar getSelectedDay();

}
