package com.example.troytaylor.dailyexpense.UI;

import com.example.troytaylor.dailyexpense.License;
import com.example.troytaylor.dailyexpense.R;
import com.grapecity.xuni.core.LicenseManager;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;


public class MainActivity extends AppCompatActivity {

    private FragmentManager manager;
    private FragmentTransaction transaction;
    private Fragment calendarFragment;
    private Fragment expenseListFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        LicenseManager.KEY = License.KEY;

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        calendarFragment = new CalendarFragment();
        expenseListFragment = new ExpenseListFragment();

        manager = getSupportFragmentManager();
        transaction = manager.beginTransaction();

        transaction.replace(R.id.fragment_container, calendarFragment);
        transaction.commit();

    }

    //TODO: create a method that handles replacing the calendarFragment with the expenseListFragment
    public void loadCalendarFragment(){
        transaction = manager.beginTransaction();
        transaction.replace(R.id.expense_recycler_view, calendarFragment);
        transaction.commit();
    }

    public void loadExpenseListFragment(){


        transaction = manager.beginTransaction();
        transaction.replace(R.id.calendar, expenseListFragment);
        transaction.commit();
    }


}
