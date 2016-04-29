package com.example.troytaylor.dailyexpense;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * Created by troytaylor on 4/28/16.
 */
public class TestRepository implements IRepository {

    private List<Expense> AllExpenses = new ArrayList<>();

    public TestRepository(){

        /* generate some dates, descriptions and amounts */

        Calendar today = Calendar.getInstance();
        //Calendar start = today;
        //start.set(Calendar.MONTH, 1);
        //start.set(Calendar.DAY_OF_MONTH, 12);

        /* add expenses */
        // today's expenses
        AllExpenses.add(new Expense(today, "Chipotle", 10.92));
        AllExpenses.add(new Expense(today, "Noodlehead", 9.63));
        AllExpenses.add(new Expense(today, "CVS", 23.11));

        /*
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
        */
    }

    public boolean addExpense(Expense expense){
        //TODO: add expense to AllExpenses
        return AllExpenses.add(expense);
    }

    public boolean removeExpense(Expense expense){

        //TODO: remove expense from AllExpenses.
        boolean isRemoved = false;
        int s = AllExpenses.size();
        Calendar expenseDate = expense.getDate();
        int expenseDay = expenseDate.get(Calendar.DAY_OF_YEAR);
        String expenseDescription = expense.getDescription();
        double expenseAmount = expense.getAmount();

        for(int i=0; i<s; i++){

            Expense currentExpense = AllExpenses.get(i);
            Calendar currentDate = currentExpense.getDate();

            int currentDay = currentDate.get(Calendar.DAY_OF_YEAR);
            String currentDescription = currentExpense.getDescription();
            double currentAmount = currentExpense.getAmount();

            if(!(currentDay == expenseDay)){
                continue;
            }

            if(!currentDescription.equals(expenseDescription)){
                continue;
            }
            if(!(currentAmount == expenseAmount)){
                continue;
            }

            System.out.println("Found the expense to remove");
            AllExpenses.remove(i);
            isRemoved = true;
            break;

        }

        return isRemoved;
    }

    public List<Expense> getExpenses(Calendar day){
        //TODO: search and return all expenses on day
        List<Expense> returnList = new ArrayList<>();
        int s = AllExpenses.size();
        Calendar current;
        Expense expense;
        for(int i = 0; i < s; i++){
            expense = AllExpenses.get(i);
            current = expense.getDate();

            if(day.get(Calendar.YEAR) == current.get(Calendar.YEAR)){
                if(day.get(Calendar.MONTH) == current.get(Calendar.MONTH)) {
                    if (day.get(Calendar.DAY_OF_MONTH) == current.get(Calendar.DAY_OF_MONTH)) {
                        returnList.add(expense);
                    }
                }
            }
        }

        return returnList;
    }

    public double getTotalDayAmount(Calendar day){
        //TODO: return sum of all expenses on day
        double dayAmount = 0.0;

        return dayAmount;
    }

    public double getTotalMonthAmount(Calendar month){
        //TODO: return sum of all expenses in month
        double monthAmount = 0.0;

        return monthAmount;
    }

}
