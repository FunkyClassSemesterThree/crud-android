package com.dele.my.project.crudandroid;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.dele.my.project.crudandroid.operations.db.customers.CustomerRecords;
import com.dele.my.project.crudandroid.operations.pojo.Customers;
import com.dele.my.project.crudandroid.operations.pojo.NotificationData;
import com.dele.my.project.crudandroid.operations.utils.AppConstants;
import com.dele.my.project.crudandroid.operations.utils.Helper;
import com.dele.my.project.crudandroid.receivers.ConnectivityChangedReceiver;

public class MainActivity extends AppCompatActivity {

    private EditText customerFullName, customerEmailAddress, customerPhoneNumber;
    private Spinner customerGender;
    private Button customerSubmitBtn, viewListCustomers;
    private TextView formMessageField;

    private CustomerRecords customerRecords;

    private ConnectivityChangedReceiver changedReceiver;
    private IntentFilter intentFilter;

    private Bundle bundle;

    private int customerId = 0;

    private Customers defaultCustomer = null;

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

        bundle = getIntent().getExtras();

        if (bundle != null) {
            customerId = (int) bundle.getLong(AppConstants.CUSTOMER_ID_KEY);
        }

        // this will display genders
        Helper.setupDropdownResource(this, customerGender, AppConstants.genderList);

        handleSubmit();
        handleNavigate();
        populateByCustomerId();
        setupAndStartReceiver();
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

            if (!Helper.isEmailAddress(emailAddress)) {
                formMessageField.setText(AppConstants.CUSTOMER_ERROR_MESSAGE);
                return;
            }

            if (customerId == 0 && defaultCustomer == null) {
                if (customerRecords.findByCustomerIdentity(emailAddress, 0) != null) {
                    formMessageField.setText(AppConstants.CUSTOMER_ERROR_MESSAGE);
                    return;
                }
                if (customerRecords.findByCustomerIdentity(fullName, 0) != null) {
                    formMessageField.setText(AppConstants.CUSTOMER_ERROR_MESSAGE);
                    return;
                }
            }

            int actionPerformed = 0;

            if (customerId == 0 && defaultCustomer == null) {
                actionPerformed = (int) customerRecords.createCustomer(
                        new Customers(fullName, emailAddress, gender, phoneNumber, "", Helper.generateUUID())
                );
            }
            else {
                actionPerformed = customerRecords.updateCustomer(
                        new Customers(customerId, fullName, emailAddress, gender, phoneNumber)
                );
            }

            if (actionPerformed > 0) {
                formMessageField.setText(AppConstants.CUSTOMER_SUCCESS_MESSAGE);
                Toast.makeText(this, AppConstants.CUSTOMER_SUCCESS_MESSAGE, Toast.LENGTH_LONG).show();
                Helper.createNotificationChannel(this, AppConstants.CUSTOMER_CHANNEL_ID, AppConstants.CUSTOMER_CHANNEL_NAME);
                Helper.createNotification(
                        this,
                        AppConstants.CUSTOMER_CHANNEL_ID,
                        "Welcome to Our Application",
                        "We welcome you to our application with warm regards",
                        R.drawable.ic_launcher_background
                );
            } else formMessageField.setText(AppConstants.CUSTOMER_ERROR_MESSAGE);

        });
    }

    private void handleNavigate() {
        viewListCustomers.setOnClickListener(view -> {
            Helper.navigate(this, ListCustomersActivity.class, null);
        });
    }

    private void populateByCustomerId() {
        if (customerId != 0) {
            defaultCustomer = customerRecords.findByCustomerIdentity("", customerId);
            if (defaultCustomer != null) {
                customerFullName.setText(defaultCustomer.getFullName());
                customerEmailAddress.setText(defaultCustomer.getEmailAddress());
                customerPhoneNumber.setText(defaultCustomer.getPhoneNumber());
            }
        }
    }

    private void setupAndStartReceiver() {
        changedReceiver = new ConnectivityChangedReceiver();
        intentFilter = new IntentFilter();
        intentFilter.addAction(getPackageName() + "android.net.conn.CONNECTIVITY_CHANGE");
        Intent intent = new Intent("com.dele.my.project.crudandroid.CHANGE_CONNECTION");
        sendBroadcast(intent);
    }

    @Override
    protected void onResume() {
        super.onResume();
        registerReceiver(changedReceiver, intentFilter);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(changedReceiver);
    }

}