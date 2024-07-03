package org.firstinspires.ftc.teamcode.Auto.Vision;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.util.ElapsedTime;

import java.util.Timer;

@Autonomous(name = "Vision Test", group = "Testing")
public class VisionTest extends LinearOpMode {

    HuskyVision huskey;

    @Override
    public void runOpMode() throws InterruptedException {
        huskey = new HuskyVision(this);
        int pos = huskey.redPropPos();

        telemetry.addData("Position:", pos);
        telemetry.update();

        while (!isStarted() || isStopRequested()){
            idle();
        }
    }
}
