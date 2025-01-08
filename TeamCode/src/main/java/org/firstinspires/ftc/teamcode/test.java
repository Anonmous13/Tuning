//package org.firstinspires.ftc.teamcode;
//
//import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
//import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
//import com.qualcomm.robotcore.hardware.DcMotor;
//import com.qualcomm.robotcore.hardware.CRServo;
//import com.qualcomm.robotcore.hardware.DcMotorSimple;
//import com.qualcomm.robotcore.util.Range;
//
//@TeleOp
//public class test extends LinearOpMode {
//
//    @Override
//    public void runOpMode() throws InterruptedException {
//        // Initialize drive motors
//        DcMotor frontLeftMotor = hardwareMap.get(DcMotor.class, "lf_drive");
//        DcMotor frontRightMotor = hardwareMap.get(DcMotor.class, "rf_drive");
//        DcMotor backLeftMotor = hardwareMap.get(DcMotor.class, "lb_drive");
//        DcMotor backRightMotor = hardwareMap.get(DcMotor.class, "rb_drive");
//
//        // Initialize linear slide and slide movement motors
//        DcMotor linearSlideMotor = hardwareMap.get(DcMotor.class, "back");
//        DcMotor slideMovementMotor = hardwareMap.get(DcMotor.class, "linearSlide");
//
//        // Initialize continuous rotation servos for intake
//        DcMotor intakeMotor = hardwareMap.get(DcMotor.class, "intake");
//        CRServo backIntakeServo1 = hardwareMap.get(CRServo.class, "backIntakeServoL");
//        CRServo backIntakeServo2 = hardwareMap.get(CRServo.class, "backIntakeServoR");
//        CRServo frontIntakeServo = hardwareMap.get(CRServo.class, "frontIntakeServo");
//        frontLeftMotor.setDirection(DcMotorSimple.Direction.REVERSE);
//        backLeftMotor.setDirection(DcMotorSimple.Direction.REVERSE);
//
//        // Power constants
//        final double STOP_POWER = 0;
//        final double UP_POWER = 0.5;
//        final double DOWN_POWER = -0.5;
//
//        // Configure motor behaviors
//        linearSlideMotor.setDirection(DcMotor.Direction.FORWARD);
//        linearSlideMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
//        linearSlideMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
//        slideMovementMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
//
//        frontLeftMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
//        frontRightMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
//        backLeftMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
//        backRightMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
//
//        double speed = 0.5;
//
//        waitForStart();
//
//        if (isStopRequested()) return;
//
//        while (opModeIsActive()) {
//            double lfPower;
//            double rfPower;
//            double rbPower;
//            double lbPower;
//
//            double poweslide;
//            double powerslidefoward;
//
//            // Choose to drive using either Tank Mode, or POV Mode
//            // Comment out the method that's not used.  The default below is POV.
//
//            // POV Mode uses left stick to go forward, and right stick to turn.
//            // - This uses basic math to combine motions and is easier to drive straight.
//            double drive = gamepad1.right_stick_x * 0.68;
//            double strafe = gamepad1.left_stick_x * 0.60;
//            double turn = -gamepad1.left_stick_y * 0.63;
//            lfPower = Range.clip(turn + strafe + drive, -1.0, 1.0);
//            rfPower = Range.clip(turn - strafe + drive, -1.0, 1.0);
//            lbPower = Range.clip(turn - strafe - drive, -1.0, 1.0);
//            rbPower = Range.clip(turn + strafe - drive, -1.0, 1.0);
//
//            frontLeftMotor.setPower(lfPower);
//            backLeftMotor.setPower(rfPower);
//            frontRightMotor.setPower(lbPower);
//            backRightMotor.setPower(rbPower);
//
//            // Gamepad2 - Intake/Outtake Controls
//            if (gamepad2.dpad_left) {
//                // Intake
//                backIntakeServo1.setPower(-1.0);
//                backIntakeServo2.setPower(1.0);
//
//            } else if (gamepad2.dpad_right) {
//                // Outtake
//                backIntakeServo1.setPower(1.0);
//                backIntakeServo2.setPower(-1.0);
//            } else {
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
//                frontIntakeServo.setPower(0);
//            }
//
//            // Gamepad2 - Linear Slide and Slide Movement Controls
//            if (gamepad2.b) {
//                linearSlideMotor.setPower(UP_POWER);
//            } else if (gamepad2.x) {
//                linearSlideMotor.setPower(DOWN_POWER);
//            } else {
//                linearSlideMotor.setPower(STOP_POWER);
//            }
//
//            if (gamepad2.a) {
//                slideMovementMotor.setPower(UP_POWER);
//            } else if (gamepad2.y) {
//                slideMovementMotor.setPower(DOWN_POWER);
//            } else {
//                slideMovementMotor.setPower(0);
//            }
//
//            // Gamepad2 - Continuous Intake Rotation Control
//            if (gamepad2.left_bumper) {
//                intakeMotor.setPower(0.5); // Forward
//            } else if (gamepad2.right_bumper) {
//                intakeMotor.setPower(-0.5); // Reverse
//            } else {
//                intakeMotor.setPower(0); // Stop
//            }
//
//            // Ensure interrupted state clean-up if stop requested
//            if (isStopRequested()) {
//                linearSlideMotor.setPower(0);
//                slideMovementMotor.setPower(0);
//                frontLeftMotor.setPower(0);
//                frontRightMotor.setPower(0);
//                backLeftMotor.setPower(0);
//                backRightMotor.setPower(0);
//                intakeMotor.setPower(0);
//                backIntakeServo1.setPower(0);
//                backIntakeServo2.setPower(0);
//                frontIntakeServo.setPower(0);
//                return;
//            }
//        }
//    }
//}
