package org.firstinspires.ftc.teamcode;

/* Copyright (c) 2023 FIRST. All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without modification,
 * are permitted (subject to the limitations in the disclaimer below) provided that
 * the following conditions are met:
 *
 * Redistributions of source code must retain the above copyright notice, this list
 * of conditions and the following disclaimer.
 *
 * Redistributions in binary form must reproduce the above copyright notice, this
 * list of conditions and the following disclaimer in the documentation and/or
 * other materials provided with the distribution.
 *
 * Neither the name of FIRST nor the names of its contributors may be used to endorse or
 * promote products derived from this software without specific prior written permission.
 *
 * NO EXPRESS OR IMPLIED LICENSES TO ANY PARTY'S PATENT RIGHTS ARE GRANTED BY THIS
 * LICENSE. THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS
 * "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO,
 * THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 * ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR CONTRIBUTORS BE LIABLE
 * FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL
 * DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR
 * SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER
 * CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY,
 * OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE
 * OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;


/*
 * This OpMode illustrates using a camera to locate and drive towards a specific AprilTag.
 * The code assumes a Holonomic (Mecanum or X Drive) Robot.
 *
 * For an introduction to AprilTags, see the ftc-docs link below:
 * https://ftc-docs.firstinspires.org/en/latest/apriltag/vision_portal/apriltag_intro/apriltag-intro.html
 *
 * When an AprilTag in the TagLibrary is detected, the SDK provides location and orientation of the tag, relative to the camera.
 * This information is provided in the "ftcPose" member of the returned "detection", and is explained in the ftc-docs page linked below.
 * https://ftc-docs.firstinspires.org/apriltag-detection-values
 *
 * The drive goal is to rotate to keep the Tag centered in the camera, while strafing to be directly in front of the tag, and
 * driving towards the tag to achieve the desired distance.
 * To reduce any motion blur (which will interrupt the detection process) the Camera exposure is reduced to a very low value (5mS)
 * You can determine the best Exposure and Gain values by using the ConceptAprilTagOptimizeExposure OpMode in this Samples folder.
 *
 * The code assumes a Robot Configuration with motors named: leftfront_drive and rightfront_drive, leftback_drive and rightback_drive.
 * The motor directions must be set so a positive power goes forward on all wheels.
 * This sample assumes that the current game AprilTag Library (usually for the current season) is being loaded by default,
 * so you should choose to approach a valid tag ID (usually starting at 0)
 *
 * Under manual control, the left stick will move forward/back & left/right.  The right stick will rotate the robot.
 * Manually drive the robot until it displays Target data on the Driver Station.
 *
 * Press and hold the *Left Bumper* to enable the automatic "Drive to target" mode.
 * Release the Left Bumper to return to manual driving mode.
 *
 * Under "Drive To Target" mode, the robot has three goals:
 * 1) Turn the robot to always keep the Tag centered on the camera frame. (Use the Target Bearing to turn the robot.)
 * 2) Strafe the robot towards the centerline of the Tag, so it approaches directly in front  of the tag.  (Use the Target Yaw to strafe the robot)
 * 3) Drive towards the Tag to get to the desired distance.  (Use Tag Range to drive the robot forward/backward)
 *
 * Use DESIRED_DISTANCE to set how close you want the robot to get to the target.
 * Speed and Turn sensitivity can be adjusted using the SPEED_GAIN, STRAFE_GAIN and TURN_GAIN constants.
 *
 * Use Android Studio to Copy this Class, and Paste it into the TeamCode/src/main/java/org/firstinspires/ftc/teamcode folder.
 * Remove or comment out the @Disabled line to add this OpMode to the Driver Station OpMode list.
 *
 */

@Autonomous(name="obsevetory zone auto", group = "Concept")
//@Disabled
public class NovAutoWorks extends LinearOpMode
{
    // Adjust these numbers to suit your robot.
    final double DESIRED_DISTANCE = 6; //  this is how close the camera should get to the target (inches)

    //  Set the GAIN constants to control the relationship between the measured position error, and how much power is
    //  applied to the drive motors to correct the error.
    //  Drive = Error * Gain    Make these values smaller for smoother control, or larger for a more aggressive response.
    final double SPEED_GAIN  =  0.038  ;   //  Forward Speed Control "Gain". eg: Ramp up to 50% power at a 25 inch error.   (0.50 / 25.0)
    final double STRAFE_GAIN =  0.015 ;   //  Strafe Speed Control "Gain".  eg: Ramp up to 25% power at a 25 degree Yaw error.   (0.25 / 25.0)
    final double TURN_GAIN   =  0.01  ;   //  Turn Control "Gain".  eg: Ramp up to 25% power at a 25 degree error. (0.25 / 25.0)

    final double MAX_AUTO_SPEED = 0.5;   //  Clip the approach speed to this max value (adjust for your robot)
    final double MAX_AUTO_STRAFE= 0.5;   //  Clip the approach speed to this max value (adjust for your robot)
    final double MAX_AUTO_TURN  = 0.3;   //  Clip the turn speed to this max value (adjust for your robot)

    private DcMotor lf_drive   = null;  //  Used to control the left front drive wheel
    private DcMotor rf_drive  = null;  //  Used to control the right front drive wheel
    private DcMotor lb_drive    = null;  //  Used to control the left back drive wheel
    private DcMotor rb_drive   = null;  //  Used to control the right back drive wheel
    private DcMotor slideup = null;
    CRServo gecoleft;
    CRServo gecoright;
    private ElapsedTime runtime = new ElapsedTime();





    static final double     COUNTS_PER_MOTOR_REV    = 537.7 ;    // eg: GOBILDA Motor Encoder
    static final double     DRIVE_GEAR_REDUCTION    = 1.0 ;     // No External Gearing.
    static final double     WHEEL_DIAMETER_MM   = 96.0 ;      // For figuring circumference
    static final double     COUNTS_PER_MM         = (COUNTS_PER_MOTOR_REV * DRIVE_GEAR_REDUCTION) /
            (WHEEL_DIAMETER_MM * 3.1415);

   /* private DcMotor lfdrive  = null;  //  Used to control the left front drive wheel
    private DcMotor rfdrive   = null;  //  Used to control the right front drive wheel
    private DcMotor lbdrive   = null;  //  Used to control the left back drive wheel
    private DcMotor rbdrive   = null;  //  Used to control the right back drive wheel
    Servo autoarm = null;
    Servo AutoP = null;*/







    @Override public void runOpMode()
    {


        // Initialize the hardware variables. Note that the strings used here as parameters
        // to 'get' must match the names assigned during the robot configuration.
        // step (using the FTC Robot Controller app on the phone).
        lf_drive  = hardwareMap.get(DcMotor.class, "lf_drive");
        rf_drive = hardwareMap.get(DcMotor.class, "rf_drive");
        lb_drive  = hardwareMap.get(DcMotor.class, "lb_drive");
        rb_drive = hardwareMap.get(DcMotor.class, "rb_drive");
        slideup = hardwareMap.get(DcMotor.class, "slideup");
        gecoleft = hardwareMap.get(CRServo.class, "gecoleft");
        gecoright = hardwareMap.get(CRServo.class, "gecoright");


        // To drive forward, most robots need the motor on one side to be reversed, because the axles point in opposite directions.
        // When run, this OpMode should start both motors driving forward. So adjust these two lines based on your first test drive.
        // Note: The settings here assume direct drive on left and right wheels.  Gear Reduction or 90 Deg drives may require direction flips
        lf_drive.setDirection(DcMotor.Direction.REVERSE);
        rf_drive.setDirection(DcMotor.Direction.FORWARD);
        lb_drive.setDirection(DcMotor.Direction.REVERSE);
        rb_drive.setDirection(DcMotor.Direction.FORWARD);

        prepareEncoder();





        waitForStart();


        if (opModeIsActive()) {


            lf_drive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            lb_drive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            rf_drive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            rb_drive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

            lf_drive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            lb_drive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            rf_drive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            rb_drive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

            double Power = 0.3;

            set(Power);

            encoderDriveForwardInches(10);

            if (opModeIsActive()) {
                slideup.setPower(1);
                sleep(500);
                slideup.setPower(0);
                sleep(500);
            }
            encoderDriveForwardInches(1);

            if (opModeIsActive()) {
                slideup.setPower(-1);
                sleep(500);
            }

            if (opModeIsActive()) {
                gecoleft.setPower(-1);
                gecoright.setPower(1);
                slideup.setPower(-1);
                sleep(800);
            }

            encoderDriveBackwardInches(10);

            encoderDriveRightInches(5);

            //
            /*if (opModeIsActive()) {
                slideup.setPower(-1);
                sleep(1000);
            }*/





            lf_drive.setPower(0);
            lb_drive.setPower(0);
            rf_drive.setPower(0);
            rb_drive.setPower(0);

            lf_drive.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
            rf_drive.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
            lb_drive.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
            rb_drive.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        }
    }

    private void set(double power) {
    }

    /**
     * Move robot according to desired axes motions
     * <p>
     * Positive X is forward
     * <p>
     * Positive Y is strafe left
     * <p>
     * Positive Yaw is counter-clockwise
     */


    /**
     * Initialize the AprilTag processor.
     */


    /*
     Manually set the camera gain and exposure.
     This can only be called AFTER calling initAprilTag(), and only works for Webcams;
    */





    public void prepareEncoder() {

        //  arm.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        lf_drive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        lb_drive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        rf_drive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        rb_drive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        lf_drive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        lb_drive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        rf_drive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        rb_drive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

    }

    public void encoderDriveForwardInches(double inches) {
        double TotalTicks = inches*COUNTS_PER_MM*25.4;
        lf_drive.setTargetPosition((int)TotalTicks);
        lb_drive.setTargetPosition((int)TotalTicks);
        rf_drive.setTargetPosition((int)TotalTicks);
        rb_drive.setTargetPosition((int)TotalTicks);
        lf_drive.setPower(0.75);
        lb_drive.setPower(0.75);
        rf_drive.setPower(0.75);
        rb_drive.setPower(0.75);
        lf_drive.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        lb_drive.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        rf_drive.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        rb_drive.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        sleep((long)(inches*25.4*2));
        lf_drive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        lb_drive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        rf_drive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        rb_drive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

    }
    public void encoderDriveBackwardInches(double Inches) {
        double TotalTicks = Inches*COUNTS_PER_MM*25.4;
        lf_drive.setTargetPosition(-((int)TotalTicks));
        lb_drive.setTargetPosition(-((int)TotalTicks));
        rf_drive.setTargetPosition(-((int)TotalTicks));
        rb_drive.setTargetPosition(-((int)TotalTicks));
        lf_drive.setPower(0.5);
        lb_drive.setPower(0.5);
        rf_drive.setPower(0.5);
        rb_drive.setPower(0.5);
        lf_drive.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        lb_drive.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        rf_drive.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        rb_drive.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        sleep((long)(Inches*2*25.4));
        lf_drive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        lb_drive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        rf_drive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        rb_drive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
    }
    public void encoderDriveBackwardInchesSlow(double Inches) {
        double TotalTicks = Inches*COUNTS_PER_MM*25.4;
        lf_drive.setTargetPosition(-((int)TotalTicks));
        lb_drive.setTargetPosition(-((int)TotalTicks));
        rf_drive.setTargetPosition(-((int)TotalTicks));
        rb_drive.setTargetPosition(-((int)TotalTicks));
        lf_drive.setPower(0.1);
        lb_drive.setPower(0.1);
        rf_drive.setPower(0.1);
        rb_drive.setPower(0.1);
        lf_drive.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        lb_drive.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        rf_drive.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        rb_drive.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        sleep((long)(Inches*2*25.4));
        lf_drive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        lb_drive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        rf_drive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        rb_drive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
    }
    public void encoderDriveRightInches(double inches) {
        double TotalTicks = inches*COUNTS_PER_MM*25.4;
        lf_drive.setTargetPosition(((int)TotalTicks));
        lb_drive.setTargetPosition(-((int)TotalTicks));
        rf_drive.setTargetPosition(-((int)TotalTicks));
        rb_drive.setTargetPosition(((int)TotalTicks));
        lf_drive.setPower(0.6);
        lb_drive.setPower(0.6);
        rf_drive.setPower(0.6);
        rb_drive.setPower(0.6);
        lf_drive.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        lb_drive.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        rf_drive.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        rb_drive.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        sleep((long)(inches*2*25.4));
        lf_drive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        lb_drive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        rf_drive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        rb_drive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
    }

    public void encoderConcentricRightStrafe(float turnFactor, double inches) {
        double TotalTicks = inches*COUNTS_PER_MM*25.4;
        lf_drive.setTargetPosition(-((int)TotalTicks));
        lb_drive.setTargetPosition(-((int)TotalTicks));
        rf_drive.setTargetPosition(-((int)TotalTicks));
        rb_drive.setTargetPosition(((int)TotalTicks));
        lf_drive.setPower(0.75 - (turnFactor / 10));
        lb_drive.setPower(0.75 - (turnFactor / 10));
        rf_drive.setPower(0.75 + (turnFactor / 10));
        rb_drive.setPower(0.75 + (turnFactor / 10));

        lf_drive.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        lb_drive.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        rf_drive.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        rb_drive.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        sleep((long)(inches*2*25.4));
        lf_drive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        lb_drive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        rf_drive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        rb_drive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
    }
    public void encoderBackLeftStrafe(double inches) {
        double TotalTicks = inches*COUNTS_PER_MM*25.4;
        lf_drive.setTargetPosition(-((int)TotalTicks));
        lb_drive.setTargetPosition(((int)TotalTicks));
        rf_drive.setTargetPosition(((int)TotalTicks));
        rb_drive.setTargetPosition(-((int)TotalTicks));
        lf_drive.setPower(1);
        lb_drive.setPower(0.2);
        rf_drive.setPower(0.2);
        rb_drive.setPower(1);

        lf_drive.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        lb_drive.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        rf_drive.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        rb_drive.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        sleep((long)(inches*2*25.4));
        lf_drive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        lb_drive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        rf_drive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        rb_drive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
    }
    public void encoderDriveLeftInches(double inches) {
        double TotalTicks = inches*COUNTS_PER_MM*25.4;
        lf_drive.setTargetPosition(-((int)TotalTicks));
        lb_drive.setTargetPosition(((int)TotalTicks));
        rf_drive.setTargetPosition(((int)TotalTicks));
        rb_drive.setTargetPosition(-((int)TotalTicks));
        lf_drive.setPower(1);
        lb_drive.setPower(1);
        rf_drive.setPower(1);
        rb_drive.setPower(1);

        lf_drive.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        lb_drive.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        rf_drive.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        rb_drive.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        sleep((long)(inches*2*25.4));
        lf_drive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        lb_drive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        rf_drive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        rb_drive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
    }
    public void TurnLeft(double degrees) {
        double TotalTicks = (degrees/90)*COUNTS_PER_MOTOR_REV*4.2333333333;
        lf_drive.setTargetPosition(-((int)TotalTicks));
        lb_drive.setTargetPosition(-((int)TotalTicks));
        rf_drive.setTargetPosition(((int)TotalTicks));
        rb_drive.setTargetPosition(((int)TotalTicks));
        lf_drive.setPower(0.5);
        lb_drive.setPower(0.5);
        rf_drive.setPower(0.5);
        rf_drive.setPower(0.5);
        rb_drive.setPower(0.5);

        lf_drive.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        lb_drive.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        rf_drive.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        rb_drive.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        sleep((long)(degrees/90*960));
        lf_drive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        lb_drive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        rf_drive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        rb_drive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
    }
    public void TurnRight(double degrees) {
        double TotalTicks = (degrees/90)*COUNTS_PER_MOTOR_REV*4.2333333333;
        lf_drive.setTargetPosition(((int)TotalTicks));
        lb_drive.setTargetPosition(((int)TotalTicks));
        rf_drive.setTargetPosition(-((int)TotalTicks));
        rb_drive.setTargetPosition(-((int)TotalTicks));
        lf_drive.setPower(0.4);
        lb_drive.setPower(0.4);
        rf_drive.setPower(0.4);
        rb_drive.setPower(0.4);

        lf_drive.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        lb_drive.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        rf_drive.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        rb_drive.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        sleep((long)(degrees/90*960));
        lf_drive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        lb_drive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        rf_drive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        rb_drive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
    }
//    public void dropPixel(boolean facing){
//        if(facing){
//            wrist.setPosition(y); //y = some value such that the wrist is facing in the right direction
//            r_arm.setPower(x); //x = some value such that it hits the backboard
//            grip.setPosition(0.4);
//        }
//        else{
//            wrist.setPosition(y);
//            r_arm.setPower(x);
//            grip.setPosition(0.4);
//        }

}


