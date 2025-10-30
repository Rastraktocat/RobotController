package org.firstinspires.ftc.teamcode.mechanisms;

import com.qualcomm.hardware.rev.RevHubOrientationOnRobot;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.IMU;

public class MecanumDrive

{
    private DcMotor frontLeftMotor, backLeftMotor, frontRightMotor, backRightMotor;
    private IMU imu;

    public void drive(double forward, double strafe, double rotate) {
        double frontLeftPower = forward + strafe + rotate;
        double backLeftPower = forward - strafe + rotate;
        double frontRightPower = forward - strafe - rotate;
        double backRightPower = forward + strafe - rotate;

        double maxPower = 1.0;
        // resume here: https://youtu.be/sFCO4du5IZk?list=PLRHdgFNRLyaPiZ5rvINwMmGMHEIL9usla&t=970
    }
    public void init(HardwareMap hwMap) {
        frontLeftMotor = hwMap.get(DcMotor.class, "front_left_motor");
        backLeftMotor = hwMap.get(DcMotor.class, "back_left_motor");
        frontRightMotor = hwMap.get(DcMotor.class, "front_right_motor");
        backRightMotor = hwMap.get(DcMotor.class, "back_right_motor");


        frontLeftMotor.setDirection(DcMotor.Direction.REVERSE);
        backLeftMotor.setDirection(DcMotor.Direction.REVERSE);

        frontLeftMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        backLeftMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        frontRightMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        backRightMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        //the device name might change I am not sure, but basically what we are doing
        //we are basically assigning defined motors, from the source code to a class
        // and attaching a string for a device named when making the method.


        imu = hwMap.get(IMU.class, "imu");

        // for field orientation
        RevHubOrientationOnRobot revOrientation = new RevHubOrientationOnRobot(
                // assume looking at a robot from a top-down view
                // with its 'front' at the top
                // and its 'back' at the bottom
                // 'up' refers to something facing towards you
                // while 'down' refers to something facing the ground
                RevHubOrientationOnRobot.LogoFacingDirection.UP, // 'REV' logo on control hub is facing towards you
                RevHubOrientationOnRobot.UsbFacingDirection.FORWARD // USB-C port is closer to the top
        );

        imu.initialize(new IMU.Parameters(revOrientation));








    }



}
