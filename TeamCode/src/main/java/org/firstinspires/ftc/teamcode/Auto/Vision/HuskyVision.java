package org.firstinspires.ftc.teamcode.Auto.Vision;


import com.qualcomm.hardware.dfrobot.HuskyLens;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

public class HuskyVision extends LinearOpMode {
    HuskyLens huskyLens;
    private int ca = 0;

    public HuskyVision(LinearOpMode opMode) throws InterruptedException {
        huskyLens = opMode.hardwareMap.get(HuskyLens.class, "huskyLens");
    }
    public int propPos(){
        huskyLens.selectAlgorithm(HuskyLens.Algorithm.COLOR_RECOGNITION);
        for (HuskyLens.Block b : huskyLens.blocks()) {
            if (b.id == 1) {
                ca = b.y;
            } else if (b.id == 2) {
                ca = b.y * -1;
            } else {
                ca = -1;
            }
        }
        return ca;
    }

    @Override
    public void runOpMode() throws InterruptedException {
    }
}
