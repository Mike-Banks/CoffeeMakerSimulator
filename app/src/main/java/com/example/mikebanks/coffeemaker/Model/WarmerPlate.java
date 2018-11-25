package com.example.mikebanks.coffeemaker.Model;

public class WarmerPlate {

    private WarmerPlateSensor warmerPlateSensor;
    private boolean heatingPower;

    public WarmerPlate() {
        warmerPlateSensor = new WarmerPlateSensor();
        heatingPower = false;
    }

    public WarmerPlateSensor getWarmerPlateSensor() {
        return warmerPlateSensor;
    }

    public void startWarming() {
        heatingPower = true;
    }

    public void stopWarming() {
        heatingPower = false;
    }
}
