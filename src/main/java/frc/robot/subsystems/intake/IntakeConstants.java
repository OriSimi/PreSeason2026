package frc.robot.subsystems.intake;

import frc.excalib.control.gains.Gains;
import frc.excalib.control.limits.SoftLimit;
import frc.excalib.control.math.physics.Mass;

public class IntakeConstants {
    public static final int INTAKE_ROLLER_MOTOR_ID = 1;
    public static final int LEFT_FOUR_BAR_INTAKE_MOTOR_ID = 2;
    public static final int RIGHT_FOUR_BAR_INTAKE_MOTOR_ID = 3;
    public static final int INTAKE_ENCODER_ID = 4;
    public static final double INTAKE_MIN_ANGLE = 0.0;
    public static final double INTAKE_MAX_ANGLE = 0.0;
    public static final double ARM_MIN_VELOCITY_LIMIT = 0;
    public static final double ARM_MAX_VELOCITY_LIMIT = 0;
    public static final SoftLimit ARM_VELOCITY_LIMIT = new SoftLimit(
            () -> ARM_MIN_VELOCITY_LIMIT,
            () -> ARM_MAX_VELOCITY_LIMIT
    );
    public static final Gains ARM_POSITION_GAINS = new Gains(0.0, 0.0, 0.0);
    public static final Mass ARM_MASS = new Mass(() -> 0.0, () -> 0.0, 0.0);
    public static final double ARM_MAX_OUTPUT_VOLTAGE = 0.0;
}