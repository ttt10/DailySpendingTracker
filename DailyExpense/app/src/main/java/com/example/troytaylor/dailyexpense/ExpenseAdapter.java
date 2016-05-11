package com.example.troytaylor.dailyexpense;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TableRow;
import android.widget.TextView;

import java.util.List;
import java.lang.String;

/**
 * Created by troytaylor on 5/11/16.
 */
public class ExpenseAdapter extends RecyclerView.Adapter<ExpenseAdapter.ViewHolder> {

    private List<Expense> expenses;

    public static class ViewHolder extends RecyclerView.ViewHolder{

        public TableRow tableRow;
        public TextView descriptionView;
        public TextView amountView;
        public ViewHolder(TableRow tr, TextView tv1, TextView tv2){
            tableRow = tr;
            descriptionView = tv1;
            amountView = tv2;

        }
    }

    public ExpenseAdapter(List<Expense> list){
        expenses = list;
    }
    @Override
    public ExpenseAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        TableRow v = (TableRow) LayoutInflater.from(parent.getContext()).inflate(R.layout.expense_item, parent, false);

        ViewHolder vh = new ViewHolder(v);
        return vh;
    }
    @Override
    public void onBindViewHolder(ViewHolder vh, int position){
        vh.descriptionView.setText(expenses.get(position).getDescription());
        vh.amountView.setText(Double.toString(expenses.get(position).getAmount());
    }
    @Override
    public int getItemCount(){
        return expenses.size();
    }
}
