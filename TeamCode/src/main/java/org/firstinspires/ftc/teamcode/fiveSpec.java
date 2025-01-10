package org.firstinspires.ftc.teamcode;

import com.pedropathing.follower.Follower;
import com.pedropathing.localization.Pose;
import com.pedropathing.pathgen.BezierCurve;
import com.pedropathing.pathgen.BezierLine;
import com.pedropathing.pathgen.Path;
import com.pedropathing.pathgen.PathChain;
import com.pedropathing.pathgen.Point;
import com.pedropathing.util.Timer;
import com.pedropathing.util.Constants;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import pedroPathing.constants.FConstants;
import pedroPathing.constants.LConstants;

@Autonomous(name = "AutoOOoOoOOo")
public class fiveSpec extends OpMode {

    private Follower follower;
    private Timer pathTimer, actionTimer, opmodeTimer;

    private int pathState;

    private final Pose startPose = new Pose(8, 63, Math.toRadians(180));

    private final Pose scorePre = new Pose(38.4, 63, Math.toRadians(180));

    private final Pose lineup1 = new Pose(60, 23, Math.toRadians(0));
    private final Pose push1 = new Pose(15, 23, Math.toRadians(0));

    private final Pose lineup2 = new Pose(60, 12.4, Math.toRadians(0));
    private final Pose push2 = new Pose(15, 12.4, Math.toRadians(0));

    private final Pose lineup3 = new Pose(60, 8.9, Math.toRadians(0));
    private final Pose push3 = new Pose(15, 8.9, Math.toRadians(0));

    private final Pose getSpec = new Pose(11.6, 26, Math.toRadians(0));

    private final Pose spec1 = new Pose(38.4, 62, Math.toRadians(180));
    private final Pose spec2 = new Pose(38.4, 64, Math.toRadians(180));
    private final Pose spec3 = new Pose(38.4, 66, Math.toRadians(180));
    private final Pose spec4 = new Pose(38.4, 68, Math.toRadians(180));

    private final Pose park = new Pose(10.2, 13, Math.toRadians(90));

    private Path preload, end;
    private PathChain samp1L, samp1, samp2L, samp2, samp3L, samp3, get, hang1, get2, hang2, get3, hang3, get4, hang4;

    public void buildPaths() {


        preload = new Path(
                new BezierLine(
                        new Point(startPose),
                        new Point(scorePre)));
        preload.setLinearHeadingInterpolation(startPose.getHeading(), scorePre.getHeading());

        samp1L = follower.pathBuilder()
                .addPath(
                        new BezierCurve(
                                new Point(scorePre),
                                new Point(0.000, 29.000, Point.CARTESIAN),
                                new Point(79.600, 37.800, Point.CARTESIAN),
                                new Point(lineup1)))
                .setLinearHeadingInterpolation(scorePre.getHeading(), lineup1.getHeading())
                .build();

        samp1 = follower.pathBuilder()
                .addPath(
                        new BezierLine(
                                new Point(lineup1),
                                new Point(push1)))
                .setLinearHeadingInterpolation(lineup1.getHeading(), push1.getHeading())
                .build();

        samp2L = follower.pathBuilder()
                .addPath(
                        new BezierCurve(
                                new Point(push1),
                                new Point(51.200, 36.700, Point.CARTESIAN),
                                new Point(lineup2)))
                .setLinearHeadingInterpolation(push1.getHeading(), lineup2.getHeading())
                .build();

        samp2 = follower.pathBuilder()
                .addPath(
                        new BezierLine(
                                new Point(lineup2),
                                new Point(push2)))
                .setLinearHeadingInterpolation(lineup2.getHeading(), push2.getHeading())
                .build();

        samp3L = follower.pathBuilder()
                .addPath(
                        new BezierCurve(
                                new Point(push2),
                                new Point(40.900, 31.900, Point.CARTESIAN),
                                new Point(lineup3)))
                .setLinearHeadingInterpolation(push1.getHeading(), lineup2.getHeading())
                .build();

        samp3 = follower.pathBuilder()
                .addPath(
                        new BezierLine(
                                new Point(lineup3),
                                new Point(push3)))
                .setLinearHeadingInterpolation(lineup3.getHeading(), push3.getHeading())
                .build();


        get = follower.pathBuilder()
                .addPath(
                        new BezierCurve(
                                new Point(push3),
                                new Point(29.500, 19.800, Point.CARTESIAN),
                                new Point(getSpec)))
                .setLinearHeadingInterpolation(push3.getHeading(), getSpec.getHeading())
                .build();

        hang1 = follower.pathBuilder()
                .addPath(
                        new BezierCurve(
                                new Point(getSpec),
                                new Point(15.500, 59.000, Point.CARTESIAN),
                                new Point(spec1)))
                .setLinearHeadingInterpolation(getSpec.getHeading(), spec1.getHeading())
                .build();

        get2 = follower.pathBuilder()
                .addPath(
                        new BezierCurve(
                                new Point(spec1),
                                new Point(24.500, 35.800, Point.CARTESIAN),
                                new Point(getSpec)))
                .setLinearHeadingInterpolation(spec1.getHeading(), getSpec.getHeading())
                .build();

        hang2 = follower.pathBuilder()
                .addPath(
                        new BezierCurve(
                                new Point(getSpec),
                                new Point(15.500, 59.000, Point.CARTESIAN),
                                new Point(spec2)))
                .setLinearHeadingInterpolation(getSpec.getHeading(), spec2.getHeading())
                .build();

        get3 = follower.pathBuilder()
                .addPath(
                        new BezierCurve(
                                new Point(spec2),
                                new Point(24.500, 35.800, Point.CARTESIAN),
                                new Point(getSpec)))
                .setLinearHeadingInterpolation(spec2.getHeading(), getSpec.getHeading())
                .build();
        hang3 = follower.pathBuilder()
                .addPath(
                        new BezierCurve(
                                new Point(getSpec),
                                new Point(15.500, 59.000, Point.CARTESIAN),
                                new Point(spec3)))
                .setLinearHeadingInterpolation(getSpec.getHeading(), spec3.getHeading())
                .build();

        get4 = follower.pathBuilder()
                .addPath(
                        new BezierCurve(
                                new Point(spec3),
                                new Point(24.500, 35.800, Point.CARTESIAN),
                                new Point(getSpec)))
                .setLinearHeadingInterpolation(spec3.getHeading(), getSpec.getHeading())
                .build();

        hang4 = follower.pathBuilder()
                .addPath(
                        new BezierCurve(
                                new Point(getSpec),
                                new Point(15.500, 59.000, Point.CARTESIAN),
                                new Point(spec4)))
                .setLinearHeadingInterpolation(getSpec.getHeading(), spec4.getHeading())
                .build();

        end = new Path(
                new BezierLine(
                        new Point(spec4),
                        new Point(park)));
        end.setLinearHeadingInterpolation(spec4.getHeading(), park.getHeading());
    }


    public void autonomousPathUpdate() {
        switch (pathState) {

            case 0:
                follower.followPath(preload);
                setPathState(1);
                break;

            case 1:
                if(follower.getPose().getX() > (scorePre.getX() - .1) && follower.getPose().getY() > (scorePre.getY() - .1)) {
                    follower.followPath(samp1L,true);
                    setPathState(2);
                }
                break;

            case 2:

                if(follower.getPose().getX() > (lineup1.getX() - .1) && follower.getPose().getY() > (lineup1.getY() - .1)) {
                    follower.followPath(samp1,true);
                    setPathState(3);
                }
                break;

            case 3:

                if(follower.getPose().getX() > (push1.getX() - .1) && follower.getPose().getY() > (push1.getY() - .1)) {
                    follower.followPath(samp2L,true);
                    setPathState(4);
                }
                break;

            case 4:

                if(follower.getPose().getX() > (lineup2.getX() - .1) && follower.getPose().getY() > (lineup2.getY() - .1)) {
                    follower.followPath(samp2,true);
                    setPathState(5);
                }
                break;
            case 5:

                if(follower.getPose().getX() > (push2.getX() - .1) && follower.getPose().getY() > (push2.getY() - .1)) {
                    follower.followPath(samp3L,true);
                    setPathState(6);
                }
                break;

            case 6:
                if(follower.getPose().getX() > (lineup3.getX() - .1) && follower.getPose().getY() > (lineup3.getY() - .1)) {
                    follower.followPath(samp3, true);
                    setPathState(7);
                }
                break;

            case 7:
                if(follower.getPose().getX() > (push3.getX() - .1) && follower.getPose().getY() > (push3.getY() - .1)) {
                    follower.followPath(get,true);
                    setPathState(8);
                }
                break;

            case 8:
                if(follower.getPose().getX() > (getSpec.getX() - .1) && follower.getPose().getY() > (getSpec.getY() - .1)) {
                    follower.followPath(hang1,true);
                    setPathState(9);
                }
                break;

            case 9:
                if(follower.getPose().getX() > (spec1.getX() - .1) && follower.getPose().getY() > (spec1.getY() - .1)) {
                    follower.followPath(get2,true);
                    setPathState(10);
                }
                break;

            case 10:
                if(follower.getPose().getX() > (getSpec.getX() - .1) && follower.getPose().getY() > (getSpec.getY() - .1)) {
                    follower.followPath(hang2,true);
                    setPathState(11);
                }
                break;

            case 11:
                if(follower.getPose().getX() > (spec2.getX() - .1) && follower.getPose().getY() > (spec2.getY() - .1)) {
                    follower.followPath(get3,true);
                    setPathState(12);
                }
                break;

            case 12:
                if(follower.getPose().getX() > (getSpec.getX() - .1) && follower.getPose().getY() > (getSpec.getY() - .1)) {
                    follower.followPath(hang3,true);
                    setPathState(13);
                }
                break;

            case 13:
                if(follower.getPose().getX() > (spec3.getX() - .1) && follower.getPose().getY() > (spec3.getY() - .1)) {
                    follower.followPath(get4,true);
                    setPathState(14);
                }
                break;

            case 14:
                if(follower.getPose().getX() > (getSpec.getX() - 1) && follower.getPose().getY() > (getSpec.getY() - .1)) {
                    follower.followPath(hang4,true);
                    setPathState(15);
                }
                break;

            case 15:
                if(follower.getPose().getX() > (spec4.getX() - .1) && follower.getPose().getY() > (spec4.getY() - .1)) {
                    follower.followPath(end,true);
                    setPathState(-1);
                }
                break;

        }
    }

    public void setPathState(int pState) {
        pathState = pState;
        pathTimer.resetTimer();
    }

    @Override
    public void loop() {

        follower.update();
        autonomousPathUpdate();

        telemetry.addData("path state", pathState);
        telemetry.addData("x", follower.getPose().getX());
        telemetry.addData("y", follower.getPose().getY());
        telemetry.addData("heading", follower.getPose().getHeading());
        telemetry.update();
    }

    @Override
    public void init() {
        pathTimer = new Timer();
        opmodeTimer = new Timer();
        opmodeTimer.resetTimer();

        Constants.setConstants(FConstants.class, LConstants.class);
        follower = new Follower(hardwareMap);
        follower.setStartingPose(startPose);
        buildPaths();
    }

    @Override
    public void init_loop() {}

    @Override
    public void start() {
        opmodeTimer.resetTimer();
        setPathState(0);
    }

    @Override
    public void stop() {
    }
}
