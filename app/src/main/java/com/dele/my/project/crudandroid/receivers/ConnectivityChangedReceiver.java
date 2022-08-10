package com.dele.my.project.crudandroid.receivers;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;
import android.widget.Toast;

import com.dele.my.project.crudandroid.operations.utils.AppConstants;

/**
 * @author deele
 * @project CrudAndroid
 * @day Tuesday
 * @philosophy Quality must be enforced, otherwise it won't happen. We programmers must be required to write tests, otherwise we won't do it!
 * <p>
 * ------
 * Tip: Always code as if the guy who ends up maintaining your code will be a violent psychopath who knows where you live.
 * ------
 * copied ****
 * @since 09/08/2022 1:34 PM
 */

public class ConnectivityChangedReceiver extends BroadcastReceiver {

    public ConnectivityChangedReceiver () {
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        if (intent.getAction().equals("com.dele.my.project.crudandroid.CHANGE_CONNECTION")) {
            Toast.makeText(context, "We just received an action notification", Toast.LENGTH_LONG).show();
        }
        else {
            ConnectivityManager connectivityManager = ( ConnectivityManager ) context.getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo activeNetwork = connectivityManager.getActiveNetworkInfo();
            boolean isInternetConnected = activeNetwork != null && activeNetwork.isConnectedOrConnecting();
            if (isInternetConnected) {
                try {
                    Toast.makeText(context, "Network has been connected successfully", Toast.LENGTH_LONG)
                            .show();
                }
                catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        }
    }

}
