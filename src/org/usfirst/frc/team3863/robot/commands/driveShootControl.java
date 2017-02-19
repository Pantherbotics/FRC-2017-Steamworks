package org.usfirst.frc.team3863.robot.commands;

import org.usfirst.frc.team3863.robot.RobotMap;
import org.usfirst.frc.team3863.robot.commands.BaseCommand;

/**
 *
 */
public class driveShootControl extends BaseCommand {
    public driveShootControl() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(shooterMechanism);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	driveTrain.enable();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	double leftSpeed, rightSpeed;
    	if (!oi.arcadeDSstick.getRawButton(RobotMap.drive_tankToArcadeButton)){
    		leftSpeed = oi.leftDSstick.getRawAxis(RobotMap.drive_tankLeftForwardAxis);
        	rightSpeed = oi.rightDSstick.getRawAxis(RobotMap.drive_tankRightForwardAxis);
    	}else{
    		double Forward = oi.arcadeDSstick.getRawAxis(RobotMap.drive_arcadeForwardAxis);
        	double Twist = oi.arcadeDSstick.getRawAxis(RobotMap.drive_arcadeRotateAxis);	
        	leftSpeed = Forward-Twist;
        	rightSpeed = Forward+Twist;
    	}
    	driveTrain.setPower(-leftSpeed, -rightSpeed);
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
