//   /* Copyright (c) 2017 FIRST. All rights reserved.
//    *
//    * Redistribution and use in source and binary forms, with or without modification,
//    * are permitted (subject to the limitations in the disclaimer below) provided that
//    * the following conditions are met:
//    *
//    * Redistributions of source code must retain the above copyright notice, this list
//    * of conditions and the following disclaimer.
//    *
//    * Redistributions in binary form must reproduce the above copyright notice, this
//    * list of conditions and the following disclaimer in the documentation and/or
//    * other materials provided with the distribution.
//    *
//    * Neither the name of FIRST nor the names of its contributors may be used to endorse or
//    * promote products derived from this software without specific prior written permission.
//    *
//    * NO EXPRESS OR IMPLIED LICENSES TO ANY PARTY'S PATENT RIGHTS ARE GRANTED BY THIS
//    * LICENSE. THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS
//    * "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO,
//    * THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
//    * ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR CONTRIBUTORS BE LIABLE
//    * FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL
//    * DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR
//    * SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER
//    * CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY,
//    * OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE
//    * OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
//    */
//
//   package org.firstinspires.ftc.teamcode;
//
////
////   import com.acmerobotics.dashboard.FtcDashboard;
////   import com.acmerobotics.dashboard.telemetry.MultipleTelemetry;
////   import com.arcrobotics.ftclib.controller.PIDController;
////   import com.arcrobotics.ftclib.controller.PIDFController;
//
//   //import com.qualcomm.robotcore.eventloop.opmode.Disabled;
//   import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
//   import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
//   import com.qualcomm.robotcore.hardware.CRServo;
//   import com.qualcomm.robotcore.hardware.DcMotor;
//   //import com.qualcomm.robotcore.hardware.DcMotorSimple;
//   import com.qualcomm.robotcore.hardware.Servo;
//   import com.qualcomm.robotcore.util.ElapsedTime;
//   import com.qualcomm.robotcore.util.Range;
//
//
//   /*
//    * This file contains an minimal example of a Linear "OpMode". An OpMode is a 'program' that runs in either
//    * the autonomous or the teleop period of an FTC match. The names of OpModes appear on the menu
//    * of the FTC Driver Station. When a selection is made from the menu, the corresponding OpMode
//    * class is instantiated on the Robot Controller and executed.
//    *
//    * This particular OpMode just executes a basic Tank Drive Teleop for a two wheeled robot
//    * It includes all the skeletal structure that all linear OpModes contain.
//    *
//    * Use Android Studio to Copy this Class, and Paste it into your team's code folder with a new name.
//    * Remove or comment out the @Disabled line to add this OpMode to the Driver Station OpMode list
//    */
//
//   @TeleOp(name="tele nov 17", group="Linear OpMode")
////@Disabled
//   public class NovAuton extends LinearOpMode {
//
//       // Declare OpMode members.
//       private ElapsedTime runtime = new ElapsedTime();
//       private DcMotor lf_drive = null;
//       private DcMotor rf_drive = null;
//       private DcMotor lb_drive = null;
//       private DcMotor rb_drive = null;
//
//       private DcMotor slideup = null;
//       CRServo gecoleft;
//       CRServo gecoright;
//       Servo rightbucket;
//       Servo leftbucket;
//       Servo rightslide;
//       CRServo clawrotationservo;
//       Servo clawOpen;
//       DcMotor slidefoward;
//
//       //private PIDController controller;
//
//
//       @Override
//       public void runOpMode() {
//           telemetry.addData("Status", "Initialized");
//           telemetry.update();
//
//           // Initialize the hardware variables. Note that the strings used here as parameters
//           // to 'get' must correspond to the names assigned during the robot configuration
//           // step (using the FTC Robot Controller app on the phone).
//           lf_drive = hardwareMap.get(DcMotor.class, "lf_drive");
//           rf_drive = hardwareMap.get(DcMotor.class, "rf_drive");
//           lb_drive = hardwareMap.get(DcMotor.class, "lb_drive");
//           rb_drive = hardwareMap.get(DcMotor.class, "rb_drive");
//           slideup = hardwareMap.get(DcMotor.class, "slideup");
//           gecoright = hardwareMap.get(CRServo.class, "gecoright");
//           gecoleft = hardwareMap.get(CRServo.class, "gecoleft");
//           rightbucket = hardwareMap.get(Servo.class, "rightbucket");
//           leftbucket = hardwareMap.get(Servo.class, "leftbucket");
//           rightslide = hardwareMap.get(Servo.class, "rightslide");
//           clawrotationservo = hardwareMap.get(CRServo.class, "clawrotationservo");
//           clawOpen = hardwareMap.get(Servo.class, "clawOpen");
//           slidefoward = hardwareMap.get(DcMotor.class, "slidefoward");
//
//
//           slideup.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
//
//           // To drive forward, most robots need the motor on one side to be reversed, because the axles point in opposite directions.
//           // Pushing the left stick forward MUST make robot go forward. So adjust these two lines based on your first test drive.
//           // Note: The settings here assume direct drive on left and right wheels.  Gear Reduction or 90 Deg drives may require direction flips
//           lf_drive.setDirection(DcMotor.Direction.REVERSE);
//           lb_drive.setDirection(DcMotor.Direction.REVERSE);
//           slidefoward.setDirection(DcMotor.Direction.REVERSE);
//
//
//
//
//           // Wait for the game to start (driver presses PLAY)
//           waitForStart();
//           runtime.reset();
//
//           // run until the end of the match (driver presses STOP)
//           while (opModeIsActive()) {
//
//               // Setup a variable for each drive wheel to save power level for telemetry
//               double lfPower;
//               double rfPower;
//               double rbPower;
//               double lbPower;
//
//               double poweslide;
//               double powerslidefoward;
//
//               // Choose to drive using either Tank Mode, or POV Mode
//               // Comment out the method that's not used.  The default below is POV.
//
//               // POV Mode uses left stick to go forward, and right stick to turn.
//               // - This uses basic math to combine motions and is easier to drive straight.
//               double drive = gamepad1.right_stick_x * 0.75;
//               double strafe = gamepad1.left_stick_x * 0.75;
//               double turn = -gamepad1.left_stick_y * 0.75;
//               lfPower = Range.clip(turn + strafe + drive, -1.0, 1.0);
//               rfPower = Range.clip(turn - strafe - drive, -1.0, 1.0);
//               lbPower = Range.clip(turn - strafe + drive, -1.0, 1.0);
//               rbPower = Range.clip(turn + strafe - drive, -1.0, 1.0);
//
//
//               double t = gamepad2.right_stick_x;
//               double d = -gamepad2.left_stick_y;
//
//               poweslide = Range.clip(d, -1.0, 1.0);
//               powerslidefoward = Range.clip(t, -1.0, 1.0);
//
//
//               lf_drive.setPower(lfPower);
//               rf_drive.setPower(rfPower);
//               lb_drive.setPower(lbPower);
//               rb_drive.setPower(rbPower);
//               slideup.setPower(poweslide);
//               slidefoward.setPower(powerslidefoward);
//
//
//               if (poweslide == 0) {
//                   slideup.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
//               }
//               if (gamepad2.dpad_right) {
//                   gecoleft.setPower(-1);
//                   gecoright.setPower(1);
//               }
//               if (gamepad2.dpad_left) {
//                   gecoleft.setPower(1);
//                   gecoright.setPower(-1);
//               } else {
//                   gecoleft.setPower(0);
//                   gecoright.setPower(0);
//               }
//               if (gamepad2.y) {
//                   clawrotationservo.setPower(0.1);//add cr
//               }
//               if(gamepad2.a){
//                   clawrotationservo.setPower(-0.1);
//               }
//               if(gamepad2.left_stick_button){
//                   clawrotationservo.setPower(0);
//               }
//
//
//               if (gamepad2.dpad_up) {
//                   rightslide.setPosition(0.2);
//               }
//               if (gamepad2.dpad_down) {
//                   rightslide.setPosition(1);
//               }
//
//
//
//               if (gamepad2.right_bumper) {
//                   rightbucket.setPosition(1);
//                   leftbucket.setPosition(-1);
//               }
//               if (gamepad2.left_bumper) {
//                   rightbucket.setPosition(-1);
//                   leftbucket.setPosition(1);
//               }
//               if(gamepad2.x){
//                   clawOpen.setPosition(1);
//               }
//               if(gamepad2.b){
//                   clawOpen.setPosition(0.45);
//               }
//
//
//
//
//           }
//
//       }
//   }