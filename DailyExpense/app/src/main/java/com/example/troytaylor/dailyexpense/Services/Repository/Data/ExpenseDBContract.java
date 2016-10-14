package com.example.troytaylor.dailyexpense.Services.Repository.Data;

import android.provider.BaseColumns;

/**
 * Created by troytaylor on 10/13/16.
 *
 *      This class defines the Schema for the Database and contains constants for creating and deleting the database
 */
public class ExpenseDBContract {

    private static final String TEXT_TYPE = " TEXT";
    private static final String INTEGER_TYPE = " INTEGER";
    private static final String VARCHAR_TYPE = "NVARCHAR(100)";

    public static final String SQL_CREATE_DB = "CREATE DATABASE IF NOT EXISTS ";
    // TODO: ADD constraints for column values
    public static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE" + ExpenseDBContract.ExpenseDB.TABLE_NAME + "(" +
                    ExpenseDB._ID + " INTEGER PRIMARY KEY,"+
                    ExpenseDB.COLUMN_MERCHANT_NAME + TEXT_TYPE +","+
                    ExpenseDB.COLUMN_AMOUNT + INTEGER_TYPE +","+
                    ExpenseDB.COLUMN_DESCRIPTION + VARCHAR_TYPE +","+
                    ExpenseDB.COLUMN_CATEGORY + TEXT_TYPE;
    public static final String SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS "+ ExpenseDB.TABLE_NAME;


    private ExpenseDBContract( ) {}

    // define the Schema for SQLiteDatabase
    public static class ExpenseDB implements BaseColumns {
        public static final String TABLE_NAME = "expense_table";
        public static final String COLUMN_MERCHANT_NAME = "merchant_name";
        public static final String COLUMN_AMOUNT = "amount";
        public static final String COLUMN_DESCRIPTION = "description";
        public static final String COLUMN_CATEGORY = "category";
    }

}
