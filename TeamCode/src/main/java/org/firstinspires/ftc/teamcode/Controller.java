package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import org.firstinspires.ftc.teamcode.mechanisms.MotorPack;
import org.firstinspires.ftc.teamcode.mechanisms.TouchSensorPack;

@TeleOp
public class Controller extends OpMode{
    // Go into configure robot on the driver hub.
    // Click new and connect to the control hub.
    // Go to motors and connect it to the right port.


    // Plug a controller into the drive
    MotorPack mp = new MotorPack();
    TouchSensorPack tsp = new TouchSensorPack();
    @Override
    public void init(){
        mp.init(hardwareMap);
        tsp.init(hardwareMap);
        // If we named the motor motor1 this would work.
    }
    @Override
    public void loop(){
        if (tsp.isTouchSensorPressed()){
            mp.setMotorSpeed(1.0); // This is testing code. The motor should run
            // when this happens.
        }
        // motor.setPower(gamepad1.left_trigger);
        // This assigns a beings powered to left trigger.


    }
}