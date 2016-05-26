package com.example.troytaylor.dailyexpense.UI;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.troytaylor.dailyexpense.Entities.Expense;
import com.example.troytaylor.dailyexpense.R;

import java.util.List;

/**
 * Created by troytaylor on 5/11/16.
 */
public class ExpenseListAdapter extends RecyclerView.Adapter<ExpenseListAdapter.ViewHolder> {

    private List<Expense> expenses;
    private final View.OnClickListener onClickListener;

    public static class ViewHolder extends RecyclerView.ViewHolder{

        public View parent;
        public TextView descriptionView;
        public TextView amountView;
        public ViewHolder(View view){
            super(view);
            this.parent = view;
            descriptionView = (TextView) view.findViewById(R.id.description);
            amountView = (TextView) view.findViewById(R.id.amount);
        }
    }

    public ExpenseListAdapter(List<Expense> list, View.OnClickListener listener) {
        expenses = list;
        onClickListener = listener;
    }
    @Override
    public ExpenseListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        RelativeLayout v = (RelativeLayout) LayoutInflater.from(parent.getContext()).inflate(R.layout.expense_item, parent, false);
        v.setOnClickListener(onClickListener);
        ViewHolder vh = new ViewHolder(v);

        return vh;
    }
    @Override
    public void onBindViewHolder(ViewHolder vh, int position){
        vh.descriptionView.setText(expenses.get(position).getDescription());
        vh.parent.setTag(expenses.get(position));
        vh.amountView.setText("$ "+Double.toString(expenses.get(position).getAmount()));
    }

    public void setDayExpenseList(List<Expense> list){
        this.expenses = list;

        notifyDataSetChanged(); // refresh recycler view
    }
    @Override
    public int getItemCount(){
        return expenses.size();
    }
}
