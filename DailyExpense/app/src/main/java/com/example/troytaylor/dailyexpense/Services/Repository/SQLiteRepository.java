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
import java.util.Locale;

/**
 *      SQLiteRepository class
 *          - responsible for CRUD operations on the SQLite Database and providing the application data
 *
 */
public class SQLiteRepository implements IRepository {

    private SQLiteDatabase Database;
    private SQLiteOpenHelper DBHelper;

    private Calendar selectedDay;

    public SQLiteRepository(Context context){

        DBHelper = SQLiteDBHelper.getInstance(context);

        selectedDay = Calendar.getInstance();
    }

    @Override
    public boolean addExpense(Calendar date, String merchant, double amount, String description, Categories category){
        Database = DBHelper.getWritableDatabase();

        //delete db
        //DBHelper.onUpgrade(Database, 1, 2);
        ContentValues contentValues = new ContentValues();

        //format date to "YYYY-MM-DD"
        SimpleDateFormat format = new SimpleDateFormat("yyyy.MM.dd", Locale.US);
        String formattedDate = format.format(date.getTime());
        contentValues.put(SQLiteDBContract.ExpenseDB.COLUMN_NAME_DATE, formattedDate);
        contentValues.put(SQLiteDBContract.ExpenseDB.COLUMN_NAME_MERCHANT, merchant);
        contentValues.put(SQLiteDBContract.ExpenseDB.COLUMN_NAME_AMOUNT, amount);
        contentValues.put(SQLiteDBContract.ExpenseDB.COLUMN_NAME_DESCRIPTION, description);
        contentValues.put(SQLiteDBContract.ExpenseDB.COLUMN_NAME_CATEGORY, category.toString());

        long newRowId = Database.insert(SQLiteDBContract.ExpenseDB.TABLE_NAME, null, contentValues);
        Database.close();

        if( newRowId == -1) return false;
        else return true;
    }

    @Override
    public boolean removeExpense(Expense e){
        Database = DBHelper.getWritableDatabase();

        String[] selectionArgs = new String [] {
                String.valueOf(e.getId())
        };
        int result = Database.delete(SQLiteDBContract.ExpenseDB.TABLE_NAME, SQLiteDBContract.ExpenseDB._ID+" = ?", selectionArgs);
        Database.close();

        return result > 0;
    }

    @Override
    public List<Expense> getExpenses(Calendar selectedDay) {
        List<Expense> expenseList = null;
        Database = DBHelper.getReadableDatabase();

        DateFormat format = new SimpleDateFormat("yyyy.MM.dd");

        String day = format.format(selectedDay.getTime());

        String [] projection = { "*" }; // columns from db
        String selection = SQLiteDBContract.ExpenseDB.COLUMN_NAME_DATE + " = ?";
        String[] selectionArgs = { day };


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
        if(count > 0) {
            c.moveToFirst();
            expenseList = new ArrayList<>();
            for(int i=0; i<count; i++) {
                long id = c.getLong(c.getColumnIndexOrThrow(SQLiteDBContract.ExpenseDB._ID));

                String dateString = c.getString(c.getColumnIndexOrThrow(SQLiteDBContract.ExpenseDB.COLUMN_NAME_DATE));
                // convert date string to Calendar object
                Calendar date = Calendar.getInstance();
                Date d = null;
                try {
                    d = format.parse(dateString);
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
        }

        Database.close();
        return expenseList;
    }

    @Override
    public double getTotalDayAmount(Calendar day) {
        double sum = 0.0;
        Database = DBHelper.getReadableDatabase();

        SimpleDateFormat format = new SimpleDateFormat("yyyy.MM.dd");
        String selectionDay = format.format(day.getTime());
        String[] selectionArgs = { selectionDay }; // arguments for selection filter
        String sql = "SELECT SUM(amount) FROM " + SQLiteDBContract.ExpenseDB.TABLE_NAME+" WHERE "+ SQLiteDBContract.ExpenseDB.COLUMN_NAME_DATE+" = ?";

        Cursor cursor = Database.rawQuery(sql, selectionArgs);

        if(cursor.moveToFirst()){
            sum = cursor.getDouble(0);
        }
        Database.close();

        return sum;
    }

    @Override
    public double getTotalMonthAmount(Calendar month) {
        //TODO: query sum of a month
        double sum = 0.0;
        Database = DBHelper.getReadableDatabase();

        SimpleDateFormat format = new SimpleDateFormat("yyyy.MM.dd");
        String startDay, endDay;

        int daysInMonth = month.getMaximum(Calendar.DAY_OF_MONTH);
        Calendar cal = Calendar.getInstance();

        cal.set(Calendar.DAY_OF_MONTH, 1);
        startDay = format.format(cal.getTime());
        cal.set(Calendar.DAY_OF_MONTH, daysInMonth-1);
        endDay = format.format(cal.getTime());

        System.out.println("start day: "+startDay);
        System.out.println("end day: "+endDay);

        String[] selectionArgs = { startDay, endDay }; // arguments for selection filter
        String sql = "SELECT SUM(amount) FROM " + SQLiteDBContract.ExpenseDB.TABLE_NAME+" WHERE "+ SQLiteDBContract.ExpenseDB.COLUMN_NAME_DATE+" BETWEEN ? and ?";//\""+startDay+"\" AND \""+endDay+"\"";

        Cursor cursor = Database.rawQuery(sql, selectionArgs);

        if(cursor.moveToFirst()){
            sum = cursor.getDouble(0);
        }
        Database.close();

        return sum;
    }

    public void setSelectedDay(Calendar day){
        this.selectedDay = day;
    }

    public Calendar getSelectedDay(){
        return selectedDay;
    }

}
