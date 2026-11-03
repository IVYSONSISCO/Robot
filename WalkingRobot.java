package robotecosystem;

/** A bipedal robot optimized for walking and obstacle negotiation. */
public class WalkingRobot extends BaseRobot {
    private final int maximumStepHeightCm;

    public WalkingRobot(String id, int batteryLevel, int maximumStepHeightCm) {
        super(id, batteryLevel);
        if (maximumStepHeightCm < 0) {
            throw new IllegalArgumentException("Maximum step height cannot be negative.");
        }
        this.maximumStepHeightCm = maximumStepHeightCm;
    }

    @Override
    public void start() {
        super.start();
        if (isActive()) {
            System.out.println(getId() + " balance and leg systems are online.");
        }
    }

    @Override
    public String status() {
        return "WalkingRobot | " + super.status() + " | maxStep=" + maximumStepHeightCm + "cm";
    }

    public void walk(int steps) throws BatteryLowException {
        ensureActive();
        if (steps <= 0) {
            throw new IllegalArgumentException("Step count must be positive.");
        }
        int cost = Math.max(1, (int) Math.ceil(steps / 100.0));
        consumeBattery(cost, "walk " + steps + " steps");
        System.out.println(getId() + " walked " + steps + " steps (battery "
                + getBatteryLevel() + "%).");
    }

    public void negotiateObstacle(int heightCm) throws BatteryLowException {
        ensureActive();
        if (heightCm < 0 || heightCm > maximumStepHeightCm) {
            throw new IllegalArgumentException("Obstacle must be between 0 and "
                    + maximumStepHeightCm + " cm high.");
        }
        consumeBattery(1, "negotiate a " + heightCm + "cm obstacle");
        System.out.println(getId() + " negotiated a " + heightCm + "cm obstacle.");
    }

    @Override
    public void move(double speed, String direction) throws BatteryLowException {
        ensureActive();
        validateMovement(speed, direction);
        consumeBattery(Math.max(1, (int) Math.ceil(speed / 2.0)), "walk");
        System.out.println(getId() + " walking " + direction + " at " + speed + " m/s.");
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
