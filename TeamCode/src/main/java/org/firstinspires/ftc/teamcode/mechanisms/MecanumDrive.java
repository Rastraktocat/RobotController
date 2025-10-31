package org.firstinspires.ftc.teamcode.mechanisms;

import com.qualcomm.hardware.rev.RevHubOrientationOnRobot;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.IMU;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;

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

        /* the maxSpeed variable is unnecessary for comp's, but can be helpful during outreach if
        we're assisting young kids and the robot might be too fast
        double maxSpeed = 0.3; */

        maxPower = Math.max(maxPower, Math.abs(frontLeftPower));
        maxPower = Math.max(maxPower, Math.abs(backLeftPower));
        maxPower = Math.max(maxPower, Math.abs(frontRightPower));
        maxPower = Math.max(maxPower, Math.abs(backRightPower));

        frontLeftMotor.setPower(frontLeftPower / maxPower);
        backLeftMotor.setPower(backLeftPower / maxPower);
        frontRightMotor.setPower(frontRightPower / maxPower);
        backRightMotor.setPower(backRightPower / maxPower);

        // resume here: https://youtu.be/sFCO4du5IZk?list=PLRHdgFNRLyaPiZ5rvINwMmGMHEIL9usla&t=970
    }

    public void driveFieldRelative(double forward, double strafe, double rotate) {
        double theta = Math.atan2(forward, strafe);
        double r = Math.hypot(strafe, forward);

        theta = AngleUnit.normalizeRadians(theta - imu.getRobotYawPitchRollAngles().getYaw(AngleUnit.RADIANS));

        double newForward = r * Math.sin(theta);
        double newStrafe = r * Math.cos(theta);

        this.drive(newForward, newStrafe, rotate);
    }

    public void init(HardwareMap hwMap) {
        /*
        front_left_motor - port 0
        back_left_motor - port 1
        front_right_motor - port 2
        back_right_motor - port 3
         */
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
                /* assume looking at a robot from a top-down view
                with its 'front' at the top
                and its 'back' at the bottom
                'up' refers to something facing towards you
                while 'down' refers to something facing the ground */
                RevHubOrientationOnRobot.LogoFacingDirection.UP, // 'REV' logo on control hub is facing towards you
                RevHubOrientationOnRobot.UsbFacingDirection.FORWARD // USB-C port is closer to the top
        );

        imu.initialize(new IMU.Parameters(revOrientation));








    }



}
