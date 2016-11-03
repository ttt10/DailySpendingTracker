package com.example.troytaylor.dailyexpense.Services.Repository;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.troytaylor.dailyexpense.Constants.Categories;
import com.example.troytaylor.dailyexpense.Entities.Expense;
import com.example.troytaylor.dailyexpense.Services.Repository.Data.SQLiteDBHelper;
import com.example.troytaylor.dailyexpense.UI.MainActivity;

import java.util.Calendar;
import java.util.List;

/**
 * Created by troytaylor on 8/19/16.
 *
 * The SQLiteRepository is responsible for CRUD operations on the SQLite Database and providing the application data
 *
 */
public class SQLiteRepository implements IRepository {

    private SQLiteDatabase Database;
    private SQLiteOpenHelper DBHelper;
    private Context context;

    private List<Expense> expenseList;
    private Calendar selectedDay;

    public SQLiteRepository(Context context){
        //TODO: create repo, create/connect to db, upgrade db
        DBHelper = new SQLiteDBHelper(context);
    }

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
