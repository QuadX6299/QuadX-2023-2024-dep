package org.firstinspires.ftc.teamcode.Auto.GridAutos;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.geometry.Vector2d;
import com.acmerobotics.roadrunner.trajectory.Trajectory;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.Auto.Vision.HuskyVision;
import org.firstinspires.ftc.teamcode.Auto.Vision.VisionTest;
import org.firstinspires.ftc.teamcode.Auto.drive.SampleMecanumDrive;
import org.firstinspires.ftc.teamcode.Auto.trajectorysequence.TrajectorySequence;

@Autonomous(name = "BlueBlueGrid", group = "Concept")

public class BlueBlueGrid extends LinearOpMode {
    private DcMotor liftLeft;
    private DcMotor liftRight;
    private Servo servoOuttake;
    private DcMotor intake;
    private CRServo outtakeWheel;

    ElapsedTime et = new ElapsedTime();
    public void init (LinearOpMode opMode) throws InterruptedException {
        liftLeft = opMode.hardwareMap.dcMotor.get("liftLeft");
        liftRight = opMode.hardwareMap.dcMotor.get("liftRight");
        servoOuttake = opMode.hardwareMap.servo.get("outtakeAngler");
        intake = opMode.hardwareMap.dcMotor.get("intakeMotor");
        outtakeWheel = opMode.hardwareMap.crservo.get("servoOuttake");

        liftLeft.setDirection(DcMotorSimple.Direction.FORWARD);
        liftRight.setDirection(DcMotorSimple.Direction.REVERSE);
        intake.setDirection(DcMotorSimple.Direction.FORWARD);

        liftLeft.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        liftRight.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        intake.setDirection(DcMotorSimple.Direction.FORWARD);
    }

    public void runOpMode() throws InterruptedException{
        SampleMecanumDrive drive = new SampleMecanumDrive(hardwareMap);
        init(this);
        HuskyVision husky = new HuskyVision(this);
        Pose2d startPose = new Pose2d(12, 60, Math.toRadians(-90));
        drive.setPoseEstimate(startPose);
        // Right Spike
        Trajectory rtraj1 = drive.trajectoryBuilder(startPose)
                .splineToConstantHeading(new Vector2d(30, 43), Math.toRadians(-90))
                .addTemporalMarker(1.5, () -> {
                    intake.setPower(-0.4);
                })
                .build();
        Trajectory rtraj2 = drive.trajectoryBuilder(rtraj1.end(), false)
                .lineToConstantHeading(new Vector2d(30, 60))
                .addDisplacementMarker(() -> {
                    intake.setPower(0);
                })
                .build();
        Trajectory rtraj3 = drive.trajectoryBuilder(rtraj2.end())
                .splineToConstantHeading(new Vector2d(62, 41), Math.toRadians(-90))
                .addDisplacementMarker(() -> {
                    servoOuttake.setPosition(0.4);
                })
                .build();
        Trajectory rtraj4 = drive.trajectoryBuilder(rtraj3.end())
                .forward(4)
                .build();

        //Middle Spike
        Trajectory midtraj1 = drive.trajectoryBuilder(startPose)
                .lineToConstantHeading(new Vector2d(12, 22))
                .addTemporalMarker(1.5, () -> {
                    intake.setPower(-0.4);
                })
                .build();
        Trajectory midtraj2 = drive.trajectoryBuilder(midtraj1.end(), false)
                .lineToConstantHeading(new Vector2d(12, 60))
                .addDisplacementMarker(() -> {
                    intake.setPower(0);
                })
                .build();
        Trajectory midtraj3 = drive.trajectoryBuilder(midtraj2.end())
                .splineToConstantHeading(new Vector2d(62, 41), Math.toRadians(-90))
                .addDisplacementMarker(() -> {
                    servoOuttake.setPosition(0.4);
                })
                .build();
        Trajectory midtraj4 = drive.trajectoryBuilder(midtraj3.end())
                .forward(8)
                .build();

        Trajectory otraj = drive.trajectoryBuilder(startPose)
                .splineToConstantHeading(new Vector2d(62, 37), Math.toRadians(-90))
                .addDisplacementMarker(() -> {
                    servoOuttake.setPosition(0.4);
                })
                .build();

        //Left Spike
        Trajectory ltraj1 = drive.trajectoryBuilder(startPose)
                .splineToConstantHeading(new Vector2d(30, 43), Math.toRadians(-90))
                .build();
        TrajectorySequence lts1 = drive.trajectorySequenceBuilder(ltraj1.end())
                .turn(Math.toRadians(-90))
                .build();
        Trajectory ltraj2 = drive.trajectoryBuilder(lts1.end())
                .lineToConstantHeading(new Vector2d(10, 21))
                .addTemporalMarker(1.5, () -> {
                    intake.setPower(-0.4);
                })
                .build();
        Trajectory ltraj3 = drive.trajectoryBuilder(ltraj2.end())
                .splineToConstantHeading(new Vector2d(47, 37), Math.toRadians(180))
                .addDisplacementMarker(() -> {
                    intake.setPower(0);
                    servoOuttake.setPosition(0.4);
                })
                .build();

        //omni
        Pose2d aPose = new Pose2d(57, 32, Math.toRadians(180));
        Trajectory traj5 = drive.trajectoryBuilder(aPose)
                .back(2)
                .addTemporalMarker(2, () -> {
                    et.reset();
                    while (et.milliseconds() < 500) {
                        liftLeft.setPower(1);
                        liftRight.setPower(1);
                    }
                })
                .build();
        Trajectory traj6 = drive.trajectoryBuilder(traj5.end())
                .back(2)
                .addDisplacementMarker(() -> {
                    servoOuttake.setPosition(0.7);
                    outtakeWheel.setPower(1);
                })
                .build();
        Trajectory traj7 = drive.trajectoryBuilder(traj6.end())
                .forward(1)
                .addDisplacementMarker(() -> {
                    servoOuttake.setPosition(0.4);
                    et.reset();
                    while (et.milliseconds() < 500) {
                        liftLeft.setPower(-1);
                        liftRight.setPower(-1);
                    }
                })
                .build();
        Trajectory traj8 = drive.trajectoryBuilder(traj7.end())
                .forward(4)
                .addDisplacementMarker(() -> {
                    outtakeWheel.setPower(0);

                })
                .build();
        Trajectory traj9 = drive.trajectoryBuilder(traj8.end())
                .strafeRight(50)
                .build();
        Trajectory traj10 = drive.trajectoryBuilder(traj9.end())
                .back(15)
                .build();

        int num = husky.bluePropPos();
        telemetry.addData("Pos", num);
        telemetry.update();

        waitForStart();

        if((num < 0) && (num > -100)) {
            drive.followTrajectory(midtraj1);
            sleep(300);
            drive.followTrajectory(midtraj2);
            drive.followTrajectory(midtraj3);
            drive.followTrajectory(midtraj4);
        } else if (num > 0) {
            drive.followTrajectory(rtraj1);
            sleep(300);
            drive.followTrajectory(rtraj2);
            drive.followTrajectory(rtraj3);
            drive.followTrajectory(rtraj4);
        } else {
            drive.followTrajectory(ltraj1);
            drive.followTrajectorySequence(lts1);
            drive.followTrajectory(ltraj2);
            drive.followTrajectory(ltraj3);
            }

        drive.followTrajectory(traj5);
        drive.followTrajectory(traj6);
        drive.followTrajectory(traj7);
        drive.followTrajectory(traj8);
        drive.followTrajectory(traj9);
        drive.followTrajectory(traj10);
    }
}