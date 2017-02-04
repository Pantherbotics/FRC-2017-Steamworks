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
    	driveTrain.enable();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	double leftForward = oi.leftDSstick.getY();
    	double rightForward = oi.rightDSstick.getY();
    	double leftTwist = oi.leftDSstick.getRawAxis(3);
    	double rightTwist = oi.rightDSstick.getRawAxis(2);
    	
    	double leftSpeed = (leftForward+rightForward)/2 + (leftTwist+rightTwist)/2;
    	double rightSpeed = (leftForward+rightForward)/2 - (leftTwist+rightTwist)/2;
    	
    	driveTrain.setPower(leftSpeed, -rightSpeed);
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
