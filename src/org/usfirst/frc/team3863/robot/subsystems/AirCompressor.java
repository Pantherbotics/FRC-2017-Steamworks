package org.usfirst.frc.team3863.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.Compressor;
/**
 *
 */
public class AirCompressor extends Subsystem {
	Compressor comp = new Compressor(0);

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
    
    public void enableCompressor(){
    	comp.setClosedLoopControl(true);
    }

    public void disableCompressor(){
    	comp.setClosedLoopControl(false);
    }
    
}

