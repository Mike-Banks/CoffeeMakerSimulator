package com.example.mikebanks.coffeemaker.Model;

import java.util.concurrent.TimeUnit;

public class CoffeeMaker {

    //field variables for the boiler, warmerPlate and pressure Relief valve
    private Boiler boiler;
    private WarmerPlate warmerPlate;
    private PressureReliefValve pressureReliefValve;

    //constructor
    public CoffeeMaker() {
        boiler = new Boiler();
        warmerPlate = new WarmerPlate();
        pressureReliefValve = new PressureReliefValve();
    }

    //getters for the field variables
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
     */
    public String startWarming() {

        //if the pot is on the machine, and it is not empty
        if (getWarmerPlate().getWarmerPlateSensor().isPotGone() == false) {

            getPressureReliefValve().closeValve();

            //the water has now boiled, the steam is being sprayed on the grounds, coffee is dripping into the pot (pot no longer empty)
            getWarmerPlate().getWarmerPlateSensor().setPotEmpty(false);

            //the plate will now start warming, as the pot is not empty
            getWarmerPlate().startWarming();

            return "WARMING";

        } else {

            getWarmerPlate().stopWarming();
            getPressureReliefValve().openValve();

            return "NOT WARMING";

        }
    }

    public String startBoiling() {

        String returnMsg;

        //if there is water present
        if (getBoiler().getBoilerSensor().getBoilerEmpty() == false) {

            //start boiling the water
            getBoiler().startBoiling();

            //if the pot is on the machine
            if (getWarmerPlate().getWarmerPlateSensor().isPotGone() == false) {

                //close the pressure relief valve
                getPressureReliefValve().closeValve();

                returnMsg = "BOILING WITH POT";
            } else {

                getPressureReliefValve().openValve();

                returnMsg = "BOILING NO POT";
            }

            return returnMsg;

        }

        return "NOT BOILING";
    }

    /**
     * Method that determines what to do when the user interacts with the pot
     * @return a string that shows whether the brewing process is continuing or stopping
     */
    public String potInteraction() {

        //if the pot is currently on the machine
        if (getWarmerPlate().getWarmerPlateSensor().isPotGone() == false) {

            //remove the pot from the machine
            getWarmerPlate().getWarmerPlateSensor().setPotGone(true);

            //stop warming the plate
            getWarmerPlate().stopWarming();

            //open the pressure relief valve
            getPressureReliefValve().openValve();

            //return the brewing status message
            return "STOP";

        //if the pot is currently gone from the machine
        } else {

            //return the pot to the machine
            getWarmerPlate().getWarmerPlateSensor().setPotGone(false);

            //start warming the plate
            getWarmerPlate().startWarming();

            //close the pressure relief valve
            getPressureReliefValve().closeValve();

            //return the brewing status message
            return "CONTINUE";

        }
    }

}
