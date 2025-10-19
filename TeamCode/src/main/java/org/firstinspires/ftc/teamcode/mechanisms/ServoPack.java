package org.firstinspires.ftc.teamcode.mechanisms;

import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
// https://www.youtube.com/watch?v=K9x85yBHjpk&list=PLRHdgFNRLyaPiZ5rvINwMmGMHEIL9usla&index=12
// If necessary.
public class ServoPack {
    private Servo ServoPosition;
    private CRServo ServoRot;
    public void init(HardwareMap mp){
        ServoPosition = mp.get(Servo.class, "Servo Position");
        ServoRot = mp.get(CRServo.class, "Servo Rotation");
        // Use scaleRange to set the total possible angle.
    }
    public void setServPos(double angle){
        ServoPosition.setPosition(angle);
    }
    public void setServRot(double rotation){
        ServoRot.setPower(rotation);
    }
}
