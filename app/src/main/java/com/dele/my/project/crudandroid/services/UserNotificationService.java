package com.dele.my.project.crudandroid.services;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

import androidx.annotation.Nullable;

import com.dele.my.project.crudandroid.R;
import com.dele.my.project.crudandroid.operations.db.customers.CustomerRecords;
import com.dele.my.project.crudandroid.operations.pojo.Customers;
import com.dele.my.project.crudandroid.operations.utils.AppConstants;
import com.dele.my.project.crudandroid.operations.utils.Helper;

import java.util.List;

public class UserNotificationService extends Service {

    private final CustomerRecords customerRecords;

    public UserNotificationService() {
        Log.d(AppConstants.APPLICATION_TAG, "Service Instantiated");
        customerRecords = new CustomerRecords(this);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d(AppConstants.APPLICATION_TAG, "This service has been started");
        List<Customers> myCustomers = customerRecords.getAllCustomers();
        Log.d(AppConstants.APPLICATION_TAG, "We have a total of " + myCustomers.size() + " customers");
        Log.d(AppConstants.APPLICATION_TAG, "Am I a multiple? " + Helper.findMultiples(myCustomers.size(), 3));
        if (Helper.findMultiples(myCustomers.size(), 3)) {
            // trigger a notification
            Helper.createNotificationChannel(
                    this,
                    AppConstants.CUSTOMER_CHANNEL_ID,
                    AppConstants.CUSTOMER_CHANNEL_NAME);
            Helper.createNotification(
                    this,
                    AppConstants.CUSTOMER_CHANNEL_ID,
                    "Notification Max",
                    "Hey!!, Our customer base just crossed a multiple of 3",
                    R.drawable.ic_launcher_background);
        }
        return START_STICKY;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

}