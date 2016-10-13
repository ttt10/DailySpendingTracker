package com.example.troytaylor.dailyexpense.Entities.DataStore;

import android.provider.BaseColumns;

/**
 * Created by troytaylor on 10/13/16.
 */
public class ExpenseDBContract {

    private ExpenseDBContract( ) {}

    // define the Schema for SQLiteDatabase
    public static class ExpenseDB implements BaseColumns {
        public static final String TABLE_NAME = "expense_table";
        public static final String MERCHANT_NAME = "merchant_name";
        public static final String AMOUNT = "amount";
        public static final String DESCRIPTION = "description";
        public static final String CATEGORY = "category";
    }


}
