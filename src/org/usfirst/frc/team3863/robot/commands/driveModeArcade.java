package org.usfirst.frc.team3863.robot.commands;

import org.usfirst.frc.team3863.robot.commands.BaseCommand;

/**
 *
 */
public class driveModeArcade extends BaseCommand {
    public driveModeArcade() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(driveTrain);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	double leftForward = oi.leftDSstick.getY();
    	double rightForward = oi.rightDSstick.getY();
    	double leftTwist = oi.leftDSstick.getZ();
    	double rightTwist = oi.rightDSstick.getZ();
    	
    	double leftSpeed = leftForward + leftTwist;
    	double rightSpeed = rightForward - rightTwist;
    	
    	driveTrain.setPower(leftSpeed, rightSpeed);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
