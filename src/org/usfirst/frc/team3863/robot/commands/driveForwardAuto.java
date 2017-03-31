package org.usfirst.frc.team3863.robot.commands;

import org.usfirst.frc.team3863.robot.RobotMap;
import org.usfirst.frc.team3863.robot.subsystems.Winch;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class driveForwardAuto extends BaseCommand {
    
    public driveForwardAuto() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	driveTrain.enable();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {

    	System.out.print("Autonomous - Drive Forward");
    	driveTrain.setTransFast();
    	driveTrain.setPower(1*RobotMap.LEFT_CORRECTION, 1*RobotMap.RIGHT_CORRECTION);
    	Winch.setWinchPower(-0.5);
    	Timer.delay(0.5);
    	Winch.setWinchPower(0);
    	Timer.delay(2.5);
    	driveTrain.setPower(0,0);
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
