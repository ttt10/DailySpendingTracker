package com.example.troytaylor.dailyexpense.Services.Repository.Data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.ContactsContract;

/**
 * Created by troytaylor on 10/14/16.
 *
 *      SQLiteDBHelper is responsible for creating the database and managing versions. This class also
 *      handles CRUD operations to
 *      It is an extension of the SQLiteOpenHelper
 */
public class SQLiteDBHelper extends SQLiteOpenHelper{

    public static final String DATABASE_NAME = "Expense.db";
    // If you change the database schema, you must increment the database version.
    public static final int DATABASE_VERSION = 1;

    public SQLiteDBHelper(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQLiteDBContract.SQL_CREATE_DB+DATABASE_NAME);
        db.execSQL(SQLiteDBContract.SQL_CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){
        db.execSQL(SQLiteDBContract.SQL_DELETE_TABLE);
        onCreate(db);
    }

    @Override
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion){
        onUpgrade(db, oldVersion, newVersion);
    }

}
