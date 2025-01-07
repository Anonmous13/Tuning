//Aarush Sharma's example.
//package org.firstinspires.ftc.teamcode;
//
//import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
//import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
//import com.qualcomm.robotcore.hardware.CRServo;
//import com.qualcomm.robotcore.hardware.DcMotor;
//import com.qualcomm.robotcore.hardware.DcMotorSimple;
//import com.qualcomm.robotcore.hardware.Gamepad;
//import com.qualcomm.robotcore.util.Range;
//
//@TeleOp(name = "FSM Test")
//public class FSMTest extends LinearOpMode {
//    public static double kP_i = 0.02;
//    public static double kP_o = 0.01;
//
//    public enum States
//    {
//        HOME, // Where everything is idle and retracted.
//        SCORE_SPEC,
//        INTAKE_SAMPLE,
//        GRAB_SAMPLE,
//        TRANSFER,
//    }
//
//    States currentState = States.HOME;
//
//    public static double intakeTarget = 0;
//    public static double outtakeTarget = 0;
//
//    public static double intakeOut = 450;
//    public static double intakeIn = -40;
//
//    public static double outtakeIn = 0;
//    public static double outtakeSpec = 2600;
//
//    CRServo bl;
//    CRServo br;
//
//    @Override
//    public void runOpMode() throws InterruptedException
//    {
//        DcMotor frontLeftMotor = hardwareMap.get(DcMotor.class, "lf_drive");
//        DcMotor backLeftMotor = hardwareMap.get(DcMotor.class, "lb_drive");
//        DcMotor frontRightMotor = hardwareMap.get(DcMotor.class, "rf_drive");
//        DcMotor backRightMotor = hardwareMap.get(DcMotor.class, "rb_drive");
//
//        // Initialize slide motors
//        DcMotor back = hardwareMap.get(DcMotor.class, "back");
//        DcMotor lr = hardwareMap.get(DcMotor.class, "lr");
//        bl = hardwareMap.get(CRServo.class, "br");
//        br = hardwareMap.get(CRServo.class, "bl");
//
//        backLeftMotor.setDirection(DcMotorSimple.Direction.REVERSE);
//
//        back.setDirection(DcMotorSimple.Direction.REVERSE);
//        back.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
//        back.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
//
//        lr.setDirection(DcMotorSimple.Direction.REVERSE);
//        lr.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
//        lr.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
//
//        // Ensure motor resists motion when idle
//        lr.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
//        back.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
//
//        frontLeftMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
//        frontRightMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
//        backLeftMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
//        backRightMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
//
//        waitForStart();
//
//        if (isStopRequested()) return;
//
//        while(opModeIsActive())
//        {
//            switch(currentState)
//            {
//                case HOME:
//                    intakeTarget = intakeIn;
//                    outtakeTarget = outtakeIn;
//
//                    controlSpec(gamepad2);
//
//                    if(gamepad2.y)
//                    {
//                        currentState = States.SCORE_SPEC;
//                    }
//
//                    if(gamepad2.left_bumper)
//                    {
//                        currentState = States.INTAKE_SAMPLE;
//                    }
//                    break;
//                case SCORE_SPEC:
//                    outtakeTarget = outtakeSpec;
//                    controlSpec(gamepad2);
//                    if(gamepad2.a)
//                    {
//                        currentState = States.HOME;
//                    }
//                    break;
//                case INTAKE_SAMPLE:
//                    intakeTarget = intakeOut;
//
//                    if(gamepad2.a)
//                    {
//                        currentState = States.HOME;
//                    }
//                    break;
//            }
//
//            // Here, you should have your drive control.
//            double turn = gamepad1.right_stick_x;
//            double strafe = gamepad1.left_stick_x;
//            double drive = gamepad1.left_stick_y;
//
//            double lfPower = -Range.clip(-strafe - turn + drive, -1.0, 1.0);
//            double rfPower = -Range.clip(-strafe - turn - drive, -1.0, 1.0);
//            double lbPower = Range.clip(-strafe + turn - drive, -1.0, 1.0);
//            double rbPower = -Range.clip(-strafe + turn + drive, -1.0, 1.0);
//
//            frontLeftMotor.setPower(lfPower);
//            backLeftMotor.setPower(lbPower);
//            frontRightMotor.setPower(rfPower);
//            backRightMotor.setPower(rbPower);
//
//            back.setPower(outtakePid(outtakeTarget, back.getCurrentPosition()));
//            lr.setPower(intakePid(intakeTarget, lr.getCurrentPosition()));
//
//            telemetry.addData("Back Slides Position: ", back.getCurrentPosition());
//            telemetry.addData("Intake Slides Position: ", lr.getCurrentPosition());
//            telemetry.addData("State: ", currentState);
//            telemetry.update();
//        }
//    }
//
//    public double intakePid(double target, double current)
//    {
//        return (target-current)*kP_i;
//    }
//
//    public double outtakePid(double target, double current)
//    {
//        return (target-current)*kP_o;
//    }
//
//    public void intakeSpec()
//    {
//        bl.setPower(-1.0);
//        br.setPower(1.0);
//    }
//
//    public void outtakeSpec()
//    {
//        bl.setPower(1.0);
//        br.setPower(-1.0);
//    }
//
//    public void idleSpec()
//    {
//        bl.setPower(0);
//        br.setPower(0);
//    }
//
//    public void controlSpec(Gamepad gamepad)
//    {
//        if(gamepad2.b)
//        {
//            intakeSpec();
//        }
//        else if(gamepad2.x)
//        {
//            outtakeSpec();
//        }
//        else
//        {
//            idleSpec();
//        }
//    }
//}

//Aarush Tripathi's Work, DO NOT PLAGARISE OR TAKE THIS WITHOUT GIVING CREDIT!!!!
package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.Range;

@TeleOp(name = "Aarush FSM")
public class FSMTest extends LinearOpMode
{
    public static double kP_i = 0.02;
    public static double kP_o = 0.01;

    public enum States
    {
        HOME,
        SCORE_SPEC,
        INTAKE_SAMPLE,
        GRAB_SAMPLE,

    }

    States currentState = States.HOME;

    public static double intakeTarget = 0;
    public static double outtakeTarget = 0;

    public static double intakeOut = 490;
    public static double intakeIn = 0;

    public static double outtakeIn = 0;
    public static double outtakeSpec = 2600;

    Servo leftm;
    Servo rightm;
    CRServo bl;
    CRServo br;
    CRServo frontl;
    CRServo frontr;


    @Override
    public void runOpMode() throws InterruptedException
    {
        DcMotor frontLeftMotor = hardwareMap.get(DcMotor.class, "lf_drive");
        DcMotor backLeftMotor = hardwareMap.get(DcMotor.class, "lb_drive");
        DcMotor frontRightMotor = hardwareMap.get(DcMotor.class, "rf_drive");
        DcMotor backRightMotor = hardwareMap.get(DcMotor.class, "rb_drive");

        DcMotor back = hardwareMap.get(DcMotor.class, "back");
        DcMotor lr = hardwareMap.get(DcMotor.class, "lr");
        bl = hardwareMap.get(CRServo.class, "br");
        br = hardwareMap.get(CRServo.class, "bl");

        frontr = hardwareMap.get(CRServo.class, "fr");
        frontl = hardwareMap.get(CRServo.class, "fl");

        leftm = hardwareMap.get(Servo.class, "lm");
        rightm = hardwareMap.get(Servo.class, "rm");


        backLeftMotor.setDirection(DcMotorSimple.Direction.REVERSE);

        back.setDirection(DcMotorSimple.Direction.REVERSE);
        back.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        back.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        lr.setDirection(DcMotorSimple.Direction.REVERSE);
        lr.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        lr.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        //In case the things move, it'll brake to stop. (Logic LMAOA).
        lr.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        back.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        frontLeftMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        frontRightMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        backLeftMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        backRightMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        waitForStart();


        if (isStopRequested()) return;

        while (opModeIsActive())
        {
            switch (currentState)
            {
                case HOME:
                    intakeTarget = intakeIn;
                    outtakeTarget = outtakeIn;

                    controlSpec(gamepad2);
                    controlSample(gamepad2);

                    if (gamepad2.y) {
                        currentState = States.SCORE_SPEC;
                    }

                    if (gamepad2.left_bumper) {
                        currentState = States.INTAKE_SAMPLE;
                    }
                    break;

                case SCORE_SPEC:
                    outtakeTarget = outtakeSpec;
                    controlSpec(gamepad2);
                    controlSample(gamepad2);
                    if (gamepad2.a) {
                        currentState = States.HOME;
                    }
                    break;

                case INTAKE_SAMPLE:
                    intakeTarget = intakeOut;
                    controlIntake(gamepad2);
                    controlSample(gamepad2);

                    if (gamepad2.a) {
                        upIntake();
                        currentState = States.HOME;
                    }
                    break;

            }

            double turn = gamepad1.right_stick_x;
            double strafe = gamepad1.left_stick_x;
            double drive = gamepad1.left_stick_y;

            double lfPower = -Range.clip(-strafe - turn + drive, -1.0, 1.0);
            double rfPower = -Range.clip(-strafe - turn - drive, -1.0, 1.0);
            double lbPower = Range.clip(-strafe + turn - drive, -1.0, 1.0);
            double rbPower = -Range.clip(-strafe + turn + drive, -1.0, 1.0);

            frontLeftMotor.setPower(lfPower);
            backLeftMotor.setPower(lbPower);
            frontRightMotor.setPower(rfPower);
            backRightMotor.setPower(rbPower);

            back.setPower(outtakePid(outtakeTarget, back.getCurrentPosition()));
            lr.setPower(intakePid(intakeTarget, lr.getCurrentPosition()));

            telemetry.addData("Back Slides Position: ", back.getCurrentPosition());
            telemetry.addData("Intake Slides Position: ", lr.getCurrentPosition());
            telemetry.addData("State: ", currentState);
            telemetry.update();
        }
    }
    public void intakeSpec()
    {
        bl.setPower(-1.0);
        br.setPower(1.0);
    }

    public void outtakeSpec()
    {
        bl.setPower(1.0);
        br.setPower(-1.0);
    }

    public void idleSpec()
    {
        bl.setPower(0);
        br.setPower(0);
    }


    public void intakeSample()
    {
        frontl.setPower(-1.0);
        frontr.setPower(1.0);
    }

    public void outtakeSample()
    {
        frontl.setPower(1.0);
        frontr.setPower(-1.0);
    }

    public void idleSample()
    {
        frontl.setPower(0);
        frontr.setPower(0);
    }


    public void upIntake()
    {
        leftm.setPosition(0); //Tune first then tweak the code.
        rightm.setPosition(0); //Tune first then tweak the code.
    }

    public void downIntake()
    {
        leftm.setPosition(0.5); //Tune first then tweak the code.
        rightm.setPosition(0.5); //Tune first then tweak the code.
    }
    public double intakePid(double target, double current)
    {
        return (target - current) * kP_i;
    }

    public double outtakePid(double target, double current)
    {
        return (target - current) * kP_o;
    }

    public void controlSample(Gamepad gamepad)
    {
        if (gamepad2.y)
        {
            intakeSample();
        }
        else if (gamepad2.a)
        {
            outtakeSample();
        }
        else
        {
            idleSample();
        }
    }

    public void controlIntake(Gamepad gamepad)
    {
        if (gamepad2.dpad_up) {
            upIntake();
        }
        else if (gamepad2.dpad_down)
        {
            downIntake();
        }

    }


    public void controlSpec(Gamepad gamepad)
    {
        if (gamepad2.b)
        {
            intakeSpec();
        }
        else if (gamepad2.x)
        {
            outtakeSpec();
        }
        else
        {
            idleSpec();
        }
    }

}
