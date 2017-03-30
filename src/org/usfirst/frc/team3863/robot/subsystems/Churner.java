package org.usfirst.frc.team3863.robot.subsystems;

import org.usfirst.frc.team3863.robot.RobotMap;

import com.ctre.CANTalon;

import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Churner extends Subsystem {
	static CANTalon churnTalon = new CANTalon(RobotMap.churnTalonID);
    static double churnValue = 0.75;
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
    
    public static void churnForward(){
    	setPower(churnValue);
    }
    
    public static void churnReverse(){
    	setPower(-churnValue);
    }
    
    public static void setPower(double power){
        churnTalon.set(power);
	}
}

