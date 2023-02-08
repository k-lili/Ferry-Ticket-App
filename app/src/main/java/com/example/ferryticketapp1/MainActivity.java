package com.example.ferryticketapp1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import java.text.NumberFormat;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    EditText etPax;
    Spinner spDestination;
    TextView tvCost;
    private final float COST_LONG_BEACH = 40.0f;
    private final float COST_CATALINA_ISLAND = 34.0f;
    private int numOfPax;
    private float totalCost;
    private String destChosen;
    private int destOption;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etPax = findViewById(R.id.etPax);
        tvCost = findViewById(R.id.tvCost);
        spDestination = findViewById(R.id.spDestination);

        spDestination.setOnItemSelectedListener(this);
    }

    public void computeCost(View view) {
        String result;

        try {
            //calculate
            numOfPax = Integer.parseInt(etPax.getText().toString());

            //get cost * calculate cost
            switch (destOption) {
                case 0:
                    totalCost = numOfPax * COST_CATALINA_ISLAND;
                    break;
                case 1:
                    totalCost = numOfPax * COST_LONG_BEACH;
                    break;
            }
        } catch (NumberFormatException e) {
            tvCost.setText("Please enter number of tickets you need!");
            return; //break the loop
        }

        NumberFormat currency = NumberFormat.getCurrencyInstance();

        result = "For One Way Trip " + destChosen
                + " for " + numOfPax + ": " + currency.format(totalCost);

        tvCost.setText(result);

    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        destChosen = (String) spDestination.getItemAtPosition(i);
        destOption = i;
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}
