package com.example.troytaylor.dailyexpense.Services.Repository;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.ContactsContract;

import com.example.troytaylor.dailyexpense.Constants.Categories;
import com.example.troytaylor.dailyexpense.Services.Repository.Data.Entities.Expense;
import com.example.troytaylor.dailyexpense.Services.Repository.Data.SQLiteDBContract;
import com.example.troytaylor.dailyexpense.Services.Repository.Data.SQLiteDBHelper;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 *      SQLiteRepository class
 *          - responsible for CRUD operations on the SQLite Database and providing the application data
 *
 */
public class SQLiteRepository implements IRepository {

    private SQLiteDatabase Database;
    private SQLiteOpenHelper DBHelper;
    private Context context;

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

        return result > 0;
    }

    @Override
    public List<Expense> getExpenses(Calendar selectedDay) {
        List<Expense> expenseList = new ArrayList<>();
        Database = DBHelper.getReadableDatabase();

        // query db
        String [] projection = {
                SQLiteDBContract.ExpenseDB._ID,
                SQLiteDBContract.ExpenseDB.COLUMN_NAME_DATE,
                SQLiteDBContract.ExpenseDB.COLUMN_NAME_MERCHANT,
                SQLiteDBContract.ExpenseDB.COLUMN_NAME_AMOUNT,
                SQLiteDBContract.ExpenseDB.COLUMN_NAME_DESCRIPTION,
                SQLiteDBContract.ExpenseDB.COLUMN_NAME_CATEGORY
        }; // columns from db

        String selection = SQLiteDBContract.ExpenseDB.COLUMN_NAME_DATE + " = ?";
        String[] selectionArgs = { selectedDay.toString() };

        Cursor c = Database.query(
                SQLiteDBContract.ExpenseDB.TABLE_NAME,
                projection,
                selection,
                selectionArgs,
                null,
                null,
                null
        );

        // add result to expenseList
        int count = c.getCount();
        c.moveToFirst();
        for(int i=0; i<count; i++) {
            long id = c.getLong(c.getColumnIndex(SQLiteDBContract.ExpenseDB._ID));

            String dateString = c.getString(c.getColumnIndexOrThrow(SQLiteDBContract.ExpenseDB.COLUMN_NAME_DATE));
            // convert date string to Calendar object
            Calendar date = Calendar.getInstance();
            DateFormat formatter = new SimpleDateFormat("YYYY-MM-DD HH:MM:SS");
            Date d = null;
            try {
                d = formatter.parse(dateString);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            date.setTime(d);

            String merchant = c.getString(c.getColumnIndexOrThrow(SQLiteDBContract.ExpenseDB.COLUMN_NAME_MERCHANT));;
            double amount = c.getDouble(c.getColumnIndexOrThrow(SQLiteDBContract.ExpenseDB.COLUMN_NAME_AMOUNT));;
            String description = c.getString(c.getColumnIndexOrThrow(SQLiteDBContract.ExpenseDB.COLUMN_NAME_DESCRIPTION));;

            String ctg = c.getString(c.getColumnIndexOrThrow(SQLiteDBContract.ExpenseDB.COLUMN_NAME_CATEGORY));
            Categories category = Categories.valueOf(ctg);

            // add to expenseList
            expenseList.add(new Expense(id, date, merchant, amount, category, description));
            c.moveToNext(); // move cursor to next row
        }

        Database.close();
        return expenseList;
    }

    @Override
    public double getTotalDayAmount(Calendar day) {
        Database = DBHelper.getReadableDatabase();
        // TODO: query sum of a day

        String[] projection; // columns from db
        String selection; // selection query filter
        String[] selectionArgs = {}; // arguments for selection filter



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
