package org.firstinspires.ftc.teamcode.TeleOp;

import com.qualcomm.hardware.bosch.BNO055IMU;
import com.qualcomm.hardware.rev.Rev2mDistanceSensor;
import com.qualcomm.hardware.rev.RevColorSensorV3;
import com.qualcomm.hardware.rev.RevTouchSensor;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.Range;
import com.qualcomm.robotcore.util.ElapsedTime;
import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.AxesOrder;
import org.firstinspires.ftc.robotcore.external.navigation.AxesReference;
import org.firstinspires.ftc.robotcore.external.navigation.Orientation;


import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;

public abstract class CompTeleOp extends OpMode {
    private DcMotor fl;
    private DcMotor fr;
    private DcMotor bl;
    private DcMotor br;
    private Servo launcher;
    private BNO055IMU imu;
    private  RevTouchSensor TouchAgony_R;
    private  RevTouchSensor TouchTundra_L;
     private Rev2mDistanceSensor Seeker_Dist;
   //private RevColorSensorV3 Chroma_Color;

    public void init() {
        fl = hardwareMap.dcMotor.get("fl");
        fr = hardwareMap.dcMotor.get("fr");
        bl = hardwareMap.dcMotor.get("bl");
        br = hardwareMap.dcMotor.get("br");
        TouchAgony_R = (RevTouchSensor) hardwareMap.touchSensor.get("TouchAgony");
        TouchTundra_L = (RevTouchSensor) hardwareMap.touchSensor.get("TouchTundra");
        Seeker_Dist = (Rev2mDistanceSensor) hardwareMap.get("Seekers");
        imu = hardwareMap.get(BNO055IMU.class, "imu");
        //Chroma_Color = (RevColorSensorV3) hardwareMap.colorSensor.get("Chroma");

        fl.setDirection(DcMotor.Direction.FORWARD);
        fr.setDirection(DcMotor.Direction.REVERSE);
        bl.setDirection(DcMotor.Direction.FORWARD);
        br.setDirection(DcMotor.Direction.REVERSE);

        fl.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        fr.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        bl.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        br.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        BNO055IMU.Parameters parameters = new BNO055IMU.Parameters();
        parameters.angleUnit = BNO055IMU.AngleUnit.DEGREES;
        parameters.accelUnit = BNO055IMU.AccelUnit.METERS_PERSEC_PERSEC;
        parameters.calibrationDataFile = "BNO055IMUCalibration.json"; // You can create this file using the FTC Robot Controller app
        imu = hardwareMap.get(BNO055IMU.class, "imu");
        imu.initialize(parameters);
    }

    public void mechMovement() {
        float drive = gamepad1.left_stick_y;
        float turn = gamepad1.right_stick_x;
        float strafe = gamepad1.left_stick_x;

        double readings = imu.getAngularOrientation().firstAngle;
        double error = (0 - readings);

        double multiplier = (error * 0.6);

        double flPower = Range.clip(drive + (multiplier * turn) - strafe, -1.0, 1.0);
        double frPower = Range.clip(drive - (multiplier * turn) + strafe, -1.0, 1.0);
        double blPower = Range.clip(drive + (multiplier * turn) + strafe, -1.0, 1.0);
        double brPower = Range.clip(drive - (multiplier * turn) - strafe, -1.0, 1.0);

        //strafe values are caculated differently to adjust for back wheel rotations
        //being much higher than front wheels.

        if (gamepad1.right_trigger > 0.0) { //Slo-Mode drive
            flPower *= 0.5;
            frPower *= 0.5;
            blPower *= 0.5;
            brPower *= 0.5;
        }

        if (gamepad1.left_trigger > 0.0) { //SUPERHOT-Mode drive
            flPower *= 0.25;
            frPower *= 0.25;
            blPower *= 0.25;
            brPower *= 0.25;
        }

        if (TouchAgony_R.isPressed()){
            telemetry.addData("Agony(Right)",1);

        } else{
            telemetry.addData("Agony(Right)",0);
        }

        if (TouchTundra_L.isPressed()){
            telemetry.addData("Tundra(Left)",1);

        } else{
            telemetry.addData("Tundra(Left)",0);
        }

         telemetry.addData("DistanceSensor",Seeker_Dist.getDistance(DistanceUnit.CM));
        //telemetry.addData("Color_Red",Chroma_Color.red());
        //telemetry.addData("Color_Blue",Chroma_Color.blue());
        //telemetry.addData("Color_Green",Chroma_Color.green())

        fl.setPower(flPower);
        fr.setPower(frPower);
        bl.setPower(blPower);
        br.setPower(brPower);
    }
}
