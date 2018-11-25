package com.example.mikebanks.coffeemaker;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {


    // field vars for the text views
    private TextView txtBoilerStatus;
    private TextView txtWarmerPlateStatus;
    private TextView txtPressureReliefValveStatus;
    private TextView txtPotStatus;
    private TextView txtBrewingStatus;
    private TextView txtMessage;


    private Button btnStartBrewing;
    private Button btnPot;

    // listener for the start brewing button
    private View.OnClickListener clickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            // check if the brew btn is pressed
            if (view.getId() == btnStartBrewing.getId()) {

                // check if its not pressed
            } else if (view.getId() == btnPot.getId()) {

            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // set references to the text boxes
        txtBoilerStatus = findViewById(R.id.txt_boiler_status);
        txtWarmerPlateStatus = findViewById(R.id.txt_plate_status);
        txtPressureReliefValveStatus = findViewById(R.id.txt_valve_status);
        txtPotStatus = findViewById(R.id.txt_pot_status);
        txtBrewingStatus = findViewById(R.id.txt_brewing_status);
        txtMessage = findViewById(R.id.txt_message);

        // set references to the btns
        btnStartBrewing = findViewById(R.id.btn_start_brewing);
        btnPot = findViewById(R.id.btn_pot);

        btnStartBrewing.setOnClickListener(clickListener);
        btnPot.setOnClickListener(clickListener);

    }
}
