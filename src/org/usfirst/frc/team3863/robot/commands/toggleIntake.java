package org.usfirst.frc.team3863.robot.commands;

import org.usfirst.frc.team3863.robot.subsystems.Intake;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class toggleIntake extends BaseCommand {
    boolean isComplete = false;
    public toggleIntake() {
        // Use requires() here to declare subsystem dependencies
        requires(shooterMechanism);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	boolean st = Intake.getStatus();
    	if (st){
    		Intake.stopIntake();
    	}else{
    		Intake.startIntake();
    	}
    	isComplete = true;
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return isComplete;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
