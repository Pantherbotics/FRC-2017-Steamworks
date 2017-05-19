package org.usfirst.frc.team3863.robot.subsystems;

import edu.wpi.cscore.UsbCamera;
import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.command.Subsystem;
/**
 *
 */
public class CameraServers extends Subsystem {
	static UsbCamera cam0, cam1;
	static CameraServer server;
	static final int WIDTH = 320;
	static final int HEIGHT = 240;
    // Put methods for controlling this subsystem
    // here. Call these from Commands.

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
    
    public static void initCamServers(){
    	server = CameraServer.getInstance();
    	cam0 = server.startAutomaticCapture("cam0", "/dev/video0");
    	//cam0.setFPS(20);
    	cam0.setResolution(320, 240);
    	cam0.setExposureManual(1);
    	System.out.println("Camera Servers Init");
    }
    
    public static UsbCamera getCam(){
    	return cam0;
    }
    
    public static CameraServer getServer(){
    	return server;
    }
    
    public static int getWidth() { return WIDTH;}
    
    public static int getHeight() {return HEIGHT;}

}

