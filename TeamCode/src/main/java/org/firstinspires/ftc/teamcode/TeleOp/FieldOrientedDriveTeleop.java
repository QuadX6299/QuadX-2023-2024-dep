package org.firstinspires.ftc.teamcode.TeleOp;

import com.qualcomm.hardware.bosch.BNO055IMU;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.AxesOrder;
import org.firstinspires.ftc.robotcore.external.navigation.AxesReference;
import org.firstinspires.ftc.robotcore.external.navigation.Orientation;

public abstract class FieldOrientedDriveTeleop extends OpMode {
    private DcMotor fl;
    private DcMotor fr;
    private DcMotor bl;
    private DcMotor br;
    private BNO055IMU imu;
    private Orientation lastAngles = new Orientation();
    private double globalAngle;

    @Override
    public void init() {
        // Initialize hardware devices
        fl = hardwareMap.dcMotor.get("fl");
        fr = hardwareMap.dcMotor.get("fr");
        bl = hardwareMap.dcMotor.get("bl");
        br = hardwareMap.dcMotor.get("br");

        // Reverse the left motors if needed
        fl.setDirection(DcMotor.Direction.FORWARD);
        fr.setDirection(DcMotor.Direction.REVERSE);
        bl.setDirection(DcMotor.Direction.FORWARD);
        br.setDirection(DcMotor.Direction.REVERSE);

        // Initialize the gyro sensor
        BNO055IMU.Parameters parameters = new BNO055IMU.Parameters();
        parameters.angleUnit = BNO055IMU.AngleUnit.DEGREES;
        parameters.accelUnit = BNO055IMU.AccelUnit.METERS_PERSEC_PERSEC;
        parameters.calibrationDataFile = "BNO055IMUCalibration.json"; // You can create this file using the FTC Robot Controller app
        imu = hardwareMap.get(BNO055IMU.class, "imu");
        imu.initialize(parameters);
    }

    public void gyroMovement() {
        // Read gyro sensor data
        double heading = getHeading();

        // Get joystick inputs
        double drive = -gamepad1.left_stick_y; // Negative because the joystick is reversed
        double strafe = gamepad1.left_stick_x;
        double rotate = gamepad1.right_stick_x;

        // Convert joystick inputs to field-oriented drive
        double angle = Math.toRadians(heading);
        double temp = drive * Math.cos(angle) + strafe * Math.sin(angle);
        strafe = -drive * Math.sin(angle) + strafe * Math.cos(angle);
        drive = temp;

        // Calculate motor powers
        double lf = drive + strafe + rotate;
        double lb = drive - strafe + rotate;
        double rf = drive - strafe - rotate;
        double rb = drive + strafe - rotate;

        // Scale motor powers to maintain proportions and prevent exceeding motor limits
        double maxPower = Math.max(1.0, Math.max(Math.abs(lf), Math.max(Math.abs(lb), Math.max(Math.abs(rf), Math.abs(rb)))));
        lf /= maxPower;
        lb /= maxPower;
        rf /= maxPower;
        rb /= maxPower;

        // Set motor powers
        fl.setPower(lf);
        bl.setPower(lb);
        fr.setPower(rf);
        br.setPower(rb);
    }

    private double getHeading() {
        // Get absolute heading from the gyro sensor
        Orientation angles = imu.getAngularOrientation(AxesReference.INTRINSIC, AxesOrder.ZYX, AngleUnit.DEGREES);
        double deltaAngle = angles.firstAngle - lastAngles.firstAngle;

        // Keep the angle between -180 and 180 degrees
        if (deltaAngle < -180)
            deltaAngle += 360;
        else if (deltaAngle > 180)
            deltaAngle -= 360;

        globalAngle += deltaAngle;
        lastAngles = angles;

        return globalAngle;
    }
}
