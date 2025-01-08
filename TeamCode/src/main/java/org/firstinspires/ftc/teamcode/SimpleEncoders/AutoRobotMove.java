//package org.firstinspires.ftc.teamcode.SimpleEncoders;
//import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
//import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
//import com.qualcomm.robotcore.hardware.DcMotor;
//import com.qualcomm.robotcore.util.ElapsedTime;
//
//@Autonomous(name = "AutonMoveRobot", group = "Linear Opmode")
//class AutoRobotMove extends LinearOpMode {
//
//    private DcMotor leftDrive = null;
//    private DcMotor rightDrive = null;
//    private ElapsedTime runtime = new ElapsedTime();
//
//    static final double COUNTS_PER_MOTOR_REV = 1440;
//    static final double DRIVE_GEAR_REDUCTION = 1.0;
//    static final double WHEEL_DIAMETER_INCHES = 4.0;
//    static final double COUNTS_PER_INCH = (COUNTS_PER_MOTOR_REV * DRIVE_GEAR_REDUCTION) /
//            (WHEEL_DIAMETER_INCHES * 3.1415);
//
//    @Override
//    public void runOpMode() {
//        leftDrive = hardwareMap.get(DcMotor.class, "left_drive");
//        rightDrive = hardwareMap.get(DcMotor.class, "right_drive");
//
//        leftDrive.setDirection(DcMotor.Direction.FORWARD);
//        rightDrive.setDirection(DcMotor.Direction.REVERSE);
//
//        leftDrive.setPower(0);
//        rightDrive.setPower(0);
//
//        leftDrive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER); //Zeros the encoders
//        rightDrive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
//
//        leftDrive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
//        rightDrive.setMode(DcMotor.RunMode.RUN_USING_ENCODER); // Run_To_Position
//
//        telemetry.addData("Status", "Ready to run");
//        telemetry.update();
//
//        waitForStart();
//
//        boolean isBlueSide = true; // Change based on your start position
//        if (gamepad1.x) {
//            isBlueSide = true;
//        } else if (gamepad1.b) {
//            isBlueSide = false;
//        }
//
//        if (isBlueSide) {
//            runBlueAutonomous();
//        } else {
//            runRedAutonomous();
//        }
//
//        telemetry.addData("Path", "Complete");
//        telemetry.update();
//    }
//
//    private void runBlueAutonomous() {
//        encoderDrive(0.6, 24, 24, 5.0); // Forward 24 inches
//        encoderDrive(0.5, 12, -12, 4.0); // Turn right
//        encoderDrive(0.6, -24, -24, 5.0); // Backward 24 inches
//    }
//
//    private void runRedAutonomous() {
//        encoderDrive(0.6, 24, 24, 5.0); // Forward 24 inches
//        encoderDrive(0.5, -12, 12, 4.0); // Turn left
//        encoderDrive(0.6, -24, -24, 5.0); // Backward 24 inches
//    }
//
//    public void encoderDrive(double speed, double leftInches, double rightInches, double timeoutS) {
//        int newLeftTarget;
//        int newRightTarget;
//
//        newLeftTarget = leftDrive.getCurrentPosition() + (int)(leftInches * COUNTS_PER_INCH);
//        newRightTarget = rightDrive.getCurrentPosition() + (int)(rightInches * COUNTS_PER_INCH);
//        leftDrive.setTargetPosition(newLeftTarget);
//        rightDrive.setTargetPosition(newRightTarget);
//
//        leftDrive.setMode(DcMotor.RunMode.RUN_TO_POSITION);
//        rightDrive.setMode(DcMotor.RunMode.RUN_TO_POSITION);
//
//        runtime.reset();
//        leftDrive.setPower(Math.abs(speed));
//        rightDrive.setPower(Math.abs(speed));
//
//        while (opModeIsActive() &&
//                (runtime.seconds() < timeoutS) &&
//                (leftDrive.isBusy() && rightDrive.isBusy())) {
//
//            telemetry.addData("Path1", "Running to %7d :%7d", newLeftTarget, newRightTarget);
//            telemetry.addData("Path2", "Running at %7d :%7d",
//                    leftDrive.getCurrentPosition(),
//                    rightDrive.getCurrentPosition());
//            telemetry.update();
//        }
//
//        leftDrive.setPower(0);
//        rightDrive.setPower(0);
//
//        leftDrive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
//        rightDrive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
//    }
//}
