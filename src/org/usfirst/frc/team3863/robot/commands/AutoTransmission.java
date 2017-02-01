package org.usfirst.frc.team3863.robot.commands;

import org.usfirst.frc.team3863.robot.RobotMap;
import org.usfirst.frc.team3863.robot.commands.BaseCommand;

/**
 *
 */
public class AutoTransmission extends BaseCommand {

    public AutoTransmission() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	//requires(driveTrain);
    }

    // Called repeatedly when this Command is scheduled to run
    @SuppressWarnings("static-access")
	protected void execute() {
    	driveTrain.debugCalVal();
    	double vel = driveTrain.getVelocityAvg();
    	double cur = driveTrain.getCurrentAvg();
    	
    	if (vel > RobotMap.driveShiftLow_HighSpeed){
    		driveTrain.setTransFast();
    	}else if (vel < RobotMap.driveShiftHigh_LowSpeed){
    		driveTrain.setTransSlow();
    	}
    	//pseudocode:
    	//always low speed on turning
    	//shift low speed if current above setpoint
    	//
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
