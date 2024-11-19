
//package org.firstinspires.ftc.teamcode;
//
//import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
//import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
//import com.qualcomm.robotcore.hardware.DcMotor;
////import com.qualcomm.robotcore.hardware.DcMotorEx;
//
//@TeleOp
//public class TeleOpMode extends LinearOpMode {
//
//    @Override
//    public void runOpMode() throws InterruptedException {
//
//        // Initialize drive motors
//        DcMotor frontRightMotor = hardwareMap.dcMotor.get("rf_drive"); //0
//        DcMotor backRightMotor = hardwareMap.dcMotor.get("rb_drive"); //1
//        DcMotor frontLeftMotor = hardwareMap.dcMotor.get("lf_drive"); //2
//        DcMotor backLeftMotor = hardwareMap.dcMotor.get("lb_drive"); //3
//
//        // Initialize Expansion Hub motors
////        DcMotorEx linearSlideMotor = hardwareMap.get(DcMotorEx.class, "arm1");
////        DcMotorEx slideMovementMotor = hardwareMap.get(DcMotorEx.class, "movement1");
//
//        // Power constants
//        final double STOP_POWER = 0;
//        final double UP_POWER = 0.2;
//        final double DOWN_POWER = -0.5;
//
//        // Linear slide motor configuration
////        linearSlideMotor.setDirection(DcMotor.Direction.FORWARD);
////        linearSlideMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
////        linearSlideMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
//
//        // Motor power configuration
//        frontLeftMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
//        frontRightMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
//        backLeftMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
//        backRightMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
//        //slideMovementMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
//
//        double speed = 0.5;
//
//        waitForStart();
//
//        if (isStopRequested()) return;
//
//        while (opModeIsActive()) {
//            // Gamepad1 - Movement Controls
//            double y = -gamepad1.left_stick_y;
//            double x = gamepad1.left_stick_x * 1.1;
//            double rx = gamepad1.right_stick_x;
//            double deno = Math.max(Math.abs(y) + Math.abs(x) + Math.abs(rx), 1);
//
//            double frontLeftPower = -(y + x + rx) / deno * speed;
//            double backLeftPower = -(y - x + rx) / deno * speed;
//            double frontRightPower = (y - x - rx) / deno * speed;
//            double backRightPower = -(y + x - rx) / deno * speed;
//
//            frontLeftMotor.setPower(frontLeftPower);
//            backLeftMotor.setPower(backLeftPower);
//            frontRightMotor.setPower(frontRightPower);
//            backRightMotor.setPower(backRightPower);
//
//            // Gamepad2 - Linear Slide and Slide Movement Controls
////            if (gamepad2.y) {
////                linearSlideMotor.setPower(UP_POWER);
////            } else if (gamepad2.b) {
////                linearSlideMotor.setPower(DOWN_POWER);
////            } else {
////                linearSlideMotor.setPower(STOP_POWER);
////            }
////
////            if (gamepad2.x) {
////                slideMovementMotor.setPower(UP_POWER);
////            } else if (gamepad2.a) {
////                slideMovementMotor.setPower(DOWN_POWER);
////            } else {
////                slideMovementMotor.setPower(0);
////            }
//
//            // Ensure interrupted state clean-up if stop requested
//            if (isStopRequested()) {
////                linearSlideMotor.setPower(0);
////                slideMovementMotor.setPower(0);
//                frontLeftMotor.setPower(0);
//                frontRightMotor.setPower(0);
//                backLeftMotor.setPower(0);
//                backRightMotor.setPower(0);
//                return;
//            }
//        }
//    }
//}
//

/*
TeleOP: Both arm and linear slide, and movement, code continuous servo and can spin clockwise for:
One bind, and counter clockwise for another bind.
Code servo for: Continuous servo.
*/
//package org.firstinspires.ftc.teamcode;
//
//import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
//import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
//import com.qualcomm.robotcore.hardware.DcMotor;
//import com.qualcomm.robotcore.hardware.CRServo;
//
//@TeleOp
//public class TeleOpMode extends LinearOpMode {
//
//    @Override
//    public void runOpMode() throws InterruptedException {
//
//        // Initialize drive motors
//        DcMotor frontRightMotor = hardwareMap.dcMotor.get("rf_drive");
//        DcMotor backRightMotor = hardwareMap.dcMotor.get("rb_drive");
//        DcMotor frontLeftMotor = hardwareMap.dcMotor.get("lf_drive");
//        DcMotor backLeftMotor = hardwareMap.dcMotor.get("lb_drive");
//
//        // Initialize linear slide and slide movement motors
//        DcMotor linearSlideMotor = hardwareMap.dcMotor.get("arm1");
//        DcMotor slideMovementMotor = hardwareMap.dcMotor.get("movement1");
//
//        // Initialize continuous rotation servo for intake
//        CRServo intakeServo = hardwareMap.get(CRServo.class, "intakeServo");
//
//        // Power constants
//        final double STOP_POWER = 0;
//        final double UP_POWER = 0.5;
//        final double DOWN_POWER = -0.5;
//
//        // Motor configuration for the linear slide
//        linearSlideMotor.setDirection(DcMotor.Direction.FORWARD);
//        linearSlideMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
//        linearSlideMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
//
//        // Motor power configuration for driving
//        frontLeftMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
//        frontRightMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
//        backLeftMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
//        backRightMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
//        slideMovementMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
//
//        double speed = 0.5;
//
//        waitForStart();
//
//        if (isStopRequested()) return;
//
//        while (opModeIsActive()) {
//            // Gamepad1 - Movement Controls
//            double y = -gamepad1.left_stick_y;
//            double x = gamepad1.left_stick_x * 1.1;
//            double rx = gamepad1.right_stick_x;
//            double deno = Math.max(Math.abs(y) + Math.abs(x) + Math.abs(rx), 1);
//
//            double frontLeftPower = -(y + x + rx) / deno * speed;
//            double backLeftPower = -(y - x + rx) / deno * speed;
//            double frontRightPower = (y - x - rx) / deno * speed;
//            double backRightPower = -(y + x - rx) / deno * speed;
//
//            frontLeftMotor.setPower(frontLeftPower);
//            backLeftMotor.setPower(backLeftPower);
//            frontRightMotor.setPower(frontRightPower);
//            backRightMotor.setPower(backRightPower);
//
//            // Gamepad2 - Linear Slide and Slide Movement Controls
//            if (gamepad2.y) {
//                linearSlideMotor.setPower(UP_POWER);
//            } else if (gamepad2.b) {
//                linearSlideMotor.setPower(DOWN_POWER);
//            } else {
//                linearSlideMotor.setPower(STOP_POWER);
//            }
//
//            if (gamepad2.x) {
//                slideMovementMotor.setPower(UP_POWER);
//            } else if (gamepad2.a) {
//                slideMovementMotor.setPower(DOWN_POWER);
//            } else {
//                slideMovementMotor.setPower(0);
//            }
//
//            // Gamepad2 - Continuous Servo Control for Intake
//            if (gamepad2.dpad_up) {
//                intakeServo.setPower(1.0); // Spin clockwise
//            } else if (gamepad2.dpad_down) {
//                intakeServo.setPower(-1.0); // Spin counter-clockwise
//            } else {
//                intakeServo.setPower(0); // Stop
//            }
//
//            // Ensure sigma interrupted state clean-up if stop requested.
//            if (isStopRequested()) {
//                linearSlideMotor.setPower(0);
//                slideMovementMotor.setPower(0);
//                frontLeftMotor.setPower(0);
//                frontRightMotor.setPower(0);
//                backLeftMotor.setPower(0);
//                backRightMotor.setPower(0);
//                intakeServo.setPower(0);
//                return;
//            }
//        }
//    }
//}

package org.firstinspires.ftc.teamcode;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.CRServo;

@TeleOp
public class TeleOpMode extends LinearOpMode {

    @Override
    public void runOpMode() throws InterruptedException {

        // Initialize gooning drive motors.
        DcMotor frontRightMotor = hardwareMap.dcMotor.get("rf_drive");
        DcMotor backRightMotor = hardwareMap.dcMotor.get("rb_drive");
        DcMotor frontLeftMotor = hardwareMap.dcMotor.get("lf_drive");
        DcMotor backLeftMotor = hardwareMap.dcMotor.get("lb_drive");


        // Initialize Kumalala Savesta linear slide and slide movement motors
        DcMotor linearSlideMotor = hardwareMap.dcMotor.get("back");
        DcMotor slideMovementMotor = hardwareMap.dcMotor.get("main");

        // Initialize continuous rotation servos for sigma intake.
        DcMotor inR = hardwareMap.dcMotor.get("intake"); //intake rotation
        CRServo backIntakeServo1 = hardwareMap.get(CRServo.class, "backIntakeServo1");
        CRServo backIntakeServo2 = hardwareMap.get(CRServo.class, "backIntakeServo2");
        CRServo frontIntakeServo = hardwareMap.get(CRServo.class, "frontIntakeServo");

        // Power constants
        final double STOP_POWER = 0;
        final double UP_POWER = 0.5;
        final double DOWN_POWER = -0.5;

        // Motor configuration for edging the linear slide.
        linearSlideMotor.setDirection(DcMotor.Direction.FORWARD);
        linearSlideMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        linearSlideMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        // Motor power configuration for driving the Skibidi Mobile.
        frontLeftMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        frontRightMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        backLeftMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        backRightMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        slideMovementMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        double speed = 0.5;

        waitForStart();

        if (isStopRequested()) return;

        while (opModeIsActive()) {
            // Gamepad1 - Movement Controls for Rizzing.
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

            if (gamepad2.dpad_left) {
                //intake
                backIntakeServo1.setPower(-1.0);
                backIntakeServo2.setPower(1.0);
            }
            if (gamepad2.dpad_right) {
                // Outtake
                backIntakeServo1.setPower(1.0); // Clockwise.
                backIntakeServo2.setPower(-1.0); // Counter-clockwise


                if (gamepad2.dpad_up) {
                    frontIntakeServo.setPower(1.0); //intake
                }
                if (gamepad2.dpad_down) {
                    frontIntakeServo.setPower(-1.0); // outtake
                }
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

                // Gamepad2 - Continuous Ligma Servo Control for Back and Front Intake.
                if (gamepad2.left_bumper) {
                    // Spin both back intake servos in the opposite direction
                  inR.setPower(0.5);
                }
                if (gamepad2.right_bumper){
                    // Stop all baby gronk intake servos.
                    inR.setPower(-0.5);
                }

                // Ensure sigma interrupted state clean-up if stop requested.
                if (isStopRequested()) {
                    linearSlideMotor.setPower(0);
                    slideMovementMotor.setPower(0);
                    frontLeftMotor.setPower(0);
                    frontRightMotor.setPower(0);
                    backLeftMotor.setPower(0);
                    backRightMotor.setPower(0);
                    inR.setPower(0);
                    backIntakeServo1.setPower(0);
                    backIntakeServo2.setPower(0);
                    frontIntakeServo.setPower(0);
                    return;
                }
            }
        }
    }
}

//package org.firstinspires.ftc.teamcode;
//import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
//import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
//import com.qualcomm.robotcore.hardware.DcMotor;
//import com.qualcomm.robotcore.hardware.CRServo;
//
//@TeleOp
//public class TeleOpMode extends LinearOpMode {
//
//    @Override
//    public void runOpMode() throws InterruptedException {
//
//        // Initialize linear slide and slide movement motors
//        DcMotor linearSlideMotor = hardwareMap.dcMotor.get("back");
//        DcMotor slideMovementMotor = hardwareMap.dcMotor.get("main");
//
//        // Initialize continuous rotation servos for intake
//        DcMotor inR = hardwareMap.dcMotor.get("intake"); // Intake rotation
//        CRServo backIntakeServo1 = hardwareMap.get(CRServo.class, "backIntakeServo1");
//        CRServo backIntakeServo2 = hardwareMap.get(CRServo.class, "backIntakeServo2");
//        CRServo frontIntakeServo = hardwareMap.get(CRServo.class, "frontIntakeServo");
//
//        // Power constants
//        final double STOP_POWER = 0;
//        final double UP_POWER = 0.5;
//        final double DOWN_POWER = -0.5;
//
//        // Motor configuration for linear slide
//        linearSlideMotor.setDirection(DcMotor.Direction.FORWARD);
//        linearSlideMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
//        linearSlideMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
//
//        // Motor power configuration for slide movement
//        slideMovementMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
//
//        waitForStart();
//
//        if (isStopRequested()) return;
//
//        while (opModeIsActive()) {
//            // Gamepad2 - Intake/Outtake Controls
//            if (gamepad2.dpad_left) {
//                // Intake
//                backIntakeServo1.setPower(-1.0);  // Reverse direction
//                backIntakeServo2.setPower(1.0);   // Forward direction
//            } else if (gamepad2.dpad_right) {
//                // Outtake
//                backIntakeServo1.setPower(1.0);   // Forward direction
//                backIntakeServo2.setPower(-1.0);  // Reverse direction
//            } else {
//                // Stop intake servos if no button is pressed
//                backIntakeServo1.setPower(0);
//                backIntakeServo2.setPower(0);
//            }
//
//            if (gamepad2.dpad_up) {
//                // Intake
//                frontIntakeServo.setPower(1.0);
//            } else if (gamepad2.dpad_down) {
//                // Outtake
//                frontIntakeServo.setPower(-1.0);
//            } else {
//                // Stop the front intake servo if no button is pressed
//                frontIntakeServo.setPower(0);
//            }
//
//            // Gamepad2 - Linear Slide and Slide Movement Controls
//            if (gamepad2.y) {
//                linearSlideMotor.setPower(UP_POWER);
//            } else if (gamepad2.b) {
//                linearSlideMotor.setPower(DOWN_POWER);
//            } else {
//                linearSlideMotor.setPower(STOP_POWER);
//            }
//
//            if (gamepad2.x) {
//                slideMovementMotor.setPower(UP_POWER);
//            } else if (gamepad2.a) {
//                slideMovementMotor.setPower(DOWN_POWER);
//            } else {
//                slideMovementMotor.setPower(0);
//            }
//
//            // Gamepad2 - Continuous Intake Rotation Control (In/Out)
//            if (gamepad2.left_bumper) {
//                // Continuous intake rotation
//                inR.setPower(0.5); // Forward direction
//            } else if (gamepad2.right_bumper) {
//                // Continuous outtake rotation
//                inR.setPower(-0.5); // Reverse direction
//            } else {
//                // Stop intake rotation if no bumper is pressed
//                inR.setPower(0);
//            }
//
//            // Ensure interrupted state clean-up if stop requested
//            if (isStopRequested()) {
//                linearSlideMotor.setPower(0);
//                slideMovementMotor.setPower(0);
//                inR.setPower(0);
//                backIntakeServo1.setPower(0);
//                backIntakeServo2.setPower(0);
//                frontIntakeServo.setPower(0);
//                return;
//            }
//        }
//    }
//}
