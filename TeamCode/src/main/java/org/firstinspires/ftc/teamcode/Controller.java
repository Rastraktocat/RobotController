package org.firstinspires.ftc.teamcode;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
@Autonomous(name = "motor1")
public class Controller extends OpMode{
    // Go into configure robot on the driver hub.
    // Click new and connect to the control hub.
    // Go to motors and connect it to the right port.


    // Plug a controller into the driver hub to connect the controller.
    DcMotor motor;
    @Override
    public void init(){
        motor = hardwareMap.get(DcMotor.class, "motor1");
        // If we named the motor motor1 this would work.
    }
    @Override
    public void loop(){
        motor.setPower(1); // This is testing code. The motor should run
        // when this happens.

        // motor.setPower(gamepad1.left_trigger);
        // This assigns a being powered to left trigger.


    }
}