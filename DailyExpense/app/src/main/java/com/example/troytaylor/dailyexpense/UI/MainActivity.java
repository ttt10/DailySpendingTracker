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

    public void loadCalendarFragment(){
        transaction = manager.beginTransaction();
        transaction.setCustomAnimations(R.anim.calendar_enter, R.anim.expense_exit);

        transaction.replace(R.id.fragment_container, calendarFragment);
        transaction.commit();
    }

    public void loadExpenseListFragment(){
        transaction = manager.beginTransaction();
        transaction.setCustomAnimations(R.anim.expense_enter, R.anim.calendar_exit, R.anim.calendar_enter, R.anim.expense_exit);
        transaction.replace(R.id.fragment_container, expenseListFragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }

    //TODO: add onBackPressed() method to handle back navigation between the fragments
    public void onBackPressed(){
        // pops the ExpenseListFragment off the BackStack
        if(manager.getBackStackEntryCount() > 0){
            manager.popBackStack();
        }
    }

}
