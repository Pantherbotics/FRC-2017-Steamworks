package org.usfirst.frc.team3863.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

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
    public static Intake intake = new Intake();
    public static Winch winch = new Winch();
    
    public static void init() {
        oi = new OI();
        SmartDashboard.putData(driveTrain);
        SmartDashboard.putData(shooterMechanism);
        SmartDashboard.putData(intake);
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