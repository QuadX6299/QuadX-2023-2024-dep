package org.firstinspires.ftc.teamcode.Auto.FinalAutos;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.trajectory.Trajectory;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;
import org.firstinspires.ftc.teamcode.Auto.drive.SampleMecanumDrive;

@Autonomous(name = "BlueRed", group = "Concept")

public class BlueRed extends LinearOpMode {
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
        Pose2d startPose = new Pose2d(-60, 36, Math.toRadians(0));
        drive.setPoseEstimate(startPose);
        Trajectory traj1 = drive.trajectoryBuilder(startPose, false)
                .forward(35)
                .build();
        Trajectory traj2 = drive.trajectoryBuilder(traj1.end().plus(new Pose2d(0, 0, Math.toRadians(0))), false)
                .back(10)
                .build();
        Trajectory traj3 = drive.trajectoryBuilder(traj2.end().plus(new Pose2d(0, 0, Math.toRadians(0))), false)
                .strafeLeft(30)
                .build();
        Trajectory traj4 = drive.trajectoryBuilder(traj3.end().plus(new Pose2d(0, 0, Math.toRadians(0))), false)
                .forward(34)
                .build();
        Trajectory traj5 = drive.trajectoryBuilder(traj4.end().plus(new Pose2d(0, 0, Math.toRadians(90))), false)
                .forward(100)
                .build();
        Trajectory traj6 = drive.trajectoryBuilder(traj5.end().plus(new Pose2d(0, 0, Math.toRadians(90))), false)
                .forward(35)
                .build();
        Trajectory traj7 = drive.trajectoryBuilder(traj6.end().plus(new Pose2d(0, 0, Math.toRadians(90))), false)
                .back(10.5)
                .addTemporalMarker(2, () -> {
                    et.reset();
                    while (et.milliseconds() < 50) {
                        liftLeft.setPower(0.5);
                        liftRight.setPower(0.5);
                    }
                })
                .build();
        Trajectory traj8 = drive.trajectoryBuilder(traj7.end().plus(new Pose2d(0, 0, Math.toRadians(0))), false)
                .back(1)
                .addTemporalMarker(2, () -> {
                    servoOuttake.setPosition(0.75);
                })
                .build();
        Trajectory traj9 = drive.trajectoryBuilder(traj8.end().plus(new Pose2d(0, 0, Math.toRadians(0))), false)
                .forward(5)
                .build();
        Trajectory traj10 = drive.trajectoryBuilder(traj9.end().plus(new Pose2d(0, 0, Math.toRadians(0))), false)
                .back(5)
                .build();
        Trajectory traj11 = drive.trajectoryBuilder(traj10.end().plus(new Pose2d(0, 0, Math.toRadians(0))), false)
                .forward(8)
                .addTemporalMarker(1, () -> {
                    servoOuttake.setPosition(0.4);
                })
                .build();
        Trajectory traj12 = drive.trajectoryBuilder(traj11.end().plus(new Pose2d(0, 0, Math.toRadians(0))), false)
                .forward(1)
                .addTemporalMarker(1, () -> {
                    et.reset();
                    while (et.milliseconds() < 50) {
                        liftLeft.setPower(-0.5);
                        liftRight.setPower(-0.5);
                    }
                })
                .build();
        Trajectory traj13 = drive.trajectoryBuilder(traj12.end().plus(new Pose2d(0, 0, Math.toRadians(0))), false)
                .strafeRight(15)
                .build();

        waitForStart();
        drive.followTrajectory(traj1);
        drive.followTrajectory(traj2);
        drive.followTrajectory(traj3);
        drive.followTrajectory(traj4);
        drive.followTrajectory(traj5);
        drive.followTrajectory(traj6);
        drive.followTrajectory(traj7);
        sleep(1000);
        drive.followTrajectory(traj8);
        drive.followTrajectory(traj9);
        drive.followTrajectory(traj10);
        drive.followTrajectory(traj11);
        drive.followTrajectory(traj12);
        drive.followTrajectory(traj13);
    }
}
