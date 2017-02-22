package org.usfirst.frc.team3863.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
import org.usfirst.frc.team3863.robot.RobotMap;
import com.ctre.CANTalon;

/**
 *
 */
public class Winch extends Subsystem {
	static CANTalon winchTalonA = new CANTalon(RobotMap.winchATalonID);
	static CANTalon winchTalonB = new CANTalon(RobotMap.winchBTalonID);

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
    
    public void setWinchPower(double power){
    	winchTalonA.set(power);
    	winchTalonB.set(-power);
    }
    
    public void enableBreak(){
    	winchTalonA.enableBrakeMode(true);
    	winchTalonB.enableBrakeMode(true);
    }
    
    public void disableBreak(){
    	winchTalonA.enableBrakeMode(false);
    	winchTalonB.enableBrakeMode(false);
    }
}

