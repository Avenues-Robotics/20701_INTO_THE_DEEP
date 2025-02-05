package org.firstinspires.ftc.teamcode.teleop;

import static org.firstinspires.ftc.teamcode.hardware.Movement.motor_telemetry;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple.Direction;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.Range;


@TeleOp(
        name = "TELEOP"
)
public class TELEOP extends LinearOpMode {
    static DcMotor BR;
    static DcMotor FR;
    static DcMotor BL;
    static DcMotor FL;
    static DcMotor LSR;
    static DcMotor LSL; //NAME THE LINEARSLIDE MOTORS LSL AND LSR
    static DcMotor AM; //linear slides for arm
    double r;
    int maxExtension = 2000; //CHANGE THSI9 VALUE BASED ON THE MAX HEIGHT OF THE LS
    int armExtension = 2000; //chnage this value based on the max length of the arm slides
    CRServo GK;
    Servo IR;

    public void runOpMode() {

        BL = hardwareMap.get(DcMotor.class, "BL"); // LD to BL
        BR = hardwareMap.get(DcMotor.class, "BR"); // RD to BR
        FL = hardwareMap.get(DcMotor.class, "FL"); // LU to FL
        FR = hardwareMap.get(DcMotor.class, "FR"); // RU to FR
        FR.setDirection(DcMotor.Direction.REVERSE);
        FR.setDirection(DcMotor.Direction.REVERSE);
        BL.setDirection(DcMotor.Direction.FORWARD);
        FL.setDirection(DcMotor.Direction.REVERSE);

        LSR = hardwareMap.get(DcMotor.class, "LSR");
        LSL = hardwareMap.get(DcMotor.class, "LSL");
        LSR.setDirection(Direction.REVERSE);

        AM = hardwareMap.get(DcMotor.class, "AM");
        IR = hardwareMap.get(Servo.class, "IR");
        GK = hardwareMap.get(CRServo.class, "GK");

        //roller = continuous
        //intake rotator = 270 degrees

        LSR.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        LSL.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        LSR.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        LSL.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        telemetry.addData(">", "Press Start to run Motors.");
        telemetry.update();
        waitForStart();

        while (opModeIsActive()) {
            r = Math.hypot(-gamepad1.left_stick_y, gamepad1.left_stick_x);
            double robotAngle = Math.atan2(gamepad1.left_stick_x, -gamepad1.left_stick_y) - Math.PI / 4;
            double rightX = gamepad1.right_stick_x;
            final double v4 = r * Math.cos(robotAngle) - rightX;
            final double v3 = r * Math.sin(robotAngle) - rightX;
            final double v2 = r * Math.sin(robotAngle) + rightX;
            final double v1 = r * Math.cos(robotAngle) + rightX;

            //normal -- ++

            BR.setPower(Range.clip(-v4, -1.0D, 1.0D));
            BL.setPower(Range.clip(-v3, -1.0D, 1.0D));
            FR.setPower(Range.clip(-v2, -1.0D, 1.0D));
            FL.setPower(Range.clip(-v1, -1.0D, 1.0D));


//I DON'T KNOW IF THIS WILL WORK, WE HAVE TO CHECK

            if (gamepad1.dpad_up) {
                LSR.setPower(0.75);
                LSL.setPower(0.75);
                if (LSL.getCurrentPosition() > 9000){
                    LSL.setPower(0);
                    LSR.setPower(0);
                }
                if (LSL.getCurrentPosition() > 7000){
                    LSL.setPower(0.75);
                    LSR.setPower(0.75);
                }
                telemetry.addData("left position", LSL.getCurrentPosition());
                telemetry.addData("right position", LSR.getCurrentPosition());
                telemetry.addData("power", LSL.getPower());
                telemetry.addData("power", LSR.getPower());
                telemetry.update();
            } else if (gamepad1.dpad_down) {
                LSR.setPower(-0.5); //if you press a, will retract the slides.
                LSL.setPower(-0.5);
                telemetry.addData("left position", LSL.getCurrentPosition());
                telemetry.addData("right position", LSR.getCurrentPosition());
                telemetry.addData("power", LSL.getPower());
                telemetry.update();
            } else if (LSL.getCurrentPosition() < 100) {
                LSR.setPower(0);
                LSL.setPower(0);
            } else {
                LSR.setPower(0.05);
                LSL.setPower(0.05);//create function to declare lsrlsl power, with parameter of speed/power
            }
            /*if (LSR.getCurrentPosition() < maxExtension && LSL.getCurrentPosition() < maxExtension) { //if it is not already stretched
                if (gamepad1.dpad_up) {
                    telemetry.update(); //if gamepad is pressed, turn on motors
                    LSR.setPower(0.5);
                    LSL.setPower(0.5);
                    telemetry.addData("left position", LSL.getCurrentPosition());
                    telemetry.addData("right position", LSR.getCurrentPosition());
                    telemetry.update();
                } else {
                    if (LSR.getCurrentPosition() != 0) {
                        LSR.setPower(0.2);
                        LSL.setPower(0.2);
                        telemetry.addData("left position", LSL.getCurrentPosition());
                        telemetry.addData("right position", LSR.getCurrentPosition());
                        telemetry.update();
                    } else {
                        LSR.setPower(0.2);
                        LSL.setPower(0.2);//if it isn't, turn off
                        LSR.setPower(0.2);
                        LSL.setPower(0.2); //if it is at max position, set power to 0
                        telemetry.addData("left position", LSL.getCurrentPosition());
                        telemetry.addData("right position", LSR.getCurrentPosition());
                        telemetry.update();
                    }
                    if (LSL.getCurrentPosition() > 0) { //if they are not at position 0
                        if (gamepad1.dpad_down) {
                            LSR.setPower(-0.5); //if you press a, will retract the slides.
                            LSL.setPower(-0.5);
                            telemetry.addData("left position", LSL.getCurrentPosition());
                            telemetry.addData("right position", LSR.getCurrentPosition());
                            telemetry.update();
                        } else {
                            LSR.setPower(0.2); //if it isn't, turn off
                            LSL.setPower(0.2);
                            telemetry.addData("left position", LSL.getCurrentPosition());
                            telemetry.addData("right position", LSR.getCurrentPosition());
                            telemetry.update();
                        }

                    }
                }*/

            //AM W/O ENCODERS
            if (gamepad1.dpad_right) {
                AM.setPower(-0.5);
                telemetry.addData("arm motor position", AM.getCurrentPosition());
                telemetry.update();
                if (AM.getCurrentPosition() > 45){
                    AM.setPower(0);
                    telemetry.addData("arm motor position", AM.getCurrentPosition());
                    telemetry.update();
                }
            } else if (gamepad1.dpad_left) {
                AM.setPower(0.5);
                telemetry.addData("arm motor position", AM.getCurrentPosition());
                telemetry.update();

            } else {
                AM.setPower(0);
                telemetry.addData("arm motor position", AM.getCurrentPosition());
                telemetry.update();
            }

//GECKO WHEEL
            if (gamepad1.right_bumper) {
                GK.setPower(1);
            } else if (gamepad1.left_bumper) {
                GK.setPower(-1);
            } else {
                GK.setPower(0);
            }
            //INTAKE ROLLER

            if (gamepad1.y) {
                IR.setPosition(1);
            } else if (gamepad1.a) {
                IR.setPosition(-1);
            }

        }

    }
}
