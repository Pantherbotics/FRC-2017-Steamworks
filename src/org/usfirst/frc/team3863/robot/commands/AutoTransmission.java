package org.usfirst.frc.team3863.robot.commands;

import org.usfirst.frc.team3863.robot.RobotMap;
import org.usfirst.frc.team3863.robot.commands.BaseCommand;

/**
 *
 */
public class AutoTransmission extends BaseCommand {
	double switchTimeout = 0;
    public AutoTransmission() {
    }

    // Called repeatedly when this Command is scheduled to run
    @SuppressWarnings("static-access")
	protected void execute() {
    	return;
    	if(oi.arcadeDSstick.getRawButton(RobotMap.drive_transToggleButton) && !oi.autoTransmissionTrigger){
    		System.out.println("AutoTrans Disabled");
    		oi.autoTransmissionEnabled = false;
    	}else if(!oi.arcadeDSstick.getRawButton(RobotMap.drive_transToggleButton) && oi.autoTransmissionTrigger){
    		System.out.println("AutoTrans Enabled");
    		oi.autoTransmissionEnabled = true;
    	}
    	oi.autoTransmissionTrigger = oi.arcadeDSstick.getRawButton(RobotMap.drive_transToggleButton);
    	
    	if(!oi.autoTransmissionEnabled){
    		driveTrain.setTransSlow();
    		return;
    	}
    	driveTrain.debugCalVal();
    	double vel = driveTrain.getVelocityAvg();
    	double cur = driveTrain.getCurrentAvg();
    	double[] dir = driveTrain.getMotorDirections();
    	boolean isTurning = (!(dir[0]==dir[1]) && vel>250);
    	//System.out.println("t:"+isTurning+" s:"+driveTrain.transState);
    	
    	if (switchTimeout > 0){
    		switchTimeout -= 1;
    	
    	}else if (driveTrain.transState == true){ // if high speed
    		if (isTurning){
    			driveTrain.setTransSlow();
    			System.out.println("AutoTrans - Switch to low");
    			switchTimeout = 20;
    		}
    	}else if (driveTrain.transState == false){ //low speed
    		if (!isTurning){
    			driveTrain.setTransFast();
    			System.out.println("AutoTrans - Switch to high");
    			switchTimeout = 20;
    		}
    	}
    }
    
    

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return True;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	
    }
}
