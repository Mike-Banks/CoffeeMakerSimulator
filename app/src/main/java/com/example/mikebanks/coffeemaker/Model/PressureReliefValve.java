package com.example.mikebanks.coffeemaker.Model;

public class PressureReliefValve {

    private boolean isOpen;

    public PressureReliefValve() {
        isOpen = true;
    }

    public boolean isValveOpen() {
        return isOpen;
    }

    public void openValve() {
        isOpen = true;
    }

    public void closeValve() {
        isOpen = false;
    }

}
