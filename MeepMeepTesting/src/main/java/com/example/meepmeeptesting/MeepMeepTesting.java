package com.example.meepmeeptesting;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.geometry.Vector2d;
import com.noahbres.meepmeep.MeepMeep;
import com.noahbres.meepmeep.roadrunner.DefaultBotBuilder;
import com.noahbres.meepmeep.roadrunner.entity.RoadRunnerBotEntity;

public class MeepMeepTesting {
    public static void main(String[] args) {
        MeepMeep meepMeep = new MeepMeep(800);
        RoadRunnerBotEntity myBot = new DefaultBotBuilder(meepMeep)
                .setDimensions(18, 19)
                .setConstraints(60, 60, Math.toRadians(180), Math.toRadians(180), 20)
                .followTrajectorySequence(drive ->

                        /*
                        //BluePark1
                        drive.trajectorySequenceBuilder(new Pose2d(-35, 60.5, Math.toRadians(-90)))
                                        .forward(49)
                                        .strafeLeft(95)
                                        .build()
                        */

                        /*
                        //RedPark1
                        drive.trajectorySequenceBuilder(new Pose2d(-35, -60.5, Math.toRadians(90)))
                                .forward(49)
                                .strafeRight(95)
                                .build()
                         */

                        /*
                        //BluePark2
                        drive.trajectorySequenceBuilder(new Pose2d(12, 60.5, Math.toRadians(-90)))
                                .strafeLeft(48)
                                .build()
                        */

                        /*
                        //RedPark2
                        drive.trajectorySequenceBuilder(new Pose2d(12, -60.5, Math.toRadians(90)))
                                .strafeRight(48)
                                .build()
                        */

                        //BlueBackboard1
                        drive.trajectorySequenceBuilder(new Pose2d(-35, 60.5, Math.toRadians(-90)))
                                .forward(49)
                                .strafeLeft(50)
                                .splineToLinearHeading(new Pose2d(40, 35, Math.toRadians(180)), Math.toRadians(0))
                                .back(9)
                                .build()

                        /*
                        //RedBackboard1
                        drive.trajectorySequenceBuilder(new Pose2d(-35, -60.5, Math.toRadians(90)))
                                .forward(49)
                                .strafeRight(50)
                                .splineToLinearHeading(new Pose2d(40, -35, Math.toRadians(180)), Math.toRadians(0))
                                .back(9)
                                .build()
                         */
                );

        meepMeep.setBackground(MeepMeep.Background.FIELD_CENTERSTAGE_OFFICIAL)
                .setDarkMode(true)
                .setBackgroundAlpha(0.95f)
                .addEntity(myBot)
                .start();
    }
}