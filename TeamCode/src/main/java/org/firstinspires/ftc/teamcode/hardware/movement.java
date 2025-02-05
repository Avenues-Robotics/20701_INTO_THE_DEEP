package org.firstinspires.ftc.teamcode.hardware;

import static android.os.SystemClock.sleep;

import com.qualcomm.robotcore.hardware.DcMotor;
import org.firstinspires.ftc.robotcore.external.Telemetry;

import com.qualcomm.robotcore.hardware.DcMotorEx;

public class Movement {

    static double rotations;
    static double ticks;
    public static volatile boolean on = false;

    public static void forward(double distance, Telemetry telemetry, DcMotor BL, DcMotor BR, DcMotor FL, DcMotor FR) {
        reset_encoders(BL, BR, FL, FR);
        rotations = distance * 21.74;
        // SET TARGET POSITION
        FR.setTargetPosition((int) rotations);
        FL.setTargetPosition((int) rotations);
        BL.setTargetPosition((int) rotations);
        BR.setTargetPosition((int) -rotations);
        // RUN TO POSITION
        BL.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        BR.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        FL.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        FR.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        // POWER (left slide)
        FR.setPower(.1);
        FL.setPower(.1);
        BL.setPower(.1);
        BR.setPower(.1);
        while (BL.isBusy() && BR.isBusy() && FL.isBusy() && FR.isBusy()) {
            telemetry.addData("function", FL.getCurrentPosition());
            motor_telemetry(telemetry, BL, BR, FL, FR);
            telemetry.update();
        }
        stopMotors(BL, BR, FL, FR);
        reset_encoders(BL, BR, FL, FR);
    }

    public static void left(int distance, Telemetry telemetry, DcMotor BL, DcMotor BR, DcMotor FL, DcMotor FR) {
        reset_encoders(BL, BR, FL, FR);
        rotations = distance * 21.74;
        // SET TARGET POSITION
        FR.setTargetPosition((int) rotations);
        FL.setTargetPosition((int) -rotations);
        BL.setTargetPosition((int) rotations);
        BR.setTargetPosition((int) rotations);
        // RUN TO POSITION
        BL.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        BR.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        FL.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        FR.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        // POWER (left slide)
        ((DcMotorEx) FR).setVelocity(100);
        ((DcMotorEx) FL).setVelocity(100);
        ((DcMotorEx) BL).setVelocity(100);
        ((DcMotorEx) BR).setVelocity(100);
        while (BL.isBusy() && BR.isBusy() && FL.isBusy() && FR.isBusy()) {
            telemetry.addData("function", "forward...");
            motor_telemetry(telemetry, BL, BR, FL, FR);
            telemetry.update();
        }
        stopMotors(BL, BR, FL, FR);
        reset_encoders(BL, BR, FL, FR);
    }

    public static void right(double distance, Telemetry telemetry, DcMotor BL, DcMotor BR, DcMotor FL, DcMotor FR) {
        reset_encoders(BL, BR, FL, FR);
        rotations = distance * 21.74;
        // SET TARGET POSITION
        FR.setTargetPosition((int) rotations);
        FL.setTargetPosition((int) -rotations);
        BL.setTargetPosition((int) -rotations);
        BR.setTargetPosition((int) -rotations);
        // RUN TO POSITION
        BL.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        BR.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        FL.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        FR.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        // POWER (left slide)
        ((DcMotorEx) FR).setVelocity(-200);
        ((DcMotorEx) FL).setVelocity(-200);
        ((DcMotorEx) BL).setVelocity(-200);
        ((DcMotorEx) BR).setVelocity(-200);
        while (BL.isBusy() && BR.isBusy() && FL.isBusy() && FR.isBusy()) {
            telemetry.addData("function", "backward");
            motor_telemetry(telemetry, BL, BR, FL, FR);
            telemetry.update();
        }
        stopMotors(BL, BR, FL, FR);
        reset_encoders(BL, BR, FL, FR);
    }

    public static void backward(double distance, Telemetry telemetry, DcMotor BL, DcMotor BR, DcMotor FL, DcMotor FR) {
        reset_encoders(BL, BR, FL, FR);
        rotations = distance * 21.74;
        // SET TARGET POSITION
        FR.setTargetPosition((int) -rotations);
        FL.setTargetPosition((int) -rotations);
        BL.setTargetPosition((int) -rotations);
        BR.setTargetPosition((int) rotations);
        // RUN TO POSITION
        BL.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        BR.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        FL.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        FR.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        // POWER (left slide)
        ((DcMotorEx) FR).setVelocity(-200);
        ((DcMotorEx) FL).setVelocity(200);
        ((DcMotorEx) BL).setVelocity(200);
        ((DcMotorEx) BR).setVelocity(200);
        while (BL.isBusy() && BR.isBusy() && FL.isBusy() && FR.isBusy()) {
            telemetry.addData("going backwards: bl", BL.getCurrentPosition());
            motor_telemetry(telemetry, BL, BR, FL, FR);
            telemetry.update();
        }
        stopMotors(BL, BR, FL, FR);
        reset_encoders(BL, BR, FL, FR);
    }


    //rotation right
    // Rotation Left (Counterclockwise)
    public static void rotateRight(double degrees, Telemetry telemetry, DcMotor BL, DcMotor BR, DcMotor FL, DcMotor FR) {
        reset_encoders(BL, BR, FL, FR);

        // Calculate the number of encoder ticks for the given degree of rotation
        rotations = degrees * 14;  // Adjust this constant based on your robot's configuration

        // Set target positions for a counterclockwise turn
        FR.setTargetPosition((int) -rotations);  // Right motors move forward (counterclockwise)
        FL.setTargetPosition((int) -rotations); // Left motors move backward (counterclockwise)
        BL.setTargetPosition((int) rotations); // Left motors move backward (counterclockwise)
        BR.setTargetPosition((int) rotations);  // Right motors move forward (counterclockwise)

        // Run to position mode
        FR.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        FL.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        BL.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        BR.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        // Set velocity for a smooth turn
        ((DcMotorEx) FR).setVelocity(-200);
        ((DcMotorEx) FL).setVelocity(-200);
        ((DcMotorEx) BL).setVelocity(200);
        ((DcMotorEx) BR).setVelocity(200);

        while (BL.isBusy() && BR.isBusy() && FL.isBusy() && FR.isBusy()) {
            telemetry.addData("Rotating Left", "In Progress");
            motor_telemetry(telemetry, BL, BR, FL, FR);
            telemetry.update();
        }
        stopMotors(BL, BR, FL, FR);
        reset_encoders(BL, BR, FL, FR);
    }



    // Rotation Right (Clockwise)
    public static void rotateLeft(double degrees, Telemetry telemetry, DcMotor BL, DcMotor BR, DcMotor FL, DcMotor FR) {
        reset_encoders(BL, BR, FL, FR);

        // Calculate number of encoder ticks for the given degree of rotation
        rotations = degrees * 3.4;  // Adjust this constant based on your robot's configuration

        // Set target positions for a turn (opposite directions for left and right motors)
        FR.setTargetPosition((int) rotations);
        FL.setTargetPosition((int) rotations);
        BL.setTargetPosition((int) rotations);
        BR.setTargetPosition((int) rotations);

        // Run to position mode
        FR.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        FL.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        BL.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        BR.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        // Set velocity for a smooth turn
        ((DcMotorEx) FR).setVelocity(200);
        ((DcMotorEx) FL).setVelocity(200);
        ((DcMotorEx) BL).setVelocity(-200);
        ((DcMotorEx) BR).setVelocity(-200);

        // Wait until the robot reaches the target position
        while (BL.isBusy() && BR.isBusy() && FL.isBusy() && FR.isBusy()) {
            telemetry.addData("Rotating Right", "In Progress");
            motor_telemetry(telemetry, BL, BR, FL, FR);
            telemetry.update();
        }

        // Stop motors after rotation is complete
        stopMotors(BL, BR, FL, FR);
        reset_encoders(BL, BR, FL, FR);
    }

    public static void LS_up(double ticks, Telemetry telemetry, DcMotor LSL, DcMotor LSR) {
        // Reset encoders before starting
        LSL.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        LSR.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        LSL.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        LSR.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        telemetry.addData("lsl target", LSL.getTargetPosition());
        telemetry.addData("lsr target", LSR.getTargetPosition());


        LSL.setTargetPosition((int) ticks);   // Left slide
        LSR.setTargetPosition((int) ticks);   // Right slide

        LSL.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        LSR.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        ((DcMotorEx) LSL).setVelocity(1000);  // Adjust velocity for a smooth motion
        ((DcMotorEx) LSR).setVelocity(1000);
        LSL.setPower(0.5);
        LSR.setPower(0.5);

        while (LSL.isBusy() && LSR.isBusy()) {
            telemetry.addData("left position", LSL.getCurrentPosition());
            telemetry.addData("right position", LSR.getCurrentPosition());
            telemetry.addData("left power", LSL.getPower());
            telemetry.addData("right power", LSR.getPower());
            telemetry.update();
        }
        LSL.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        LSR.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

    }
    /* public static void LS_up(double ticks, Telemetry telemetry, DcMotor LSL, DcMotor LSR) {
        // Reset encoders before starting
        on = true;
        LSL.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        LSR.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        // Ensure the motors resist gravity when not powered
        LSL.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        LSR.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        LSL.setTargetPosition((int) ticks);
        LSR.setTargetPosition((int) ticks);

        LSL.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        LSR.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        ((DcMotorEx) LSL).setVelocity(1000);
        ((DcMotorEx) LSR).setVelocity(1000);
        LSL.setPower(0.5);
        LSR.setPower(0.5);

        while (LSL.isBusy() && LSR.isBusy()) {
            telemetry.addData("left position", LSL.getCurrentPosition());
            telemetry.addData("right position", LSR.getCurrentPosition());
            telemetry.addData("left power", LSL.getPower());
            telemetry.addData("right power", LSR.getPower());
            telemetry.update();
        }

        // Keep holding the position
        LSL.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        LSR.setMode(DcMotor.RunMode.RUN_USING_ENCODER);


        // **Loop to maintain position indefinitely in autonomous**
        while (on) {
            if (!on) {
                telemetry.addData("on?", on);
                telemetry.update();
                LSL.setPower(0);
                LSR.setPower(0);
                break;
            } // Explicitly break when `on` is false
            LSL.setPower(0.01);
            LSR.setPower(0.01);
            telemetry.addData("Holding Position", LSL.getCurrentPosition());
            telemetry.addData("on?", on);
            telemetry.update();
        }


    }
*/

    public static void LS_down(Telemetry telemetry, DcMotor LSL, DcMotor LSR) {
        // Reset encoders before starting
        reset_slides(LSL, LSR);
        on = false;
        telemetry.addData("on?", on);
        telemetry.addData("going down...", LSL.getCurrentPosition());
        telemetry.update();
        LSL.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        LSR.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        telemetry.addData("left position", LSL.getCurrentPosition());
        telemetry.addData("right position", LSR.getCurrentPosition());
        LSL.setTargetPosition((int) 0);
        LSR.setTargetPosition((int) 0);
        LSL.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        LSR.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        ((DcMotorEx) LSL).setVelocity(-1000);
        ((DcMotorEx) LSR).setVelocity(-1000);
        LSL.setPower(-0.5);
        LSR.setPower(-0.5);

        while (LSL.isBusy() || LSR.isBusy()) {
            telemetry.addData("Lowering", "Left: %d, Right: %d", LSL.getCurrentPosition(), LSR.getCurrentPosition());
            telemetry.update();;
        }
        LSL.setPower(0);
        LSR.setPower(0);
        LSL.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        LSR.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
    }

    public static void AM_in(Telemetry telemetry, DcMotor AM) {
        reset_arm(AM);
        AM.setTargetPosition((int) 0);
        AM.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        ((DcMotorEx) AM).setVelocity(500);
        AM.setPower(-0.5);
        while (AM.isBusy()) {
            telemetry.addData("function", AM.getCurrentPosition());
            telemetry.update();
        }
        AM.setPower(0);
    }

    public static void AM_out(double distance, Telemetry telemetry, DcMotor AM) {
        reset_arm(AM);
        AM.setTargetPosition((int) distance);
        AM.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        ((DcMotorEx) AM).setVelocity(750);
        AM.setPower(0.5);
        while (AM.isBusy()) {
            telemetry.addData("function", AM.getCurrentPosition());
            telemetry.update();
        }
        AM.setPower(0);

    }

    //SMALL FUNCTIONS BELOW:

    public static void reset_encoders(DcMotor BL, DcMotor BR, DcMotor FL, DcMotor FR) {
        BL.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        BR.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        FL.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        FR.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        BL.setMode((DcMotor.RunMode.RUN_USING_ENCODER));
        BR.setMode((DcMotor.RunMode.RUN_USING_ENCODER));
        FL.setMode((DcMotor.RunMode.RUN_USING_ENCODER));
        FR.setMode((DcMotor.RunMode.RUN_USING_ENCODER));
    }

    public static void reset_slides(DcMotor LSL, DcMotor LSR) {
        LSL.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        LSR.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
    }

    public static void reset_arm(DcMotor AM) {
        AM.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
    }

    public static void motor_telemetry(Telemetry telemetry, DcMotor BL, DcMotor BR, DcMotor FL, DcMotor FR) {
        telemetry.addData("back left pos", BL.getCurrentPosition());
        telemetry.addData("back right pos", BR.getCurrentPosition());
        telemetry.addData("front left pos", FL.getCurrentPosition());
        telemetry.addData("front right pos", FR.getCurrentPosition());
        telemetry.addData("back left pow", BL.getPower());
        telemetry.addData("back right pow", BR.getPower());
        telemetry.addData("front left pow", FL.getPower());
        telemetry.addData("front right pow", FR.getPower());
        telemetry.update();
    }

    public static void stopMotors(DcMotor BL, DcMotor BR, DcMotor FL, DcMotor FR) {
        BL.setPower(0);
        BR.setPower(0);
        FL.setPower(0);
        FR.setPower(0);
    }

}
