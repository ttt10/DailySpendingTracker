package com.example.troytaylor.dailyexpense;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Random;

/**
 * Created by troytaylor on 4/28/16.
 */
public class TestRepository implements IRepository {

    private List<Expense> AllExpenses = new ArrayList<Expense>();

    public TestRepository(){

        /* generate some dates, descriptions and amounts */

        Calendar today = Calendar.getInstance();
        Calendar start = today;
        start.set(Calendar.MONTH, 1);
        start.set(Calendar.DAY_OF_MONTH, 12);


        /* add expenses */
        // today's expenses
        AllExpenses.add(new Expense(today, "Chipotle", 10.92));
        AllExpenses.add(new Expense(today, "Noodlehead", 9.63));
        AllExpenses.add(new Expense(today, "CVS", 23.11));

        //random expenses
        int i = 0;
        int j = 0;
        String[] desc = {"Shoes", "Groceries","Clothes","Dining","Bill(s)","Misc."};
        double[] amount = {5.00,125.45,44.70,14.28,63.03,35.00,27.80,14.39};

        Calendar current = start;
        while (current.compareTo(today) < 0){
            AllExpenses.add(new Expense(current, desc[i%6], amount[j%8]));
            i++;
            j++;
            current.add(Calendar.DATE, 1);
        }
    }

    public void addExpense(Expense expense){
        //TODO: add expense to AllExpenses
        AllExpenses.add(expense);
    }

    public void removeExpense(Expense expense){
        //TODO: remove expense from AllExpenses.
    }

    public List<Expense> getExpenses(Calendar day){
        //TODO: search and return all expenses on day
    }

    public double getTotalDayAmount(Calendar day){
        //TODO: return sum of all expenses on day
    }

    public double getTotalMonthAmount(Calendar month){
        //TODO: return sum of all expenses in month
    }

}
