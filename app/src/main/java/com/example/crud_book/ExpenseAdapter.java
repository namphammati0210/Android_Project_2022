package com.example.crud_book;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.*;

public class ExpenseAdapter extends RecyclerView.Adapter<ExpenseAdapter.MyViewHolder> {
    private Context context;
    Activity activity;
    private ArrayList<Expense> expenseArrayList;


    public ExpenseAdapter(Activity activity, Context context, ArrayList<Expense> expenseArrayList) {
        this.activity = activity;
        this.context = context;
        this.expenseArrayList = expenseArrayList;
    }

    @NonNull
    @Override
    public ExpenseAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.my_expense_row, parent, false );

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Expense expense = expenseArrayList.get(position);

        String expense_id = String.valueOf(expense.getExpenseId());
        String expense_type = String.valueOf(expense.getExpenseType());
        String expense_amount = String.valueOf(expense.getExpenseAmount());
        String expense_date = String.valueOf(expense.getExpenseDate());


        holder.expense_id_txt.setText(expense_id);
        holder.expense_type_txt.setText(expense_type);
        holder.expense_amount_txt.setText(expense_amount);
        holder.expense_date_txt.setText(expense_date);
    }


    @Override
    public int getItemCount() {
        return expenseArrayList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView expense_id_txt, expense_type_txt, expense_amount_txt, expense_date_txt;
//        LinearLayout expenseLayout;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            expense_id_txt = itemView.findViewById(R.id.expense_id_txt);
            expense_type_txt = itemView.findViewById(R.id.expense_type_txt);
            expense_amount_txt = itemView.findViewById(R.id.expense_amount_txt);
            expense_date_txt = itemView.findViewById(R.id.expense_date_txt);
//            expenseLayout = itemView.findViewById(R.id.expenseLayout);
        }
    }
}
