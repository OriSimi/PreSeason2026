package frc.robot.subsystems.intake;

public enum IntakeStates {
    OPEN(0),
    CLOSE(0);
    final double angle;

    IntakeStates(double angle) {
        this.angle = angle;
    }
}