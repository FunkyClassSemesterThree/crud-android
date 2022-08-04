package com.dele.my.project.crudandroid.operations.utils;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.dele.my.project.crudandroid.operations.pojo.IntentExtra;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.stream.Collectors;

public class Helper {

    /**
     * This method helps us to generate a dropdown list item
     */
    public static void setupDropdownResource(Context context, Spinner spinner, String[] items) {
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                context, android.R.layout.simple_spinner_dropdown_item, items);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
    }

    public static ContentValues createRecord(Map<String, String> map) {
        Set<Map.Entry<String, String>> set = map.entrySet();
        List<Map.Entry<String, String>> data = new ArrayList<>(set);
        ContentValues contentValues = new ContentValues();
        for (int i = 0; i < data.size(); i++) {
            contentValues.put(data.get(i).getKey(), data.get(i).getValue());
        }
        return contentValues;
    }

    public static String generateUUID() {
        return UUID.randomUUID().toString();
    }

    public static Boolean isEmailAddress(String identification) {
        return  AppConstants.EMAIL_REGEX_PATTERN.matcher(identification).matches();
    }

    public static void navigate(Activity from, Class to, IntentExtra intentExtra) {
        from.finish();
        Intent intent = new Intent(from.getApplicationContext(), to);
        if (intentExtra != null) {
            intent.putExtra(intentExtra.getKey(), intentExtra.getValue(0));
        }
        from.startActivity(intent);
    }

    public static String capitalizeFirstLetterEachWord(String word) {
        return Arrays
                .stream(word.split("\\s+"))
                .map(t -> t.substring(0, 1).toUpperCase() + t.substring(1))
                .collect(Collectors.joining(" "));
    }

    public static String capitalizeFirstLetterEachWordFully(String word) {
        if (word == null || word.isEmpty()) return word;
        return Arrays
                .stream(word.split("\\s+"))
                .map(t -> t.substring(0, 1).toUpperCase() + t.substring(1).toLowerCase())
                .collect(Collectors.joining(" "));
    }

}
