package com.example.troytaylor.dailyexpense.UI;

import com.example.troytaylor.dailyexpense.Services.Repository.Data.Entities.Expense;
import com.example.troytaylor.dailyexpense.License;
import com.example.troytaylor.dailyexpense.MyApp;
import com.example.troytaylor.dailyexpense.R;
import com.example.troytaylor.dailyexpense.Services.Repository.IRepository;
import com.example.troytaylor.dailyexpense.Services.Repository.SQLiteRepository;
import com.grapecity.xuni.core.LicenseManager;

import android.content.DialogInterface;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

/**
 *      MainActivity class
 *          - manages fragments
 *
 */
public class MainActivity extends AppCompatActivity implements ExpenseListFragment.OnFABClickListener, EditExpenseFragment.OnBackClickListener{

    private FragmentManager manager;
    private FragmentTransaction transaction;
    private Fragment calendarFragment;
    private Fragment expenseListFragment;
    // be careful of memory leaks here..
    private Fragment editExpenseFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        LicenseManager.KEY = License.KEY; // License Xuni Calendar

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        calendarFragment = new CalendarFragment();
        expenseListFragment = new ExpenseListFragment();
        editExpenseFragment = new EditExpenseFragment();

        manager = getSupportFragmentManager();
        transaction = manager.beginTransaction();

        transaction.replace(R.id.fragment_container, calendarFragment);
        transaction.commit();
    }

//    public void loadCalendarFragment(){
//        transaction = manager.beginTransaction();
//        transaction.setCustomAnimations(R.anim.calendar_enter, R.anim.expense_exit);
//
//        transaction.replace(R.id.fragment_container, calendarFragment);
//        transaction.commit();
//    }

    // create new expense
    public void loadEditExpenseFragment(){
        transaction = manager.beginTransaction();
        transaction.setCustomAnimations(R.anim.expense_enter, R.anim.calendar_exit, R.anim.calendar_enter, R.anim.expense_exit);
        editExpenseFragment = new EditExpenseFragment();
        transaction.replace(R.id.fragment_container, editExpenseFragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }

    // edit existing expense
    public void loadEditExpenseFragment(Expense expense){
        transaction = manager.beginTransaction();

        //pass expense object to fragment
        editExpenseFragment = new EditExpenseFragment();
        Bundle data = new Bundle();
        data.putSerializable("Exp", expense);
        editExpenseFragment.setArguments(data);

        transaction.setCustomAnimations(R.anim.expense_enter, R.anim.calendar_exit, R.anim.calendar_enter, R.anim.expense_exit);
        transaction.replace(R.id.fragment_container, editExpenseFragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }


    public void loadExpenseListFragment(){
        manager.saveFragmentInstanceState(calendarFragment);

        transaction = manager.beginTransaction();

        transaction.setCustomAnimations(R.anim.expense_enter, R.anim.calendar_exit, R.anim.calendar_enter, R.anim.expense_exit);
        transaction.replace(R.id.fragment_container, expenseListFragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }

    public void loadDeleteDialog(final Expense expense){

        final Runnable run = new Runnable(){
            public void run(){
                expenseListFragment.onResume();
            }
        };

        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Are you sure you want to delete?");
        builder.setMessage(expense.getDescription()+ "\t $ "+expense.getAmount());
        builder.setPositiveButton("Delete", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // delete from repository
                MyApp.getServicesComponent().getRepository().removeExpense(expense);

                // Notify adapter update on UI thread
                updateExpenseList();
                MainActivity.this.runOnUiThread(run);

            }
        });

        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // exit
                dialog.dismiss();
            }
        });

        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

    public void updateExpenseList(){
        expenseListFragment.onResume();
        transaction = manager.beginTransaction();
        transaction.detach(expenseListFragment);
        transaction.attach(expenseListFragment);
        transaction.commit();
    }
    public void onBackPressed(){
        // pops the ExpenseListFragment off the BackStack
        if(manager.getBackStackEntryCount() > 0){
            manager.popBackStack();
            // System.out.println("popping from back stack...");
        }
    }

}
