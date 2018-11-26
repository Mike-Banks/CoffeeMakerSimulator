package com.example.mikebanks.coffeemaker.Model;

public class Boiler {

    private BoilerSensor boilerSensor;
    private boolean heatingPower;

    public Boiler() {
        boilerSensor = new BoilerSensor();
        heatingPower = false;
    }

    public BoilerSensor getBoilerSensor() {
        return boilerSensor;
    }

    public void startBoiling() {
        heatingPower = true;
    }

    public void stopBoiling() {
      heatingPower = false;
    }
}
