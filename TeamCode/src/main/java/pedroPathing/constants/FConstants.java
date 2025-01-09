package pedroPathing.constants;

import com.pedropathing.follower.FollowerConstants;
import com.pedropathing.localization.GoBildaPinpointDriver;
import com.pedropathing.localization.Localizers;
import com.pedropathing.localization.constants.PinpointConstants;
import com.pedropathing.util.CustomFilteredPIDFCoefficients;
import com.pedropathing.util.CustomPIDFCoefficients;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;

public class FConstants {
    static {
        FollowerConstants.localizers = Localizers.PINPOINT;

        FollowerConstants.leftFrontMotorName = "lf_drive";
        FollowerConstants.leftRearMotorName = "lb_drive";
        FollowerConstants.rightFrontMotorName = "rf_drive";
        FollowerConstants.rightRearMotorName = "rb_drive";

        FollowerConstants.leftFrontMotorDirection = DcMotorSimple.Direction.FORWARD;
        FollowerConstants.leftRearMotorDirection = DcMotorSimple.Direction.REVERSE;
        FollowerConstants.rightFrontMotorDirection = DcMotorSimple.Direction.FORWARD;
        FollowerConstants.rightRearMotorDirection = DcMotorSimple.Direction.FORWARD;

        FollowerConstants.xMovement = output;
        FollowerConstants.yMovement = OUTPUT;
        FollowerConstants.forwardZeroPowerAcceleration = OUTPUT;
        FollowerConstants.lateralZeroPowerAcceleration = OUTPUT;
        FollowerConstants.mass = MASS;

        FollowerConstants.translationalPIDFCoefficients = new CustomPIDFCoefficients(P,I,D,F); //replace pidf with nujmbers
        FollowerConstants.headingPIDFCoefficients = new CustomPIDFCoefficients(P,I,D,F);
        FollowerConstants.zeroPowerAccelerationMultiplier = VALUE;

        //Higher values: Faster braking but more oscillations.
        //Lower values: Slower braking with fewer oscillations.

        FollowerConstants.drivePIDFCoefficients = new CustomFilteredPIDFCoefficients(P,I,D,T,F);

        FollowerConstants.centripetalScaling = VALUE;
        // if bot curves inward, increase
        // if bot curves outward, decreate

        PinpointConstants.forwardY = 1; // tune
        PinpointConstants.strafeX = -2.5; // tune
        PinpointConstants.distanceUnit = DistanceUnit.INCH;
        PinpointConstants.hardwareMapName = "pinpoint";
        PinpointConstants.useYawScalar = false;
        PinpointConstants.yawScalar = 1.0;
        PinpointConstants.useCustomEncoderResolution = false;
        PinpointConstants.encoderResolution = GoBildaPinpointDriver.GoBildaOdometryPods.goBILDA_SWINGARM_POD;
        PinpointConstants.customEncoderResolution = 13.26291192;
        PinpointConstants.forwardEncoderDirection = GoBildaPinpointDriver.EncoderDirection.REVERSED;
        PinpointConstants.strafeEncoderDirection = GoBildaPinpointDriver.EncoderDirection.FORWARD;

    }
}
