package com.example.troytaylor.dailyexpense.UI;

import android.app.Dialog;
import android.content.Context;
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
import android.widget.ImageView;

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

    public OnFABClickListener callbackListener;

    public Calendar day;
    public List<Expense> dayExpenses;

    private final View.OnClickListener editListener = new View.OnClickListener(){
        @Override
        public void onClick(View v) {
            //TODO: pass arguments or save info from expense to populate in EditExpenseFragment
            // ((MainActivity)getActivity()).loadEditExpenseFragment();
        }
    };
    private final View.OnClickListener clearListener = new View.OnClickListener()
    {
        @Override
        public void onClick(View v) {
            new ClearConfirmationDialog();
        }
    };


    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstance){
        //inflate the layout for this fragment
        container.removeAllViews();
        View view = inflater.inflate(R.layout.expense_list_fragment, container, false);

        FloatingActionButton fab = (FloatingActionButton) view.findViewById(R.id.fab);

        repository = MyApp.getServicesComponent().getRepository();
        day = repository.getSelectedDay();
        dayExpenses = repository.getExpenses(day);

        recyclerView = (RecyclerView) view.findViewById(R.id.expense_recycler_view);

        layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);

        adapter = new ExpenseListAdapter(dayExpenses, editListener, clearListener);
        recyclerView.setAdapter(adapter);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                callbackListener.loadEditExpenseFragment();
            }
        });

        return view;
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

    public static class ClearConfirmationDialog extends DialogFragment{

        public ClearConfirmationDialog(){}

        @Override
        public void onCreate(Bundle savedInstance){
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
            d.show();
            //return d;
        }


    }

    public interface OnFABClickListener {
        void loadEditExpenseFragment();
        //TODO: add  method that takes parameter about selected expense
    }
}
