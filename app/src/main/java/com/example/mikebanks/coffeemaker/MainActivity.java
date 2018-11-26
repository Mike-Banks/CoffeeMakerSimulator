package com.example.mikebanks.coffeemaker;

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
    private TextView txtBrewingStatus;
    private TextView txtPotMessage;
    private TextView txtMessage;
    private Button btnStartBrewing;
    private Button btnPot;

    private CoffeeMaker coffeeMaker;
    private Timer brewingTimer;

    private View.OnClickListener clickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            if (view.getId() == btnStartBrewing.getId()) {
                try {
                    startBrewing();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

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
        brewingTimer = new Timer();

    }

    private void setupViews() {

        txtBoilerStatus = findViewById(R.id.txt_boiler_status);
        txtWarmerPlateStatus = findViewById(R.id.txt_plate_status);
        txtPressureReliefValveStatus = findViewById(R.id.txt_valve_status);
        txtPotStatus = findViewById(R.id.txt_pot_status);
        txtBrewingStatus = findViewById(R.id.txt_brewing_status);
        txtPotMessage = findViewById(R.id.txt_pot_msg);
        txtMessage = findViewById(R.id.txt_message);

        btnStartBrewing = findViewById(R.id.btn_start_brewing);
        btnPot = findViewById(R.id.btn_pot);

        btnStartBrewing.setOnClickListener(clickListener);
        btnPot.setOnClickListener(clickListener);

        txtMessage.setText("CLICK THE BUTTON TO START BREWING");
    }

    /**
     *
     * @throws InterruptedException - this is involved when using the 'Sleep' method in the 'TimeUnit' package
     */
    private void startBrewing() throws InterruptedException {

        //start boiling the water
        if (coffeeMaker.startBoiling() == "BOILING WITH POT") {

            //update UI
            txtBoilerStatus.setText("BOILING");
            txtPressureReliefValveStatus.setText("CLOSED");
            txtPotStatus.setText("POT DETECTED");

            //wait and let the water boil
            TimeUnit.SECONDS.sleep(5);

            //the water is spraying on the grounds after boiling, coffee is dripping into the pot, call the warming method
            if (coffeeMaker.startWarming() == "WARMING") {

                txtWarmerPlateStatus.setText("WARMING");
                txtMessage.setText("THERE IS COFFEE IN THE POT");
            }


        } else if (coffeeMaker.startBoiling() == "BOILING NO POT") {
            //boil water, valve stays open, inform user to add the pot

            txtBoilerStatus.setText("BOILING");

            txtMessage.setText("RETURN THE POT SO THAT THE WATER CAN PROPERLY BOIL");

        } else if (coffeeMaker.startBoiling() == "NOT BOILING") {

            txtMessage.setText("NO WATER IN STRAINER. PLEASE ADD");
        }
    }

    private void potClick() {
        if (coffeeMaker.potInteraction().equals("STOP")) {
            txtWarmerPlateStatus.setText("OFF");
            txtPotStatus.setText("NO POT");
            txtPressureReliefValveStatus.setText("OPEN");
            txtPotMessage.setText("CLICK POT TO RETURN IT TO COFFEE MAKER");
            txtMessage.setText("BREWING STOPPED");

        } else if (coffeeMaker.potInteraction().equals("CONTINUE")) {
            txtWarmerPlateStatus.setText("ON");
            txtPotStatus.setText("POT DETECTED");
            txtPressureReliefValveStatus.setText("CLOSED");
            txtPotMessage.setText("CLICK POT TO REMOVE IT TO COFFEE MAKER");
            txtMessage.setText("BREWING RESUMED");
        }
    }
}
