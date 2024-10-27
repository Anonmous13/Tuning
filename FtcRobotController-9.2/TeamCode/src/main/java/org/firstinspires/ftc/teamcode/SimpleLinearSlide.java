package org.firstinspires.ftc.teamcode;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

@TeleOp(name="Simple Linear Slide", group="Linear Opmode")
public class SimpleLinearSlide extends LinearOpMode {

    // Declare motor
    private DcMotor linearSlideMotor = hardwareMap.dcMotor.get("arm1");;

    // Define constants for motor power
    private static final double UP_POWER = 0.5;
    private static final double DOWN_POWER = -0.5;
    private static final double STOP_POWER = 0;

    @Override
    public void runOpMode() {

        linearSlideMotor = hardwareMap.get(DcMotor.class, "linearSlideMotor");

        linearSlideMotor.setDirection(DcMotor.Direction.FORWARD);

        linearSlideMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        linearSlideMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        waitForStart();

        while (opModeIsActive()) {

            if (gamepad1.y) {
                linearSlideMotor.setPower(UP_POWER);
            } else if (gamepad1.b) {
                linearSlideMotor.setPower(DOWN_POWER);
            } else {
                linearSlideMotor.setPower(STOP_POWER);
            }

            // Display the current encoder value on telemetry
            telemetry.addData("Slide Position", linearSlideMotor.getCurrentPosition());
            telemetry.update();
        }
    }
}
