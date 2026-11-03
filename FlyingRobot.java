package robotecosystem;

/** A robot designed for aerial maneuvers and altitude control. */
public class FlyingRobot extends BaseRobot {
    private final int maximumAltitudeMeters;

    public FlyingRobot(String id, int batteryLevel, int maximumAltitudeMeters) {
        super(id, batteryLevel);
        if (maximumAltitudeMeters <= 0) {
            throw new IllegalArgumentException("Maximum altitude must be positive.");
        }
        this.maximumAltitudeMeters = maximumAltitudeMeters;
    }

    @Override
    public void start() {
        super.start();
        if (isActive()) {
            System.out.println(getId() + " flight systems are online.");
        }
    }

    @Override
    public String status() {
        return "FlyingRobot | " + super.status() + " | maxAltitude=" + maximumAltitudeMeters + "m";
    }

    public void fly(int altitudeMeters) throws BatteryLowException {
        ensureActive();
        if (altitudeMeters <= 0 || altitudeMeters > maximumAltitudeMeters) {
            throw new IllegalArgumentException("Altitude must be between 1 and "
                    + maximumAltitudeMeters + " meters.");
        }
        int cost = Math.max(1, (int) Math.ceil(altitudeMeters / 100.0));
        consumeBattery(cost, "fly to " + altitudeMeters + "m");
        System.out.println(getId() + " flying at " + altitudeMeters + "m (battery "
                + getBatteryLevel() + "%).");
    }

    @Override
    public void move(double speed, String direction) throws BatteryLowException {
        ensureActive();
        validateMovement(speed, direction);
        consumeBattery(Math.max(1, (int) Math.ceil(speed)), "perform an aerial maneuver");
        System.out.println(getId() + " maneuvering " + direction + " at " + speed + " m/s.");
    }

    private static void validateMovement(double speed, String direction) {
        if (speed <= 0 || !Double.isFinite(speed)) {
            throw new IllegalArgumentException("Speed must be a positive finite number.");
        }
        if (direction == null || direction.isBlank()) {
            throw new IllegalArgumentException("Direction must not be blank.");
        }
    }
}
