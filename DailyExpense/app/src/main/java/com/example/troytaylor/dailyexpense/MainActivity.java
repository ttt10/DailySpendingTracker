package com.example.troytaylor.dailyexpense;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;


public class MainActivity extends AppCompatActivity {

    private FragmentManager manager;
    private FragmentTransaction transaction;
    private Fragment calendarFragment;
    private ExpenseListFragment expenseListFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        calendarFragment = new CalendarFragment();

        manager = getSupportFragmentManager();
        transaction = manager.beginTransaction();

        transaction.replace(R.id.fragment_container, calendarFragment);
        transaction.commit();
    }


}
