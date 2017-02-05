package org.usfirst.frc.team3863.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
import java.lang.Math;
import com.ctre.CANTalon;
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
    DoubleSolenoid transSolenoid = new DoubleSolenoid(RobotMap.solTransHigh, 
			                                          RobotMap.solTransLow);
	Value transRest = DoubleSolenoid.Value.kOff;
	Value transFast = DoubleSolenoid.Value.kForward;
	Value transSlow = DoubleSolenoid.Value.kReverse;
	
	public static boolean transState = false;
	
	//static CANTalon leftTalon = new CANTalon(RobotMap.leftDriveTalonID);
    // Put methods for controlling this subsystem
    // here. Call these from Commands.

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
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
    
    public void setTransFast(){
    	transSolenoid.set(DoubleSolenoid.Value.kForward);
    	//transSolenoid.set(transFast);
    	transState = true;
    }
    
    public  void setTransSlow(){
    	transSolenoid.set(DoubleSolenoid.Value.kReverse);
    	//transSolenoid.set(transSlow);
    	transState = false;
    }
    
    public static void setPower(double left, double right){
    	leftTalonA.set(left);
    	leftTalonB.set(left);
    	rightTalonA.set(right);
    	rightTalonB.set(right);
    }
    
    public void disable(){
    	leftTalonA.disable();
    	leftTalonB.disable();
    	rightTalonA.disable();
    	rightTalonB.disable();
    	transSolenoid.set(transRest);
    }
    
    public static void enable(){
    	leftTalonA.enable();
    	leftTalonB.enable();
    	rightTalonA.enable();
    	rightTalonB.enable();
    	//setTransFast();
    }
}

