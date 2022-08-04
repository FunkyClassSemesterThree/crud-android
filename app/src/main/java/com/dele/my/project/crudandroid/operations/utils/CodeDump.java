package com.dele.my.project.crudandroid.operations.utils;

public class CodeDump {

    /**
     * code to return data from cursor
     *
     * do {
     *      if (!cursor.moveToFirst()) return new ArrayList<>();
     *      allCustomers.add(
     *      new Customers(
     *      cursor.getLong(cursor.getColumnIndex("ID")),
     *      cursor.getString(cursor.getColumnIndex("FULL_NAME")),
     *      cursor.getString(cursor.getColumnIndex("EMAIL_ADDRESS")),
     *      cursor.getString(cursor.getColumnIndex("GENDER")),
     *      cursor.getString(cursor.getColumnIndex("DATE_CREATED")),
     *      cursor.getString(cursor.getColumnIndex("PHONE_NUMBER")),
     *      cursor.getString(cursor.getColumnIndex("REFERRAL")),
     *      cursor.getString(cursor.getColumnIndex("UUID"))
     *      )
     *      );
     *      }
     *      while(cursor.moveToNext());
     *
     */

}
