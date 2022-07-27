package com.dele.my.project.crudandroid;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;

import com.dele.my.project.crudandroid.operations.db.customers.CustomerAdapter;
import com.dele.my.project.crudandroid.operations.db.customers.CustomerRecords;
import com.dele.my.project.crudandroid.operations.pojo.Customers;

import java.util.ArrayList;

public class ListCustomersActivity extends AppCompatActivity {

    private CustomerRecords customerRecords;
    private ListView listViewForCustomers;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_customers);

        setupViews();
        populateCustomers();
    }

    private void setupViews() {
        listViewForCustomers = findViewById(R.id.listViewForCustomers);
        customerRecords = new CustomerRecords(this);
    }

    private void populateCustomers() {
        ArrayList<Customers> allCustomers = customerRecords.getAllCustomers();
        CustomerAdapter customerAdapter = new CustomerAdapter(this, allCustomers);
        listViewForCustomers.setAdapter(customerAdapter);
    }

}