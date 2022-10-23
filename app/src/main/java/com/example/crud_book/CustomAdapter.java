package com.example.crud_book;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.*;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.MyViewHolder> implements Filterable {
    private Context context;
    Activity activity;
    private ArrayList<Trip> tripArrayList, tripArrayListAll;


    public CustomAdapter(Activity activity, Context context, ArrayList<Trip> tripArrayList) {
        this.activity = activity;
        this.context = context;
        this.tripArrayList = tripArrayList;

        tripArrayListAll = new ArrayList<>();
        tripArrayListAll.addAll(tripArrayList);
    }

    @NonNull
    @Override
    public CustomAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.my_row, parent, false );

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CustomAdapter.MyViewHolder holder, final int position) {
        Trip trip = tripArrayList.get(position);

        String trip_id = String.valueOf(trip.getTripId());
        String trip_name = String.valueOf(trip.getTripName());
        String trip_destination = String.valueOf(trip.getTripDestination());
        String trip_date = String.valueOf(trip.getTripDate());
        String trip_risk = String.valueOf(trip.getTripRisk());
        String trip_description = String.valueOf(trip.getTripDescription());

        holder.trip_id_txt.setText(trip_id);
        holder.trip_name_txt.setText(trip_name);
        holder.trip_destination_txt.setText("To: " + trip_destination);
        holder.trip_date_txt.setText(trip_date);
        holder.trip_risk_txt.setText("Risk: " + trip_risk);
        holder.trip_description_txt.setText("Description: " + trip_description);

        if(trip_risk.equals("Yes")) {
            holder.trip_risk_txt.setTextColor(Color.parseColor("#FF0000"));
        }else{
            holder.trip_risk_txt.setTextColor(Color.parseColor("#00FF00"));
        }

        holder.mainLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, UpdateActivity.class);
                intent.putExtra("id", trip_id);
                intent.putExtra("name", trip_name);
                intent.putExtra("destination", trip_destination);
                intent.putExtra("date", trip_date);
                intent.putExtra("risk", trip_risk);
                intent.putExtra("description", trip_description);

                activity.startActivityForResult(intent, 1);
            }
        });
    }

    @Override
    public int getItemCount() {
        return tripArrayList.size();
    }

    @Override
    public Filter getFilter() {
        return filter;
    }

    Filter filter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence charSequence) {

            ArrayList<Trip> filteredList = new ArrayList<>();

            if(charSequence.toString().isEmpty()) {
                filteredList.addAll(tripArrayListAll);
            }else{
                Log.d("charSequence", (String) charSequence);

                for(Trip trip: tripArrayListAll) {
                    if(trip.getTripName().toLowerCase().contains(charSequence.toString().toLowerCase())) {
                        filteredList.add(trip);
                    }
                }
            }

            FilterResults filterResults = new FilterResults();
            filterResults.values = filteredList;

            return filterResults;
        }

        @Override
        protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
            tripArrayList.clear();
            tripArrayList.addAll((Collection<? extends Trip>) filterResults.values);
            notifyDataSetChanged();
        }
    };

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView trip_id_txt, trip_name_txt, trip_destination_txt, trip_date_txt, trip_risk_txt, trip_description_txt;
        LinearLayout mainLayout;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            trip_id_txt = itemView.findViewById(R.id.trip_id_txt);
            trip_name_txt = itemView.findViewById(R.id.trip_name_txt);
            trip_destination_txt = itemView.findViewById(R.id.trip_destination_txt);
            trip_date_txt = itemView.findViewById(R.id.trip_date_txt);
            trip_risk_txt = itemView.findViewById(R.id.trip_risk_txt);
            trip_description_txt = itemView.findViewById(R.id.trip_description_txt);
            mainLayout = itemView.findViewById(R.id.mainLayout);
        }
    }
}
