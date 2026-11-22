package frc.robot.subsystems.intake;

import com.ctre.phoenix6.hardware.CANcoder;
import edu.wpi.first.wpilibj2.command.*;
import frc.excalib.control.limits.SoftLimit;
import frc.excalib.control.motor.controllers.MotorGroup;
import frc.excalib.control.motor.controllers.TalonFXMotor;
import frc.excalib.mechanisms.Arm.Arm;
import frc.excalib.mechanisms.Mechanism;
import monologue.Logged;

import java.util.function.DoubleSupplier;

import static frc.robot.Constants.SUBSYSTEMS_CANBUS;
import static frc.robot.subsystems.intake.IntakeConstants.*;
import static frc.robot.subsystems.intake.IntakeStates.*;
import static frc.robot.subsystems.intake.IntakeStates.OPEN;
import static frc.robot.subsystems.intake.IntakeStates.CLOSE;

public class Intake extends SubsystemBase implements Logged {
    public final TalonFXMotor leftFourBarIntakeMotor;
    public final TalonFXMotor rightFourBarIntakeMotor;
    public final TalonFXMotor intakeRollerMotor;

    public final MotorGroup armMotorGroup;

    public final Mechanism rollerMechanism;
    public final Arm fourBarMechanism;
    
    public final CANcoder intakeEncoder;

    public final SoftLimit intakeAngleLimit;
    public boolean isIntakeOpen = false;
    public final DoubleSupplier angleSupplier;

    public IntakeStates currentState;

    public Intake() {
        leftFourBarIntakeMotor = new TalonFXMotor(IntakeConstants.LEFT_FOUR_BAR_INTAKE_MOTOR_ID, SUBSYSTEMS_CANBUS);
        rightFourBarIntakeMotor = new TalonFXMotor(IntakeConstants.RIGHT_FOUR_BAR_INTAKE_MOTOR_ID, SUBSYSTEMS_CANBUS);
        intakeRollerMotor = new TalonFXMotor(IntakeConstants.INTAKE_ROLLER_MOTOR_ID, SUBSYSTEMS_CANBUS);

        leftFourBarIntakeMotor.setCurrentLimit(80, 60);
        rightFourBarIntakeMotor.setCurrentLimit(80, 60);
        intakeRollerMotor.setCurrentLimit(80, 60);

        armMotorGroup = new MotorGroup(leftFourBarIntakeMotor, rightFourBarIntakeMotor);

        rollerMechanism = new Mechanism(intakeRollerMotor);

        intakeEncoder = new CANcoder(IntakeConstants.INTAKE_ENCODER_ID, SUBSYSTEMS_CANBUS);

        intakeAngleLimit = new SoftLimit(()-> INTAKE_MIN_ANGLE, ()-> INTAKE_MAX_ANGLE);

        angleSupplier = armMotorGroup::getMotorPosition;

        fourBarMechanism = new Arm(armMotorGroup, angleSupplier, ARM_VELOCITY_LIMIT, ARM_POSITION_GAINS, ARM_MASS, ARM_MAX_OUTPUT_VOLTAGE);

        currentState = IntakeStates.CLOSE;
    }

    public void setIntakeState(IntakeStates state) {
        currentState = state;
        switch (state) {
            case OPEN:
                isIntakeOpen = true;
                break;
            case CLOSE:
                isIntakeOpen = false;
                break;
        }
    }

    
}
