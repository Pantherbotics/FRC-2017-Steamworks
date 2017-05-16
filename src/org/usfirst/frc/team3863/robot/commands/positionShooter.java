package org.usfirst.frc.team3863.robot.commands;

import org.usfirst.frc.team3863.robot.RobotMap;
import org.usfirst.frc.team3863.robot.vision.*;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.vision.VisionThread;
import edu.wpi.first.wpilibj.vision.VisionPipeline;
import edu.wpi.first.wpilibj.vision.VisionRunner;
import org.usfirst.frc.team3863.robot.subsystems.*;
import org.opencv.core.Mat;
import org.opencv.imgproc.Imgproc;
import org.opencv.core.Rect;
import edu.wpi.cscore.CvSink;
import edu.wpi.cscore.CvSource;
import org.opencv.imgproc.Imgproc;

public class positionShooter extends BaseCommand{
	
	private Thread visionThread;
	private GripPipeline pipeline;
	public positionShooter() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	visionThread = new Thread(() -> {
    		pipeline = new GripPipeline();
    		CvSink cvSink = CameraServers.getServer().getVideo();
    		Mat source = new Mat();
    		double centerX = 0.0;
    		double error = 0.0;
    		cvSink.grabFrame(source);
    		while(!Thread.interrupted() || centerX < error){
    			pipeline.process(source);
    			if (!pipeline.filterContoursOutput().isEmpty()) {
    	            Rect r = Imgproc.boundingRect(pipeline.filterContoursOutput().get(0));
    	            centerX = r.x;
    	            
    	            if(centerX > CameraServers.getWidth()/2){
    	            	error = CameraServers.getWidth()/2-centerX;
    	            	DriveTrain.setPower(1, -1);
    	            	Timer.delay(0.5);
    	       
    	            }
    	            
    	            if(centerX < CameraServers.getWidth()/2){
    	            	error = CameraServers.getWidth()/2-centerX;
    	            	DriveTrain.setPower(-1, 1);
    	            	Timer.delay(0.5);
    	            }
    			}
    			
    			
    		}
    		
    	});

    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	visionThread.start();

    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return true;
    }

    // Called once after isFinished returns true
    protected void end() {
    	visionThread.interrupt();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }

}
