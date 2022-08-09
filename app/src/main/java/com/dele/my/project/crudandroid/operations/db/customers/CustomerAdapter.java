package com.dele.my.project.crudandroid.operations.db.customers;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.dele.my.project.crudandroid.R;
import com.dele.my.project.crudandroid.operations.interfaces.CustomerButtonClick;
import com.dele.my.project.crudandroid.operations.pojo.Customers;
import com.dele.my.project.crudandroid.operations.utils.Helper;

import java.util.ArrayList;

public class CustomerAdapter extends ArrayAdapter<Customers> {

    CustomerButtonClick customerButtonClick;

    public void setCustomerButtonListener(CustomerButtonClick listener) {
        this.customerButtonClick = listener;
    }

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
        RelativeLayout layout = convertView.findViewById(R.id.customerDataRL);
        Button editCustomerBtn = convertView.findViewById(R.id.editCustomerData);
        Button deleteCustomerBtn = convertView.findViewById(R.id.deleteCustomerData);

        if (position % 2 == 0) {
            layout.setBackgroundColor(Color.BLACK);
            fullNameTextVew.setTextColor(Color.WHITE);
        }
        else {
            layout.setBackgroundColor(Color.GRAY);
            fullNameTextVew.setTextColor(Color.YELLOW);
        }

        // populate data to the view rendered above via the data object
        fullNameTextVew.setText(Helper.capitalizeFirstLetterEachWord(customer.getFullName()));

        // onclick edit button
        editCustomerBtn.setOnClickListener(view -> {
            if (customerButtonClick != null) customerButtonClick.onButtonClickListener(position, customer, "EDIT");
        });

        // onclick delete button
        deleteCustomerBtn.setOnClickListener(view -> {
            if (customerButtonClick != null) customerButtonClick.onButtonClickListener(position, customer, "DELETE");
        });

        // return the completed view to screen
        return convertView;
    }
}
