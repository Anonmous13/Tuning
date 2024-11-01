package org.firstinspires.ftc.teamcode;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

@TeleOp
public class TeleOpMode extends LinearOpMode {

    @Override
    public void runOpMode() throws InterruptedException {

        // Initialize motors
        DcMotor frontLeftMotor = hardwareMap.dcMotor.get("lf_drive");
        DcMotor frontRightMotor = hardwareMap.dcMotor.get("rf_drive");
        DcMotor backLeftMotor = hardwareMap.dcMotor.get("lb_drive");
        DcMotor backRightMotor = hardwareMap.dcMotor.get("rb_drive");
        DcMotor linearSlideMotor = hardwareMap.dcMotor.get("arm1");
        DcMotor slideMovementMotor = hardwareMap.dcMotor.get("movement1");

        // Initialize servo
        Servo armServo = hardwareMap.servo.get("servo_arm");

        // Power constants
        final double STOP_POWER = 0;
        final double UP_POWER = 0.2;
        final double DOWN_POWER = -0.5;

        // Servo positions
        final double SERVO_MIN_POSITION = 0.0;
        final double SERVO_MAX_POSITION = 1.0;
        double servoPosition = 0.5; // Start at midpoint
        armServo.setPosition(servoPosition); // Initialize servo position

        // Linear slide motor configuration
        linearSlideMotor.setDirection(DcMotor.Direction.FORWARD);
        linearSlideMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        linearSlideMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        // Motor power configuration
        frontLeftMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        frontRightMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        backLeftMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        backRightMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        slideMovementMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        // Slide positions
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

            // Gamepad2 - Linear Slide and Slide Movement Controls
            if (gamepad2.y) {
                linearSlideMotor.setPower(UP_POWER);
            } else if (gamepad2.b) {
                linearSlideMotor.setPower(DOWN_POWER);
            } else {
                linearSlideMotor.setPower(STOP_POWER);
            }

            if (gamepad2.x) {
                slideMovementMotor.setPower(UP_POWER);
            } else if (gamepad2.a) {
                slideMovementMotor.setPower(DOWN_POWER);
            } else {
                slideMovementMotor.setPower(0);
            }

            // Gamepad2 - Servo Controls with Larger Steps
            if (gamepad2.dpad_up) {
                servoPosition = SERVO_MIN_POSITION;  // Move to left position
            } else if (gamepad2.dpad_down) {
                servoPosition = SERVO_MAX_POSITION;  // Move to right position
            }

            armServo.setPosition(servoPosition);

            // Telemetry for debugging
            telemetry.addData("Servo Target Position", servoPosition);
            telemetry.addData("Actual Servo Position", armServo.getPosition());
            telemetry.update();

            // Ensure interrupted state clean-up if stop requested
            if (isStopRequested()) {
                linearSlideMotor.setPower(0);
                slideMovementMotor.setPower(0);
                frontLeftMotor.setPower(0);
                frontRightMotor.setPower(0);
                backLeftMotor.setPower(0);
                backRightMotor.setPower(0);
                return;
            }
        }
    }
}
