package org.usfirst.frc.team3863.robot.commands;

import org.usfirst.frc.team3863.robot.RobotMap;
import org.usfirst.frc.team3863.robot.subsystems.Winch;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class gearAuto extends BaseCommand {

    public gearAuto() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    }

 // Called just before this Command runs the first time
    protected void initialize() {
    	driveTrain.enable();
    }

    // Called repeatedly when this Command is scheduled to run
    //Drive to wall: 110.02 inches
    protected void execute() {
    	System.out.print("Autonomous - Gear");
    	driveTrain.setTransSlow();
    	driveTrain.setPower(0.3*RobotMap.LEFT_CORRECTION, 0.3*RobotMap.RIGHT_CORRECTION);
    	Timer.delay(1.75);
    	driveTrain.setPower(0.2*RobotMap.LEFT_CORRECTION, 0.2*RobotMap.RIGHT_CORRECTION);
    	Timer.delay(4);
    	driveTrain.setPower(0,0);
    	//Timer.delay(2);
    	//driveTrain.setPower(-1*LEFT_CORRECTION, -1*RIGHT_CORRECTION);
    	//Timer.delay(3);
    	//driveTrain.setPower(0, 0);
    	
    	
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
