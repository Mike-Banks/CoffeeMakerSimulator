package com.example.mikebanks.coffeemaker.Model;

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

}
