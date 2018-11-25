package com.example.mikebanks.coffeemaker.Model;

public class WarmerPlateSensor {

    private boolean potEmpty;
    private boolean potGone;

    public WarmerPlateSensor() {
        potEmpty = true;
        potGone = false;
    }

    public boolean isPotEmpty() {
        return potEmpty;
    }

    public void setPotVolume(boolean newPotEmpty) {
        potEmpty = newPotEmpty;
    }

    public boolean isPotGone() {
        return potGone;
    }

    public void setPotGone(boolean newPotGone) {
        potGone = newPotGone;
    }
}
