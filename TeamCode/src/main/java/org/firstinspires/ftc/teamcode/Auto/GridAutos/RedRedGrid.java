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
import org.firstinspires.ftc.teamcode.Auto.trajectorysequence.TrajectorySequence;

@Autonomous(name = "RedRedGrid", group = "Concept")

public class RedRedGrid extends LinearOpMode {
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

    public void runOpMode() throws InterruptedException{
        SampleMecanumDrive drive = new SampleMecanumDrive(hardwareMap);
        init(this);
        Pose2d startPose = new Pose2d(12, -60, Math.toRadians(90));
        drive.setPoseEstimate(startPose);
        // Right Spike
        Trajectory traj1 = drive.trajectoryBuilder(startPose)
                .splineToConstantHeading(new Vector2d(38, -28), Math.toRadians(0))
                .addTemporalMarker(1.5, () -> {
                    intake.setPower(-0.4);
                })
                .build();
        Trajectory traj2 = drive.trajectoryBuilder(traj1.end(), false)
                .lineToConstantHeading(new Vector2d(38, -60))
                .addDisplacementMarker(() -> {
                    intake.setPower(0);
                })
                .build();
         Trajectory traj3 = drive.trajectoryBuilder(traj2.end())
                .splineToConstantHeading(new Vector2d(57, -26), Math.toRadians(0))
                .addDisplacementMarker(() -> {
                    servoOuttake.setPosition(0.4);
                })
                .build();
        TrajectorySequence ts1 = drive.trajectorySequenceBuilder(traj3.end())
                .turn(Math.toRadians(90))
                .build();

        //Middle Spike
        /*
        Trajectory traj1 = drive.trajectoryBuilder(startPose)
                .lineToConstantHeading(new Vector2d(12, -18))
                .addTemporalMarker(1.5, () -> {
                    intake.setPower(-0.4);
                })
                .build();
        Trajectory traj2 = drive.trajectoryBuilder(traj1.end(), false)
                .lineToConstantHeading(new Vector2d(12, -60))
                .addDisplacementMarker(() -> {
                    intake.setPower(0);
                })
                .build();
         Trajectory traj3 = drive.trajectoryBuilder(traj2.end())
                .splineToConstantHeading(new Vector2d(57, -26), Math.toRadians(0))
                .addDisplacementMarker(() -> {
                    servoOuttake.setPosition(0.4);
                })
                .build();
        TrajectorySequence ts1 = drive.trajectorySequenceBuilder(traj3.end())
                .turn(Math.toRadians(90))
                .build();

         */
        Trajectory traj4 = drive.trajectoryBuilder(ts1.end())
                .back(7)
                .addDisplacementMarker(() -> {
                     et.reset();
                     while (et.milliseconds() < 500) {
                         liftLeft.setPower(1);
                         liftRight.setPower(1);
                     }
                 })
                 .build();
        Trajectory traj5 = drive.trajectoryBuilder(traj4.end())
                .back(2)
                .addDisplacementMarker(() -> {
                    servoOuttake.setPosition(0.6);
                })
                .build();
        Trajectory traj6 = drive.trajectoryBuilder(traj5.end())
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
        Trajectory traj7 = drive.trajectoryBuilder(traj6.end())
                .forward(4)
                .build();
        Trajectory traj8 = drive.trajectoryBuilder(traj7.end())
                .strafeLeft(80)
                .build();
        Trajectory traj9 = drive.trajectoryBuilder(traj8.end())
                .back(15)
                .build();

        waitForStart();
        drive.followTrajectory(traj1);
        sleep(300);
        drive.followTrajectory(traj2);
        drive.followTrajectory(traj3);
        drive.followTrajectorySequence(ts1);
        drive.followTrajectory(traj4);
        drive.followTrajectory(traj5);
        drive.followTrajectory(traj6);
        drive.followTrajectory(traj7);
        drive.followTrajectory(traj8);
        drive.followTrajectory(traj9);
    }
}