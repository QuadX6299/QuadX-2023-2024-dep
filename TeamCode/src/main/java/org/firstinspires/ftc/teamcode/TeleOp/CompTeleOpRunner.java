package org.firstinspires.ftc.teamcode.TeleOp;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

@TeleOp(name="CompTeleOpRunner", group="Iterative OpMode")

public class CompTeleOpRunner extends CompTeleOp {
    public void loop() {
        mechMovement();
        telemetry.update();
    }
}

