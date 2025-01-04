package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;

@Autonomous(name = "ITD Auto Encoders")
public class TheRealEncodersAuton extends LinearOpMode
{
    public DcMotor frontLeftMotor, frontRightMotor, backLeftMotor, backRightMotor, linearSlideMotor, intakeMotor, backMotor;
    public CRServo backIntakeServo1, backIntakeServo2, linearSlideServoL, linearSlideServoR;

    public static final double ARM_OPEN_POSITION = 0.0;
    public static final double ARM_CLOSE_POSITION = 1.0;

    @Override
    public void runOpMode()
    {
        // Initialize hardware components
        frontLeftMotor = hardwareMap.get(DcMotor.class, "lf_drive");
        frontRightMotor = hardwareMap.get(DcMotor.class, "rf_drive");
        backLeftMotor = hardwareMap.get(DcMotor.class, "lb_drive");
        backRightMotor = hardwareMap.get(DcMotor.class, "rb_drive");
        linearSlideMotor = hardwareMap.get(DcMotor.class, "linearSlide");
        backMotor = hardwareMap.get(DcMotor.class, "back");
        intakeMotor = hardwareMap.get(DcMotor.class, "intake");
        backIntakeServo1 = hardwareMap.get(CRServo.class, "backIntakeServoL");
        backIntakeServo2 = hardwareMap.get(CRServo.class, "backIntakeServoR");
        linearSlideServoL = hardwareMap.get(CRServo.class, "backIntakeServoL");
        linearSlideServoR = hardwareMap.get(CRServo.class, "backIntakeServoR");

        // Set motor directions
        frontLeftMotor.setDirection(DcMotor.Direction.REVERSE);
        backLeftMotor.setDirection(DcMotor.Direction.REVERSE);



        // Set motor zero power behavior to BRAKE
        setMotorZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        backMotor.setTargetPosition(0);

        backMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        backMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        // Reset encoders and set motor modes
        resetEncoders();

        telemetry.addData("Status", "Initialized");
        telemetry.update();

        waitForStart();

        if (opModeIsActive()) {
            runAutonomousSequence();
        }
    }

    private void runAutonomousSequence()
    {
        telemetry.addData("Status", "Starting Autonomous.");
        telemetry.update();

        // Step 1: Move forward for 2 seconds
        moveForwardForTime(1.18);
        sleep(500);  // Small delay to stabilize

        // Step 2: Lift the back (adjust the height)
        backMotor.setPower(-0.85);
        sleep(1650); // Set back height to 1.0 (max)
        backMotor.setPower(0);
        sleep(2000);  // Hold it for 1 second

        // Step 3: Move forward for 1 second
        moveForwardForTime(0.105);
        sleep(500);  // Small delay to stabilize

        // Step 4: Lower the back (adjust the height)
        backMotor.setPower(0.8);  // Reset back height to 0.0 (min)
        sleep(1000);  // Hold it for 1 second
        backMotor.setPower(0);

        // Step 5: Move backward for 2 seconds
        sleep(500);
        moveBackwardForTime(1.2);
        sleep(500);  // Small delay to stabilize

        // Step 6: Strafe right for 1 second
        strafeRightForTime(2.1);
        sleep(5000);

        telemetry.addData("Status", "Autonomous Complete.");
        telemetry.update();
    }

    // Method to move the robot forward for a specified time in seconds
    private void moveForwardForTime(double seconds)
    {
        setMotorPower(0.5, 0.5, 0.5, 0.5);
        sleep((long)(seconds * 1000));  // Convert to milliseconds
        stopMotors();
    }


    // Method to move the robot backward for a specified time in seconds
    private void moveBackwardForTime(double seconds)
    {
        setMotorPower(-0.5, -0.5, -0.5, -0.5);
        sleep((long)(seconds * 1000));  // Convert to milliseconds
        stopMotors();
    }

    // Method to strafe the robot to the right for a specified time in seconds
    private void strafeRightForTime(double seconds)
    {
        setMotorPower(0.5, -0.5, -0.5, 0.5);
        sleep((long)(seconds * 1000));  // Convert to milliseconds
        stopMotors();
    }



    // Method to stop all motors
    private void stopMotors()
    {

        setMotorPower(0, 0, 0, 0);
    }

    // Method to set the power of all motors
    private void setMotorPower(double fl, double fr, double bl, double br)
    {
        frontLeftMotor.setPower(fl);
        frontRightMotor.setPower(fr);
        backLeftMotor.setPower(bl);
        backRightMotor.setPower(br);
    }




    // Reset encoders
    private void resetEncoders()
    {
        intakeMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        linearSlideMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        backMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        frontLeftMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        frontRightMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        backLeftMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        backRightMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        intakeMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        linearSlideMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        backMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        frontLeftMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        frontRightMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        backLeftMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        backRightMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
    }

    // Set motor zero power behavior to BRAKE
    private void setMotorZeroPowerBehavior(DcMotor.ZeroPowerBehavior behavior)
    {
        frontLeftMotor.setZeroPowerBehavior(behavior);
        frontRightMotor.setZeroPowerBehavior(behavior);
        backLeftMotor.setZeroPowerBehavior(behavior);
        backRightMotor.setZeroPowerBehavior(behavior);
    }
}
