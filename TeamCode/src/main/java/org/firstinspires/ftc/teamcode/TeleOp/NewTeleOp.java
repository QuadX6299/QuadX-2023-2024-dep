package org.firstinspires.ftc.teamcode.TeleOp;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.Range;
import com.qualcomm.robotcore.util.ElapsedTime;

public abstract class NewTeleOp extends OpMode {
    private DcMotor fl;
    private DcMotor fr;
    private DcMotor bl;
    private DcMotor br;
    private DcMotor intake;
    private DcMotor liftLeft;
    private DcMotor liftRight;
    private Servo servoOuttake;
    private Servo LEDS;
    private Servo Launcher;
    ElapsedTime time = new ElapsedTime();

    public void init() {
        fl = hardwareMap.dcMotor.get("fl");
        fr = hardwareMap.dcMotor.get("fr");
        bl = hardwareMap.dcMotor.get("bl");
        br = hardwareMap.dcMotor.get("br");
        intake = hardwareMap.dcMotor.get("intakeMotor");
        liftLeft = hardwareMap.dcMotor.get("liftLeft");
        liftRight = hardwareMap.dcMotor.get("liftRight");
        servoOuttake = hardwareMap.servo.get("servoOuttake");
        LEDS = hardwareMap.servo.get("LED");
        Launcher = hardwareMap.servo.get("Launcher");

        fl.setDirection(DcMotor.Direction.FORWARD);
        fr.setDirection(DcMotor.Direction.REVERSE);
        bl.setDirection(DcMotor.Direction.FORWARD);
        br.setDirection(DcMotor.Direction.REVERSE);
        intake.setDirection(DcMotorSimple.Direction.FORWARD);
        liftLeft.setDirection(DcMotorSimple.Direction.FORWARD);
        liftRight.setDirection(DcMotorSimple.Direction.REVERSE);

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
        float strafe = gamepad1.left_stick_x;
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

        if (gamepad2.b) { //Outtake controls
            servoOuttake.setPosition(0.4);
            telemetry.addData("Servo position: Close -", servoOuttake.getPosition());
        } else if (gamepad2.a) {
            servoOuttake.setPosition(0.75);
            telemetry.addData("Servo position: Open -", servoOuttake.getPosition());
        }


        if(gamepad2.x){
            Launcher.setPosition(0);
        }




        fl.setPower(flPower);
        fr.setPower(frPower);
        bl.setPower(blPower);
        br.setPower(brPower);
        intake.setPower(intakeSpeed);
        liftLeft.setPower(liftSpeed);
        liftRight.setPower(liftSpeed);
    }
    public void LEDFunction() {
        if (gamepad2.dpad_down){
            LEDS.setPosition(0.68); //Yellow
        }
        if(gamepad2.dpad_up){
            LEDS.setPosition(0.755); //Purple
        }
        if(gamepad2.dpad_left){
            LEDS.setPosition(0.71); //Green
        }
        if(gamepad2.dpad_right){
            LEDS.setPosition(0.7725); //White
        }
        if(gamepad2.back){
            LEDS.setPosition(0.775); //Off Position
        }

        if(gamepad2.left_stick_x > 0.5){  //White-White Pulse. (Right)
            time.reset();
            while (time.milliseconds() < 1500) {
            LEDS.setPosition(0.7725); //White
            }
            time.reset();
            while (time.milliseconds() < 500) {
            LEDS.setPosition(0.775); //Off Position
            }
            time.reset();
            while (time.milliseconds() < 1500) {
                LEDS.setPosition(0.7725); //White
            }
            time.reset();
            while (time.milliseconds() < 500) {
                LEDS.setPosition(0.775); //Off Position
            }

    }
        if(gamepad2.left_stick_x < -0.5){  //Green-White Pulse. (Left)
            time.reset();
            while (time.milliseconds() < 1500) {
                LEDS.setPosition(0.71); //Green
            }
            time.reset();
            while (time.milliseconds() < 500) {
                LEDS.setPosition(0.775); //Off Position
            }
            time.reset();
            while (time.milliseconds() < 1500) {
                LEDS.setPosition(0.7725); //White
            }
            time.reset();
            while (time.milliseconds() < 500) {
                LEDS.setPosition(0.775); //Off Position
            }

        }
        if(gamepad2.left_stick_y > 0.5){  //Purple-White Pulse. (Up)
            time.reset();
            while (time.milliseconds() < 1500) {
                LEDS.setPosition(0.71); //Green
            }
            time.reset();
            while (time.milliseconds() < 500) {
                LEDS.setPosition(0.775); //Off Position
            }
            time.reset();
            while (time.milliseconds() < 1500) {
                LEDS.setPosition(0.7725); //White
            }
            time.reset();
            while (time.milliseconds() < 500) {
                LEDS.setPosition(0.775); //Off Position
            }

        }
        if(gamepad2.left_stick_y < -0.5){  //Yellow-White Pulse (Down)
            time.reset();
            while (time.milliseconds() < 1500) {
                LEDS.setPosition(0.68); //Green
            }
            time.reset();
            while (time.milliseconds() < 500) {
                LEDS.setPosition(0.775); //Off Position
            }
            time.reset();
            while (time.milliseconds() < 1500) {
                LEDS.setPosition(0.7725); //White
            }
            time.reset();
            while (time.milliseconds() < 500) {
                LEDS.setPosition(0.775); //Off Position
            }

        }
}
}
