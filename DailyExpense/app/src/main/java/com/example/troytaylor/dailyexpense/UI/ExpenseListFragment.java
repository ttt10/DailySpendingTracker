package com.example.troytaylor.dailyexpense.UI;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.Adapter;
import android.support.v7.widget.RecyclerView.LayoutManager;

import com.example.troytaylor.dailyexpense.Entities.Expense;
import com.example.troytaylor.dailyexpense.MyApp;
import com.example.troytaylor.dailyexpense.R;
import com.example.troytaylor.dailyexpense.Services.Repository.IRepository;

import java.util.Calendar;
import java.util.List;

/**
 * Created by troytaylor on 4/28/16.
 */
public class ExpenseListFragment extends Fragment {
    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private IRepository repository;

    public Calendar today;
    public List<Expense> todaysExpenses;


    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstance){
        //inflate the layout for this fragment
        container.removeAllViews();
        View view = inflater.inflate(R.layout.expense_list_fragment, container, false);

        repository = MyApp.getServicesComponent().getRepository();
        today = repository.getSelectedDay();
        todaysExpenses = repository.getExpenses(today);

        recyclerView = (RecyclerView) view.findViewById(R.id.expense_recycler_view);

        layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);

        adapter = new ExpenseListAdapter(todaysExpenses);
        recyclerView.setAdapter(adapter);

        return view;
    }
}
