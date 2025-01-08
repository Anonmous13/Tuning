//package org.firstinspires.ftc.teamcode;
//
//import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
//import com.qualcomm.robotcore.hardware.DcMotor;
//
//@com.qualcomm.robotcore.eventloop.opmode.TeleOp(name="Basic Drive", group="Linear Opmode")
//public class IAFCode extends LinearOpMode {
//
//    private DcMotor frontLeftMotor;
//    private DcMotor frontRightMotor;
//    private DcMotor backLeftMotor;
//    private DcMotor backRightMotor;
//
//    @Override
//    public void runOpMode() {
//        frontLeftMotor = hardwareMap.get(DcMotor.class, "lf_drive");
//        frontRightMotor = hardwareMap.get(DcMotor.class, "rf_drive");
//        backLeftMotor = hardwareMap.get(DcMotor.class, "lb_drive");
//        backRightMotor = hardwareMap.get(DcMotor.class, "rb_drive");
//
//        frontLeftMotor.setDirection(DcMotor.Direction.FORWARD);
//        frontRightMotor.setDirection(DcMotor.Direction.REVERSE);
//        backLeftMotor.setDirection(DcMotor.Direction.FORWARD);
//        backRightMotor.setDirection(DcMotor.Direction.REVERSE);
//
//        waitForStart();
//
//        while (opModeIsActive()) {
//            double left  = -gamepad1.left_stick_y;
//            double right = -gamepad1.right_stick_y;
//            double strafe = (gamepad1.right_stick_x+gamepad1.left_stick_x)/2;
//
//            double frontLeftPower = left;
//            double frontRightPower = right;
//            double backLeftPower = left;
//            double backRightPower = right;
//
//            frontLeftMotor.setPower(frontLeftPower);
//            frontRightMotor.setPower(frontRightPower);
//            backLeftMotor.setPower(backLeftPower);
//            backRightMotor.setPower(backRightPower);
//
//            telemetry.addData("Front Left Motor Power", frontLeftPower);
//            telemetry.addData("Front Right Motor Power", frontRightPower);
//            telemetry.addData("Back Left Motor Power", backLeftPower);
//            telemetry.addData("Back Right Motor Power", backRightPower);
//            telemetry.update();
//        }
//    }
//}
