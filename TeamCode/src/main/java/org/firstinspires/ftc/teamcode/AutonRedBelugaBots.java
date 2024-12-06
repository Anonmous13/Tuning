package org.firstinspires.ftc.teamcode;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.util.ElapsedTime;

@Autonomous(name = "auto red")
//@Disabled                            // Comment this out to add to the opmode list
public class AutonRedBelugaBots extends LinearOpMode {

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
        lbDrive.setDirection(DcMotor.Direction.FORWARD);
        rbDrive.setDirection(DcMotor.Direction.FORWARD);


        waitForStart();
        runtime.reset();

        while (opModeIsActive()) {


            lfDrive.setPower(-0.6);
            rfDrive.setPower(0.6);
            lbDrive.setPower(0.6);
            rbDrive.setPower(-0.6);
            sleep(1100);
            lfDrive.setPower(0.6);
            rfDrive.setPower(-0.6);
            lbDrive.setPower(-0.6);
            rbDrive.setPower(0.6);
            sleep(500);
            lfDrive.setPower(0.6);
            rfDrive.setPower(0.6);
            lbDrive.setPower(0.6);
            rbDrive.setPower(0.6);
            sleep(3000);
            break;

//                // Show the elapsed game time and wheel power.
//                telemetry.addData("Status", "Run Time: " + runtime.toString());
//                telemetry.update();
        }
    }
}

