package org.firstinspires.ftc.teamcode.Auto.Vision;


import com.qualcomm.hardware.dfrobot.HuskyLens;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

public class HuskyVision extends LinearOpMode {
    HuskyLens huskyLens;
    private int cy = 0;

    public HuskyVision(LinearOpMode opMode) throws InterruptedException {
        huskyLens = opMode.hardwareMap.get(HuskyLens.class, "huskyLens");
    }
    public int bluePropPos(){
        huskyLens.selectAlgorithm(HuskyLens.Algorithm.COLOR_RECOGNITION);
            for (HuskyLens.Block b : huskyLens.blocks()) {
                if (b.id == 1) {
                    cy = b.y;
                }
                else if (b.id == 2) {
                    cy = -b.y;
                } else {
                    cy = -1;
                }
            }
//        if (cx < 85){
//            return "LEFT";
//        }
//        else if (cx > 85 && cx < 180){
//            return "MIDDLE";
//        }
//        else if (cx > 180){
//            return "RIGHT";
//        }
//        else {
//            return "null";
//        }
        return cy;
    }
    /*
    public String redPropPos(){
        for (HuskyLens.Block b : huskyLens.blocks()){
            if (b.id == 2){
                cx = b.x;
            }
            else{
                cx = -1;
            }
        }
        if (cx < 85){
            return "LEFT";
        }
        else if (cx > 85 && cx < 180){
            return "MIDDLE";
        }
        else if (cx > 180){
            return "RIGHT";
        }
        else {
            return "null";
        }
    }
    */
    @Override
    public void runOpMode() throws InterruptedException {
    }
}
