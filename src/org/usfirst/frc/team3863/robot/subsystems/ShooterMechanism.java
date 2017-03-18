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
	static double setShootSpeed = 0.775;
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
        SmartDashboard.putNumber("ShroudPosisition", flywheelCoverTalon.getPosition());
        SmartDashboard.putBoolean("ShroudLimitSwitch", flywheelCoverTalon.isFwdLimitSwitchClosed());
        SmartDashboard.putNumber("actualCoverPower", flywheelCoverTalon.get());
        SmartDashboard.putNumber("setFlywheelPower", setShootSpeed);
        SmartDashboard.putNumber("actualFlywheelSpeedA", flywheelATalon.getEncVelocity());
        SmartDashboard.putNumber("actualFlywheelSpeedB", flywheelBTalon.getEncVelocity());
    }
    
    public static void changeSetShootSpeed(double incr){
    	setShootSpeed += incr;
    }
    
    public static void zeroShroud(){
    	boolean zeroed = false;
    	int idx = 0;
    	while (!zeroed){
    		if (idx > 500){break;}
    		zeroed = flywheelCoverTalon.isFwdLimitSwitchClosed();
    		lowerShroud(0.01);
    		idx += 1;
    		System.out.println("idx: "+idx);
    	}
    	idx = 0;
    	while (zeroed){
    		if (idx > 500){break;}
    		zeroed = flywheelCoverTalon.isFwdLimitSwitchClosed();
    		raiseShroud(0.01);
    		idx += 1;
    		System.out.println("idx: "+idx);
    		
    	}
    	System.out.println("Zeroed!");
    	flywheelCoverTalon.setPosition(0);
    }
    
    public static void extendShroud(){
    	boolean extend = false;
    	int idx = 0;
    	while (!extend){
    		if (idx > 200){break;}
    		extend = raiseShroud(0.01);
    		idx += 1;
    		System.out.println("idx:"+idx);
    	}
    	System.out.println("Extended");
    }
    
    public static boolean raiseShroud(double RTime){
    	if (flywheelCoverTalon.getPosition() >= 2800){
    		return true;
    	}
    	flywheelCoverTalon.set(-0.5);
    	Timer.delay(RTime);
    	flywheelCoverTalon.set(0);
    	return false;
    }
    
    public static void lowerShroud(double LTime){
    	flywheelCoverTalon.set(0.75);
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
    
    public static void updateSpeed(){
    	if (flywheelATalon.get() == 0){return;}
    	setFlywheelSpeed(setShootSpeed);
    }
    
    public static void enableIntakeMode(){
    	System.out.println("Intake Mode Enabled");
    	SmartDashboard.putString("Shooter Mode: ", "Intake");
    	extendShroud();
    	closeGate();
    	setFlywheelSpeed(0.35);
    	setBeltSpeed(1);
    }
    
    public static void disableMode(){
    	System.out.println("Shooter Disabled");
    	SmartDashboard.putString("Shooter Mode: ", "Disabled");
    	setFlywheelSpeed(0);
    	setBeltSpeed(0);
    	closeGate();
    	zeroShroud();
    }
    
    public static void enableShootMode(){
    	System.out.println("Shoot Mode Enabled");
    	SmartDashboard.putString("Shooter Mode: ", "Shoot High");
    	setFlywheelSpeed(setShootSpeed);
    	zeroShroud();
    	setBeltSpeed(1);
    	openGate();
    }
    
    public static void openGate(){
    	gateSolenoid.set(DoubleSolenoid.Value.kReverse);
    	SmartDashboard.putString("Gate Mode: ", "Open");
    }
    public static void closeGate(){
    	gateSolenoid.set(DoubleSolenoid.Value.kForward);
    	SmartDashboard.putString("Gate Mode: ", "Closed");
    }
    
    public static boolean getGateState(){
    	DoubleSolenoid.Value val = gateSolenoid.get();
    	if (val == DoubleSolenoid.Value.kForward || val == DoubleSolenoid.Value.kOff){
    		return true;
    	}else{
    		return false;
    	}
    }
    
    public static boolean getBeltState(){
    	return !(flywheelBeltTalon.get() == 0);
    }
    
    public static void setBeltBlock(boolean isBlocked){
    	if (isBlocked){
    		if (flywheelBeltTalon.get() == 0){return;}
        	else{
        		setBeltSpeed(0.04);
        	}
    	}else{
    		if (flywheelBeltTalon.get()==0.04){return;}
        	else{
        		setBeltSpeed(1);
        	}
    	}
    	
    }
    
    public static void calcConstantSpeed(){
    	if (flywheelATalon.get() <= 0.01){return;}
    	double vel = (flywheelATalon.getEncVelocity()+flywheelBTalon.getEncVelocity())/2;
    	double error = 450-vel;
    	changeSetShootSpeed(error*0.01);
    }
}

