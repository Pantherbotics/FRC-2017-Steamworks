package org.usfirst.frc.team3863.robot.subsystems;

import org.usfirst.frc.team3863.robot.RobotMap;

import com.ctre.CANTalon;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class ShooterMechanism extends Subsystem {
	boolean intakeMode = false;
	static CANTalon flywheelATalon = new CANTalon(RobotMap.flywheelATalonID);
	static CANTalon flywheelBTalon = new CANTalon(RobotMap.flywheelBTalonID);
	static CANTalon flywheelBeltTalon = new CANTalon(RobotMap.flywheelBeltTalonID);
	static CANTalon clywheelCoverTalon = new CANTalon(RobotMap.flywheelCoverTalonID);
	static DoubleSolenoid gateSolenoid = new DoubleSolenoid(RobotMap.solGateOpen, 
                                                      RobotMap.solGateClose);

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
    
    public static void setFlywheelSpeed(double speed){
    	flywheelATalon.set(-speed);
    	flywheelBTalon.set(speed);
    }
    
    public static void setBeltSpeed(double speed){
    	flywheelBeltTalon.set(-speed);
    }
    
    public static void enableIntakeMode(){
    	setFlywheelSpeed(0.3);
    	setBeltSpeed(1);
    	closeGate();
    }
    
    public static void enableShootMode(){
    	setFlywheelSpeed(1);
    	setBeltSpeed(1);
    	openGate();
    }
    
    public static void openGate(){
    	gateSolenoid.set(DoubleSolenoid.Value.kReverse);
    }
    public static void closeGate(){
    	gateSolenoid.set(DoubleSolenoid.Value.kForward);
    }
}

