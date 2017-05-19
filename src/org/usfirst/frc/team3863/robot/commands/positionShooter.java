package org.usfirst.frc.team3863.robot.commands;

import org.opencv.core.Mat;
import org.opencv.core.Rect;
import org.opencv.imgproc.Imgproc;
import org.usfirst.frc.team3863.robot.subsystems.CameraServers;
import org.usfirst.frc.team3863.robot.subsystems.DriveTrain;
import org.usfirst.frc.team3863.robot.vision.GripPipeline;

import edu.wpi.cscore.CvSink;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.networktables.NetworkTable;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class positionShooter extends BaseCommand{
	
	NetworkTable table;
	public positionShooter() {
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	table = NetworkTable.getTable("GRIP/boilerContours");
    	int centX = (int)table.getNumberArray("centerX")[0];
    	while(Math.abs(160-centX) > 5 && centX!=0){
    	if(centX < 160){
    		driveTrain.setPower(0.35, -0.35);
 	}
    	if(centX > 160){
    		driveTrain.setPower(-0.35, 0.35);
    	}
		centX = (int)table.getNumberArray("centerX")[0];
    	SmartDashboard.putNumber("boiler loc", centX);
    	}


    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {

    	
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	return true;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }

}
