package com.dele.my.project.crudandroid.operations.db.customers;

import android.annotation.SuppressLint;
import android.content.Context;
import android.database.Cursor;
import android.util.Log;

import androidx.annotation.Nullable;

import com.dele.my.project.crudandroid.operations.db.DatabaseHelper;
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

    public long createCustomer(Customers customer) {
        Map<String, String> map = new HashMap<>();
        map.put("FULL_NAME", customer.getFullName());
        map.put("EMAIL_ADDRESS", customer.getEmailAddress());
        map.put("GENDER", customer.getGender());
        map.put("PHONE_NUMBER", customer.getPhoneNumber());
        map.put("UUID", customer.getUuid());
        return this.create(map, AppConstants.CUSTOMER_TABLE_NAME);
    }

    public Integer updateCustomer(Customers customer) {
        Map<String, String> map = new HashMap<>();
        map.put("FULL_NAME", customer.getFullName());
        map.put("EMAIL_ADDRESS", customer.getEmailAddress());
        map.put("GENDER", customer.getGender());
        map.put("PHONE_NUMBER", customer.getPhoneNumber());
        return this.update(map, AppConstants.CUSTOMER_TABLE_NAME, "id = ?", new String[] {
                customer.getId() + ""
        });
    }

    @SuppressLint("Range")
    public Customers findByCustomerIdentity(String identification, int customerId) {

        if ("".equals(identification) && customerId == 0) {
            // throw an error here
        }

        String column = ("".equals(identification)) ? "ID" : determineColumn(identification);
        identification = ("".equals(identification)) ? customerId + "" : identification;

        Cursor cursor = this.read().rawQuery(Queries.RETRIEVE_CUSTOMER_RECORD + " WHERE " + column + " = ?", new String[]{
                identification
        });
        cursor.moveToFirst();
        Customers customer = null;
        if (cursor.isFirst()) {
            customer = new Customers(
                    cursor.getLong(cursor.getColumnIndex("ID")),
                    cursor.getString(cursor.getColumnIndex("FULL_NAME")),
                    cursor.getString(cursor.getColumnIndex("EMAIL_ADDRESS")),
                    cursor.getString(cursor.getColumnIndex("GENDER")),
                    cursor.getString(cursor.getColumnIndex("DATE_CREATED")),
                    cursor.getString(cursor.getColumnIndex("PHONE_NUMBER")),
                    cursor.getString(cursor.getColumnIndex("REFERRAL")),
                    cursor.getString(cursor.getColumnIndex("UUID"))
            );
        }
        cursor.close();
        return customer;
    }

    @SuppressLint("Range")
    public ArrayList<Customers> getAllCustomers() {
        Cursor cursor = this.read().rawQuery(Queries.RETRIEVE_CUSTOMER_RECORD, null);
        ArrayList<Customers> allCustomers = new ArrayList<>();

        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            allCustomers.add(
                    new Customers(
                            cursor.getLong(cursor.getColumnIndex("ID")),
                            cursor.getString(cursor.getColumnIndex("FULL_NAME")),
                            cursor.getString(cursor.getColumnIndex("EMAIL_ADDRESS")),
                            cursor.getString(cursor.getColumnIndex("GENDER")),
                            cursor.getString(cursor.getColumnIndex("DATE_CREATED")),
                            cursor.getString(cursor.getColumnIndex("PHONE_NUMBER")),
                            cursor.getString(cursor.getColumnIndex("REFERRAL")),
                            cursor.getString(cursor.getColumnIndex("UUID"))
                    )
            );
            cursor.moveToNext();
        }
        cursor.close();
        return allCustomers;
    }

    private String determineColumn(String identification) {
        if (Helper.isEmailAddress(identification)) return "EMAIL_ADDRESS";
        return "FULL_NAME";
    }

}
