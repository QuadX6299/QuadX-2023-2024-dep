package org.firstinspires.ftc.teamcode.Auto.SafetyAutos;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

@Autonomous(name = "RedPark2", group = "Concept")

public class RedPark2 extends LinearOpMode {
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
    public void forwards(long x) {
        fl.setPower(1);
        fr.setPower(1);
        bl.setPower(1);
        br.setPower(1);
        sleep(x);
    }

    public void backwards(long x) {
        fl.setPower(-1);
        fr.setPower(-1);
        bl.setPower(-1);
        br.setPower(-1);
        sleep(x);
    }

    public void strafeLeft(long x) {
        fl.setPower(-1);
        fr.setPower(1);
        bl.setPower(1);
        br.setPower(-1);
        sleep(x);
    }

    public void strafeRight(long x) {
        fl.setPower(1);
        fr.setPower(-1);
        bl.setPower(-1);
        br.setPower(1);
        sleep(x);
    }

    public void stopMotors() {
        fl.setPower(0);
        fr.setPower(0);
        bl.setPower(0);
        br.setPower(0);
    }

    public void Left(long x) {
        fl.setPower(1);
        fr.setPower(-1);
        bl.setPower(1);
        br.setPower(-1);
        sleep(x);
    }

    public void Right(long x) {
        fl.setPower(-1);
        fr.setPower(1);
        bl.setPower(-1);
        br.setPower(1);
        sleep(x);
    }

    @Override
    public void runOpMode() throws InterruptedException {
        init(this);
        waitForStart();
        forwards(1000);
        Right(300);
        forwards(1600);
        Right(75);
        forwards(150);
        stopMotors();
    }
}

