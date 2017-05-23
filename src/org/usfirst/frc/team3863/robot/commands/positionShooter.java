package org.usfirst.frc.team3863.robot.commands;

import org.usfirst.frc.team3863.robot.subsystems.DriveTrain;

import edu.wpi.first.wpilibj.networktables.NetworkTable;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class positionShooter extends BaseCommand{
	
	NetworkTable table;
	double error = 0;
	int centX;
	double speed;
	double[] defaultVal = {0};
	public positionShooter() {
    }

    // Called just before this Command runs the first time
	protected void initialize() {
    	table = NetworkTable.getTable("GRIP/boilerContours");
    	centX = (int)table.getNumberArray("centerX", defaultVal)[0];
    	error = Math.abs(160-centX);
    	speed = 0.3;
    	driveTrain.isCommandRunning(true);
    }

    private double calcSpeed(){
    	double speed = error/(160.0*2);
    	if(speed < .25)
    		speed = .25;
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

    // Called onceinished returns true
    protected void end() {
    	driveTrain.isCommandRunning(false);
    	System.out.println("CENTERED!");
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }

}
