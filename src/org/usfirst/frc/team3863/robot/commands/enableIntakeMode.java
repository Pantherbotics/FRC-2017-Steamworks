package org.usfirst.frc.team3863.robot.commands;

import org.usfirst.frc.team3863.robot.subsystems.*;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class enableIntakeMode extends BaseCommand {

    public enableIntakeMode() {
        // Use requires() here to declare subsystem dependencies
        requires(shooterMechanism);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	if (!(oi.lastIntakeState==1)){
    		oi.lastIntakeState = 1;
    		Intake.startIntake();
    		ShooterMechanism.enableIntakeMode();
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
