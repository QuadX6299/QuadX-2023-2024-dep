package org.firstinspires.ftc.teamcode.Auto.Vision;


import android.util.Size;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
import org.firstinspires.ftc.vision.VisionPortal;
import org.firstinspires.ftc.vision.apriltag.AprilTagDetection;
import org.firstinspires.ftc.vision.apriltag.AprilTagProcessor;

@TeleOp
public class OpenCV_AprilTag extends LinearOpMode {
    private static final int TARGET_TAG_ID = 2;

    @Override
    public void runOpMode() throws InterruptedException {

        AprilTagProcessor tagProcessor = new AprilTagProcessor.Builder()
                .setDrawAxes(true)
                .setDrawCubeProjection(true)
                .setDrawTagID(true)
                .setDrawTagOutline(true)
                .build();

        VisionPortal visionPortal = new VisionPortal.Builder()
                .addProcessor(tagProcessor)
                .setCamera(hardwareMap.get(WebcamName.class, "Webcam"))
                .setCameraResolution(new Size(640, 480))
                .build();

        waitForStart();

        while (!isStarted() && opModeIsActive()) {

            // Clear previous telemetry
            telemetry.clear();

            if (tagProcessor.getDetections().size() > 0) {
                // Iterate through detections and find the one with the desired tag ID
                AprilTagDetection targetTag = null;

                for (AprilTagDetection tag : tagProcessor.getDetections()) {
                    if (tag.id == TARGET_TAG_ID) {
                        targetTag = tag;
                        break; // Exit the loop once the target tag is found
                    }
                }

                if (targetTag != null) {
                    telemetry.addData("Target Tag Detected", true);
                    telemetry.addData("Tag ID", targetTag.id);
                    telemetry.addData("x", targetTag.ftcPose.x);
                    telemetry.addData("y", targetTag.ftcPose.y);
                    telemetry.addData("z", targetTag.ftcPose.z);
                    telemetry.addData("roll", targetTag.ftcPose.roll);
                    telemetry.addData("pitch", targetTag.ftcPose.pitch);
                    telemetry.addData("yaw", targetTag.ftcPose.yaw);
                } else {
                    telemetry.addLine("No matching AprilTag found");
                }
            } else {
                telemetry.addLine("No AprilTags found");
            }

            telemetry.update();
        }
    }
}
