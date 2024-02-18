package org.firstinspires.ftc.teamcode.TeleOp;


import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

@TeleOp(name="TestTeleOpRunner", group="Iterative OpMode")

public class TestTeleOpRunner extends TestTeleOp {
    public void loop() {
        testMovement();
        telemetry.update();
    }
}

