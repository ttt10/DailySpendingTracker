package com.example.troytaylor.dailyexpense.Services.Repository;

import com.example.troytaylor.dailyexpense.Constants.Categories;
import com.example.troytaylor.dailyexpense.Entities.Expense;

import java.text.DecimalFormat;
import java.text.NumberFormat;
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
    private Calendar SelectedDay; // today as default

    public TestRepository(){

        /* generate some dates, descriptions and amounts */
        Calendar today = Calendar.getInstance();
        SelectedDay = today;

        /* add expenses */
        // today's expenses
        AllExpenses.add(new Expense(today, "Chipotle", 10.92, Categories.FOOD, ""));
        AllExpenses.add(new Expense(today, "Noodlehead", 9.63, Categories.FOOD, ""));
        AllExpenses.add(new Expense(today, "CVS", 23.11, Categories.MISCELLANEOUS, ""));

        //random expenses from January 12
        int i = 0;
        int j = 0;
        String[] merchants = {"CVS", "Giant Eagle","Target","Verizon","Banana Republic","Gilt", "Nike"};
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

            //checks that the current day does not add any additional entries
            if(today.get(Calendar.DAY_OF_YEAR) == c.get(Calendar.DAY_OF_YEAR)) break;

            Expense e = new Expense(c, merchants[i%7], amount[j%8], Categories.NONE, desc[i%6]);
            AllExpenses.add(e);
            //System.out.println(current.toString());
            i++;
            j++;
            current.add(Calendar.DATE,1);
        }
    }

    public boolean addExpense(Expense expense){
        return AllExpenses.add(expense);
    }
    //TODO: public boolean updateExpense() {}

    public boolean removeExpense(Expense expense){

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

        NumberFormat numberFormat = new DecimalFormat("#.##");
        dayAmount = Double.parseDouble(numberFormat.format(dayAmount));
        return dayAmount;
    }

    public double getTotalMonthAmount(Calendar month){
        double monthAmount = 0.0;
        int m = month.get(Calendar.MONTH);
        int s = AllExpenses.size();
        int expMonth;

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

    public void setSelectedDay(Calendar day){
        SelectedDay = day;
    }

    public Calendar getSelectedDay(){
        return SelectedDay;
    }
}
