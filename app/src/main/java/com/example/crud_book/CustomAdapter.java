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
//import java.util.ArrayList;
//import java.util.Collection;
//import java.util.List;
//import java.util.Locale;
//import java.util.Set;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.MyViewHolder> {
    private Context context;
    Activity activity;
    private ArrayList trip_id, trip_name, trip_destination, trip_date, trip_risk, trip_description;

    CustomAdapter(Activity activity, Context context, ArrayList trip_id, ArrayList trip_name, ArrayList trip_destination, ArrayList trip_date, ArrayList trip_risk, ArrayList trip_description) {
        this.activity = activity;
        this.context = context;
        this.trip_id = trip_id;
        this.trip_name = trip_name;
        this.trip_destination = trip_destination;
        this.trip_date = trip_date;
        this.trip_risk = trip_risk;
        this.trip_description = trip_description;

//        this.trip_id_filter = trip_id;
//        this.trip_name_filter = trip_name;
//        this.trip_destination_filter = trip_destination;
//        this.trip_date_filter = trip_date;
//        this.trip_risk_filter = trip_risk;
//        this.trip_description_filter = trip_description;
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

        holder.trip_id_txt.setText(String.valueOf(trip_id.get(position)));
        holder.trip_name_txt.setText(String.valueOf(trip_name.get(position)));
        holder.trip_destination_txt.setText(String.valueOf("To: " + trip_destination.get(position)));
        holder.trip_date_txt.setText(String.valueOf(trip_date.get(position)));
        holder.trip_risk_txt.setText(String.valueOf("Risk: " + trip_risk.get(position)));
        holder.trip_description_txt.setText(String.valueOf("Description: " + trip_description.get(position)));

        if(trip_risk.get(position).equals("Yes")) {
            holder.trip_risk_txt.setTextColor(Color.parseColor("#FF0000"));
        }else{
            holder.trip_risk_txt.setTextColor(Color.parseColor("#00FF00"));
        }

        holder.mainLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, UpdateActivity.class);
                intent.putExtra("id", String.valueOf(trip_id.get(position)));
                intent.putExtra("name", String.valueOf(trip_name.get(position)));
                intent.putExtra("destination", String.valueOf(trip_destination.get(position)));
                intent.putExtra("date", String.valueOf(trip_date.get(position)));
                intent.putExtra("risk", String.valueOf(trip_risk.get(position)));
                intent.putExtra("description", String.valueOf(trip_description.get(position)));
//                activity.startActivityForResult(intent, 123);
                activity.startActivityForResult(intent, 1);
            }
        });
    }

    @Override
    public int getItemCount() {
        return trip_id.size();
    }

//    @Override
//    public Filter getFilter() {
//        return filter;
//    }
//
//    Filter filter = new Filter() {
//        @Override
//        protected FilterResults performFiltering(CharSequence charSequence) {
////            Set<String> filteredIdList = new HashSet<String>();
////            Set<String> filteredNameList = new HashSet<String>();
////            Set<String> filteredDestinationList = new HashSet<String>();
////            Set<String> filteredDateList = new HashSet<String>();
////            Set<String> filteredRiskList = new HashSet<String>();
////            Set<String> filteredDescriptionList = new HashSet<String>();
//
////            HashMap<String, ArrayList> finalFilteredList = new HashMap<String, ArrayList>();
//            List<String> filteredIdList = new ArrayList<String>();
//
//            if(charSequence.toString().isEmpty()) {
////                filteredNameList.addAll(trip_name);
////                filteredIdList.addAll(trip_id);
////                filteredDestinationList.addAll(trip_destination);
////                filteredDateList.addAll(trip_date);
////                filteredRiskList.addAll(trip_risk);
////                filteredDescriptionList.addAll(trip_description);
//                filteredIdList.addAll(trip_id);
//            }else{
//                Log.d("charSequence", (String) charSequence);
//
//                for(int i=0; i < trip_name.size(); i++) {
//
//                    String tripId = String.valueOf(trip_id.get(i));
//                    String tripName = String.valueOf(trip_name.get(i));
////                    String tripDestination = String.valueOf(trip_destination.get(i));
////                    String tripDate = String.valueOf(trip_date.get(i));
////                    String tripRisk = String.valueOf(trip_risk.get(i));
////                    String tripDescription = String.valueOf(trip_description.get(i));
//
//                    if(tripName.toLowerCase().contains(charSequence.toString().toLowerCase())) {
//                        Log.d("Found tripName", tripName);
//
//                        filteredIdList.add(tripId);
//                    }
//                }
//            }
//
//            FilterResults filterResults = new FilterResults();
//            filterResults.values = filteredIdList;
//
//            Log.d("filteredIdList", String.valueOf(filteredIdList));
//
//            return filterResults;
//        }
//
//        @Override
//        protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
////            ArrayList result = (ArrayList) filterResults.values;
//
////            System.out.println(Arrays.deepToString(result.toArray()));
////            trip_name.addAll((Collection) filterResults.values);
////            trip_id.clear();
////            trip_id.addAll((Collection) filterResults.values);
//            Log.d("filterResults values", String.valueOf(filterResults.values));
//
//            ArrayList<String> results = (ArrayList<String>) filterResults.values;
//
//
//
//
//
//
//                for(String result: results) {
//                    Log.d("compare value 1", result);
//
//                    for (int i = 0; i < trip_id.size(); i++) {
//                        Log.d("compare value 2", String.valueOf(i));
//
//                        if(!result.contains(String.valueOf(i)) && i != 0) {
//                            Log.d("filter Index", String.valueOf(i));
//                            trip_id.remove(i);
//                            trip_name.remove(i);
//                            trip_date.remove(i);
//                            trip_destination.remove(i);
//                            trip_risk.remove(i);
//                            trip_description.remove(i);
//                        }
//                    }
//                }
//
//
////            for (int i = 0; i < trip_name.size(); i++) {
////                Log.d("Render tripName", tripName);
////            }
//            Log.d("render trip_name", String.valueOf(trip_name));
////            trip_name.removeIf((name, i) -> Objects.equals(filterResults.values));
//            notifyDataSetChanged();
//        }
//    };





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
