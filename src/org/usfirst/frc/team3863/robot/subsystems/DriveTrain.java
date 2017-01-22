package org.usfirst.frc.team3863.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
import com.ctre.CANTalon;
import org.usfirst.frc.team3863.robot.RobotMap;

/**
 *
 */
public class DriveTrain extends Subsystem {
	static CANTalon leftTalon = new CANTalon(RobotMap.leftDriveTalonID);
    // Put methods for controlling this subsystem
    // here. Call these from Commands.

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
    
    public static void setPower(double power){
    	leftTalon.set(power);
    }
}

