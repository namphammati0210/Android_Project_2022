package com.example.crud_book;

public class Expense {
    private String expense_id;
    private String expense_type;
    private String expense_amount;
    private String expense_date;
    private String expense_trip_id;

    // creating getter and setter methods
    public String getExpenseId() {
        return expense_id;
    }

    public void setExpenseId(String id) {
        this.expense_id = id;
    }

    public String getExpenseType() {
        return expense_type;
    }

    public void setExpenseType(String type) {
        this.expense_type = type;
    }

    public String getExpenseAmount() {
        return expense_amount;
    }

    public void setExpenseAmount(String amount) {
        this.expense_amount = amount;
    }

    public String getExpenseDate() {
        return expense_date;
    }

    public void setExpenseDate(String date) {
        this.expense_date = date;
    }

    public String getExpenseTripId() {
        return expense_trip_id;
    }

    public void setExpenseTripId(String tripId) {
        this.expense_trip_id = tripId;
    }

    public Expense(String id, String type, String amount, String date, String tripId) {
        this.expense_id = id;
        this.expense_type = type;
        this.expense_amount = amount;
        this.expense_date = date;
        this.expense_trip_id = tripId;
    }
}
