package com.dele.my.project.crudandroid;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private EditText customerFullName, customerEmailAddress, customerPhoneNumber;
    private Spinner customerGender, customerBusiness;
    private Button customerSubmitBtn;
    private TextView formMessageField;

    private final String[] genderList = {
            "Male",
            "Female"
    };

    private final String[] businessList = {
            "Hansen Pharmaceuticals",
            "Blessed Okeyson and Sons"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setupViews();

        // this will display genders
        setupDropdownResource(customerGender, genderList);
        // this will display businesses
        setupDropdownResource(customerBusiness, businessList);
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
        customerBusiness = findViewById(R.id.customerBusiness);

        // Buttons
        customerSubmitBtn = findViewById(R.id.customerSubmitBtn);

        // TextViews
        formMessageField = findViewById(R.id.formMessageField);
    }

    /**
     * This method helps us to generate a dropdown list item
     */
    private void setupDropdownResource(Spinner spinner, String[] items) {
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                this, android.R.layout.simple_spinner_dropdown_item, items);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
    }
}