package com.example.troytaylor.dailyexpense;

import java.util.Calendar;
import java.util.List;

/**
 * Created by troytaylor on 4/28/16.
 */
public interface IRepository {

    /**
     * Adds an expense object to the repository
     * @param expense: new com.example.troytaylor.dailyexpense.Expense object to be added
     */
    void addExpense(Expense expense);

    /**
     *  Removes an com.example.troytaylor.dailyexpense.Expense object from the repository
     *  @param expense: the expense object that will be removed
     */
    void removeExpense(Expense expense);

    /**
     *  Gets a list of Expenses for a specified day
     * @param selectedDay: the day the user wants expenses for
     * @return: returns List of com.example.troytaylor.dailyexpense.Expense objects for a given day
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

}
