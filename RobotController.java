package robotecosystem;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/** Coordinates a heterogeneous collection of robots through the Robot interface. */
public class RobotController {
    private final List<Robot> robots = new ArrayList<>();

    public void addRobot(Robot robot) {
        robots.add(Objects.requireNonNull(robot, "robot must not be null"));
    }

    public void startAll() {
        for (Robot robot : robots) {
            robot.start();
        }
    }

    public void stopAll() {
        for (Robot robot : robots) {
            robot.stop();
        }
    }

    public void printStatuses() {
        for (Robot robot : robots) {
            System.out.println(robot.status());
        }
    }
}
