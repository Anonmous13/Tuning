package org.firstinspires.ftc.teamcode;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.Servo;

@TeleOp
public class PossibleTeleOPWithMotors extends LinearOpMode {

    @Override
    public void runOpMode() throws InterruptedException {

        // Initialize motors
        DcMotorEx frontRightMotor = hardwareMap.get(DcMotorEx.class, "rf_drive"); //0
        DcMotorEx backRightMotor = hardwareMap.get(DcMotorEx.class, "rb_drive"); //1
        DcMotor frontLeftMotor = hardwareMap.dcMotor.get("lf_drive"); //2
        DcMotor backLeftMotor = hardwareMap.dcMotor.get("lb_drive"); //3
        DcMotorEx slideMotor = hardwareMap.get(DcMotorEx.class, "slide_motor"); // Example additional DC motor
        Servo armServo = hardwareMap.servo.get("servo_arm"); // Example servo

        // Power constants
        final double STOP_POWER = 0;
        final double UP_POWER = 0.2;
        final double DOWN_POWER = -0.5;

        // Servo positions
        final double SERVO_MIN_POSITION = 0.0;
        final double SERVO_MAX_POSITION = 1.0;
        double servoPosition = 0.5;
        armServo.setPosition(servoPosition); // Initialize servo position

        // Motor power configuration
        frontLeftMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        frontRightMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        backLeftMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        backRightMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        slideMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        double speed = 0.5;

        waitForStart();

        if (isStopRequested()) return;

        //Test For Karthi's Github.

        // Configuration: 2 DC Motors Synchronized with Encoders
        frontRightMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        backRightMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        frontRightMotor.setPower(speed);
        backRightMotor.setPower(speed);

        // Configuration: 1 DC Motor, 1 Servo Single Rotation Encoder/Discrete Mode
        frontRightMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        armServo.setPosition(SERVO_MIN_POSITION); // Set servo to discrete or specific position
        // Switch between encoder or discrete mode for the DC motor
        frontRightMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER); // Encoder mode
        // Optionally, run without encoder for discrete steps
        frontRightMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER); // Discrete mode

        // Configuration: 1 DC Motor, 2 Servos, Single Rotation Encoder/Discrete Mode
        frontLeftMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        Servo additionalServo = hardwareMap.servo.get("additional_servo");
        additionalServo.setPosition(SERVO_MAX_POSITION);
        // Switch between encoder and discrete mode for motors
        frontLeftMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        frontLeftMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER); // Discrete mode

        // Configuration: 1 DC Motor, 1 Servo Synchronized Encoder/Discrete Mode
        backRightMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        armServo.setPosition(servoPosition);
        backRightMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER); // Discrete mode

        // Configuration: 2 DC Motors, 2 Servos Synchronized Encoder/Discrete Mode
        frontRightMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        backRightMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        frontLeftMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        backLeftMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER); // Discrete mode
        armServo.setPosition(SERVO_MIN_POSITION);
        additionalServo.setPosition(SERVO_MAX_POSITION);

        // Configuration: 1 Servo Controlled by Controller (Continuous Rotation)
        armServo.setPosition(0.5); // Set to mid-position for continuous rotation

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

//            // Gamepad2 - Linear Slide and Slide Movement Controls
//            if (gamepad2.y) {
//                slideMotor.setPower(UP_POWER);
//            } else if (gamepad2.b) {
//                slideMotor.setPower(DOWN_POWER);
//            } else {
//                slideMotor.setPower(STOP_POWER);
//            }
//
//            if (gamepad2.x) {
//                frontRightMotor.setPower(UP_POWER);
//            } else if (gamepad2.a) {
//                frontRightMotor.setPower(DOWN_POWER);
//           } else {
//                frontRightMotor.setPower(0);
//           }

//             Gamepad2 - Servo Controls with Larger Steps
//            if (gamepad2.dpad_up) {
//                servoPosition = SERVO_MIN_POSITION;  // Move to left position
//            } else if (gamepad2.dpad_down) {
//                servoPosition = SERVO_MAX_POSITION;  // Move to right position
//            }
//
//            armServo.setPosition(servoPosition);
//
//            // Telemetry for debugging
//            telemetry.addData("Servo Target Position", servoPosition);
//            telemetry.addData("Actual Servo Position", armServo.getPosition());
//            telemetry.update();

            // Ensure interrupted state clean-up if stop requested
            if (isStopRequested()) {
                slideMotor.setPower(0);
                frontRightMotor.setPower(0);
                frontLeftMotor.setPower(0);
                backRightMotor.setPower(0);
                backLeftMotor.setPower(0);
                return;
            }
        }
    }
}
