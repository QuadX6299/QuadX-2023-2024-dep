package org.firstinspires.ftc.teamcode.TeleOp;

import com.qualcomm.hardware.bosch.BNO055IMU;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.Range;
import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.AxesOrder;
import org.firstinspires.ftc.robotcore.external.navigation.AxesReference;
import org.firstinspires.ftc.robotcore.external.navigation.Orientation;

public class TurnToDegree extends OpMode {
    private DcMotor fl;
    private DcMotor fr;
    private DcMotor bl;
    private DcMotor br;
    private BNO055IMU imu;
    private double targetDegree = 90.0;  // Target degree for the robot to turn to
    private double tolerance = 2.0;     // Tolerance for considering the turn complete

    @Override
    public void init() {
        // Initialize hardware devices
        fl = hardwareMap.dcMotor.get("fl");
        bl = hardwareMap.dcMotor.get("bl");
        fr = hardwareMap.dcMotor.get("fr");
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

    @Override
    public void loop() {
        // Check if the button is pressed to initiate the turn
        if (gamepad1.a) {
            // Rotate the robot to the target degree
            turnToDegree(targetDegree);
        }

        telemetry.addData("Current Heading", getHeading());
        telemetry.update();
    }

    private double getHeading() {
        // Get absolute heading from the gyro sensor
        Orientation angles = imu.getAngularOrientation(AxesReference.INTRINSIC, AxesOrder.ZYX, AngleUnit.DEGREES);
        return angles.firstAngle;
    }

    private void turnToDegree(double targetDegree) {
        double currentDegree = getHeading();
        double headingDifference = targetDegree - currentDegree;

        while (Math.abs(headingDifference) > tolerance) {
            // Adjust the motor powers based on the heading difference
            double rotatePower = Range.clip(headingDifference / 90.0, -1.0, 1.0);

            // Apply the motor powers for rotation
            fl.setPower(-rotatePower);
            bl.setPower(-rotatePower);
            fr.setPower(rotatePower);
            br.setPower(rotatePower);

            // Update the heading difference
            currentDegree = getHeading();
            headingDifference = targetDegree - currentDegree;
        }

        // Stop the robot when the turn is complete
        fl.setPower(0);
        bl.setPower(0);
        fr.setPower(0);
        br.setPower(0);
    }
}
