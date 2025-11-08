package org.firstinspires.ftc.teamcode;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.mechanisms.RobotActions;
import org.firstinspires.ftc.teamcode.mechanisms.Vision;

@Autonomous(name = "MyAuto", group = "Autonomous")
public class Auton extends LinearOpMode {
    private DcMotor frontLeftMotor, backLeftMotor, frontRightMotor, backRightMotor;
    private RobotActions robAct = RobotActions.Read_Tag;
    private Vision vision;
    public void runOpMode(){
        frontLeftMotor = hardwareMap.get(DcMotor.class, "front_left_motor");
        backLeftMotor = hardwareMap.get(DcMotor.class, "back_left_motor");
        frontRightMotor = hardwareMap.get(DcMotor.class, "front_right_motor");
        backRightMotor = hardwareMap.get(DcMotor.class, "back_right_motor");
        frontRightMotor.setDirection(DcMotor.Direction.REVERSE);
        backRightMotor.setDirection(DcMotor.Direction.REVERSE);
        vision = new Vision(hardwareMap, "VisionCam");
        waitForStart();
        if (opModeIsActive() && robAct == robAct.Read_Tag) {
            driveForward(1000);
            sleep(500);
            turnRight(500);
            sleep(500);
            setAllPower(0.0); // This currently is placeholder code.
            // April Tag reading is not implemented yet.
        }
        if (opModeIsActive() && robAct == robAct.Collect_points) {
            moveToShoot();
            shootMakeBetter(); // When we know about the shooting system
            // implement this.
        }
    }
    private void moveToShoot(){
        int targetPosition =
    }
    private void shootMakeBetter(){
        sleep(500);
    }
    private void driveForward(int timeMs) {
        setAllPower(0.5);
        sleep(timeMs);
        setAllPower(0.0);
    }

    private void turnRight(int timeMs) {
        frontLeftMotor.setPower(0.5);
        backLeftMotor.setPower(0.5);
        frontRightMotor.setPower(-0.5);
        backRightMotor.setPower(-0.5);
        sleep(timeMs);
        setAllPower(0.0);
    }

    private void setAllPower(double power) {
        frontLeftMotor.setPower(power);
        frontRightMotor.setPower(power);
        backLeftMotor.setPower(power);
        backRightMotor.setPower(power);
    }

}
