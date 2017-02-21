package org.usfirst.frc.team3863.robot.subsystems;

import org.usfirst.frc.team3863.robot.RobotMap;

import com.ctre.CANTalon;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class ShooterMechanism extends Subsystem {
	boolean intakeMode = false;
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
    
    public static void debugShroud(){
        System.out.println(""+flywheelCoverTalon.getPosition());
    }
    
    public static void zeroShroud(){
    	boolean zeroed = false;
    	while (!zeroed){
    		zeroed = flywheelCoverTalon.isFwdLimitSwitchClosed();
    		lowerShroud(0.01);
    	}
    	while (zeroed){
    		zeroed = flywheelCoverTalon.isFwdLimitSwitchClosed();
    		raiseShroud(0.01);
    	}
    	System.out.println("Zeroed!");
    	flywheelCoverTalon.setPosition(0);
    }
    
    public static void extendShroud(){
    	boolean extend = false;
    	while (!extend){
    		extend = raiseShroud(0.01);
    	}
    	System.out.println("Extended");
    }
    
    public static boolean raiseShroud(double RTime){
    	if (flywheelCoverTalon.getPosition() >= 2800){
    		return true;
    	}
    	flywheelCoverTalon.set(-0.6);
    	Timer.delay(RTime);
    	flywheelCoverTalon.set(0);
    	return false;
    }
    
    public static void lowerShroud(double LTime){
    	flywheelCoverTalon.set(0.6);
    	Timer.delay(LTime);
    	flywheelCoverTalon.set(0);
    }
    
    public static void setFlywheelSpeed(double speed){
    	flywheelATalon.set(-speed);
    	flywheelBTalon.set(speed);
    }
    
    public static void setBeltSpeed(double speed){
    	flywheelBeltTalon.set(-speed);
    }
    
    public static void enableIntakeMode(){
    	System.out.println("Intake Mode Enabled");
    	setFlywheelSpeed(0.35);
    	setBeltSpeed(1);
    	closeGate();
    	extendShroud();
    }
    
    public static void disableMode(){
    	System.out.println("Shooter Disabled");
    	setFlywheelSpeed(0);
    	setBeltSpeed(0);
    	closeGate();
    	zeroShroud();
    }
    
    public static void enableShootMode(){
    	System.out.println("Shoot Mode Enabled");
    	setFlywheelSpeed(1);
    	setBeltSpeed(1);
    	openGate();
    	zeroShroud();
    }
    
    public static void openGate(){
    	gateSolenoid.set(DoubleSolenoid.Value.kReverse);
    }
    public static void closeGate(){
    	gateSolenoid.set(DoubleSolenoid.Value.kForward);
    }
    
    public static boolean getGateState(){
    	DoubleSolenoid.Value val = gateSolenoid.get();
    	if (val == DoubleSolenoid.Value.kForward){
    		return true;
    	}else{
    		return false;
    	}
    }
}

