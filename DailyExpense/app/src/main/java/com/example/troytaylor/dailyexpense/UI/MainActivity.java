package com.example.troytaylor.dailyexpense.UI;

import com.example.troytaylor.dailyexpense.Entities.Expense;
import com.example.troytaylor.dailyexpense.License;
import com.example.troytaylor.dailyexpense.R;
import com.grapecity.xuni.core.LicenseManager;

import android.app.Dialog;
import android.content.DialogInterface;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;



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
        manager.saveFragmentInstanceState(calendarFragment);

        transaction = manager.beginTransaction();

        transaction.setCustomAnimations(R.anim.expense_enter, R.anim.calendar_exit, R.anim.calendar_enter, R.anim.expense_exit);
        transaction.replace(R.id.fragment_container, expenseListFragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }


    public void onBackPressed(){
        // pops the ExpenseListFragment off the BackStack
        if(manager.getBackStackEntryCount() > 0){
            manager.popBackStack();
        }
    }

    public void clearOnClick(View view){
        //create and show the dialog
        ClearConfirmationDialog clearConfirmationDialog = new ClearConfirmationDialog();
        clearConfirmationDialog.show(manager,"ClearConfirmationDialog");
    }

    public static class ClearConfirmationDialog extends DialogFragment{

        public ClearConfirmationDialog(){}

        @Override
        public Dialog onCreateDialog(Bundle savedInstance){
            super.onCreate(savedInstance);

            AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
            builder.setMessage("Delete this expense?");
            builder.setPositiveButton("Delete", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    // delete from repository
                    // MyApp.getServicesComponent().getRepository().removeExpense(    );
                }
            });

            builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    // exit
                    dialog.dismiss();
                }
            });
            Dialog d = builder.create();
            return d;
        }
    }

}
