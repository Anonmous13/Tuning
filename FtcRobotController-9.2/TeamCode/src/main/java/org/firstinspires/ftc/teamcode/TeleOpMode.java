package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
@TeleOp
public class TeleOpMode extends LinearOpMode {
    @Override
    public void runOpMode() throws InterruptedException {

        DcMotor frontLeftMotor = hardwareMap.dcMotor.get("lf_drive");
        DcMotor frontRightMotor = hardwareMap.dcMotor.get("rf_drive");
        DcMotor backLeftMotor = hardwareMap.dcMotor.get("lb_drive");
        DcMotor backRightMotor = hardwareMap.dcMotor.get("rb_drive");
        DcMotor linearSlideMotor = hardwareMap.dcMotor.get("arm1");
        DcMotor slideMovementMotor = hardwareMap.dcMotor.get("movement1");

        // Declare servo for linear slide claw
    // Servo clawServo = hardwareMap.servo.get("claw_servo");

        // Constants for motor power
        final double STOP_POWER = 0;
        final double UP_POWER = 0.2;
        final double DOWN_POWER = -0.5;


        // Set the linear slide motor to use encoders
        linearSlideMotor.setDirection(DcMotor.Direction.FORWARD);

        linearSlideMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        // Define target positions in encoder counts for different heights
        linearSlideMotor = hardwareMap.get(DcMotor.class, "arm1");

        linearSlideMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        // Define target positions in encoder counts for different heights
        final int SLIDE_LOW_POSITION = 0;       // Bottom position
        final int SLIDE_MID_POSITION = 1000;    // Mid position (adjust as needed)
        final int SLIDE_HIGH_POSITION = 2000;   // Top position (adjust as needed)
        final double SLIDE_POWER = 1.0;         // Power level for moving slide

        // Variables to control the servo position
        double clawPosition = 0.25; // Initial position, corresponds to 90 degrees
        final double CLAW_INCREMENT = 0.01; // Small increment value for precise control
        final double CLAW_MIN_POSITION = 0.25; // Corresponds to 90 degrees
        final double CLAW_MAX_POSITION = 0.75; // Corresponds to 270 degrees

        // Speed variable for motor power scaling
        double speed = 1;

        waitForStart();

        if (isStopRequested()) return;

        while (opModeIsActive()) {
            // Get joystick input for direction and rotation
            double y = -gamepad1.left_stick_y;  // Forward/backward
            double x = gamepad1.left_stick_x * 1.1;  // Strafing
            double rx = gamepad1.right_stick_x;  // Rotation

            // Calculate the denominator for normalizing motor power
            double deno = Math.max(Math.abs(y) + Math.abs(x) + Math.abs(rx), 1);

            // Calculate power for each motor
            double frontLeftPower = -(y + x + rx) / deno * speed;
            double backLeftPower = -(y - x + rx) / deno * speed;
            double frontRightPower = (y - x - rx) / deno * speed;
            double backRightPower = -(y + x - rx) / deno * speed;

            // Skibidi linear slide height with gamepad buttons
            if (gamepad1.y) {
                linearSlideMotor.setPower(UP_POWER);
            } else if (gamepad1.b) {
                linearSlideMotor.setPower(DOWN_POWER);
            } else {
                linearSlideMotor.setPower(STOP_POWER);
            }

            if(gamepad1.x)
                slideMovementMotor.setPower(UP_POWER);
            else if(gamepad1.a)
                slideMovementMotor.setPower(DOWN_POWER);
            else
                slideMovementMotor.setPower(0);
            slideMovementMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);







//            if (gamepad1.a && clawPosition < CLAW_MAX_POSITION) {
//                clawPosition += CLAW_INCREMENT; // Increase position
//            } else if (gamepad1.b && clawPosition > CLAW_MIN_POSITION) {
//                clawPosition -= CLAW_INCREMENT; // Decrease position
//            }

//            clawPosition = Math.max(Math.min(clawPosition, CLAW_MAX_POSITION), CLAW_MIN_POSITION);
//            clawServo.setPosition(clawPosition);

            // Set motor powers
            frontLeftMotor.setPower(frontLeftPower);
            backLeftMotor.setPower(backLeftPower);
            frontRightMotor.setPower(frontRightPower);
            backRightMotor.setPower(backRightPower);

            // Update telemetry for debugging
            // Display the current encoder value on telemetry
            telemetry.addData("Slide Position", linearSlideMotor.getCurrentPosition());
            telemetry.update();

            // Update telemetry for debugging
          telemetry.addData("Claw Position", clawPosition);
            telemetry.addData("Slide Target Position", linearSlideMotor.getTargetPosition());
            telemetry.addData("Slide Current Position", linearSlideMotor.getCurrentPosition());
            telemetry.update();
        }

    }
}
