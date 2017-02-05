package org.usfirst.frc.team3863.robot;


/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap {
	//================ROBOT I/O MAPPINGS===============//
	//Joystick IDs
	public static int leftDSJoystick = 0;
	public static int rightDSJoystick = 1;
	
	//CAN bus IDs
	public static int leftADriveTalonID = 13;
	public static int leftBDriveTalonID = 15;
	public static int rightADriveTalonID = 12;
	public static int rightBDriveTalonID = 14;
	public static int PCM_ID = 0;
	public static int PDB_ID = 0;
	
	//PCM I/O pins
	public static int solTransHigh = 3;
	public static int solTransLow = 2;
	
	//==============CALIBRATION CONSTANTS==============//
	//Drive code calibration constants (DO NOT CHANGE UNLESS YOU KNOW WHAT YOU ARE DOING!)
	public static double MAX_DRIVE_SPEED = 4000;
	
	//=====================SETTINGS====================//
	//Joystick Button Mappings
	public static int drive_tankLeftForwardAxis = 1;
	public static int drive_tankRightForwardAxis = 1;
	public static int drive_tankToArcadeButton = 2;
	public static int drive_arcadeForwardAxis = 1;
	public static int drive_arcadeRotateAxis = 2;
	
	//Tunable drive code parameters
	public static double driveShiftLow_HighSpeed = 0.7*MAX_DRIVE_SPEED;
	public static double driveShiftHigh_LowSpeed = 0.3*MAX_DRIVE_SPEED;
	
}
