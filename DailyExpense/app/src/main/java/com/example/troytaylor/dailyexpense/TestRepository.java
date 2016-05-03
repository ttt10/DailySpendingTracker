package com.example.troytaylor.dailyexpense;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by troytaylor on 4/28/16.
 */
public class TestRepository implements IRepository {

    private List<Expense> AllExpenses = new ArrayList<>();

    public TestRepository(){

        /* generate some dates, descriptions and amounts */

        Calendar today = Calendar.getInstance();

        /* add expenses */
        // today's expenses
        AllExpenses.add(new Expense(today, "Chipotle", 10.92));
        AllExpenses.add(new Expense(today, "Noodlehead", 9.63));
        AllExpenses.add(new Expense(today, "CVS", 23.11));

        //random expenses from January 12
        int i = 0;
        int j = 0;
        String[] desc = {"Shoes", "Groceries","Clothes","Dining","Bill(s)","Misc."};
        double[] amount = {5.00,125.45,44.70,14.28,63.03,35.00,27.80,14.39};

        Calendar current = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd");
        Date start = null;

        try{
            start = sdf.parse("2016.01.12");
            current.setTime(start);
        }catch (ParseException pe){
            System.out.println(pe.toString());
        }

        while (current.compareTo(today) < 0){
            Date d = current.getTime();
            Calendar c = Calendar.getInstance();
            c.setTime(d);
            Expense e = new Expense(c, desc[i%6], amount[j%8]);
            AllExpenses.add(e);
            //System.out.println(current.toString());
            i++;
            j++;
            current.add(Calendar.DATE,1);
        }
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
        int s = AllExpenses.size();
        Calendar current;
        Expense expense;
        for(int i=0; i<s; i++){
            expense = AllExpenses.get(i);
            current = expense.getDate();

            if(day.get(Calendar.YEAR) == current.get(Calendar.YEAR)){
                if(day.get(Calendar.MONTH) == current.get(Calendar.MONTH)){
                    if(day.get(Calendar.DAY_OF_MONTH) == current.get(Calendar.DAY_OF_MONTH)){
                        dayAmount += expense.getAmount();
                    }
                }
            }
        }
        return dayAmount;
    }

    public double getTotalMonthAmount(Calendar month){
        //TODO: return sum of all expenses in month
        double monthAmount = 0.0;
        int m = month.get(Calendar.MONTH);
        int s = AllExpenses.size();
        int expMonth = 0;

        for(int i=0; i<s; i++){
            Expense exp = AllExpenses.get(i);
            Calendar date = exp.getDate();
            expMonth = date.get(Calendar.MONTH);
            if(expMonth == m){
                monthAmount += exp.getAmount();
                //print out values added
            }
        }
        return monthAmount;
    }

}
