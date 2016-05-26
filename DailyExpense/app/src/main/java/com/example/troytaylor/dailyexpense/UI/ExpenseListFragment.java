package com.example.troytaylor.dailyexpense.UI;

import android.content.DialogInterface;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.support.design.widget.FloatingActionButton;

import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.Adapter;
import android.support.v7.widget.RecyclerView.LayoutManager;

import com.example.troytaylor.dailyexpense.Entities.Expense;
import com.example.troytaylor.dailyexpense.MyApp;
import com.example.troytaylor.dailyexpense.R;
import com.example.troytaylor.dailyexpense.Services.OnTaskCompleted;
import com.example.troytaylor.dailyexpense.Services.Repository.IRepository;

import java.util.Calendar;
import java.util.List;

/**
 * Created by troytaylor on 4/28/16.
 */
public class ExpenseListFragment extends Fragment /* implements OnTaskCompleted<List<Expense>> */{
    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private IRepository repository;
    private final View.OnClickListener clearListener = new View.OnClickListener()
    {
        @Override
        public void onClick(View v) {

        }
    };

    public Calendar day;
    public List<Expense> dayExpenses;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstance){
        //inflate the layout for this fragment
        container.removeAllViews();
        View view = inflater.inflate(R.layout.expense_list_fragment, container, false);

        FloatingActionButton fab = (FloatingActionButton) view.findViewById(R.id.fab);

        //TODO: add onClickListener to fab to manually add an expense
        /*
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //create dialog fragment with empty fields
            }
        });
        */

        repository = MyApp.getServicesComponent().getRepository();
        day = repository.getSelectedDay();
        dayExpenses = repository.getExpenses(day);

        recyclerView = (RecyclerView) view.findViewById(R.id.expense_recycler_view);

        layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);

        adapter = new ExpenseListAdapter(dayExpenses, clearListener);
        recyclerView.setAdapter(adapter);

        return view;
    }


    /*
    @Override
    public void onPause(){

    }

    @Override
    public void onResume(){

    }


    /* @Override
    public void onSuccess(List<Expense> result){
        dayExpenses = repository.getExpenses(day);

        // adapter.setDayExpenseList(result);

    }
    */
}
