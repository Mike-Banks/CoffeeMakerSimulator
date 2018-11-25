package com.example.mikebanks.coffeemaker.Model;

import java.util.concurrent.TimeUnit;

public class CoffeeMaker {

    private Boiler boiler;
    private WarmerPlate warmerPlate;
    private PressureReliefValve pressureReliefValve;

    public CoffeeMaker() {
        boiler = new Boiler();
        warmerPlate = new WarmerPlate();
        pressureReliefValve = new PressureReliefValve();
    }

    public Boiler getBoiler() {
        return boiler;
    }

    public WarmerPlate getWarmerPlate() {
        return warmerPlate;
    }

    public PressureReliefValve getPressureReliefValve() {
        return pressureReliefValve;
    }

    public void startBrewing() throws InterruptedException {
        while (getBoiler().getBoilerSensor().getBoilerEmpty() == false) {
            getBoiler().startBoiling();
            getPressureReliefValve().closeValve();

            TimeUnit.SECONDS.sleep(5);
            getWarmerPlate().startWarming();
        }
    }

    public String potInteraction() {
        if (getWarmerPlate().getWarmerPlateSensor().isPotGone() == false) {
            getWarmerPlate().getWarmerPlateSensor().setPotGone(true);
            getWarmerPlate().stopWarming();
            getPressureReliefValve().openValve();
            return "CONTINUE";

        } else {

            getWarmerPlate().getWarmerPlateSensor().setPotGone(false);
            getWarmerPlate().startWarming();
            getPressureReliefValve().closeValve();
            return "STOP";

        }
    }

}
