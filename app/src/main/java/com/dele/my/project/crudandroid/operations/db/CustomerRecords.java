package com.dele.my.project.crudandroid.operations.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import androidx.annotation.Nullable;

import com.dele.my.project.crudandroid.operations.pojo.Customers;
import com.dele.my.project.crudandroid.operations.utils.AppConstants;
import com.dele.my.project.crudandroid.operations.utils.Helper;
import com.dele.my.project.crudandroid.operations.utils.Queries;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class CustomerRecords extends DatabaseHelper {

    public CustomerRecords(@Nullable Context context) {
        super(context);
    }

    public Long createCustomer(Customers customer) {
        Map<String, String> map = new HashMap<>();
        map.put("FULL_NAME", customer.getFullName());
        map.put("EMAIL_ADDRESS", customer.getEmailAddress());
        map.put("GENDER", customer.getGender());
        map.put("PHONE_NUMBER", customer.getPhoneNumber());
        map.put("UUID", customer.getUuid());
        return this.create(map, AppConstants.CUSTOMER_TABLE_NAME);
    }

    public ArrayList<Customers> getAllCustomers() {
        Cursor cursor = this.read().rawQuery(Queries.RETRIEVE_CUSTOMER_RECORD, null);
        cursor.moveToFirst();
        ArrayList<Customers> allCustomers = new ArrayList<>();
        while (!cursor.isAfterLast()) {
            allCustomers.add(
                    new Customers(
                            cursor.getLong(0), cursor.getString(1),
                            cursor.getString(2), cursor.getString(3),
                            cursor.getString(4), cursor.getString(5),
                            cursor.getString(6), cursor.getString(7))
            );
        }
        cursor.close();
        return allCustomers;
    }

}
