package org.usfirst.frc.team3863.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
import java.lang.Math;
import com.ctre.CANTalon;
import com.ctre.CANTalon.TalonControlMode;

import org.usfirst.frc.team3863.robot.RobotMap;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;

/**
 *
 */
public class DriveTrain extends Subsystem {
	static CANTalon leftTalonA = new CANTalon(RobotMap.leftADriveTalonID);
	static CANTalon leftTalonB = new CANTalon(RobotMap.leftBDriveTalonID);
	static CANTalon rightTalonA = new CANTalon(RobotMap.rightADriveTalonID);
	static CANTalon rightTalonB = new CANTalon(RobotMap.rightBDriveTalonID);
    static DoubleSolenoid transSolenoid = new DoubleSolenoid(RobotMap.solTransHigh, 
			                                          RobotMap.solTransLow);
	static Value transRest = DoubleSolenoid.Value.kOff;
	static Value transFast = DoubleSolenoid.Value.kForward;
	static Value transSlow = DoubleSolenoid.Value.kReverse;
	
	public static boolean transState = false;
	
	//static CANTalon leftTalon = new CANTalon(RobotMap.leftDriveTalonID);
    // Put methods for controlling this subsystem
    // here. Call these from Commands.

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
    
    public static void turn(int degrees){
    	
    }
    
    public static double getVelocityAvg(){
    	double lV = leftTalonA.getEncVelocity();
    	double rV = rightTalonA.getEncVelocity();
    	double avg = (Math.abs(lV) + Math.abs(rV)) / 2;
    	return avg;
    }
    
    public static double getCurrentAvg(){
    	double lA = leftTalonA.getOutputCurrent();
    	double rA = rightTalonA.getOutputCurrent();
    	double avg = (lA + rA) / 2;
    	return avg;
    }
    
    public static double[] getMotorDirections(){
    	double[] directions = new double[2];
    	directions[0] = leftTalonA.getSpeed()/Math.abs(leftTalonA.getSpeed());
    	directions[1] = rightTalonA.getSpeed()/Math.abs(rightTalonA.getSpeed())*-1;
    	if (Double.isNaN(directions[0])){directions[0] = 0;}
    	if (Double.isNaN(directions[1])){directions[1] = 0;}
		return directions ;
    	
    }
    
    public static void debugCalVal(){
    	double aV = getVelocityAvg();
    	double aC = getCurrentAvg();
    	double[] dir = getMotorDirections();
    	System.out.println("cAvg: "+aC+" vAvg: "+aV+" dL: "+dir[0]+" dR: "+dir[1]);
    }
    
    public static void setTransFast(){
    	transSolenoid.set(DoubleSolenoid.Value.kForward);
    	//transSolenoid.set(transFast);
    	transState = true;
    }
    
    public static void setTransSlow(){
    	transSolenoid.set(DoubleSolenoid.Value.kReverse);
    	//transSolenoid.set(transSlow);
    	transState = false;
    }
    
    public static void setPower(double left, double right){
    	leftTalonA.set(left);
    	rightTalonA.set(-right);
    }
    
    public static void driveFeet(double feet){
    	double revs = feet/(RobotMap.WHEEL_CIRCUM/12);
    	int ticks = (int) revs*RobotMap.driveTrainEncoderTicks;
    	leftTalonA.changeControlMode(TalonControlMode.Position);
    	rightTalonA.changeControlMode(TalonControlMode.Position);
    	leftTalonA.set(leftTalonA.getEncPosition()+ticks);
    	rightTalonA.set(rightTalonA.getEncPosition()+ticks);
    }
    
    public static void disable(){
    	leftTalonA.disable();
    	leftTalonB.disable();
    	rightTalonA.disable();
    	rightTalonB.disable();
    	transSolenoid.set(transRest);
    }
    
    public static void enable(){

    	configureTalons();
    	leftTalonA.enable();
    	leftTalonB.enable();
    	rightTalonA.enable();
    	rightTalonB.enable();
    	configureTalons();
    	//setTransFast();
    }
    
    public static void configureTalons(){
    	//rightTalonA.changeControlMode(TalonControlMode.Speed);
    	rightTalonB.changeControlMode(TalonControlMode.Follower);
    	rightTalonB.set(RobotMap.rightADriveTalonID);
    	
    	//leftTalonA.changeControlMode(TalonControlMode.Speed);
    	leftTalonB.changeControlMode(TalonControlMode.Follower);
    	leftTalonB.set(RobotMap.leftADriveTalonID);
    	
    	rightTalonA.configEncoderCodesPerRev(RobotMap.driveTrainEncoderTicks);
    	leftTalonA.configEncoderCodesPerRev(RobotMap.driveTrainEncoderTicks);
    	
    }
    
    public static void debugSpeeds(){
    	leftTalonA.getSpeed();
    	rightTalonA.getSpeed();
    }
}

