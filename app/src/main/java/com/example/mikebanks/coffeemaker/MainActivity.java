package com.example.mikebanks.coffeemaker;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.mikebanks.coffeemaker.Model.CoffeeMaker;

public class MainActivity extends AppCompatActivity {

    private TextView txtBoilerStatus;
    private TextView txtWarmerPlateStatus;
    private TextView txtPressureReliefValveStatus;
    private TextView txtPotStatus;
    private TextView txtBrewingStatus;
    private TextView txtMessage;

    private Button btnStartBrewing;
    private Button btnPot;

    private CoffeeMaker coffeeMaker;

    private View.OnClickListener clickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            if (view.getId() == btnStartBrewing.getId()) {
                startBrewing();
            } else if (view.getId() == btnPot.getId()) {
                potClick();
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setupViews();

        coffeeMaker = new CoffeeMaker();

    }

    private void setupViews() {

        txtBoilerStatus = findViewById(R.id.txt_boiler_status);
        txtWarmerPlateStatus = findViewById(R.id.txt_plate_status);
        txtPressureReliefValveStatus = findViewById(R.id.txt_valve_status);
        txtPotStatus = findViewById(R.id.txt_pot_status);
        txtBrewingStatus = findViewById(R.id.txt_brewing_status);
        txtMessage = findViewById(R.id.txt_message);

        btnStartBrewing = findViewById(R.id.btn_start_brewing);
        btnPot = findViewById(R.id.btn_pot);

        btnStartBrewing.setOnClickListener(clickListener);
        btnPot.setOnClickListener(clickListener);
    }

    private void startBrewing() {
        while (coffeeMaker.getBoiler().getBoilerSensor().getBoilerEmpty() == false) {

        }
    }

    private void potClick() {
        if (coffeeMaker.getWarmerPlate().getWarmerPlateSensor().isPotGone() == false) {
            coffeeMaker.getWarmerPlate().getWarmerPlateSensor().setPotGone(true);
            coffeeMaker.getWarmerPlate().stopWarming();

        } else {

            coffeeMaker.getWarmerPlate().getWarmerPlateSensor().setPotGone(false);
            coffeeMaker.getWarmerPlate().startWarming();
        }
    }
}
