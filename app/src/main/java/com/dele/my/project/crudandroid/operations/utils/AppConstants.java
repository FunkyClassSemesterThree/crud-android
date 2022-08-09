package com.dele.my.project.crudandroid.operations.utils;

import java.util.regex.Pattern;

public class AppConstants {

    public static final String[] genderList = {
            "Male",
            "Female"
    };

    public static final String DATABASE_NAME = "CRUDE_ANDROID";
    public static final Integer DATABASE_VERSION = 1;

    public static final String CUSTOMER_TABLE_NAME = "CUSTOMERS";

    public static final String CUSTOMER_SUCCESS_MESSAGE = "CUSTOMER HAS BEEN CREATED SUCCESSFULLY";
    public static final String CUSTOMER_ERROR_MESSAGE = "THERE WAS AN ERROR CREATING THIS CUSTOMER";

    public static final Pattern EMAIL_REGEX_PATTERN = Pattern.compile("[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,4}");

    public static final String APPLICATION_TAG = "CRUD_ANDROID";

    public static final String CUSTOMER_ID_KEY = "CUS_KEY";

    public static final String CUSTOMER_CHANNEL_ID = "CUSTOMER_CHANNEL_ID";
    public static final String CUSTOMER_CHANNEL_NAME = "CUSTOMER_CHANNEL_NAME";

}
