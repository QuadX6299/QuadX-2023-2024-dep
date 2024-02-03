package org.firstinspires.ftc.teamcode.Auto.FinalAutos;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.trajectory.Trajectory;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.teamcode.Auto.drive.SampleMecanumDrive;
// TODO - Test this one
@Autonomous(name = "BlueBlue", group = "Concept")

public class BlueBlue extends LinearOpMode {

    public void runOpMode() throws InterruptedException {
        SampleMecanumDrive drive = new SampleMecanumDrive(hardwareMap);


        Pose2d startPose = new Pose2d(-60, 36, Math.toRadians(0));

        drive.setPoseEstimate(startPose);

        Trajectory traj1 = drive.trajectoryBuilder(startPose, false)
                .forward(25)
                .build();

        Trajectory traj2 = drive.trajectoryBuilder(traj1.end().plus(new Pose2d(0, 0, Math.toRadians(90))), false)
                .forward(65)
                .build();

        waitForStart();

        drive.followTrajectory(traj1);
        drive.turn(Math.toRadians(90));
        drive.followTrajectory(traj2);
    }
}
