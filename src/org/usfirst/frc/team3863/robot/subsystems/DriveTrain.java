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
	static CANTalon leftTalon = new CANTalon(RobotMap.leftDriveTalonID);
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
    	double lV = leftTalon.getEncVelocity();
    	double rV = lV;//rightTalon.getEncVelocity();
    	double avg = (Math.abs(lV) + Math.abs(rV)) / 2;
    	return avg;
    }
    
    public static double getCurrentAvg(){
    	double lA = leftTalon.getOutputCurrent();
    	double rA = lA;//rightTalon.getOutputCurrent();
    	double avg = (lA + rA) / 2;
    	return avg;
    }
    
    public static void debugCalVal(){
    	double aV = getVelocityAvg();
    	double aC = getCurrentAvg();
    	System.out.println("cAvg: "+aC+" vAvg: "+aV);
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
    	leftTalon.set(left);
    	//rightTalon.set(right);
    }
    
    public void disable(){
    	leftTalon.disable();
    	transSolenoid.set(transRest);
    	//rightTalon.disable()
    }
    
    public static void enable(){
    	leftTalon.enable();
    	//setTransFast();
    	//rightTalon.enable();
    }
}

