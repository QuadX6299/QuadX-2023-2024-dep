//This is the testing class for the LED lights on the robot.


package org.firstinspires.ftc.teamcode.TeleOp;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.LED;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.Range;

public abstract class LEDTesting extends OpMode {
    private Servo LEDS;

    public void init(){
        LEDS = hardwareMap.servo.get("LED");
    }

    public void LEDFunction(){
        if (gamepad1.dpad_down){
            LEDS.setPosition(0.68); //Yellow
        }
        if(gamepad1.dpad_up){
            LEDS.setPosition(0.755); //Purple
        }
        if(gamepad1.dpad_left){
            LEDS.setPosition(0.71); //Green
        }
        if(gamepad1.dpad_right){
            LEDS.setPosition(0.7725); //White
        }

}


}
