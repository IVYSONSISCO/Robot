package robotecosystem;

/** Demonstrates the robot ecosystem assignment requirements. */
public class RobotEcosystemDemo {
    public static void main(String[] args) {
        FlyingRobot flyingRobot = new FlyingRobot("FLY-01", 65, 1_200);
        MovingRobot movingRobot = new MovingRobot("MOVE-01", 80, "gravel");
        WalkingRobot walkingRobot = new WalkingRobot("WALK-01", 50, 30);
        FlyingRobot lowBatteryRobot = new FlyingRobot("FLY-LOW", 5, 1_200);

        RobotController controller = new RobotController();
        controller.addRobot(flyingRobot);
        controller.addRobot(movingRobot);
        controller.addRobot(walkingRobot);
        controller.addRobot(lowBatteryRobot);

        System.out.println("== Start all robots polymorphically ==");
        controller.startAll();
        controller.printStatuses();

        System.out.println("\n== Demonstrate overloaded move methods ==");
        try {
            flyingRobot.move(4.0);                   // move(speed)
            flyingRobot.move(3.5, "north-east");   // move(speed, direction)
            movingRobot.move(2.0);
            walkingRobot.move(1.5, "toward the waypoint");

            System.out.println("\n== Type-specific behaviors ==");
            flyingRobot.fly(900);
            movingRobot.drive(5.0);
            walkingRobot.walk(150);
            walkingRobot.negotiateObstacle(20);

            System.out.println("\n== Demonstrate checked low-battery handling ==");
            lowBatteryRobot.fly(1_200); // Requires 12% but this robot has only 5%.
        } catch (BatteryLowException exception) {
            System.out.println("Handled battery warning: " + exception.getMessage());
        }

        System.out.println("\n== Final status ==");
        controller.printStatuses();
        controller.stopAll();
    }
}
