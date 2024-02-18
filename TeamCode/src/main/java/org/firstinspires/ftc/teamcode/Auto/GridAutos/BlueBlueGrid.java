package org.firstinspires.ftc.teamcode.Auto.GridAutos;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.geometry.Vector2d;
import com.acmerobotics.roadrunner.trajectory.Trajectory;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;
import org.firstinspires.ftc.teamcode.Auto.drive.SampleMecanumDrive;

@Autonomous(name = "BlueBlueGrid", group = "Concept")

public class BlueBlueGrid extends LinearOpMode {
    private DcMotor liftLeft;
    private DcMotor liftRight;
    private Servo servoOuttake;
    ElapsedTime et = new ElapsedTime();
    public void init (LinearOpMode opMode) throws InterruptedException {
        liftLeft = opMode.hardwareMap.dcMotor.get("liftLeft");
        liftRight = opMode.hardwareMap.dcMotor.get("liftRight");
        servoOuttake = opMode.hardwareMap.servo.get("servoOuttake");

        liftLeft.setDirection(DcMotorSimple.Direction.FORWARD);
        liftRight.setDirection(DcMotorSimple.Direction.REVERSE);

        liftLeft.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        liftRight.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
    }

    public void runOpMode() throws InterruptedException{
        SampleMecanumDrive drive = new SampleMecanumDrive(hardwareMap);
        init(this);
        Pose2d startPose = new Pose2d(-14, -60, Math.toRadians(180));
        drive.setPoseEstimate(startPose);

        waitForStart();
    }
}
