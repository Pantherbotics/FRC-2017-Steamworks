package org.usfirst.frc.team3863.robot.commands;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class switchLowSpeedTransmission extends BaseCommand {
	boolean isComplete = false;
    public switchLowSpeedTransmission() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	driveTrain.setTransSlow();
    	isComplete = true;
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
