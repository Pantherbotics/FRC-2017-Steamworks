package org.usfirst.frc.team3863.robot.commands;

import edu.wpi.first.wpilibj.command.Command;

import org.usfirst.frc.team3863.robot.OI;
import org.usfirst.frc.team3863.robot.subsystems.*;

/**
 *
 */
public abstract class BaseCommand extends Command {

    public static OI oi;

    //SubSystems
    public static DriveTrain driveTrain = new DriveTrain();
    public static ShooterMechanism shooterMechanism = new ShooterMechanism();
    
    public static void init() {
        oi = new OI();
    }

    public BaseCommand() {
        super();
    }

    public BaseCommand(String name) {
        super(name);
    }

    public BaseCommand(String name, double timeout) {
        super(name, timeout);
    }

    public BaseCommand(double timeout) {
        super(timeout);
    }
}