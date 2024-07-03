package org.firstinspires.ftc.teamcode.Auto.Vision;


import com.qualcomm.hardware.dfrobot.HuskyLens;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

public class HuskyVision extends LinearOpMode {
    HuskyLens huskyLens;
    private int cx = -1;

    public HuskyVision(LinearOpMode opMode) throws InterruptedException {
        huskyLens = opMode.hardwareMap.get(HuskyLens.class, "huskyLens");
    }
    public int bluePropPos(){
        huskyLens.selectAlgorithm(HuskyLens.Algorithm.COLOR_RECOGNITION);
        for (HuskyLens.Block b : huskyLens.blocks()) {
            if (b.id == 1) {
                cx = b.y;
                break;
            }
        }
        return cx;
    }

    public int redPropPos(){
        huskyLens.selectAlgorithm(HuskyLens.Algorithm.COLOR_RECOGNITION);
        for (HuskyLens.Block b : huskyLens.blocks()) {
            if (b.id == 2) {
                cx = -b.y;
                break;
            }
        }
        return cx;
    }

    @Override
    public void runOpMode() throws InterruptedException {
    }
}
