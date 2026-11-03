package robotecosystem;

/** Raised when a robot cannot afford the battery cost of an action. */
public class BatteryLowException extends Exception {
    private static final long serialVersionUID = 1L;

    public BatteryLowException(String message) {
        super(message);
    }
}
