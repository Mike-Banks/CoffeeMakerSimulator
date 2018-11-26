package com.example.mikebanks.coffeemaker;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.mikebanks.coffeemaker.Model.CoffeeMaker;

import java.util.Timer;
import java.util.concurrent.TimeUnit;

public class MainActivity extends AppCompatActivity {

    private TextView txtBoilerStatus;
    private TextView txtWarmerPlateStatus;
    private TextView txtPressureReliefValveStatus;
    private TextView txtPotStatus;
    private TextView txtBrewingIndicator;
    private TextView txtMessage;
    private Button btnStartBrewing;
    private Button btnStatus;
    private Button btnPot;

    private CoffeeMaker coffeeMaker;
    private boolean processStarted;
    private long processStartTime;
    private boolean coffeeFinished;

    private View.OnClickListener clickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            if (view.getId() == btnStartBrewing.getId()) {
                try {
                    txtMessage.setText("STARTING");
                    startBrewing();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            } else if (view.getId() == btnPot.getId()) {
                try {
                    potClick();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            } else if (view.getId() == btnStatus.getId()) {
                checkCoffeeStatus();
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setupViews();

        coffeeMaker = new CoffeeMaker();
        processStarted = false;

    }

    private void setupViews() {

        txtBoilerStatus = findViewById(R.id.txt_boiler_status);
        txtWarmerPlateStatus = findViewById(R.id.txt_plate_status);
        txtPressureReliefValveStatus = findViewById(R.id.txt_valve_status);
        txtPotStatus = findViewById(R.id.txt_pot_status);
        txtBrewingIndicator = findViewById(R.id.txt_brewing_status);
        txtMessage = findViewById(R.id.txt_message);

        btnStartBrewing = findViewById(R.id.btn_start_brewing);
        btnStatus = findViewById(R.id.btn_status);
        btnPot = findViewById(R.id.btn_pot);

        btnStartBrewing.setOnClickListener(clickListener);
        btnStatus.setOnClickListener(clickListener);
        btnPot.setOnClickListener(clickListener);

        txtMessage.setText("CLICK THE BUTTON TO START BREWING");
    }

    /**
     *
     * @throws InterruptedException - this is involved when using the 'Sleep' method in the 'TimeUnit' package
     */
    private void startBrewing() throws InterruptedException {

        processStarted = true;
        processStartTime = System.currentTimeMillis();

        String boilingResult = coffeeMaker.startBoiling();

        if (boilingResult.equals("BOILING WITH POT")) {

            //update UI
            txtBoilerStatus.setText("BOILING");
            txtPressureReliefValveStatus.setText("CLOSED");
            txtPotStatus.setText("POT DETECTED");

            //wait and let the water boil
            TimeUnit.SECONDS.sleep(3);

            //the water is spraying on the grounds after boiling, coffee is dripping into the pot, call the warming method
            if (coffeeMaker.startWarming().equals("WARMING")) {

                txtWarmerPlateStatus.setText("WARMING");
                txtMessage.setText("THERE IS COFFEE IN THE POT");
            }


        } else if (boilingResult.equals("BOILING NO POT")) {
            //boil water, valve stays open, inform user to add the pot

            txtBoilerStatus.setText("BOILING");

            txtMessage.setText("RETURN THE POT SO THAT THE WATER CAN PROPERLY BOIL");

        } else if (boilingResult.equals("NOT BOILING")) {

            txtMessage.setText("NO WATER IN STRAINER. PLEASE ADD");
        }


    }


    private void checkCoffeeStatus() {
        if (processStarted == true && coffeeMaker.getWarmerPlate().getWarmerPlateSensor().isPotGone() == false) {
            long currentTime = System.currentTimeMillis();
            long value = currentTime - processStartTime;
            if (currentTime - processStartTime > 20000) {
                coffeeFinished = true;
                txtMessage.setText("COFFEE FINISHED");

                txtBrewingIndicator.setBackgroundColor(Color.GREEN);
                txtBoilerStatus.setText("FINISHED");
                txtWarmerPlateStatus.setText("WARMING");
                txtPotStatus.setText("FULL");
                txtPressureReliefValveStatus.setText("CLOSED");
            } else {
                txtMessage.setText("COFFEE STILL BEING MADE");
            }
        } else {
            txtMessage.setText("Update Unavailable");
        }

    }

    private void potClick() throws InterruptedException {

        String result = coffeeMaker.potInteraction();

        if (result.equals("STOP")) {
            txtWarmerPlateStatus.setText("OFF");
            txtPotStatus.setText("NO POT");
            txtPressureReliefValveStatus.setText("OPEN");

            btnPot.setText("RETURN POT");
            if (processStarted == true) {
                txtMessage.setText("BREWING STOPPED");
            } else {
                txtMessage.setText("NO POT IN THE COFFEE MAKER");
            }

        } else if (result.equals("CONTINUE")) {

            if (coffeeMaker.getWarmerPlate().getWarmerPlateSensor().isPotEmpty() == false) {
                txtWarmerPlateStatus.setText("ON");
            }
            txtPotStatus.setText("POT DETECTED");
            txtPressureReliefValveStatus.setText("CLOSED");

            btnPot.setText("REMOVE POT");

            if (processStarted == true) {
                txtMessage.setText("BREWING RESUMED");
                String resumeResult = coffeeMaker.resumeBrewing();
                if (resumeResult.equals("RESUME BREWING")) {
                    txtWarmerPlateStatus.setText("WARMING");
                } else {
                    startBrewing();
                }
            } else {
                txtMessage.setText("CLICK THE BUTTON TO START BREWING");
            }
        }
    }

}
