package com.example.troytaylor.dailyexpense.Services.Repository.Data;

import android.provider.BaseColumns;

/**
 * Created by troytaylor on 10/13/16.
 *
 *      This class defines the Schema for the Database table and contains constants for creating and deleting the database
 */
public final class SQLiteDBContract {

    public static final String SQL_CREATE_DB = "CREATE DATABASE IF NOT EXISTS ";
    public static final String SQL_CREATE_TABLE =
            "CREATE TABLE IF NOT EXISTS " + SQLiteDBContract.ExpenseDB.TABLE_NAME + " (" +
                    ExpenseDB._ID +" "+ ExpenseDB.INTEGER_TYPE + " PRIMARY KEY AUTOINCREMENT, "+
                    ExpenseDB.COLUMN_NAME_DATE +" "+ ExpenseDB.DATE_TYPE + " NOT NULL, "+
                    ExpenseDB.COLUMN_NAME_MERCHANT +" "+ ExpenseDB.TEXT_TYPE +" NOT NULL, "+
                    ExpenseDB.COLUMN_NAME_AMOUNT +" "+ ExpenseDB.DOUBLE_TYPE +" NOT NULL, "+
                    ExpenseDB.COLUMN_NAME_DESCRIPTION +" "+ ExpenseDB.VARCHAR_TYPE +", "+
                    ExpenseDB.COLUMN_NAME_CATEGORY +" "+ ExpenseDB.TEXT_TYPE +")";
    public static final String SQL_DELETE_DB = "DELETE DATABASE ";
    public static final String SQL_DELETE_TABLE =
            "DROP TABLE IF EXISTS "+ ExpenseDB.TABLE_NAME;


    private SQLiteDBContract( ) {}

    /**
     *   ExpenseDB defines the table contents
     *     - BaseColumns adds an _ID primary key
     */
    public static class ExpenseDB implements BaseColumns {

        /* table names */
        public static final String TABLE_NAME = "expense_table";

        /* column names */
        public static final String COLUMN_NAME_DATE = "date";
        public static final String COLUMN_NAME_MERCHANT = "merchant";
        public static final String COLUMN_NAME_AMOUNT = "amount";
        public static final String COLUMN_NAME_DESCRIPTION = "description";
        public static final String COLUMN_NAME_CATEGORY = "category";

        /* data types */
        public static final String INTEGER_TYPE = "INTEGER";
        public static final String TEXT_TYPE = " TEXT";
        public static final String DATE_TYPE = "DATE";
        public static final String DOUBLE_TYPE = "DECIMAL(10,2)";
        public static final String VARCHAR_TYPE = "NVARCHAR(100)";

    }

}
