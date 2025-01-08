//package org.firstinspires.ftc.teamcode.SimpleEncoders;
//import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
//import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
//import com.qualcomm.robotcore.hardware.DcMotor;
//import com.qualcomm.robotcore.hardware.DcMotorSimple;
//import com.sun.tools.javac.util.List;
//
//import java.sql.Array;
//import java.util.ArrayList;
//
//@TeleOp
//public class OpModeEncoders extends LinearOpMode {
//    @Override
//    public void runOpMode() throws InterruptedException {
//
//        DcMotor frontLeftMotor = hardwareMap.dcMotor.get("lf_drive");
//        DcMotor frontRightMotor = hardwareMap.dcMotor.get("rf_drive");
//        DcMotor backLeftMotor = hardwareMap.dcMotor.get("lb_drive");
//        DcMotor backRightMotor = hardwareMap.dcMotor.get("rb_drive");
//        ArrayList<DcMotor> wheels = new ArrayList<>(List.of(frontLeftMotor, frontRightMotor, backLeftMotor, backRightMotor));
//
//        frontLeftMotor.setDirection(DcMotorSimple.Direction.REVERSE);
//
//        for (DcMotor wheel : wheels) {
//            wheel.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
//            wheel.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
//        }
//
//        waitForStart();
//        if (isStopRequested()) return;
//
//        while (opModeIsActive()) {
//            double y = -gamepad1.left_stick_y;
//            double x = gamepad1.left_stick_x * 1; //imp strafe counter (remember to change if needed)
//            double rx = gamepad1.right_stick_x;
//
//            double deno = Math.max(Math.abs(y) + Math.abs(x) + Math.abs(rx), 1);
////            double frontLeftPower = (x + y + rx) / deno;
////            double backLeftPower = (x - y + rx) / deno;
////            double frontRightPower = (x - y - rx) / deno;
////            double backRightPower = (x + y - rx) / deno;
//
//
////
////            frontLeftMotor.setPower(frontLeftPower);
//////            backLeftMotor.setPower(backLeftPower);
//////            frontRightMotor.setPower(frontRightPower);
//////            backRightMotor.setPower(backRightPower);
//
//            frontLeftMotor.setPower(0.5);
//             backLeftMotor.setPower(0.5);
//            frontRightMotor.setPower(0.5);
//            backRightMotor.setPower(0.5);
//
//            double CPR = 537.7;
//
//            double diameter = 3.77953; // Measured in inches; diameter is 96mm
//            double circumference = Math.PI * diameter;
//
//            int position = frontLeftMotor.getCurrentPosition();
//            double revolutions = position/CPR;
//            double angle = revolutions * 360;
//            double angleNormalized = angle % 360;
//            double distance = circumference * revolutions;
//
//            //Show the position of the motor on telemetry
//            telemetry.addData("Encoder Position", position);
//            telemetry.addData("Encoder Revolutions", revolutions);
//            telemetry.addData("Encoder Angle (Degrees)", angle);
//            telemetry.addData("Encoder Angle - Normalized (Degrees)", angleNormalized);
//            telemetry.addData("Linear Distance", distance);
//            telemetry.update();
//
//        }
//
//    }
//}
//
//
//
