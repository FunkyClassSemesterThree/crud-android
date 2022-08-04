package com.dele.my.project.crudandroid;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ListView;

import com.dele.my.project.crudandroid.operations.db.customers.CustomerAdapter;
import com.dele.my.project.crudandroid.operations.db.customers.CustomerRecords;
import com.dele.my.project.crudandroid.operations.pojo.Customers;
import com.dele.my.project.crudandroid.operations.pojo.IntentExtra;
import com.dele.my.project.crudandroid.operations.utils.AppConstants;
import com.dele.my.project.crudandroid.operations.utils.Helper;

import java.util.ArrayList;

public class ListCustomersActivity extends AppCompatActivity
        implements CustomerAdapter.CustomerEditButtonClick {

    private CustomerRecords customerRecords;
    private ListView listViewForCustomers;
    private Button addNewCustomersActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_customers);

        setupViews();
    }

    private void setupViews() {
        listViewForCustomers = findViewById(R.id.listViewForCustomers);
        customerRecords = new CustomerRecords(this);
        addNewCustomersActivity = findViewById(R.id.addNewCustomersActivity);

        populateCustomers();
        handleNavigate();
    }

    private void populateCustomers() {
        ArrayList<Customers> allCustomers = customerRecords.getAllCustomers();
        CustomerAdapter customerAdapter = new CustomerAdapter(this, allCustomers);
        customerAdapter.setCustomerEditButtonListener(ListCustomersActivity.this);
        listViewForCustomers.setAdapter(customerAdapter);
    }

    private void handleNavigate() {
        addNewCustomersActivity.setOnClickListener(view -> {
            Helper.navigate(this, MainActivity.class, null);
        });
    }

    @Override
    public void onButtonClickListener(int position, Customers customer) {
        Helper.navigate(this, MainActivity.class,
                        new IntentExtra(AppConstants.CUSTOMER_ID_KEY, customer.getId()));
    }

}