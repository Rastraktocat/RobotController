package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.IMU;

public class MecanumDrive

{
    private DcMotor frontleftmotor, backleftmotor, frontrightmotor, backrightmotor;
    private IMU imu;


    public void init(HardwareMap hwMap) {
        frontleftmotor = hwMap.get(DcMotor.class, "front_left_motor");
        backleftmotor = hwMap.get(DcMotor.class, "back_left_motor");
        frontrightmotor = hwMap.get(DcMotor.class, "front_right_motor");
        backrightmotor = hwMap.get(DcMotor.class, "back_right_motor");


        frontleftmotor.setDirection(DcMotor.Direction.REVERSE);
        backleftmotor.setDirection(DcMotor.Direction.REVERSE);

        frontleftmotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        backleftmotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        frontrightmotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        backrightmotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        //the device name might change I am not sure, but basically what we are doing
        //we are basically assigning defined motors, from the source code to a class
        // and attaching a string for a device named when making the method.


        imu = hwMap.get(IMU.class, "imu");




    }

    public void loop()
    {

    }



}
