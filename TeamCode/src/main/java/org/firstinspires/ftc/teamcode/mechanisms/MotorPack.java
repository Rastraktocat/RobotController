package org.firstinspires.ftc.teamcode.mechanisms;


import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class MotorPack {
        private DcMotor motor;
        public void init(HardwareMap hwmp) {
            motor = hwmp.get(DcMotor.class, "motor"); // If you are having problems
            // come here first.
            motor.setMode(DcMotor.RunMode.RUN_USING_ENCODER); // Checks to make sure that the
            // motor is actually running at a certain speed. Will always try to run at whatever the
            // set speed is.

        }
        public void setMotorSpeed(double speed) {
            // can only be -1.0 to 1.0
            motor.setPower(speed);
        }
}
