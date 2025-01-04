//package org.firstinspires.ftc.teamcode;
//
//import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
//import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
//import com.qualcomm.robotcore.hardware.DcMotor;
////import com.qualcomm.robotcore.hardware.CRServo;
//import com.qualcomm.robotcore.hardware.DcMotorSimple;
//import com.qualcomm.robotcore.util.Range;
//
//@TeleOp(name = "testing")
//public class linkage extends LinearOpMode
//{
//    @Override
//    public void runOpMode() throws InterruptedException
//    {
//        // Initialize drive motors
////        =
//
//        // Initialize linear slide and slide movement motors
//        DcMotor lr = hardwareMap.get(DcMotor.class, "lr");
//
//
//
//        // Configure motor behaviors
//        lr.setDirection(DcMotor.Direction.FORWARD);
//        lr.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
//        lr.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
//
//        double speed = 0.01;
//
//        waitForStart();
//
//        if (isStopRequested()) return;
//
//        while (opModeIsActive()) {
//
//            //Set different powers IDK.
//            // Gamepad2 - Joystick Control for motor (if needed). Holy Skibidi
//            if (Math.abs(gamepad2.right_stick_y) > 0.1) {
//                // Right joystick (gamepad2.right_stick_y) controls motor speed. To Skibidi or Not?
//                double rightJoystickPower = -gamepad2.right_stick_y; // Inverted for correct direction
//
//            }
//
//            if (Math.abs(gamepad2.left_stick_y) > 0.1) {
//                // Left joystick (gamepad2.left_stick_y) controls another motor speed (if necessary)
//                double leftJoystickPower = -gamepad2.left_stick_y; // Inverted for correct direction
//                // If you have another motor, you can control it here
//                // For example:
//                // motor2.setPower(Range.clip(leftJoystickPower, -1.0, 1.0));
//            }
//
//
//            // Deadzone threshold for joystick input.
//            final double DEADZONE_THRESHOLD = 0.01;  // Ignore small joystick movements
//
//            // Control right motor with gamepad2 right joystick
//            if (Math.abs(gamepad2.right_stick_y) > DEADZONE_THRESHOLD) {
//                lr.setPower(-gamepad2.right_stick_y);  // Invert if necessary for correct direction
//            }
//
//            else
//            {
//                lr.setPower(0);  // Stop motor if joystick is within deadzone
//            }
//
//            // Control linear slide (lr) using left joystick (gamepad1.left_stick_y)
//            if (Math.abs(gamepad1.left_stick_y) > DEADZONE_THRESHOLD) {
//                // If joystick is outside the deadzone, move the linear slide
//                lr.setPower(-gamepad1.left_stick_y);  // Invert if necessary for correct direction
//            } else {
//                // If joystick is within the deadzone, stop the linear slide
//                lr.setPower(0);
//            }
//
//            // Control left motor with gamepad2 left joystick (if needed)
//            if (Math.abs(gamepad2.left_stick_y) > DEADZONE_THRESHOLD) {
//                // motor2.setPower(-gamepad2.left_stick_y);  // Uncomment for second motor
//            }
//
//            else
//            {
//                // motor2.setPower(0);  // Stop motor if joystick is within deadzone
//            }
//
//
//            // Ensure interrupted state clean-up if stop requested
//            if (isStopRequested())
//            {
//                lr.setPower(0);
//                return;
//            }
//        }
//    }
//}
//
////Yes. 💀💀

package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.Range;

@TeleOp(name = "testing")
public class linkage extends LinearOpMode
{
    @Override
    public void runOpMode() throws InterruptedException
    {
        // Initialize linear slide motor
        DcMotor lr = hardwareMap.get(DcMotor.class, "lr");

        // Configure motor behaviors
        lr.setDirection(DcMotor.Direction.FORWARD);
        lr.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        lr.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        // Deadzone threshold for joystick input
        final double DEADZONE_THRESHOLD = 0.1;

        waitForStart();
        //dont fucking laugh or anything but we swapped techo and our hub batteries

        if (isStopRequested()) return;

        while (opModeIsActive()) {
            // Get joystick inputs
            double leftStickY1 = -gamepad1.left_stick_y; // Invert for correct direction
            double rightStickY2 = -gamepad2.right_stick_y; // Invert for correct direction

            // Apply deadzones
            leftStickY1 = Math.abs(leftStickY1) > DEADZONE_THRESHOLD ? leftStickY1 : 0;
            rightStickY2 = Math.abs(rightStickY2) > DEADZONE_THRESHOLD ? rightStickY2 : 0;

            // Merge inputs or prioritize one over the other
            double motorPower = 0;

            if (Math.abs(leftStickY1) > 0)
            {
                motorPower = leftStickY1; // Prioritize gamepad1 control
            }

            else if (Math.abs(rightStickY2) > 0)
            {
                motorPower = rightStickY2; // Use gamepad2 control if gamepad1 is not active
            }

            // Set motor power
            lr.setPower(motorPower);

            // Stop motor if op mode is stopped
            if (isStopRequested())
            {
                lr.setPower(0);
                return;
            }
        }
    }
}

