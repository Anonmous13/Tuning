package org.firstinspires.ftc.teamcode;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import java.util.Random;

@TeleOp
public class TeleOPTwo extends LinearOpMode {

    @Override
    public void runOpMode() throws InterruptedException {

        // Initialize motors
        DcMotor frontRightMotor = hardwareMap.dcMotor.get("rf_drive"); //0
        DcMotor backRightMotor = hardwareMap.dcMotor.get("rb_drive"); //1
        DcMotor frontLeftMotor = hardwareMap.dcMotor.get("lf_drive"); //2
        DcMotor backLeftMotor = hardwareMap.dcMotor.get("lb_drive"); //3

        // Power constant
        final double MOTOR_POWER = 0.5;

        // Motor power configuration
        frontLeftMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        frontRightMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        backLeftMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        backRightMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        Random rand = new Random();

        waitForStart();

        if (isStopRequested()) return;

        while (opModeIsActive()) {

            // Generate a random number between 0 and 3 to select a motor
            int motorChoice = rand.nextInt(4);

            // Set power to the selected motor, turn off others
            frontLeftMotor.setPower(motorChoice == 0 ? MOTOR_POWER : 0);
            backLeftMotor.setPower(motorChoice == 1 ? MOTOR_POWER : 0);
            frontRightMotor.setPower(motorChoice == 2 ? MOTOR_POWER : 0);
            backRightMotor.setPower(motorChoice == 3 ? MOTOR_POWER : 0);

            // Telemetry for debugging
            telemetry.addData("Active Motor", motorChoice);
            telemetry.update();

            // Wait 1 second before changing the powered motor
            sleep(1000);  // Delay for 1 second

            // Ensure interrupted state clean-up if stop requested
            if (isStopRequested()) {
                frontLeftMotor.setPower(0);
                frontRightMotor.setPower(0);
                backLeftMotor.setPower(0);
                backRightMotor.setPower(0);
                return;
            }
        }
    }
}
