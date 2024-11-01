package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

@TeleOp
public class TeleOpMode extends LinearOpMode {

    @Override
    public void runOpMode() throws InterruptedException {

        // Is motors
        DcMotor frontLeftMotor = hardwareMap.dcMotor.get("lf_drive");
        DcMotor frontRightMotor = hardwareMap.dcMotor.get("rf_drive");
        DcMotor backLeftMotor = hardwareMap.dcMotor.get("lb_drive");
        DcMotor backRightMotor = hardwareMap.dcMotor.get("rb_drive");
        DcMotor linearSlideMotor = hardwareMap.dcMotor.get("arm1");
        DcMotor slideMovementMotor = hardwareMap.dcMotor.get("movement1");

        // Is servo
        Servo armServo = hardwareMap.servo.get("servo_arm");

        // Pow
        final double STOP_POWER = 0;
        final double UP_POWER = 0.2;
        final double DOWN_POWER = -0.5;

        // Servo positions
        final double SERVO_OPEN_POSITION = 0.2;   // Adjust this position as needed
        final double SERVO_CLOSED_POSITION = 0.8; // Adjust this position as needed

        // LSlide
        linearSlideMotor.setDirection(DcMotor.Direction.FORWARD);
        linearSlideMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        linearSlideMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        // Pos
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
            if (gamepad2.y) {
                linearSlideMotor.setPower(UP_POWER);
            } else if (gamepad2.b) {
                linearSlideMotor.setPower(DOWN_POWER);
            } else {
                linearSlideMotor.setPower(STOP_POWER);
            }

            if (gamepad2.x)
                slideMovementMotor.setPower(UP_POWER);
            else if (gamepad2.a)
                slideMovementMotor.setPower(DOWN_POWER);
            else
                slideMovementMotor.setPower(0);

            slideMovementMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

            // Gamepad2 - Servo Controls
            if (gamepad2.dpad_up) {
                armServo.setPosition(SERVO_OPEN_POSITION);
            } else if (gamepad2.dpad_down) {
                armServo.setPosition(SERVO_CLOSED_POSITION);
            }

            telemetry.addData("Slide Position", linearSlideMotor.getCurrentPosition());
            telemetry.addData("Servo Position", armServo.getPosition());
            telemetry.update();
        }
    }
}
