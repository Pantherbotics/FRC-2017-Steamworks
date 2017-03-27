package org.usfirst.frc.team3863.robot.commands;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class churnBalls extends BaseCommand {
    int cycleState = 0;
    int cycle = 0;
    public churnBalls() {
        // Use requires() here to declare subsystem dependencies
        //requires(churner);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	churner.churnForward();
    	System.out.println("Cycle 0 started - Forward");
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	if (cycleState == 0 && cycle >= 50){
    		System.out.println("Cycle 1 started - wait");
    		cycle = 0;
    		cycleState = 1;
    		churner.setPower(0);
    	}else if (cycleState == 1 && cycle >= 100){
    		System.out.println("Cycle 2 reverse");
    		cycle = 0;
    		cycleState = 2;
    		churner.churnReverse();
    	}	else if (cycleState == 2 && cycle >= 50){
    		System.out.println("Cycle 3 wait");
    		cycle = 0;
    		cycleState = 3;
    		churner.setPower(0);
    	}else if (cycleState == 3 && cycle >= 100){
    		System.out.println("Cycle 3 finished - repeat");
    		cycle = 0;
    		cycleState = 0;
    		churner.churnForward();
    	}
    	cycle += 1;
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    	churner.setPower(0);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	churner.setPower(0);
    }
}
