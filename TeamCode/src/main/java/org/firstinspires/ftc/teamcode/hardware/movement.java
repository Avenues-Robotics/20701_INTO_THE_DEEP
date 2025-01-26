package org.firstinspires.ftc.teamcode.hardware;

import com.qualcomm.robotcore.hardware.DcMotor;
import org.firstinspires.ftc.robotcore.external.Telemetry;

import com.qualcomm.robotcore.hardware.DcMotorEx;

public class Movement {

    static double rotations;
    static double ticks;

    public static void forward(double distance, Telemetry telemetry, DcMotor BL, DcMotor BR, DcMotor FL, DcMotor FR) {
        reset_encoders(BL, BR, FL, FR);
        rotations = distance * 21.74;
        // SET TARGET POSITION
        FR.setTargetPosition((int) rotations);
        FL.setTargetPosition((int) -rotations);
        BL.setTargetPosition((int) rotations);
        BR.setTargetPosition((int) -rotations);
        // RUN TO POSITION
        BL.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        BR.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        FL.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        FR.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        // POWER (left slide)
        ((DcMotorEx) FR).setVelocity(1000);
        ((DcMotorEx) FL).setVelocity(-1000);
        ((DcMotorEx) BL).setVelocity(1000);
        ((DcMotorEx) BR).setVelocity(-1000);
        while (BL.isBusy() && BR.isBusy() && FL.isBusy() && FR.isBusy()) {
            telemetry.addData("function", "left");
            motor_telemetry(telemetry, BL, BR, FL, FR);
            telemetry.update();
        }
        FR.setPower(0);
        BR.setPower(0);
        FL.setPower(0);
        BL.setPower(0);
    }

    public static void left(int distance, Telemetry telemetry, DcMotor BL, DcMotor BR, DcMotor FL, DcMotor FR) {
        reset_encoders(BL, BR, FL, FR);
        rotations = distance * 21.74;
        // SET TARGET POSITION
        FR.setTargetPosition((int) rotations);
        FL.setTargetPosition((int) rotations);
        BL.setTargetPosition((int) rotations);
        BR.setTargetPosition((int) rotations);
        // RUN TO POSITION
        BL.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        BR.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        FL.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        FR.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        // POWER (left slide)
        ((DcMotorEx) FR).setVelocity(1000);
        ((DcMotorEx) FL).setVelocity(1000);
        ((DcMotorEx) BL).setVelocity(1000);
        ((DcMotorEx) BR).setVelocity(1000);
        while (BL.isBusy() && BR.isBusy() && FL.isBusy() && FR.isBusy()) {
            telemetry.addData("function", "forward...");
            motor_telemetry(telemetry, BL, BR, FL, FR);
            telemetry.update();
        }
    }

    public static void right(double distance, Telemetry telemetry, DcMotor BL, DcMotor BR, DcMotor FL, DcMotor FR) {
        reset_encoders(BL, BR, FL, FR);
        rotations = distance * 21.74;
        // SET TARGET POSITION
        FR.setTargetPosition((int) -rotations);
        FL.setTargetPosition((int) -rotations);
        BL.setTargetPosition((int) -rotations);
        BR.setTargetPosition((int) -rotations);
        // RUN TO POSITION
        BL.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        BR.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        FL.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        FR.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        // POWER (left slide)
        ((DcMotorEx) FR).setVelocity(-1000);
        ((DcMotorEx) FL).setVelocity(-1000);
        ((DcMotorEx) BL).setVelocity(-1000);
        ((DcMotorEx) BR).setVelocity(-1000);
        while (BL.isBusy() && BR.isBusy() && FL.isBusy() && FR.isBusy()) {
            telemetry.addData("function", "backward");
            motor_telemetry(telemetry, BL, BR, FL, FR);
            telemetry.update();
        }
    }

    public static void backward(double distance, Telemetry telemetry, DcMotor BL, DcMotor BR, DcMotor FL, DcMotor FR) {
        reset_encoders(BL, BR, FL, FR);
        rotations = distance * 21.74;
        // SET TARGET POSITION
        FR.setTargetPosition((int) -rotations);
        FL.setTargetPosition((int) rotations);
        BL.setTargetPosition((int) -rotations);
        BR.setTargetPosition((int) rotations);
        // RUN TO POSITION
        BL.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        BR.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        FL.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        FR.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        // POWER (left slide)
        ((DcMotorEx) FR).setVelocity(-500);
        ((DcMotorEx) FL).setVelocity(500);
        ((DcMotorEx) BL).setVelocity(-800);
        ((DcMotorEx) BR).setVelocity(500);
        while (BL.isBusy() && BR.isBusy() && FL.isBusy() && FR.isBusy()) {
            telemetry.addData("function", BL.getCurrentPosition());
            motor_telemetry(telemetry, BL, BR, FL, FR);
            telemetry.update();
        }
        FR.setPower(0);
        BR.setPower(0);
        FL.setPower(0);
        BL.setPower(0);
    }


    //rotation right
    // Rotation Left (Counterclockwise)
    public static void rotateRight(double degrees, Telemetry telemetry, DcMotor BL, DcMotor BR, DcMotor FL, DcMotor FR) {
        reset_encoders(BL, BR, FL, FR);

        // Calculate the number of encoder ticks for the given degree of rotation
        rotations = degrees * 3.4;  // Adjust this constant based on your robot's configuration

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
        ((DcMotorEx) FR).setVelocity(-500);
        ((DcMotorEx) FL).setVelocity(-500);
        ((DcMotorEx) BL).setVelocity(500);
        ((DcMotorEx) BR).setVelocity(500);

        while (BL.isBusy() && BR.isBusy() && FL.isBusy() && FR.isBusy()) {
            telemetry.addData("Rotating Left", "In Progress");
            motor_telemetry(telemetry, BL, BR, FL, FR);
            telemetry.update();
        }
        stopMotors(BL, BR, FL, FR);
    }



    // Rotation Right (Clockwise)
    public static void rotateLeft(double degrees, Telemetry telemetry, DcMotor BL, DcMotor BR, DcMotor FL, DcMotor FR) {
        reset_encoders(BL, BR, FL, FR);

        // Calculate number of encoder ticks for the given degree of rotation
        rotations = degrees * 3.4;  // Adjust this constant based on your robot's configuration

        // Set target positions for a turn (opposite directions for left and right motors)
        FR.setTargetPosition((int) rotations);
        FL.setTargetPosition((int) rotations);
        BL.setTargetPosition((int) -rotations);
        BR.setTargetPosition((int) -rotations);

        // Run to position mode
        FR.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        FL.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        BL.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        BR.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        // Set velocity for a smooth turn
        ((DcMotorEx) FR).setVelocity(500);
        ((DcMotorEx) FL).setVelocity(500);
        ((DcMotorEx) BL).setVelocity(-500);
        ((DcMotorEx) BR).setVelocity(-500);

        // Wait until the robot reaches the target position
        while (BL.isBusy() && BR.isBusy() && FL.isBusy() && FR.isBusy()) {
            telemetry.addData("Rotating Right", "In Progress");
            motor_telemetry(telemetry, BL, BR, FL, FR);
            telemetry.update();
        }

        // Stop motors after rotation is complete
        stopMotors(BL, BR, FL, FR);
    }

    public static void LS_up(double ticks, Telemetry telemetry, DcMotor LSL, DcMotor LSR) {
        // Reset encoders before starting
        reset_slides(LSL, LSR);

        LSL.setTargetPosition((int) ticks);   // Left slide
        LSR.setTargetPosition((int) ticks);   // Right slide

        LSL.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        LSR.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        ((DcMotorEx) LSL).setVelocity(750);  // Adjust velocity for a smooth motion
        ((DcMotorEx) LSR).setVelocity(750);

        while (LSL.isBusy() && LSR.isBusy()) {
            telemetry.addData("Linear Slide Position", "Left: %d, Right: %d", LSL.getCurrentPosition(), LSR.getCurrentPosition());
            telemetry.update();
        }

        if (LSL.getCurrentPosition() == LSL.getTargetPosition()){
            LSL.setPower(0);
            LSR.setPower(0);
        }

        LSL.setPower(0);
        LSR.setPower(0);

    }

    public static void LS_down(Telemetry telemetry, DcMotor LSL, DcMotor LSR) {
        // Reset encoders before starting
        reset_slides(LSL, LSR);
        LSL.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        LSR.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        ((DcMotorEx) LSL).setVelocity(-750);  // Adjust velocity for a smooth motion
        ((DcMotorEx) LSR).setVelocity(-750);

        while (LSL.isBusy() && LSR.isBusy()) {
            telemetry.addData("Linear Slide Position", "Left: %d, Right: %d", LSL.getCurrentPosition(), LSR.getCurrentPosition());
            telemetry.update();
        }
        
        LSL.setPower(0);
        LSR.setPower(0);
    }

    public static void AM_in(double distance, Telemetry telemetry, DcMotor AM) {
        reset_arm(AM);
        AM.setTargetPosition((int) distance);
        AM.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        ((DcMotorEx) AM).setVelocity(750);
        while (AM.isBusy()) {
            telemetry.addData("function", AM.getCurrentPosition());
            telemetry.update();
        }
    }

    public static void AM_out(double distance, Telemetry telemetry, DcMotor AM) {
        reset_arm(AM);
        AM.setTargetPosition((int) -distance);
        AM.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        ((DcMotorEx) AM).setVelocity(750);
        while (AM.isBusy()) {
            telemetry.addData("function", AM.getCurrentPosition());
            telemetry.update();
        }
    }

    //SMALL FUNCTIONS BELOW:

    public static void reset_encoders(DcMotor BL, DcMotor BR, DcMotor FL, DcMotor FR) {
        BL.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        BR.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        FL.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        FR.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
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
        telemetry.update();
    }

    private static void stopMotors(DcMotor BL, DcMotor BR, DcMotor FL, DcMotor FR) {
        BL.setPower(0);
        BR.setPower(0);
        FL.setPower(0);
        FR.setPower(0);
    }

}
