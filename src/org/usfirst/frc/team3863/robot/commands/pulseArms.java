package org.usfirst.frc.team3863.robot.commands;

import org.usfirst.frc.team3863.robot.subsystems.ShooterMechanism;

import edu.wpi.first.wpilibj.Timer;

/**
 *
 */
public class pulseArms extends BaseCommand {

    public pulseArms() {
        // Use requires() here to declare subsystem dependencies
        requires(shooterMechanism);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	boolean st = ShooterMechanism.getGateState();
    	if (!st){
    		ShooterMechanism.closeGate();
    	}else{
    		ShooterMechanism.openGate();
    	}
    	
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
