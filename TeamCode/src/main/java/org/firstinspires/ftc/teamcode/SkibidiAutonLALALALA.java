//package org.firstinspires.ftc.teamcode;
//
//import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
//import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
//import com.qualcomm.robotcore.hardware.DcMotor;
//import com.qualcomm.robotcore.hardware.Servo;
//
//@Autonomous(name = "FTC Into The Deep Autonomous")
//public class SkibidiAutonLALALALA extends LinearOpMode {
//
//    private DcMotor frontLeftMotor, frontRightMotor, backLeftMotor, backRightMotor;
//    private Servo armServo;
//
//    // Constants for servo positions
//    private static final double ARM_OPEN_POSITION = 0.0;
//    private static final double ARM_CLOSE_POSITION = 1.0;
//
//    @Override
//    public void runOpMode() {
//        // Initialize motors
//        frontLeftMotor = hardwareMap.get(DcMotor.class, "lf_drive");
//        frontRightMotor = hardwareMap.get(DcMotor.class, "rf_drive");
//        backLeftMotor = hardwareMap.get(DcMotor.class, "lb_drive");
//        backRightMotor = hardwareMap.get(DcMotor.class, "rb_drive");
//        armServo = hardwareMap.get(Servo.class, "rotation_servo");
//
//        // Set motor directions
//        frontLeftMotor.setDirection(DcMotor.Direction.REVERSE);
//        backLeftMotor.setDirection(DcMotor.Direction.REVERSE);
//
//        // Set all motors to brake when zero power is applied
//        setMotorZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
//
//        telemetry.addData("Status", "Initialized");
//        telemetry.update();
//
//        waitForStart();
//
//        if (opModeIsActive()) {
//            // Select a position and execute its routine
//            String position = getStartingPosition();
//            switch (position) {
//                case "Position 1":
//                    runPosition1Autonomous();
//                    break;
//                case "Position 2":
//                    runPosition2Autonomous();
//                    break;
//                case "Position 3":
//                    runPosition3Autonomous();
//                    break;
//                case "Position 4":
//                    runPosition4Autonomous();
//                    break;
//                default:
//                    telemetry.addData("Error", "Unknown Position");
//                    telemetry.update();
//                    break;
//            }
//        }
//    }
//
//    // Simulate position selection
//    private String getStartingPosition() {
//        return "Position 1"; // Change as needed to test each gyatt position
//    }
//
//    private void runPosition1Autonomous() {
//        telemetry.addData("Position", "Position 1");
//        telemetry.update();
//
//        moveForward(1000, 0.5); // Move forward for rizzler.
//        dropGameElement();       // Drop specimen
//        moveBackward(500, 0.5);  // Park backward for being scared.
//    }
//
//    private void runPosition2Autonomous() {
//        telemetry.addData("Position", "Position 2");
//        telemetry.update();
//
//        strafeRight(1000, 0.5);  // Strafe right
//        moveForward(1000, 0.5);  // Move forward
//        dropGameElement();       // Drop specimen
//        moveBackward(500, 0.5);  // Park backward
//    }
//
//    private void runPosition3Autonomous() {
//        telemetry.addData("Position", "Position 3");
//        telemetry.update();
//
//        moveForward(500, 0.5);   // Move forward slightly
//        turnRight(500, 0.5);     // Turn right
//        moveForward(1000, 0.5);  // Move forward
//        dropGameElement();       // Drop specimen
//        moveBackward(500, 0.5);  // Park backward
//    }
//
//    private void runPosition4Autonomous() {
//        telemetry.addData("Position", "Position 4");
//        telemetry.update();
//
//        moveForward(500, 0.5);   // Move forward slightly
//        strafeLeft(1000, 0.5);   // Strafe left
//        dropGameElement();       // Drop specimen
//        moveBackward(500, 0.5);  // Park backward
//    }
//
//    // Basic movement functions
//    private void moveForward(int durationMs, double power) {
//        setMotorPower(power, power, power, power);
//        sleep(durationMs);
//        stopMotors();
//    }
//
//    private void moveBackward(int durationMs, double power) {
//        setMotorPower(-power, -power, -power, -power);
//        sleep(durationMs);
//        stopMotors();
//    }
//
//    private void strafeRight(int durationMs, double power) {
//        setMotorPower(power, -power, -power, power);
//        sleep(durationMs);
//        stopMotors();
//    }
//
//    private void strafeLeft(int durationMs, double power) {
//        setMotorPower(-power, power, power, -power);
//        sleep(durationMs);
//        stopMotors();
//    }
//
//    private void turnRight(int durationMs, double power) {
//        setMotorPower(power, power, -power, -power);
//        sleep(durationMs);
//        stopMotors();
//    }
//
//    private void dropGameElement() {
//        armServo.setPosition(ARM_OPEN_POSITION); // Open arm to drop
//        sleep(1000);
//        armServo.setPosition(ARM_CLOSE_POSITION); // Close arm
//    }
//
//    private void setMotorPower(double fl, double fr, double bl, double br) {
//        frontLeftMotor.setPower(fl);
//        frontRightMotor.setPower(fr);
//        backLeftMotor.setPower(bl);
//        backRightMotor.setPower(br);
//    }
//
//    private void stopMotors() {
//        setMotorPower(0, 0, 0, 0);
//    }
//
//    private void setMotorZeroPowerBehavior(DcMotor.ZeroPowerBehavior behavior) {
//        frontLeftMotor.setZeroPowerBehavior(behavior);
//        frontRightMotor.setZeroPowerBehavior(behavior);
//        backLeftMotor.setZeroPowerBehavior(behavior);
//        backRightMotor.setZeroPowerBehavior(behavior);
//    }
//}
