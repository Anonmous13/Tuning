package org.firstinspires.ftc.teamcode;

import com.pedropathing.pathgen.BezierCurve;
import com.pedropathing.pathgen.BezierLine;
import com.pedropathing.pathgen.PathBuilder;
import com.pedropathing.pathgen.Point;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

@Autonomous(name = "5 Spec Red")
public class fiveSpecRed {

    public fiveSpecRed() {
        PathBuilder builder = new PathBuilder();

        builder
                .addPath(
                        // Line 1 (forward to spec bar)
                        new BezierLine(
                                new Point(144.000 - 8.000, 65.000, Point.CARTESIAN),
                                new Point(144.000 - 38.000, 65.000, Point.CARTESIAN)
                        )
                )
                .setLinearHeadingInterpolation(Math.toRadians(0), Math.toRadians(0))
                .addPath(
                        // Line 2 (curve to the 3 alliance sample side)
                        new BezierCurve(
                                new Point(144.000 - 38.000, 65.000, Point.CARTESIAN),
                                new Point(144.000 - 0.000, 29.023, Point.CARTESIAN),
                                new Point(144.000 - 79.575, 37.794, Point.CARTESIAN),
                                new Point(144.000 - 59.538, 24.692, Point.CARTESIAN)
                        )
                )
                .setLinearHeadingInterpolation(Math.toRadians(0), Math.toRadians(180))
                .addPath(
                        // Line 3 (push sample 1)
                        new BezierLine(
                                new Point(144.000 - 59.538, 24.692, Point.CARTESIAN),
                                new Point(144.000 - 12.692, 24.462, Point.CARTESIAN)
                        )
                )
                .setLinearHeadingInterpolation(Math.toRadians(180), Math.toRadians(180))
                .addPath(
                        // Line 4 (reset)
                        new BezierCurve(
                                new Point(144.000 - 12.692, 24.462, Point.CARTESIAN),
                                new Point(144.000 - 51.462, 31.385, Point.CARTESIAN),
                                new Point(144.000 - 63.231, 13.615, Point.CARTESIAN)
                        )
                )
                .setLinearHeadingInterpolation(Math.toRadians(180), Math.toRadians(180))
                .addPath(
                        // Line 5 (push sample 2)
                        new BezierLine(
                                new Point(144.000 - 63.231, 13.615, Point.CARTESIAN),
                                new Point(144.000 - 12.462, 12.923, Point.CARTESIAN)
                        )
                )
                .setLinearHeadingInterpolation(Math.toRadians(180), Math.toRadians(180))
                .addPath(
                        // Line 6 (reset)
                        new BezierCurve(
                                new Point(144.000 - 12.462, 12.923, Point.CARTESIAN),
                                new Point(144.000 - 39.923, 23.769, Point.CARTESIAN),
                                new Point(144.000 - 63.692, 8.538, Point.CARTESIAN)
                        )
                )
                .setLinearHeadingInterpolation(Math.toRadians(180), Math.toRadians(180))
                .addPath(
                        // Line 7 (push sample 3)
                        new BezierLine(
                                new Point(144.000 - 63.692, 8.538, Point.CARTESIAN),
                                new Point(144.000 - 21.462, 8.308, Point.CARTESIAN)
                        )
                )
                .setLinearHeadingInterpolation(Math.toRadians(180), Math.toRadians(180))
                .addPath(
                        // Line 8 (pick spec)
                        new BezierCurve(
                                new Point(144.000 - 21.462, 8.308, Point.CARTESIAN),
                                new Point(144.000 - 29.538, 19.846, Point.CARTESIAN),
                                new Point(144.000 - 9.923, 26.538, Point.CARTESIAN)
                        )
                )
                .setLinearHeadingInterpolation(Math.toRadians(180), Math.toRadians(180))
                .addPath(
                        // Line 9 (hang)
                        new BezierCurve(
                                new Point(144.000 - 9.923, 26.538, Point.CARTESIAN),
                                new Point(144.000 - 16.154, 54.231, Point.CARTESIAN),
                                new Point(144.000 - 37.154, 60.692, Point.CARTESIAN)
                        )
                )
                .setLinearHeadingInterpolation(Math.toRadians(180), Math.toRadians(0))
                .addPath(
                        // Line 10 (pick spec)
                        new BezierCurve(
                                new Point(144.000 - 37.154, 60.692, Point.CARTESIAN),
                                new Point(144.000 - 26.538, 35.769, Point.CARTESIAN),
                                new Point(144.000 - 10.846, 26.538, Point.CARTESIAN)
                        )
                )
                .setLinearHeadingInterpolation(Math.toRadians(0), Math.toRadians(180))
                .addPath(
                        // Line 11 (hang spec)
                        new BezierCurve(
                                new Point(144.000 - 10.846, 26.538, Point.CARTESIAN),
                                new Point(144.000 - 13.154, 49.615, Point.CARTESIAN),
                                new Point(144.000 - 37.154, 69.462, Point.CARTESIAN)
                        )
                )
                .setLinearHeadingInterpolation(Math.toRadians(180), Math.toRadians(0))
                .addPath(
                        // Line 12 (pick spec)
                        new BezierCurve(
                                new Point(144.000 - 37.154, 69.462, Point.CARTESIAN),
                                new Point(144.000 - 26.950, 42.738, Point.CARTESIAN),
                                new Point(144.000 - 11.077, 26.077, Point.CARTESIAN)
                        )
                )
                .setLinearHeadingInterpolation(Math.toRadians(0), Math.toRadians(180))
                .addPath(
                        // Line 13 (hang)
                        new BezierCurve(
                                new Point(144.000 - 11.077, 26.077, Point.CARTESIAN),
                                new Point(144.000 - 20.769, 71.308, Point.CARTESIAN),
                                new Point(144.000 - 37.846, 74.077, Point.CARTESIAN)
                        )
                )
                .setLinearHeadingInterpolation(Math.toRadians(180), Math.toRadians(0))
                .addPath(
                        // Line 14 (pick spec)
                        new BezierCurve(
                                new Point(144.000 - 37.846, 74.077, Point.CARTESIAN),
                                new Point(144.000 - 37.615, 44.077, Point.CARTESIAN),
                                new Point(144.000 - 11.538, 25.615, Point.CARTESIAN)
                        )
                )
                .setLinearHeadingInterpolation(Math.toRadians(0), Math.toRadians(180))
                .addPath(
                        // Line 15 (hang)
                        new BezierCurve(
                                new Point(144.000 - 11.538, 25.615, Point.CARTESIAN),
                                new Point(144.000 - 11.538, 59.077, Point.CARTESIAN),
                                new Point(144.000 - 38.077, 77.769, Point.CARTESIAN)
                        )
                )
                .setLinearHeadingInterpolation(Math.toRadians(180), Math.toRadians(0))
                .addPath(
                        // Line 16 (park)
                        new BezierLine(
                                new Point(144.000 - 38.077, 77.769, Point.CARTESIAN),
                                new Point(144.000 - 10.154, 12.923, Point.CARTESIAN)
                        )
                )
                .setLinearHeadingInterpolation(Math.toRadians(0), Math.toRadians(90));
    }
}
