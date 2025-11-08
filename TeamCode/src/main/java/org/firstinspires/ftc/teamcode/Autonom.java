package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.teamcode.mechanisms.RobotActions;
import org.firstinspires.ftc.teamcode.mechanisms.Vision;

@Autonomous
public class Autonom extends LinearOpMode {

    // Drive motors
    private DcMotor frontLeftMotor = null;
    private DcMotor backLeftMotor = null;
    private DcMotor frontRightMotor = null;
    private DcMotor backRightMotor = null;

    // Encoder constants
    static final double COUNTS_PER_MOTOR_REV = 537.7; // goBILDA 312 RPM motors
    static final double DRIVE_GEAR_REDUCTION = 1.0;   // 1:1 gearing
    static final double WHEEL_DIAMETER_INCHES = 3.78; // wheel diameter
    static final double COUNTS_PER_INCH = (COUNTS_PER_MOTOR_REV * DRIVE_GEAR_REDUCTION) /
            (WHEEL_DIAMETER_INCHES * Math.PI);

    private RobotActions robAct = RobotActions.Read_Tag;

    private Vision vision;

    @Override
    public void runOpMode() {

        // Initialize hardware
        frontLeftMotor = hardwareMap.get(DcMotor.class, "front_left_motor");
        backLeftMotor  = hardwareMap.get(DcMotor.class, "back_left_motor");
        frontRightMotor = hardwareMap.get(DcMotor.class, "front_right_motor");
        backRightMotor  = hardwareMap.get(DcMotor.class, "back_right_motor");

        // Match directions with TeleOp
        frontLeftMotor.setDirection(DcMotor.Direction.REVERSE);
        backLeftMotor.setDirection(DcMotor.Direction.REVERSE);
        frontRightMotor.setDirection(DcMotor.Direction.FORWARD);
        backRightMotor.setDirection(DcMotor.Direction.FORWARD);

        // Reset encoders before starting
        frontLeftMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        backLeftMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        frontRightMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        backRightMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        // Display initialization status
        telemetry.addData("Status", "Initialized and Ready");
        telemetry.update();

        vision = new Vision(hardwareMap, "VisionCam");

        // Wait for the start signal
        waitForStart();

        if (opModeIsActive() && robAct == robAct.Read_Tag) { // This is the code for
            // running to the qr code. It currently doesn't read the qr code.

            // Drive forward 24 inches at 40% power
            encoderDrive(0.4, 24, 24, 5.0);
            // I don't know where the qr code is so I haven't implemented anything yet.
        }
        if (opModeIsActive() && robAct == robAct.Collect_points) { // This starts collecting
            // points when it knows the pattern. It currently doesn't work.
        }
    }

    /**
     * Drives forward or backward using encoders for precise movement.
     */
    public void encoderDrive(double speed, double leftInches, double rightInches, double timeoutS) {
        int newFrontLeftTarget;
        int newFrontRightTarget;
        int newBackLeftTarget;
        int newBackRightTarget;

        // Calculate target positions
        newFrontLeftTarget = frontLeftMotor.getCurrentPosition() + (int)(leftInches * COUNTS_PER_INCH);
        newFrontRightTarget = frontRightMotor.getCurrentPosition() + (int)(rightInches * COUNTS_PER_INCH);
        newBackLeftTarget = backLeftMotor.getCurrentPosition() + (int)(leftInches * COUNTS_PER_INCH);
        newBackRightTarget = backRightMotor.getCurrentPosition() + (int)(rightInches * COUNTS_PER_INCH);

        // Set motor target positions
        frontLeftMotor.setTargetPosition(newFrontLeftTarget);
        frontRightMotor.setTargetPosition(newFrontRightTarget);
        backLeftMotor.setTargetPosition(newBackLeftTarget);
        backRightMotor.setTargetPosition(newBackRightTarget);

        // Run to those positions
        frontLeftMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        frontRightMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        backLeftMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        backRightMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        // Start motion
        frontLeftMotor.setPower(Math.abs(speed));
        frontRightMotor.setPower(Math.abs(speed));
        backLeftMotor.setPower(Math.abs(speed));
        backRightMotor.setPower(Math.abs(speed));

        double startTime = getRuntime();

        // Wait until movement finishes or timeout occurs
        while (opModeIsActive() &&
                (getRuntime() - startTime < timeoutS) &&
                (frontLeftMotor.isBusy() && frontRightMotor.isBusy() &&
                        backLeftMotor.isBusy() && backRightMotor.isBusy())) {
            telemetry.addData("Status", "Moving Forward");
            telemetry.addData("Target (FL/FR)", "%d / %d", newFrontLeftTarget, newFrontRightTarget);
            telemetry.update();
        }

        // Stop all motors
        frontLeftMotor.setPower(0);
        frontRightMotor.setPower(0);
        backLeftMotor.setPower(0);
        backRightMotor.setPower(0);

        // Return motors to normal mode
        frontLeftMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        frontRightMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        backLeftMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        backRightMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
    }
}
