package com.dele.my.project.crudandroid.operations.utils;

public class Queries {

    // Customer Queries
    public static final String CREATE_CUSTOMER_TABLE = "CREATE TABLE IF NOT EXISTS "
            + AppConstants.CUSTOMER_TABLE_NAME + " (ID INTEGER PRIMARY KEY AUTOINCREMENT, " +
            "FULL_NAME TEXT NOT NULL, EMAIL_ADDRESS TEXT NOT NULL, GENDER TEXT NOT NULL, " +
            "DATE_CREATED TIMESTAMP DEFAULT CURRENT_TIMESTAMP, PHONE_NUMBER TEXT NOT NULL, " +
            "REFERRAL TEXT DEFAULT 'NONE', UUID TEXT NOT NULL)";

    public static final String RETRIEVE_CUSTOMER_RECORD = "SELECT * FROM " + AppConstants.CUSTOMER_TABLE_NAME;

}
