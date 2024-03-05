package org.firstinspires.ftc.teamcode.TeleOp;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.Range;
import com.qualcomm.robotcore.util.ElapsedTime;

public abstract class CompTeleOp extends OpMode {
    private DcMotor fl;
    private DcMotor fr;
    private DcMotor bl;
    private DcMotor br;
    private DcMotor intake;
    private DcMotor liftLeft;
    private DcMotor liftRight;
    private CRServo servoOuttake;
    private Servo outtakeAngler;
    private Servo LEDS;
    private Servo launcher;

    private ElapsedTime et = new ElapsedTime();


    public void init() {
        fl = hardwareMap.dcMotor.get("fl");
        fr = hardwareMap.dcMotor.get("fr");
        bl = hardwareMap.dcMotor.get("bl");
        br = hardwareMap.dcMotor.get("br");
        intake = hardwareMap.dcMotor.get("intakeMotor");
        liftLeft = hardwareMap.dcMotor.get("liftLeft");
        liftRight = hardwareMap.dcMotor.get("liftRight");
        servoOuttake = hardwareMap.crservo.get("servoOuttake");
        outtakeAngler = hardwareMap.servo.get("outtakeAngler");
        LEDS = hardwareMap.servo.get("LED");
        launcher = hardwareMap.servo.get("Launcher");
        //counterWeight = hardwareMap.servo.get("weight");

        fl.setDirection(DcMotor.Direction.FORWARD);
        fr.setDirection(DcMotor.Direction.REVERSE);
        bl.setDirection(DcMotor.Direction.FORWARD);
        br.setDirection(DcMotor.Direction.REVERSE);
        intake.setDirection(DcMotorSimple.Direction.FORWARD);
        liftLeft.setDirection(DcMotorSimple.Direction.FORWARD);
        liftRight.setDirection(DcMotorSimple.Direction.REVERSE);
        servoOuttake.setDirection(CRServo.Direction.FORWARD);

        fl.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        fr.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        bl.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        br.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        intake.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        liftLeft.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        liftRight.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
    }

    public void mechMovement() {
        float drive = gamepad1.left_stick_y;
        float turn = -gamepad1.right_stick_x;
        float strafe = -gamepad1.left_stick_x;
        float intakeSpeed;
        double liftSpeed;

        double flPower = Range.clip(drive + turn + strafe, -1.0, 1.0);
        double frPower = Range.clip(drive - turn - strafe, -1.0, 1.0);
        double blPower = Range.clip(drive + turn - strafe, -1.0, 1.0);
        double brPower = Range.clip(drive - turn + strafe, -1.0, 1.0);

        if (gamepad1.right_trigger > 0.0) { //Slo-Mode drive
            flPower *= 0.5;
            frPower *= 0.5;
            blPower *= 0.5;
            brPower *= 0.5;
        }

        if (gamepad1.left_trigger > 0.0) { //SUPERHOT-Mode drive
            flPower *= 0.25;
            frPower *= 0.25;
            blPower *= 0.25;
            brPower *= 0.25;
        }

        if (gamepad2.right_trigger > 0.0) { //Lift Controls
            liftSpeed = 1;
        } else if (gamepad2.left_trigger > 0.0) {
            liftSpeed = -1;
        } else {
            liftSpeed = 0;
        }

        if (gamepad2.right_bumper) { //Intake Controls
            intakeSpeed = 1;
        } else if (gamepad2.left_bumper) {
            intakeSpeed = -1;
        } else {
            intakeSpeed = 0;
        }

        if(gamepad1.x){
            launcher.setPosition(1);
        } else if (gamepad1.y){
            launcher.setPosition(0);
        }

        if (gamepad2.x) { //Outtake controls
            outtakeAngler.setPosition(0.78);
        } else {
            outtakeAngler.setPosition(0.95);
        }

        if (gamepad2.a) {
            et.reset();
            while (et.milliseconds() < 500) {
                servoOuttake.setPower(0);
            }
        } else if (gamepad2.b) {
            et.reset();
            while (et.milliseconds() < 1000) {
                servoOuttake.setPower(-1);
            }
        } else if (gamepad2.x){
            et.reset();
            while (et.milliseconds() < 1000) {
                servoOuttake.setPower(1);
            }
        }


        fl.setPower(flPower);
        fr.setPower(frPower);
        bl.setPower(blPower);
        br.setPower(brPower);
        intake.setPower(intakeSpeed);
        liftLeft.setPower(liftSpeed);
        liftRight.setPower(liftSpeed);
    }
}
