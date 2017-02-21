package org.usfirst.frc.team3863.robot.subsystems;

import org.usfirst.frc.team3863.robot.RobotMap;

import com.ctre.CANTalon;

import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Intake extends Subsystem {
	static CANTalon intakeTalon = new CANTalon(RobotMap.intakeTalonID);

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
    
    public static void startIntake(){
    	intakeTalon.enable();
    	intakeTalon.set(1);
    }
    
    public static void stopIntake(){
    	intakeTalon.set(0);
    	intakeTalon.disable();
    }
    
    public static boolean getStatus(){
    	double setSpeed = intakeTalon.get();
    	return Math.abs(setSpeed) > 0;
    }
}

