package org.firstinspires.ftc.teamcode;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;

@Autonomous(name = "Auto Blue - Into The Deep")
public class BasicAutonITD extends LinearOpMode {

    private ElapsedTime runtime = new ElapsedTime();
    private DcMotor lfDrive = null;
    private DcMotor rfDrive = null;
    private DcMotor lbDrive = null;
    private DcMotor rbDrive = null;

    @Override
    public void runOpMode() {
        telemetry.addData("Status", "Initialized");
        telemetry.update();

        lfDrive  = hardwareMap.get(DcMotor.class, "lf_drive");
        rfDrive = hardwareMap.get(DcMotor.class, "rf_drive");
        lbDrive  = hardwareMap.get(DcMotor.class, "lb_drive");
        rbDrive = hardwareMap.get(DcMotor.class, "rb_drive");

        lfDrive.setDirection(DcMotor.Direction.REVERSE);
        rfDrive.setDirection(DcMotor.Direction.FORWARD);
        lbDrive.setDirection(DcMotor.Direction.REVERSE);
        rbDrive.setDirection(DcMotor.Direction.FORWARD);

        waitForStart();
        runtime.reset();

        if (opModeIsActive()) {
            moveForward(0.5, 1000);

            strafeRight(0.5, 800);

            moveForward(0.5, 1500);

            stopMotors();

            telemetry.addData("Status", "Run Time: " + runtime.toString());
            telemetry.update();
        }
    }

    private void moveForward(double power, long duration) {
        lfDrive.setPower(power);
        rfDrive.setPower(power);
        lbDrive.setPower(power);
        rbDrive.setPower(power);
        sleep(duration);
    }

    private void strafeRight(double power, long duration) {
        lfDrive.setPower(power);
        rfDrive.setPower(-power);
        lbDrive.setPower(-power);
        rbDrive.setPower(power);
        sleep(duration);
    }

    private void stopMotors() {
        lfDrive.setPower(0);
        rfDrive.setPower(0);
        lbDrive.setPower(0);
        rbDrive.setPower(0);
    }
}
