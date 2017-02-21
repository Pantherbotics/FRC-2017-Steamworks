package org.usfirst.frc.team3863.robot.commands;

import org.usfirst.frc.team3863.robot.subsystems.ShooterMechanism;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class toggleFlywheelMode extends Command {
	boolean isComplete = false; 
    public toggleFlywheelMode() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	boolean st = ShooterMechanism.getGateState();
    	System.out.println(st);
    	if (st){
    		ShooterMechanism.enableShootMode();
    	}else{
    		ShooterMechanism.enableIntakeMode();
    	}
    	isComplete = true;
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return isComplete;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
