package com.dele.my.project.crudandroid.operations.db.customers;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.dele.my.project.crudandroid.R;
import com.dele.my.project.crudandroid.operations.pojo.Customers;

import java.util.ArrayList;

public class CustomerAdapter extends ArrayAdapter<Customers> {

    public CustomerAdapter(@NonNull Context context, ArrayList<Customers> customers) {
        super(context, 0, customers);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        // Get Customer in this position
        Customers customer = getItem(position);

        // check if view is reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.customer_data, parent, false);
        }

        // lookup existing view in inflated layout
        TextView fullNameTextVew = convertView.findViewById(R.id.listItemCustomerFullName);

        // populate data to the view rendered above via the data object
        fullNameTextVew.setText(customer.getFullName());

        // return the completed view to screen
        return convertView;
    }
}
