//package org.firstinspires.ftc.teamcode.SimpleEncoders;
//
//import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
//import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
//import com.qualcomm.robotcore.hardware.DcMotor;
//import com.qualcomm.robotcore.hardware.DcMotorSimple;
//
//@TeleOp
//public class PerciseMovementOpMode extends LinearOpMode {
//
//    private DcMotor frontLeftMotor;
//    private DcMotor frontRightMotor;
//    private DcMotor backLeftMotor;
//    private DcMotor backRightMotor;
//
//    double CPR = 537.7;
//
//    double diameter = 3.77953; // Measured in inches; diameter is 96mm
//    double circumference = Math.PI * diameter;
//
//    @Override
//    public void runOpMode() throws InterruptedException {
//
//        frontLeftMotor = hardwareMap.dcMotor.get("lf_drive");
//        frontRightMotor = hardwareMap.dcMotor.get("rf_drive");
//        backLeftMotor = hardwareMap.dcMotor.get("lb_drive");
//        backRightMotor = hardwareMap.dcMotor.get("rb_drive");
//
//        frontLeftMotor.setDirection(DcMotorSimple.Direction.REVERSE);
//        backLeftMotor.setDirection(DcMotorSimple.Direction.REVERSE);
//
//        frontLeftMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
//        frontRightMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
//        backLeftMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
//        backRightMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
//
//        waitForStart();
//        if (isStopRequested()) return;
//
//        while (opModeIsActive()) {
//            move(0, 5, 0);
//            sleep(1000);
//            move(5, 0, 0);
//            sleep(1000);
//            move(0, 0, 180);
//            sleep(1000);
//        }
//
//
//    }
//
//    public void moveX(double x) {
//        move(x, 0, 0);
//    }
//
//    public void moveX(double x, double speed) {
//        move(x, 0, 0, speed);
//    }
//
//    public void moveY(double y) {
//        move(0,y,0);
//    }
//
//    public void moveA(double angle) {
//        move(0,0,angle);
//    }
//
//    public void move(double x, double y, double angle) {
//        move(x, y, angle, 10);
//    }
//
//    public void move(double x, double y, double angle, double speed) {
//
//        double ticksX = x / (circumference / CPR);
//        double ticksY = y / (circumference / CPR);
//        double ticksRotation = angle * 10.5733;
//
//        //Set X and Y to a position that IS not the default. Hint: Use the GoBilda mecanum wheel chart.
//        frontLeftMotor.setTargetPosition((int) (frontLeftMotor.getCurrentPosition() + ticksX + ticksY + ticksRotation));
//        frontRightMotor.setTargetPosition((int) (frontRightMotor.getCurrentPosition() + ticksX - ticksY - ticksRotation));
//        backLeftMotor.setTargetPosition((int) (backLeftMotor.getCurrentPosition() + ticksX - ticksY + ticksRotation));
//        backRightMotor.setTargetPosition((int) (backRightMotor.getCurrentPosition() + ticksX + ticksY - ticksRotation));
//
//        frontLeftMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
//        frontRightMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
//        backLeftMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
//        backRightMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
//
//        frontLeftMotor.setPower(speed);
//        frontRightMotor.setPower(speed);
//        backLeftMotor.setPower(speed);
//        backRightMotor.setPower(speed);
//
//        while (frontLeftMotor.isBusy() || frontRightMotor.isBusy() || backLeftMotor.isBusy() || backRightMotor.isBusy()) {
//            // Wait for the motors to finish moving
//            telemetry.addData("Motor Status", "Moving...");
//            telemetry.addData("Front Left Motor Position", frontLeftMotor.getCurrentPosition());
//            telemetry.addData("Front Right Motor Position", frontRightMotor.getCurrentPosition());
//            telemetry.addData("Back Left Motor Position", backLeftMotor.getCurrentPosition());
//            telemetry.addData("Back Right Motor Position", backRightMotor.getCurrentPosition());
//            telemetry.update();
//        }
//
//        frontLeftMotor.setPower(0);
//        frontRightMotor.setPower(0);
//        backLeftMotor.setPower(0);
//        backRightMotor.setPower(0);
//    }
//}