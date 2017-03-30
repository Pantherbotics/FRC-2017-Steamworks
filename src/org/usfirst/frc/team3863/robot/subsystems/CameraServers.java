package org.usfirst.frc.team3863.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.cscore.CvSink;
import edu.wpi.cscore.CvSource;
import edu.wpi.cscore.UsbCamera;
import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.IterativeRobot;
import org.opencv.core.Mat;
import org.opencv.core.Point;
import org.opencv.core.Scalar;
import org.opencv.imgproc.Imgproc;
/**
 *
 */
public class CameraServers extends Subsystem {
	UsbCamera cam0, cam1;

    // Put methods for controlling this subsystem
    // here. Call these from Commands.

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
    
    public static void initCamServers(){
    	CameraServer server = CameraServer.getInstance();
    	
    	UsbCamera cam0 = server.startAutomaticCapture("cam0", "/dev/video0");
    	cam0.setResolution(640, 480);
    	System.out.println("Camera Servers Init");
    }
}

