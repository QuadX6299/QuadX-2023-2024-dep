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

import org.firstinspires.ftc.teamcode.Auto.Vision.HuskyVision;
import org.firstinspires.ftc.teamcode.Auto.Vision.VisionTest;
import org.firstinspires.ftc.teamcode.Auto.drive.SampleMecanumDrive;
import org.firstinspires.ftc.teamcode.Auto.trajectorysequence.TrajectorySequence;

@Autonomous(name = "BlueRedGrid", group = "Concept")

public class BlueRedGrid extends LinearOpMode {
    private DcMotor liftLeft;
    private DcMotor liftRight;
    private Servo servoOuttake;
    private DcMotor intake;

    ElapsedTime et = new ElapsedTime();
    public void init (LinearOpMode opMode) throws InterruptedException {
        liftLeft = opMode.hardwareMap.dcMotor.get("liftLeft");
        liftRight = opMode.hardwareMap.dcMotor.get("liftRight");
        servoOuttake = opMode.hardwareMap.servo.get("servoOuttake");
        intake = opMode.hardwareMap.dcMotor.get("intakeMotor");

        liftLeft.setDirection(DcMotorSimple.Direction.FORWARD);
        liftRight.setDirection(DcMotorSimple.Direction.REVERSE);
        intake.setDirection(DcMotorSimple.Direction.FORWARD);

        liftLeft.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        liftRight.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        intake.setDirection(DcMotorSimple.Direction.FORWARD);
    }

    public void runOpMode() throws InterruptedException {
        SampleMecanumDrive drive = new SampleMecanumDrive(hardwareMap);
        init(this);
        HuskyVision husky = new HuskyVision(this);
        Pose2d startPose = new Pose2d(-12, 60, Math.toRadians(-90));
        drive.setPoseEstimate(startPose);

        Trajectory midtraj1 = drive.trajectoryBuilder(startPose)
                .lineToConstantHeading(new Vector2d(-12, 22))
                .addTemporalMarker(1.5, () -> {
                    intake.setPower(-0.4);
                })
                .build();
        Trajectory midtraj2 = drive.trajectoryBuilder(midtraj1.end(), false)
                .lineToConstantHeading(new Vector2d(-12, 60))
                .addDisplacementMarker(() -> {
                    intake.setPower(0);
                })
                .build();
        Trajectory midtraj3 = drive.trajectoryBuilder(midtraj2.end())
                .splineToConstantHeading(new Vector2d(-42, 0), Math.toRadians(-90))
                .addDisplacementMarker(() -> {
                    servoOuttake.setPosition(0.4);
                })
                .build();
        Trajectory midtraj4 = drive.trajectoryBuilder(midtraj3.end())
                .lineToConstantHeading(new Vector2d(60, 0))
                .build();

        Trajectory midtraj5 = drive.trajectoryBuilder(midtraj4.end())
                .splineToConstantHeading(new Vector2d(110,35), Math.toRadians(0))
                .addDisplacementMarker(() -> {
                    servoOuttake.setPosition(0.4);
                })
                .build();

        TrajectorySequence midtraj6 = drive.trajectorySequenceBuilder(midtraj5.end())
                .turn(Math.toRadians(90))
                .addTemporalMarker(2, () -> {
                    et.reset();
                    while (et.milliseconds() < 500) {
                        liftLeft.setPower(1);
                        liftRight.setPower(1);
                    }
                })
                .build();

        waitForStart();
        drive.followTrajectory(midtraj1);
        sleep(300);
        drive.followTrajectory(midtraj2);
        drive.followTrajectory(midtraj3);
        drive.followTrajectory(midtraj4);
        drive.followTrajectory(midtraj5);
        drive.followTrajectory(midtraj5);
        drive.followTrajectorySequence(midtraj6);

    }



}
