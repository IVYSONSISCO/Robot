package robotecosystem;

/** Common contract used by every robot type. */
public interface Robot {
    void start();

    void stop();

    String status();
}
