package com.dele.my.project.crudandroid.operations.utils;

import android.app.Activity;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import androidx.core.app.NotificationCompat;

import com.dele.my.project.crudandroid.operations.pojo.IntentExtra;
import com.dele.my.project.crudandroid.operations.pojo.NotificationData;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.stream.Collectors;

/**
 * @author deele
 * @project CrudAndroid
 * @day Monday
 * @philosophy Quality must be enforced, otherwise it won't happen. We programmers must be required to write tests, otherwise we won't do it!
 * <p>
 * ------
 * Tip: Always code as if the guy who ends up maintaining your code will be a violent psychopath who knows where you live.
 * ------
 * copied ****
 * @since 08/08/2022 1:29 PM
 */

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

    /**
     * Helps us to generate a content values list
     * @param map
     * @return
     */
    public static ContentValues createRecord(Map<String, String> map) {
        Set<Map.Entry<String, String>> set = map.entrySet();
        List<Map.Entry<String, String>> data = new ArrayList<>(set);
        ContentValues contentValues = new ContentValues();
        for (int i = 0; i < data.size(); i++) {
            contentValues.put(data.get(i).getKey(), data.get(i).getValue());
        }
        return contentValues;
    }

    /**
     * Helps to generate a UUID
     * @return
     */
    public static String generateUUID() {
        return UUID.randomUUID().toString();
    }

    /**
     * Helps to identify if string is an email
     * @param identification
     * @return
     */
    public static Boolean isEmailAddress(String identification) {
        return  AppConstants.EMAIL_REGEX_PATTERN.matcher(identification).matches();
    }

    /**
     * Helps us to navigate between activities
     * @param from
     * @param to
     * @param intentExtra
     */
    public static void navigate(Activity from, Class to, IntentExtra intentExtra) {
        from.finish();
        Intent intent = new Intent(from.getApplicationContext(), to);
        if (intentExtra != null) intent.putExtra(intentExtra.getKey(), intentExtra.getValue(1L));
        from.startActivity(intent);
    }

    /**
     * Helps us to capitalize each letter of each word (V1)
     * @param word
     * @return
     */
    public static String capitalizeFirstLetterEachWord(String word) {
        return Arrays
                .stream(word.split("\\s+"))
                .map(t -> t.substring(0, 1).toUpperCase() + t.substring(1))
                .collect(Collectors.joining(" "));
    }

    /**
     * Helps us to capitalize each letter of each word (V2)
     * @param word
     * @return
     */
    public static String capitalizeFirstLetterEachWordFully(String word) {
        if (word == null || word.isEmpty()) return word;
        return Arrays
                .stream(word.split("\\s+"))
                .map(t -> t.substring(0, 1).toUpperCase() + t.substring(1).toLowerCase())
                .collect(Collectors.joining(" "));
    }

    public static void createNotification(Context context, NotificationData notificationData, Class className) {
        NotificationCompat.Builder builder = new NotificationCompat
                .Builder(context)
                .setContentTitle(notificationData.getContentTitle())
                .setContentText(notificationData.getContentText());
        Intent notificationIntent = new Intent(context, className);
        PendingIntent contentIntent =
                PendingIntent.getActivity(context, 0,
                        notificationIntent, PendingIntent.FLAG_UPDATE_CURRENT);
        builder.setContentIntent(contentIntent);
        NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(0, builder.build());
    }

}
