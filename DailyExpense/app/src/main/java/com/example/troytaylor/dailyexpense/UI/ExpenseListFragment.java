package com.example.troytaylor.dailyexpense.UI;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.support.design.widget.FloatingActionButton;

import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import com.example.troytaylor.dailyexpense.Services.Repository.Data.Entities.Expense;
import com.example.troytaylor.dailyexpense.MyApp;
import com.example.troytaylor.dailyexpense.R;
import com.example.troytaylor.dailyexpense.Services.Repository.IRepository;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

/**
 * Created by troytaylor on 4/28/16.
 */
public class ExpenseListFragment extends Fragment implements ExpenseListAdapter.ViewHolder.RecycleItemClicks /* OnTaskCompleted<List<Expense>> */{
    private RecyclerView recyclerView;
    public RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private IRepository repository;

    public OnFABClickListener callbackListener;

    public Calendar day;
    public List<Expense> dayExpenses;


    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstance){
        //inflate the layout for this fragment
        container.removeAllViews();
        View view = inflater.inflate(R.layout.expense_list_fragment, container, false);

        FloatingActionButton fab = (FloatingActionButton) view.findViewById(R.id.fab);

        repository = MyApp.getServicesComponent().getRepository();
        day = repository.getSelectedDay();
        dayExpenses = repository.getExpenses(day);

        /* Display the date of the expenses */
        TextView textView = (TextView) view.findViewById(R.id.list_date);
        String date = new SimpleDateFormat("MMMM").format(day.getTime())+ " "+day.get(Calendar.DAY_OF_MONTH)+", "+day.get(Calendar.YEAR);
        textView.setText(date);

        recyclerView = (RecyclerView) view.findViewById(R.id.expense_recycler_view);

        layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);

        adapter = new ExpenseListAdapter(dayExpenses, this); //fragment is the listener
        recyclerView.setAdapter(adapter);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                callbackListener.loadEditExpenseFragment();
            }
        });

        return view;
    }

    //@Override
    public void onResume(){
        super.onResume();

        // refresh recyclerview
        adapter.notifyDataSetChanged();

    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFABClickListener) {
            callbackListener = (OnFABClickListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    public void loadEditExpenseFragment(Expense expense){
        ((MainActivity)this.getActivity()).loadEditExpenseFragment(expense);
    }
    public void loadDeleteDialog(Expense expense){
        ((MainActivity)this.getActivity()).loadDeleteDialog(expense);
    }

    public interface OnFABClickListener {
        void loadEditExpenseFragment();
    }
}
