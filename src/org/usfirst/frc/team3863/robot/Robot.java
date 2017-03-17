
package org.usfirst.frc.team3863.robot;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import org.usfirst.frc.team3863.robot.commands.*;
import org.usfirst.frc.team3863.robot.subsystems.DriveTrain;
import org.usfirst.frc.team3863.robot.subsystems.*;
import org.usfirst.frc.team3863.robot.commands.AutoTransmission;


/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class Robot extends IterativeRobot {
    int lastPOV = 0;
    
    boolean lastIncShoot = false;
    boolean lastIncFly = false;
    boolean lastIntakeToggle = false;
	//public static final ExampleSubsystem exampleSubsystem = new ExampleSubsystem();
	public static OI oi;

	Command autonomousCommand;
	Command autoTransCommand;
	SendableChooser<Command> chooser = new SendableChooser<>();
	SendableChooser<Command> autonChooser = new SendableChooser<>();

	/**
	 * This function is run when the robot is first started up and should be
	 * used for any initialization code.
	 */
	@Override
	public void robotInit() {
		System.out.println("if you use enough angle brackets you can get anywhere"  );
		BaseCommand.init();
		oi = new OI();
		chooser.addDefault("Arcade Drive - Default", new driveModeArcade());
		SmartDashboard.putData("Drive Mode", chooser);
		//autoTransCommand = new AutoTransmission();
		
		autonChooser.addDefault("No Autonomous", null);
		autonChooser.addObject("Compressor Only", null);
		autonChooser.addObject("Drive Forward 3s", new driveForwardAuto());
		
		SmartDashboard.putData("Autonomous Selection", autonChooser);
	}

	/**
	 * This function is called once each time the robot enters Disabled mode.
	 * You can use it to reset any subsystem information you want to clear when
	 * the robot is disabled.
	 */
	@Override
	public void disabledInit() {

	}

	@Override
	public void disabledPeriodic() {
		Scheduler.getInstance().run();
	}

	/**
	 * This autonomous (along with the chooser code above) shows how to select
	 * between different autonomous modes using the dashboard. The sendable
	 * chooser code works with the Java SmartDashboard. If you prefer the
	 * LabVIEW Dashboard, remove all of the chooser code and uncomment the
	 * getString code to get the auto name from the text box below the Gyro
	 *
	 * You can add additional auto modes by adding additional commands to the
	 * chooser code above (like the commented example) or additional comparisons
	 * to the switch structure below with additional strings & commands.
	 */
	@Override
	public void autonomousInit() {

		Command autonomousCommand = autonChooser.getSelected();
		 
		// schedule the autonomous command (example)
		if (autonomousCommand != null)
			autonomousCommand.start();
	}

	/**
	 * This function is called periodically during autonomous
	 */
	@Override
	public void autonomousPeriodic() {
		Scheduler.getInstance().run();
	}

	@Override
	public void teleopInit() {
		Command driveModeCommand = chooser.getSelected();
		
		if (autonomousCommand != null)
			autonomousCommand.cancel();
		
		if (autoTransCommand != null)
			autoTransCommand.start();
		
		if (driveModeCommand != null)
			driveModeCommand.start();
		
		DriveTrain.enable();
		ShooterMechanism.zeroShroud();
		Command dis = new disableMode();
		dis.start();
		Intake.startIntake();
		//ShooterMechanism.extendShroud();
		//ShooterMechanism.enableIntakeMode();
	}

	/**
	 * This function is called periodically during operator control
	 */
	@Override
	public void teleopPeriodic() {
		SmartDashboard.putData(Scheduler.getInstance());
		Scheduler.getInstance().run();
		ShooterMechanism.debugShroud();
		Command shroudCommand = null;
		if (oi.partnerDSstick.getPOV() == 0 && lastPOV == -1){
			shroudCommand = new enableShooterMode();
		}
		else if (oi.partnerDSstick.getPOV() == 270 && lastPOV == -1){
			shroudCommand = new enableIntakeMode();
		}
		else if (oi.partnerDSstick.getPOV() == 180 && lastPOV == -1){
			shroudCommand = new disableMode();
        }
		if (shroudCommand != null)
			shroudCommand.start();
		lastPOV = oi.partnerDSstick.getPOV();
		
		if (oi.partnerDSstick.getRawButton(7) && oi.partnerDSstick.getRawButton(8) && !lastIntakeToggle){
			Command curCMD = new toggleShooterBelt();
			curCMD.start();
		}
		lastIntakeToggle = (oi.partnerDSstick.getRawButton(7) && oi.partnerDSstick.getRawButton(8));
		
		Command posCommand = null;
		if (oi.partnerDSstick.getRawButton(6) && !lastIncShoot){
			posCommand = new incrementShroud();
		}
		else if (oi.partnerDSstick.getRawAxis(3) >= 0.98 && !lastIncShoot){
			posCommand = new decrementShroud();
		}
		if (posCommand != null)
			posCommand.start();
		lastIncShoot = (oi.partnerDSstick.getRawButton(6) | oi.partnerDSstick.getRawAxis(3) >= 0.98);
		
		Command flyCommand = null;
		if (oi.partnerDSstick.getRawButton(5) && !lastIncFly){
			flyCommand = new incrementFlywheelSpeed();
		}
		else if (oi.partnerDSstick.getRawAxis(2) >= 0.98 && !lastIncFly){
			flyCommand = new decrementFlywheelSpeed();
		}
		if (flyCommand != null)
			flyCommand.start();
		lastIncFly = (oi.partnerDSstick.getRawButton(5) | oi.partnerDSstick.getRawAxis(2) >= 0.98);		
				//butGateToggle.whenPressed(new pulseArms());
		if (oi.partnerDSstick.getRawButton(4)){
			Winch.setWinchPower(-1);
			Winch.enableBreak();
		}else{
			Winch.setWinchPower(0);
			//Winch.disableBreak();
		}
	}

	/**
	 * This function is called periodically during test mode
	 */
	@Override
	public void testPeriodic() {
    	
		LiveWindow.run();
		
	}
	
}
