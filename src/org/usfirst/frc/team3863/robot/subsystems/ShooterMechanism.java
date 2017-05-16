package org.usfirst.frc.team3863.robot.subsystems;

import org.usfirst.frc.team3863.robot.RobotMap;

import com.ctre.CANTalon;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class ShooterMechanism extends Subsystem {
    boolean intakeMode = false;
    static double setShootSpeed = 50;    //need to tweak values
    static CANTalon flywheelATalon = new CANTalon(RobotMap.flywheelATalonID);
    static CANTalon flywheelBTalon = new CANTalon(RobotMap.flywheelBTalonID);
    static CANTalon flywheelBeltTalon = new CANTalon(RobotMap.flywheelBeltTalonID);
    static CANTalon flywheelCoverTalon = new CANTalon(RobotMap.flywheelCoverTalonID);
    static DoubleSolenoid gateSolenoid = new DoubleSolenoid(RobotMap.solGateOpen,
            RobotMap.solGateClose);

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }

    public static void debugShroud() {
        SmartDashboard.putNumber("ShroudPosition", flywheelCoverTalon.getPosition());
        SmartDashboard.putBoolean("ShroudLimitSwitch", flywheelCoverTalon.isFwdLimitSwitchClosed());
        SmartDashboard.putNumber("actualCoverPower", flywheelCoverTalon.get());
        SmartDashboard.putNumber("setFlywheelPower", setShootSpeed);
        SmartDashboard.putNumber("actualFlywheelSpeedA", flywheelATalon.getEncVelocity());
        SmartDashboard.putNumber("actualFlywheelSpeedB", flywheelBTalon.getEncVelocity());
    }

    public static void changeSetShootSpeed(double incr) {
        setShootSpeed += incr;
    }

    public static void hardstopShroud() {
        boolean zeroed = false;
        int idx = 0;
        while (!zeroed) {
            if (idx > 500) {
                break;
            }
            zeroed = flywheelCoverTalon.isFwdLimitSwitchClosed();
            lowerShroud(0.01);
            idx += 1;
            System.out.println("idxl: " + idx);
        }
    }

    public static void zeroShroud() {
        hardstopShroud();
        int idx = 0;
        boolean zeroed = true;
        while (zeroed) {
            if (idx > 80) {
                System.out.println("SHROUD SAFETY TRIPPED! LIMIT SWITCH MAY HAVE FAILED!!!!!!!");
                break;
            }
            zeroed = flywheelCoverTalon.isFwdLimitSwitchClosed();
            raiseShroud(0.01, 2700);
            idx += 1;
            System.out.println("idxr: " + idx);

        }
        System.out.println("Zeroed!");
        flywheelCoverTalon.setPosition(0);
    }

    public static void extendShroud(double raisePos) {
        boolean extend = false;
        int idx = 0;
        while (!extend) {
            if (idx > 200) {
                System.out.println("SHROUD SAFETY TRIPPED! ENCODER MAY HAVE FAILED!!!!!!!");
                break;
            }
            extend = raiseShroud(0.01, raisePos);
            idx += 1;
            System.out.println("idx:" + idx);
        }
        System.out.println("Extended");
    }

    public static boolean raiseShroud(double RTime, double limitMax) {
        if (limitMax > 2700) {
            limitMax = 2700;
        }
        if (flywheelCoverTalon.getPosition() >= limitMax) {
            return true;
        }
        flywheelCoverTalon.set(-0.5);
        Timer.delay(RTime);
        flywheelCoverTalon.set(0);
        return false;
    }


    public static void lowerShroud(double LTime) {
        flywheelCoverTalon.set(0.75);
        Timer.delay(LTime);
        flywheelCoverTalon.set(0);
    }

    public static void setFlywheelSpeed(double speed) {
        flywheelATalon.set(speed);    //swap anderson connector
        flywheelBTalon.set(speed);
    }

    public static void setBeltSpeed(double speed) {
        flywheelBeltTalon.set(-speed);
    }

    public static void updateSpeed() {
        /*if (flywheelATalon.get() == 0) {
            return;
        }*/
        setFlywheelSpeed(setShootSpeed);
    }

    public static void enableIntakeMode() {
        System.out.println("Intake Mode Enabled");
        SmartDashboard.putString("Shooter Mode: ", "Intake");
        extendShroud(2700);
        closeGate();
        setFlywheelSpeed(0.35);
        setBeltSpeed(1);
        System.out.println(flywheelATalon.getSpeed());
        System.out.println(flywheelBTalon.getSpeed());
    }

    public static void disableMode() {
        System.out.println("Shooter Disabled");
        SmartDashboard.putString("Shooter Mode: ", "Disabled");
        flywheelATalon.changeControlMode(CANTalon.TalonControlMode.PercentVbus);
        flywheelBTalon.changeControlMode(CANTalon.TalonControlMode.PercentVbus);
        flywheelATalon.set(0);
        flywheelBTalon.set(0);
        setBeltSpeed(0);
        closeGate();
        zeroShroud();
    }

    public static void enableShootMode() {
        System.out.println("Shoot High Mode Enabled");
        SmartDashboard.putString("Shooter Mode: ", "Shoot High");
        flywheelATalon.enable();
        flywheelBTalon.enable();
        flywheelATalon.setProfile(0);
        flywheelATalon.setFeedbackDevice(CANTalon.FeedbackDevice.QuadEncoder);
        flywheelATalon.changeControlMode(CANTalon.TalonControlMode.Speed);
        flywheelBTalon.changeControlMode(CANTalon.TalonControlMode.Speed);
        //flywheelATalon.setPID(RobotMap.flywheelKp, RobotMap.flywheelKi, RobotMap.flywheelKd);
        // flywheelBTalon.setPID(RobotMap.flywheelKp, RobotMap.flywheelKi, RobotMap.flywheelKd);
        flywheelATalon.reverseSensor(true);
        flywheelATalon.reverseOutput(true);
        flywheelBTalon.reverseSensor(true);
        flywheelBTalon.reverseOutput(false);
        flywheelATalon.configEncoderCodesPerRev(20);
        flywheelBTalon.configEncoderCodesPerRev(20);
        flywheelATalon.setCloseLoopRampRate(5);
        flywheelBTalon.setCloseLoopRampRate(5);
        setFlywheelSpeed(50);
        //hardstopShroud();
        setBeltSpeed(1);
    }

    public static void enableLowShooterMode() {
        System.out.println("Shoot Low Mode Enabled");
        SmartDashboard.putString("Shooter Mode: ", "Shoot Low");
        setFlywheelSpeed(setShootSpeed * 0.9);
        zeroShroud();
        extendShroud(2200);
        setBeltSpeed(1);
    }

    public static void openGate() {
        gateSolenoid.set(DoubleSolenoid.Value.kReverse);
        SmartDashboard.putString("Gate Mode: ", "Open");
    }

    public static void closeGate() {
        gateSolenoid.set(DoubleSolenoid.Value.kForward);
        SmartDashboard.putString("Gate Mode: ", "Closed");
    }

    public static boolean getGateState() {
        DoubleSolenoid.Value val = gateSolenoid.get();
        if (val == DoubleSolenoid.Value.kForward || val == DoubleSolenoid.Value.kOff) {
            return true;
        } else {
            return false;
        }
    }

    public static boolean getBeltState() {
        return !(flywheelBeltTalon.get() == 0);
    }

    public static void setBeltBlock(boolean isBlocked) {
        if (isBlocked) {
            if (flywheelBeltTalon.get() == 0) {
                return;
            } else {
                setBeltSpeed(0.04);
            }
        } else {
            if (flywheelBeltTalon.get() == 0.04) {
                return;
            } else {
                setBeltSpeed(1);
            }
        }

    }

}

