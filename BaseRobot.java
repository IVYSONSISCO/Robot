package robotecosystem;

/** Shared implementation for the robot family. */
public abstract class BaseRobot implements Robot {
    private final String id;
    private int batteryLevel;
    private boolean active;

    protected BaseRobot(String id, int batteryLevel) {
        if (id == null || id.isBlank()) {
            throw new IllegalArgumentException("Robot id must not be blank.");
        }
        if (batteryLevel < 0 || batteryLevel > 100) {
            throw new IllegalArgumentException("Battery level must be between 0 and 100.");
        }
        this.id = id;
        this.batteryLevel = batteryLevel;
    }

    public final String getId() {
        return id;
    }

    public final int getBatteryLevel() {
        return batteryLevel;
    }

    public final boolean isActive() {
        return active;
    }

    @Override
    public void start() {
        if (batteryLevel == 0) {
            System.out.println(id + " cannot start: battery is empty.");
            return;
        }
        active = true;
        System.out.println(id + " started.");
    }

    @Override
    public void stop() {
        active = false;
        System.out.println(id + " stopped.");
    }

    @Override
    public String status() {
        return String.format("%s | active=%s | battery=%d%%", id, active, batteryLevel);
    }

    /** Overload 1: move at the requested speed in the default forward direction. */
    public void move(double speed) throws BatteryLowException {
        move(speed, "forward");
    }

    /** Overload 2: move at the requested speed in an explicit direction. */
    public abstract void move(double speed, String direction) throws BatteryLowException;

    protected final void ensureActive() {
        if (!active) {
            throw new IllegalStateException(id + " must be started before it can move.");
        }
    }

    protected final void consumeBattery(int amount, String action) throws BatteryLowException {
        if (amount <= 0) {
            throw new IllegalArgumentException("Battery cost must be positive.");
        }
        if (batteryLevel < amount) {
            throw new BatteryLowException(String.format(
                    "%s cannot %s: needs %d%% battery but has %d%%.",
                    id, action, amount, batteryLevel));
        }
        batteryLevel -= amount;
    }
}
