package frc.robot.subsystems;

import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.hal.SimDouble;
import edu.wpi.first.hal.simulation.SimDeviceDataJNI;
import edu.wpi.first.wpilibj.SPI;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Test extends SubsystemBase
{
    private AHRS navX;
    private int device;
    private SimDouble angle;

    public Test()
    {
        SmartDashboard.putBoolean("Reset Yaw", false);
        navX = new AHRS(SPI.Port.kMXP);
        device = SimDeviceDataJNI.getSimDeviceHandle("navX-Sensor[0]");
        angle = new SimDouble(SimDeviceDataJNI.getSimValueHandle(device, "Yaw"));
    }

    public double getYaw()
    {
        return navX.getYaw();
    }

    public double getAngle()
    {
        return navX.getAngle();
    }

    public void resetYaw()
    {
        navX.zeroYaw();
    }

    @Override
    public void periodic()
    {
        SmartDashboard.putNumber("Yaw:", getYaw());
        SmartDashboard.putNumber("Angle:", getAngle());
        SmartDashboard.putNumber("SimAngle:", angle.get());
        if (SmartDashboard.getBoolean("Reset Yaw", false))  resetYaw();
    }
    
}
