package robotecosystem;

/** A wheeled robot designed to navigate across terrain. */
public class MovingRobot extends BaseRobot {
    private final String terrainType;

    public MovingRobot(String id, int batteryLevel, String terrainType) {
        super(id, batteryLevel);
        if (terrainType == null || terrainType.isBlank()) {
            throw new IllegalArgumentException("Terrain type must not be blank.");
        }
        this.terrainType = terrainType;
    }

    @Override
    public void start() {
        super.start();
        if (isActive()) {
            System.out.println(getId() + " wheel motors are engaged.");
        }
    }

    @Override
    public String status() {
        return "MovingRobot | " + super.status() + " | terrain=" + terrainType;
    }

    public void drive(double speed) throws BatteryLowException {
        move(speed, "across " + terrainType);
    }

    @Override
    public void move(double speed, String direction) throws BatteryLowException {
        ensureActive();
        validateMovement(speed, direction);
        consumeBattery(Math.max(1, (int) Math.ceil(speed / 2.0)), "drive");
        System.out.println(getId() + " driving " + direction + " at " + speed + " m/s.");
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
