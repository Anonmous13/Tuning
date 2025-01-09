//package org.firstinspires.ftc.teamcode;
//
//import com.pedropathing.follower.Follower;
//import com.pedropathing.pathgen.BezierCurve;
//import com.pedropathing.pathgen.BezierLine;
//import com.pedropathing.pathgen.PathChain;
//import com.pedropathing.pathgen.PathBuilder;
//import com.pedropathing.pathgen.Point;
//import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
//import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
//import com.qualcomm.robotcore.hardware.DcMotor;
//
//@Autonomous(name = "5 Spec Red")
//public class fiveSpecPathChainRed extends LinearOpMode {
//
//    private DcMotor back;
//    private static final double kP_o = 0.01;
//    private static final int TOLERANCE = 10;
//
//    // PID control for the slide motor
//    public double outtakePid(double target, double current) {
//        return (target - current) * kP_o;
//    }
//
//    // Moves the slide to the target position
//    private void moveSlideToPosition(int targetPosition) {
//        while (Math.abs(targetPosition - back.getCurrentPosition()) > TOLERANCE && opModeIsActive()) {
//            double power = outtakePid(targetPosition, back.getCurrentPosition());
//            back.setPower(power);
//            telemetry.addData("Target", targetPosition);
//            telemetry.addData("Current", back.getCurrentPosition());
//            telemetry.addData("Power", power);
//            telemetry.update();
//        }
//        back.setPower(0);
//    }
//
//    @Override
//    public void runOpMode() throws InterruptedException {
//        back = hardwareMap.get(DcMotor.class, "back");
//        back.setDirection(DcMotor.Direction.REVERSE);
//        back.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
//        back.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
//        back.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
//
//        Follower follower = new Follower(hardwareMap);
//        waitForStart();
//
//        if (opModeIsActive()) {
//            // Define individual path chains
//            PathChain pathChain1 = new PathChain(
//                    .setLinearHeadingInterpolation(Math.toRadians(-180), Math.toRadians(-180))
//                    .addPath(
//                            // Line 1 (forward to spec bar)
//                            new BezierLine(
//                                    new Point(8.000, -65.000, Point.CARTESIAN),  // Line point
//                                    new Point(38.000, -65.000, Point.CARTESIAN) // Line point
//                            )
//                    ))
//
//            PathChain pathChain2 = new PathChain()
//                    .addPath(new BezierCurve(
//                            new Point(38.000, -65.000, Point.CARTESIAN),
//                            new Point(0.000, -29.023, Point.CARTESIAN),
//                            new Point(79.575, -37.794, Point.CARTESIAN),
//                            new Point(59.538, -24.692, Point.CARTESIAN)
//                    ));
//
//            PathChain pathChain3 = new PathChain()
//                    .addPath(new BezierLine(
//                            new Point(59.538, -24.692, Point.CARTESIAN),
//                            new Point(12.692, -24.462, Point.CARTESIAN)
//                    ));
//
//            PathChain pathChain4 = new PathChain()
//                    .addPath(new BezierCurve(
//                            new Point(12.692, -24.462, Point.CARTESIAN),
//                            new Point(51.462, -31.385, Point.CARTESIAN),
//                            new Point(63.231, -13.615, Point.CARTESIAN)
//                    ));
//
//            PathChain pathChain5 = new PathChain()
//                    .addPath(new BezierLine(
//                            new Point(63.231, -13.615, Point.CARTESIAN),
//                            new Point(12.462, -12.923, Point.CARTESIAN)
//                    ));
//
//            PathChain pathChain6 = new PathChain()
//                    .addPath(new BezierCurve(
//                            new Point(12.462, -12.923, Point.CARTESIAN),
//                            new Point(39.923, -23.769, Point.CARTESIAN),
//                            new Point(63.692, -8.538, Point.CARTESIAN)
//                    ));
//
//            PathChain pathChain7 = new PathChain()
//                    .addPath(new BezierLine(
//                            new Point(63.692, -8.538, Point.CARTESIAN),
//                            new Point(21.462, -8.308, Point.CARTESIAN)
//                    ));
//
//            PathChain pathChain8 = new PathChain()
//
//                    .addPath(new BezierCurve(
//                            new Point(21.462, -8.308, Point.CARTESIAN),
//                            new Point(29.538, -19.846, Point.CARTESIAN),
//                            new Point(9.923, -26.538, Point.CARTESIAN)
//                    ))
//                    .build();
//
//            PathChain pathChain9 = new PathChain()
//                    .addPath(new BezierCurve(
//                            new Point(9.923, -26.538, Point.CARTESIAN),
//                            new Point(16.154, -54.231, Point.CARTESIAN),
//                            new Point(37.154, -60.692, Point.CARTESIAN)
//                    ));
//
//            PathChain pathChain10 = new PathChain()
//                    .addPath(new BezierCurve(
//                            new Point(37.154, -60.692, Point.CARTESIAN),
//                            new Point(26.538, -35.769, Point.CARTESIAN),
//                            new Point(10.846, -26.538, Point.CARTESIAN)
//                    ));
//
//            PathChain pathChain11 = new PathChain()
//                    .addPath(new BezierCurve(
//                            new Point(10.846, -26.538, Point.CARTESIAN),
//                            new Point(13.154, -49.615, Point.CARTESIAN),
//                            new Point(37.154, -69.462, Point.CARTESIAN)
//                    ));
//
//            PathChain pathChain12 = new PathChain()
//                    .addPath(new BezierCurve(
//                            new Point(37.154, -69.462, Point.CARTESIAN),
//                            new Point(26.950, -42.738, Point.CARTESIAN),
//                            new Point(11.077, -26.077, Point.CARTESIAN)
//                    ));
//
//            PathChain pathChain13 = new PathChain()
//                    .addPath(new BezierCurve(
//                            new Point(11.077, -26.077, Point.CARTESIAN),
//                            new Point(20.769, -71.308, Point.CARTESIAN),
//                            new Point(37.846, -74.077, Point.CARTESIAN)
//                    ));
//
//            PathChain pathChain14 = new PathChain()
//                    .addPath(new BezierLine(
//                            new Point(37.846, -74.077, Point.CARTESIAN),
//                            new Point(10.154, -12.923, Point.CARTESIAN)
//                    ));
//
//            // Execution loop with states
//            int state = 0;
//
//            while (opModeIsActive()) {
//                switch (state) {
//                    case 0:
//                        moveSlideToPosition(2600); // Move to hang position
//                        state++;
//                        break;
//                    case 1:
//                        follower.followPath(pathChain1.build());
//                        state++;
//                        break;
//                    case 2:
//                        follower.followPath(pathChain2.build());
//                        state++;
//                        break;
//                    case 3:
//                        follower.followPath(pathChain3.build());
//                        state++;
//                        break;
//                    case 4:
//                        follower.followPath(pathChain4.build());
//                        state++;
//                        break;
//                    case 5:
//                        follower.followPath(pathChain5.build());
//                        state++;
//                        break;
//                    case 6:
//                        follower.followPath(pathChain6.build());
//                        state++;
//                        break;
//                    case 7:
//                        follower.followPath(pathChain7.build());
//                        state++;
//                        break;
//                    case 8:
//                        follower.followPath(pathChain8.build());
//                        state++;
//                        break;
//                    case 9:
//                        follower.followPath(pathChain9.build());
//                        state++;
//                        break;
//                    case 10:
//                        follower.followPath(pathChain10.build());
//                        state++;
//                        break;
//                    case 11:
//                        follower.followPath(pathChain11.build());
//                        state++;
//                        break;
//                    case 12:
//                        follower.followPath(pathChain12.build());
//                        state++;
//                        break;
//                    case 13:
//                        follower.followPath(pathChain13.build());
//                        state++;
//                        break;
//                    case 14:
//                        follower.followPath(pathChain14.build());
//                        state++;
//                        break;
//                    default:
//                        stop();
//                        break;
//                }
//            }
//        }
//    }
//}
