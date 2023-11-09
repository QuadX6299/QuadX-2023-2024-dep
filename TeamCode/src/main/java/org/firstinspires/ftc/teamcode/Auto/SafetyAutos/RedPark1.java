package org.firstinspires.ftc.teamcode.Auto.SafetyAutos;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

@Autonomous(name = "RedPark1", group = "Concept")

public class RedPark1 extends LinearOpMode {
    private LinearOpMode newOp;
    private DcMotor fl;
    private DcMotor fr;
    private DcMotor bl;
    private DcMotor br;

    public void init(LinearOpMode opMode) throws InterruptedException {
        newOp = opMode;
        fl = opMode.hardwareMap.dcMotor.get("fl");
        fr = opMode.hardwareMap.dcMotor.get("fr");
        bl = opMode.hardwareMap.dcMotor.get("bl");
        br = opMode.hardwareMap.dcMotor.get("br");

        fl.setDirection(DcMotor.Direction.FORWARD);
        fr.setDirection(DcMotor.Direction.REVERSE);
        bl.setDirection(DcMotor.Direction.FORWARD);
        br.setDirection(DcMotor.Direction.REVERSE);

        fl.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        fr.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        bl.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        br.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        fl.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        newOp.idle();
        fr.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        newOp.idle();
        bl.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        newOp.idle();
        br.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        newOp.idle();
    }

    public void forwards() {
        fl.setPower(1);
        fr.setPower(1);
        bl.setPower(1);
        br.setPower(1);
    }

    public void backwards() {
        fl.setPower(0);
        fr.setPower(1);
        bl.setPower(0);
        br.setPower(1);
    }

    public void strafeLeft() {
        fl.setPower(-1);
        fr.setPower(1);
        bl.setPower(1);
        br.setPower(-1);
    }

    public void strafeRight() {
        fl.setPower(1);
        fr.setPower(-1);
        bl.setPower(-1);
        br.setPower(1);
    }

    public void stopMotors() {
        fl.setPower(0);
        fr.setPower(0);
        bl.setPower(0);
        br.setPower(0);
    }

    public void Left() {
        fl.setPower(1);
        fr.setPower(0);
        bl.setPower(1);
        br.setPower(0);
    }

    public void Right() {
        fl.setPower(0);
        fr.setPower(1);
        bl.setPower(0);
        br.setPower(1);
    }

    @Override
    public void runOpMode() throws InterruptedException {
        init(this);

        fl.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        newOp.idle();
        fr.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        newOp.idle();
        bl.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        newOp.idle();
        br.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        newOp.idle();

        fl.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        newOp.idle();
        fr.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        newOp.idle();
        bl.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        newOp.idle();
        br.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        newOp.idle();

        waitForStart();
        forwards();
        sleep(1000);
        strafeRight();
        sleep(1500);
        stopMotors();
    }
}


