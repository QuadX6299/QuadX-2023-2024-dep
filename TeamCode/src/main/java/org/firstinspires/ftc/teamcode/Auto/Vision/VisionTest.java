package org.firstinspires.ftc.teamcode.Auto.Vision;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.util.ElapsedTime;

import java.util.Timer;

@Autonomous(name = "Vision Test", group = "Testing")
public class VisionTest extends LinearOpMode {

    HuskyVision huskey;
    private ElapsedTime timer = new ElapsedTime();

    @Override
    public void runOpMode() throws InterruptedException {
        huskey = new HuskyVision(this);
        int pos =  10;
        double currTime = 0;

        pos = huskey.propPos();
        telemetry.addData("Position:", pos);
        telemetry.update();

        while (!isStarted() || isStopRequested()){
            idle();
        }
    }
}
