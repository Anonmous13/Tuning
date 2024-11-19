package org.firstinspires.ftc.teamcode;


import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.util.Range;


@TeleOp(name="Basic: Linear OpMode", group="Linear OpMode")

public class KarthiTeleOP extends LinearOpMode {

    // Declare OpMode members.
    private ElapsedTime runtime = new ElapsedTime();
    private DcMotor leftback;
    private DcMotor rightback;
    private DcMotor rightfront;
    private DcMotor leftfront;

    @Override
    public void runOpMode() {
        telemetry.addData("Status", "Initialized");
        telemetry.update();

        // Initialize the hardware variables. Note that the strings used here as parameters
        // to 'get' must correspond to the names assigned during the robot configuration
        // step (using the FTC Robot Controller app on the phone).
        leftback = hardwareMap.get(DcMotor.class, "Left Back Motor");
        rightback = hardwareMap.get(DcMotor.class, "Right Back Motor");
        rightfront = hardwareMap.get(DcMotor.class, "Right Front Motor");
        leftfront = hardwareMap.get(DcMotor.class, "Left Front Motor");

        // To drive forward, most robots need the motor on one side to be reversed, because the axles point in opposite directions.
        // Pushing the left stick forward MUST make robot go forward. So adjust these two lines based on your first test drive.
        // Note: The settings here assume direct drive on left and right wheels.  Gear Reduction or 90 Deg drives may require direction flips
        leftback.setDirection(DcMotor.Direction.FORWARD);
        rightback.setDirection(DcMotor.Direction.FORWARD);
        leftfront.setDirection(DcMotor.Direction.FORWARD);
        rightfront.setDirection(DcMotor.Direction.FORWARD);

        // Wait for the game to start (driver presses START)
        waitForStart();
        runtime.reset();

        // run until the end of the match (driver presses STOP)
        while (opModeIsActive()) {

            // Setup a variable for each drive wheel to save power level for telemetry
            double leftbackPower = 0;
            double rightbackPower = 0;
            double rightfrontPower = 0;
            double leftfrontPower = 0;

            double drive = -gamepad1.left_stick_y;
            double turn  =  gamepad1.right_stick_x;
            leftbackPower = Range.clip(drive + turn, -1.0, 1.0);
            leftfrontPower = Range.clip(drive + turn, -1.0, 1.0);
            rightbackPower = Range.clip(drive - turn, -1.0, 1.0);
            rightfrontPower = Range.clip(drive - turn, -1.0, 1.0);

            leftback.setPower(leftbackPower);
            rightback.setPower(rightbackPower);
            leftfront.setPower(leftfrontPower);
            rightfront.setPower(rightfrontPower);


            telemetry.addData("Status", "Run Time: " + runtime.toString());
            telemetry.addData("Motors", "Left Back Motor (%.2f), Right Back Motor (%.2f), Left Front Motor (%.2f), Right Front Motor (%.2f)", leftbackPower, rightbackPower, leftfrontPower, rightfrontPower);
            telemetry.update();

        }
    }
}
