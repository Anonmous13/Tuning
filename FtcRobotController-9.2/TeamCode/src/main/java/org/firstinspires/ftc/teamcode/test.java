package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
@TeleOp
public class test extends LinearOpMode {
    @Override
    public void runOpMode() throws InterruptedException {


        // Declare servo for linear slide claw
        // Servo clawServo = hardwareMap.servo.get("claw_servo");

        // Constants for motor power
        final double STOP_POWER = 0;
        final double UP_POWER = 0.2;
        final double DOWN_POWER = -0.5;

        // Initialize servo for clockwise and anti-clockwise movement
        Servo rotationServo = hardwareMap.servo.get("rotation_servo");
        double servoPosition = 0.0; // Start position at 0 degrees
        final double SERVO_INCREMENT = 0.01; // Increment for servo movement
        final double MAX_POSITION = 1.0; // Maximum position (equivalent to 180 degrees for most servos)
        final double MIN_POSITION = 0.0; // Minimum position (0 degrees)


        // Variables to control the servo position
        double clawPosition = 0.25; // Initial position, corresponds to 90 degrees


        // Speed variable for motor power scaling

        waitForStart();

        while (opModeIsActive()) {

            // Gamepad1 - Servo Control for Clockwise and Anti-Clockwise Movement
            if (gamepad1.dpad_right && servoPosition < MAX_POSITION) {
                servoPosition += SERVO_INCREMENT; // Move clockwise Skibidi Slicers.
            } else if (gamepad1.dpad_left && servoPosition > MIN_POSITION) {
                servoPosition -= SERVO_INCREMENT; // Move anti-clockwise Skibidi Slicers.
            }

            // Ensure servo position stays within bounds
            servoPosition = Math.max(Math.min(servoPosition, MAX_POSITION), MIN_POSITION);
            rotationServo.setPosition(servoPosition);
            // Update telemetry for debugging
            telemetry.addData("Servo Position", servoPosition);

            // Display the current encoder value on telemetry


            // Update telemetry for debugging

            telemetry.update();
        }
    }
    }

