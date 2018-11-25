package com.example.mikebanks.coffeemaker.Model;

public class Boiler {

    private BoilerSensor boilerSensor;
    private boolean heatingPower;

    public Boiler() {
        boilerSensor = new BoilerSensor();
        heatingPower = false;
    }

    public void startBoiling() {
        if (boilerSensor.getBoilerEmpty() == true) {
            heatingPower = true;
        }
    }

    public void stopBoiling() {
        if (boilerSensor.getBoilerEmpty() == false) {
            heatingPower = false;
        }
    }
}
