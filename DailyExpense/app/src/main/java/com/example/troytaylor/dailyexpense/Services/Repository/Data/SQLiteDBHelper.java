package com.example.troytaylor.dailyexpense.Services.Repository.Data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.ContactsContract;

import com.example.troytaylor.dailyexpense.R;

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
    public static final int DATABASE_VERSION = 2; //R.string.database_version; // from res/values/strings.xml

    private static SQLiteDBHelper Instance;

    // make database instance a singleton instance
    public static synchronized SQLiteDBHelper getInstance(Context context){
        // Use the application context, which will ensure that you
        // don't accidentally leak an Activity's context.
        if (Instance == null) {
            Instance = new SQLiteDBHelper(context.getApplicationContext());
        }
        return Instance;
    }

    public SQLiteDBHelper(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        //context.openOrCreateDatabase(DATABASE_NAME, context.MODE_PRIVATE, null);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
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
