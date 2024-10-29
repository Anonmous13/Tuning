package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

@TeleOp
public class TeleOpMode extends LinearOpMode {

    @Override
    public void runOpMode() throws InterruptedException {

        DcMotor frontLeftMotor = hardwareMap.dcMotor.get("lf_drive");
        DcMotor frontRightMotor = hardwareMap.dcMotor.get("rf_drive");
        DcMotor backLeftMotor = hardwareMap.dcMotor.get("lb_drive");
        DcMotor backRightMotor = hardwareMap.dcMotor.get("rb_drive");
        //DcMotor linearSlideMotor = hardwareMap.dcMotor.get("arm1");
        DcMotor slideMovementMotor = hardwareMap.dcMotor.get("movement1");

        // Initialize servo for clockwise and anti-clockwise movement
        Servo rotationServo = hardwareMap.servo.get("rotation_servo");
        double servoPosition = 0.0; // Start position at 0 degrees
        final double SERVO_INCREMENT = 0.01; // Increment for servo movement
        final double MAX_POSITION = 1.0; // Maximum position (equivalent to 180 degrees for most servos)
        final double MIN_POSITION = 0.0; // Minimum position (0 degrees)

        // Constants for motor power
        final double STOP_POWER = 0;
        final double UP_POWER = 0.2;
        final double DOWN_POWER = -0.5;

        // Configure linear slide motor
//        linearSlideMotor.setDirection(DcMotor.Direction.FORWARD);
//        linearSlideMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
//        linearSlideMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        // Define slide positions
        final int SLIDE_LOW_POSITION = 0;
        final int SLIDE_MID_POSITION = 1000;
        final int SLIDE_HIGH_POSITION = 2000;

        double speed = 1;

        waitForStart();

        if (isStopRequested()) return;

        while (opModeIsActive()) {
            // Gamepad1 - Movement Controls
            double y = -gamepad1.left_stick_y;
            double x = gamepad1.left_stick_x * 1.1;
            double rx = gamepad1.right_stick_x;
            double deno = Math.max(Math.abs(y) + Math.abs(x) + Math.abs(rx), 1);

            double frontLeftPower = -(y + x + rx) / deno * speed;
            double backLeftPower = -(y - x + rx) / deno * speed;
            double frontRightPower = (y - x - rx) / deno * speed;
            double backRightPower = -(y + x - rx) / deno * speed;

            frontLeftMotor.setPower(frontLeftPower);
            backLeftMotor.setPower(backLeftPower);
            frontRightMotor.setPower(frontRightPower);
            backRightMotor.setPower(backRightPower);

            // Gamepad2 - Non-Movement Controls for Linear Slide and Slide Movement
//            if (gamepad2.y) {
//                linearSlideMotor.setPower(UP_POWER);
//            } else if (gamepad2.b) {
//                linearSlideMotor.setPower(DOWN_POWER);
//            } else {
//                linearSlideMotor.setPower(STOP_POWER);
//            }

            if (gamepad2.x)
                slideMovementMotor.setPower(UP_POWER);
            else if (gamepad2.a)
                slideMovementMotor.setPower(DOWN_POWER);
            else
                slideMovementMotor.setPower(0);

            slideMovementMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

            // Gamepad2 - Servo Control for Clockwise and Anti-Clockwise Movement
            if (gamepad2.dpad_right && servoPosition < MAX_POSITION) {
                servoPosition += SERVO_INCREMENT; // Move clockwise Skibidi Slicers.
            } else if (gamepad2.dpad_left && servoPosition > MIN_POSITION) {
                servoPosition -= SERVO_INCREMENT; // Move anti-clockwise Skibidi Slicers.
            }

            // Ensure servo position stays within bounds
            servoPosition = Math.max(Math.min(servoPosition, MAX_POSITION), MIN_POSITION);
            rotationServo.setPosition(servoPosition);

            // Update telemetry for debugging
            telemetry.addData("Servo Position", servoPosition);
           // telemetry.addData("Slide Position", linearSlideMotor.getCurrentPosition());
            telemetry.update();
        }
    }
}