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

    /**
     * Method that runs when the brewing cycle is started
     * @throws InterruptedException - this is involved when using the 'Sleep' method in the 'TimeUnit' package
     */
    public void startBrewing() throws InterruptedException {

        //if there is water present
        if (getBoiler().getBoilerSensor().getBoilerEmpty() == false) {

            //if the pot is on the machine
            if (getWarmerPlate().getWarmerPlateSensor().isPotGone() == false) {

                //start boiling the water, and close the pressure relief valve
                getBoiler().startBoiling();
                getPressureReliefValve().closeValve();

                //wait and let the water boil
                TimeUnit.SECONDS.sleep(5);

                //the water has now boiled, the steam is being sprayed on the grounds, coffee is dripping into the pot (pot no longer empty)
                getWarmerPlate().getWarmerPlateSensor().setPotEmpty(false);

                //the plate will now start warming, as the pot is not empty
                getWarmerPlate().startWarming();
            }
        }
    }

    /**
     *
     * @return
     */
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
