package org.firstinspires.ftc.teamcode.mechanisms;

import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;

import com.qualcomm.robotcore.hardware.DistanceSensor;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class DistanceSensorPack {
    private DistanceSensor distSens;
    public void init(HardwareMap mp){
        distSens = mp.get(DistanceSensor.class, "Sensor distance");
    }
    public double getDistance(){
        return distSens.getDistance(DistanceUnit.CM); // In centimeters.
    }
}
