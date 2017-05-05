package org.usfirst.frc.team3863.robot.commands;

import org.usfirst.frc.team3863.robot.subsystems.*;
import org.usfirst.frc.team3863.robot.vision.*;
import edu.wpi.first.wpilibj.vision.*;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class enableShooterMode extends BaseCommand {

    public enableShooterMode() {
        // Use requires() here to declare subsystem dependencies
        requires(shooterMechanism);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	if (!(oi.lastIntakeState==2)){
    		oi.lastIntakeState = 2;
    		ShooterMechanism.enableShootMode();
    		Intake.startIntake();
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
