package org.firstinspires.ftc.teamcode.mechanisms;

import com.qualcomm.robotcore.hardware.HardwareMap;
import org.firstinspires.ftc.vision.VisionPortal;
import org.firstinspires.ftc.vision.apriltag.AprilTagDetection;
import org.firstinspires.ftc.vision.apriltag.AprilTagProcessor;
import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;

import java.util.List;

public class Vision {

    private AprilTagProcessor tagProcessor;
    private VisionPortal visionPortal;

    public Vision(HardwareMap hardwareMap, String webcamName) {
        tagProcessor = new AprilTagProcessor.Builder().build();

        visionPortal = new VisionPortal.Builder()
                .setCamera(hardwareMap.get(WebcamName.class, webcamName))
                .addProcessor(tagProcessor)
                .build();
    }

    public List<AprilTagDetection> getDetections() {
        return tagProcessor.getDetections();
    }

    public void stop() {
        if (visionPortal != null) {
            visionPortal.close();
        }
    }
}
