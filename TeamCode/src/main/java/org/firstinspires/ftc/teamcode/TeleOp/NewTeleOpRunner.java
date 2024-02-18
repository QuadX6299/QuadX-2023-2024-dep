package org.firstinspires.ftc.teamcode.TeleOp;


import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

@TeleOp(name="NewTeleOpRunner", group="Iterative OpMode")

public class NewTeleOpRunner extends NewTeleOp {
    public void loop() {
        mechMovement();
        telemetry.update();
    }
}

