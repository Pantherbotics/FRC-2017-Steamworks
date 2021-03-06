package org.usfirst.frc.team3863.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;

import org.usfirst.frc.team3863.robot.commands.*;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {
	//// CREATING BUTTONS
	// One type of button is a joystick button which is any button on a
	//// joystick.
	// You create one by telling it which joystick it's on and which button
	// number it is.
	// Joystick stick = new Joystick(port);
	// Button button = new JoystickButton(stick, buttonNumber);

	// There are a few additional built in buttons you can use. Additionally,
	// by subclassing Button you can create custom triggers and bind those to
	// commands the same as any other Button.

	//// TRIGGERING COMMANDS WITH BUTTONS
	// Once you have a button, it's trivial to bind it to a button in one of
	// three ways:

	// Start the command when the button is pressed and let it run the command
	// until it is finished as determined by it's isFinished method.
	// button.whenPressed(new ExampleCommand());

	// Run the command while the button is being held down and interrupt it once
	// the button is released.
	// button.whileHeld(new ExampleCommand());

	// Start the command when the button is released and let it run the command
	// until it is finished as determined by it's isFinished method.
	// button.whenReleased(new ExampleCommand());
	
	public Joystick leftDSstick = new Joystick(RobotMap.leftDSJoystick);
	public Joystick rightDSstick = new Joystick(RobotMap.rightDSJoystick);
	public Joystick arcadeDSstick = rightDSstick;
	public Joystick partnerDSstick = new Joystick(RobotMap.partnerDSJoystick);
	
	Button butIntakeBeltToggle = new JoystickButton(partnerDSstick, 2);
	Button butGateToggle = new JoystickButton(partnerDSstick, 1);
	Button butRunWinch = new JoystickButton(partnerDSstick, 4);
	
	Button butTransFast = new JoystickButton(leftDSstick, 1);
	Button butTransSlow = new JoystickButton(rightDSstick, 1);
	
	//Button butFlywheelUp = new JoystickButton(rightDSstick, 6);
	//Button butFlywheelDown = new JoystickButton(rightDSstick, 4);
	//Button butShroudUp = new JoystickButton(rightDSstick, 3);
	//Button butShroudDown = new JoystickButton(rightDSstick, 5);
	
	//Button butIntakeMode = new JoystickButton(leftDSstick, 3);
	//Button butShootMode = new JoystickButton(leftDSstick, 5);
	
	//Button butDisabledMode = new JoystickButton(leftDSstick, 2);
	
	public boolean autoTransmissionTrigger = false;
	public boolean autoTransmissionEnabled = false;
	public boolean intakeWheelToggle = false;
	public boolean intakeWheelEnabled = false;
	public int lastIntakeState = 0; //0 - Disabled
									//1 - Intake
									//2 - Shoot
	
	public OI(){
		//butFlywheelUp.whenPressed(new incrementFlywheelSpeed());
		//butFlywheelDown.whenPressed(new decrementFlywheelSpeed());
		//butIntakeToggle.whenPressed(new toggleIntake());
		
		//butDisabledMode.whenPressed(new disableMode());
		//butShroudUp.whenPressed(new decrementShroud());
		//butShroudDown.whenPressed(new incrementShroud());
		
		//butIntakeMode.whenPressed(new enableShooterMode());
		//butShootMode.whenPressed(new enableIntakeMode());
		
		//butGateToggle.whileHeld(new churnBalls());
		butIntakeBeltToggle.whenPressed(new toggleIntake());
		butGateToggle.whenPressed(new pulseArms());
		butTransSlow.whenPressed(new switchLowSpeedTransmission());
		butTransFast.whenPressed(new switchHighSpeedTransmission());
	}
	
}
