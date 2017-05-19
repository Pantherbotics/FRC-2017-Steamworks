package org.usfirst.frc.team3863.robot.commands;

import edu.wpi.first.wpilibj.networktables.NetworkTable;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class positionShooter extends BaseCommand{
	
	NetworkTable table;
	double error = 0;
	int centX;
	double speed;
	double[] defaultVal;
	public positionShooter() {
    }

    // Called just before this Command runs the first time
	protected void initialize() {
    	defaultVal[0] = -1;
    	table = NetworkTable.getTable("GRIP/boilerContours");
    	centX = (int)table.getNumberArray("centerX", defaultVal)[0];
    	error = Math.abs(160-centX);
    	speed = 0.3;
    }

    private double calcSpeed(){
    	double speed = error/160.0;
    	if(speed < .01)
    		speed = .1;
    	return speed;
    }
    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	speed = calcSpeed();
    	if(centX < 160){
    		driveTrain.setPower(speed, -speed);
 	}
    	if(centX > 160){
    		driveTrain.setPower(-speed, speed);
    	}
		centX = (int)table.getNumberArray("centerX", defaultVal)[0];
    	error = Math.abs(160-centX);
    	SmartDashboard.putNumber("boiler loc", centX);
    	
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	return error < 5 || centX == -1;
    }

    // Called once after isFinished returns true
    protected void end() {
    	System.out.println("CENTERED!");
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }

}
