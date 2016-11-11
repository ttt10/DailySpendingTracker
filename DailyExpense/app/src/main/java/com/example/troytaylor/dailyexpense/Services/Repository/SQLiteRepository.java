package com.example.troytaylor.dailyexpense.Services.Repository;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.ContactsContract;

import com.example.troytaylor.dailyexpense.Constants.Categories;
import com.example.troytaylor.dailyexpense.Entities.Expense;
import com.example.troytaylor.dailyexpense.Services.Repository.Data.SQLiteDBContract;
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

    private List<Expense> expenseList; // don't think I need this
    private Calendar selectedDay;

    public SQLiteRepository(Context context){
        DBHelper = new SQLiteDBHelper(context);
        //TODO: set selectedDay

    }

    public boolean addExpense(Calendar date, String merchant, double amount, String description, Categories category ){
        //TODO: return true when expense added to list
        Database = DBHelper.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put(SQLiteDBContract.ExpenseDB.COLUMN_NAME_DATE, date.toString());
        contentValues.put(SQLiteDBContract.ExpenseDB.COLUMN_NAME_MERCHANT, merchant);
        contentValues.put(SQLiteDBContract.ExpenseDB.COLUMN_NAME_AMOUNT, amount);
        contentValues.put(SQLiteDBContract.ExpenseDB.COLUMN_NAME_DESCRIPTION, description);
        contentValues.put(SQLiteDBContract.ExpenseDB.COLUMN_NAME_CATEGORY, category.toString());

        long newRowId = Database.insert(SQLiteDBContract.ExpenseDB.TABLE_NAME, null, contentValues);
        Database.close();

        Expense added = new Expense(newRowId, date, merchant, amount, category, description);
        expenseList.add(added);

        if( newRowId == -1) return false;
        else return true;
    }

    public boolean removeExpense(Expense e){
        //TODO: return true when expense removed from list
        Database = DBHelper.getWritableDatabase();

        String selection = SQLiteDBContract.ExpenseDB._ID + " = ?";
        String[] selectionArgs = { String.valueOf(e.getId()) };
        int result = Database.delete(SQLiteDBContract.ExpenseDB.TABLE_NAME, selection, selectionArgs);
        Database.close();

        if(result > 0){
            expenseList.remove(e);
        }else{
            System.out.println("expense not deleted from db");
        }
        return result > 0;

    }

    @Override
    public List<Expense> getExpenses(Calendar selectedDay) {
        //Database = DBHelper.getReadableDatabase();
        // TODO: should I query db here? perhaps to ensure persistence...

        //Database.close();
        return expenseList;
    }

    @Override
    public double getTotalDayAmount(Calendar day) {
        Database = DBHelper.getReadableDatabase();
        // TODO: query sum of a day

        Database.close();
        return 0;
    }

    @Override
    public double getTotalMonthAmount(Calendar month) {
        //TODO: query sum of a month
        Database = DBHelper.getReadableDatabase();

        Database.close();
        return 0;
    }

    public void setSelectedDay(Calendar day){
        this.selectedDay = day;
    }

    public Calendar getSelectedDay(){
        return selectedDay;
    }

}
