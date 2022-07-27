package com.dele.my.project.crudandroid;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.dele.my.project.crudandroid.operations.db.customers.CustomerRecords;
import com.dele.my.project.crudandroid.operations.pojo.Customers;
import com.dele.my.project.crudandroid.operations.utils.AppConstants;
import com.dele.my.project.crudandroid.operations.utils.Helper;

public class MainActivity extends AppCompatActivity {

    private EditText customerFullName, customerEmailAddress, customerPhoneNumber;
    private Spinner customerGender;
    private Button customerSubmitBtn, viewListCustomers;
    private TextView formMessageField;

    private CustomerRecords customerRecords;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setupViews();
    }

    /**
     * This method helps us to setup our views with the activity class
     */
    private void setupViews() {
        // Edit Texts
        customerFullName = findViewById(R.id.customerFullName);
        customerEmailAddress = findViewById(R.id.customerEmailAddress);
        customerPhoneNumber = findViewById(R.id.customerPhoneNumber);

        // Spinners (Dropdown)
        customerGender = findViewById(R.id.customerGender);

        // Buttons
        customerSubmitBtn = findViewById(R.id.customerSubmitBtn);
        viewListCustomers = findViewById(R.id.viewListCustomers);

        // TextViews
        formMessageField = findViewById(R.id.formMessageField);

        customerRecords = new CustomerRecords(this);

        // this will display genders
        Helper.setupDropdownResource(this, customerGender, AppConstants.genderList);

        handleSubmit();
        handleNavigate();
    }

    private void handleSubmit() {
        customerSubmitBtn.setOnClickListener(view -> {
            String fullName = customerFullName.getText().toString();
            String emailAddress = customerEmailAddress.getText().toString();
            String phoneNumber = customerPhoneNumber.getText().toString();
            String gender = customerGender.getSelectedItem().toString();

            if ("".equals(fullName)) {
                formMessageField.setText(AppConstants.CUSTOMER_ERROR_MESSAGE);
                return;
            }

            if ("".equals(emailAddress)) {
                formMessageField.setText(AppConstants.CUSTOMER_ERROR_MESSAGE);
                return;
            }

            if ("".equals(phoneNumber)) {
                formMessageField.setText(AppConstants.CUSTOMER_ERROR_MESSAGE);
                return;
            }

            if ("".equals(gender)) {
                formMessageField.setText(AppConstants.CUSTOMER_ERROR_MESSAGE);
                return;
            }

            if (customerRecords.findByCustomerIdentity(emailAddress) != null) {
                formMessageField.setText(AppConstants.CUSTOMER_ERROR_MESSAGE);
                return;
            }

            if (customerRecords.findByCustomerIdentity(fullName) != null) {
                formMessageField.setText(AppConstants.CUSTOMER_ERROR_MESSAGE);
                return;
            }

            Long isCreated = customerRecords.createCustomer(
                    new Customers(fullName, emailAddress, phoneNumber, gender, "", Helper.generateUUID())
            );
            if (isCreated > 0) {
                formMessageField.setText(AppConstants.CUSTOMER_SUCCESS_MESSAGE);
                Toast.makeText(this, AppConstants.CUSTOMER_SUCCESS_MESSAGE, Toast.LENGTH_LONG);
            }
            else formMessageField.setText(AppConstants.CUSTOMER_ERROR_MESSAGE);
        });
    }

    private void handleNavigate() {
        viewListCustomers.setOnClickListener(view -> {
            startActivity(new Intent(this, ListCustomersActivity.class));
        });
    }

}