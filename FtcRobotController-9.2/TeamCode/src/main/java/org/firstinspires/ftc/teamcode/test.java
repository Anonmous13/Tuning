package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
@TeleOp
public class test extends LinearOpMode {
    @Override
    public void runOpMode() throws InterruptedException {

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

        // Variables to control the servo position
        double clawPosition = 0.25; // Initial position, corresponds to 90 degrees


        // Speed variable for motor power scaling

        waitForStart();

        if (isStopRequested()) return;

        while (opModeIsActive()) {
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
