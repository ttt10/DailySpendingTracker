package com.example.troytaylor.dailyexpense.UI;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.troytaylor.dailyexpense.Services.Repository.Data.Entities.Expense;
import com.example.troytaylor.dailyexpense.R;

import java.util.List;

/**
 * Created by troytaylor on 5/11/16.
 */
public class ExpenseListAdapter extends RecyclerView.Adapter<ExpenseListAdapter.ViewHolder> {

    private List<Expense> expenses;
    public ViewHolder.RecycleItemClicks listener;

    public static class ViewHolder extends RecyclerView.ViewHolder {

        public Expense expense;
        public View parent;
        public TextView merchantView;
        public TextView amountView;
        public ImageView editView;
        public ImageView clearView;
        public ViewHolder.RecycleItemClicks listener;

        public ViewHolder(View view){
            super(view);
            this.parent = view;
            this.merchantView = (TextView) view.findViewById(R.id.merchant);
            this.amountView = (TextView) view.findViewById(R.id.amount);
            this.editView = (ImageView) view.findViewById(R.id.edit);
            this.clearView = (ImageView) view.findViewById(R.id.clear);

            this.editView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    listener.loadEditExpenseFragment(expense);
                }
            });

            this.clearView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    listener.loadDeleteDialog(expense);
                }
            });
        }

        public interface RecycleItemClicks{
            void loadEditExpenseFragment(Expense expense);
            void loadDeleteDialog(Expense expense);
        }
    }

    public ExpenseListAdapter(List<Expense> list, ViewHolder.RecycleItemClicks listener) {
        expenses = list;
        this.listener = listener;
    }

    @Override
    public ExpenseListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        RelativeLayout v = (RelativeLayout) LayoutInflater.from(parent.getContext()).inflate(R.layout.expense_item, parent, false);
        ViewHolder vh = new ViewHolder(v);
        vh.listener = listener;
        return vh;
    }
    @Override
    public void onBindViewHolder(ViewHolder vh, int position){
        vh.expense = expenses.get(position);
        vh.merchantView.setText(expenses.get(position).getMerchant());
        vh.parent.setTag(expenses.get(position)); // what does this do?
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
