package org.firstinspires.ftc.teamcode.Auto.FinalBackupAutos;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.trajectory.Trajectory;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.Auto.drive.SampleMecanumDrive;

@Autonomous(name = "RedRedSafety", group = "Concept")

public class BackupFinalAutosRedRed extends LinearOpMode {
    private DcMotor intake;
    ElapsedTime et = new ElapsedTime();
    public void init (LinearOpMode opMode) throws InterruptedException {
        intake = opMode.hardwareMap.dcMotor.get("intakeMotor");
        intake.setDirection(DcMotorSimple.Direction.FORWARD);
        intake.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
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
                .back(30)
                .build();
        Trajectory traj3 = drive.trajectoryBuilder(traj2.end().plus(new Pose2d(0, 0, Math.toRadians(0))), false)
                .strafeLeft(40)
                .build();

        waitForStart();
        drive.followTrajectory(traj1);
        drive.followTrajectory(traj2);
        drive.followTrajectory(traj3);
    }
}

