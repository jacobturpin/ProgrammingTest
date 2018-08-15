import java.util.List;

public class ElevatorService {
    public void requestElevator(int currentFloor, int destinationFloor, List<Elevator> elevators) {
        Elevator optimalElevator = getOptimalElevator(currentFloor, elevators);

        if (optimalElevator == null) {
            System.out.println("Optimal elevator not found");
        }

        optimalElevator.moveToFloor(destinationFloor);
    }

    public Elevator getOptimalElevator(int currentFloor, List<Elevator> elevators) {
        Elevator optimalElevator = null;

        for (Elevator elevator : elevators) {
            if (elevator.isInMaintenance()) continue;

            if (!elevator.isOccupied()) {
                boolean elevatorIsOnMyFloor = elevator.getCurrentFloor() == currentFloor;
                if (elevatorIsOnMyFloor) {
                    optimalElevator = elevator;
                    break;
                }

                // Find the currently most optimal elevator
                if (optimalElevator == null) optimalElevator = elevator;

                int elevatorDistanceToCurrentFloor = Math.abs(currentFloor + elevator.getCurrentFloor());
                if (elevatorDistanceToCurrentFloor < optimalElevator.numberOfFloorsToCurrentFloor(currentFloor)) {
                    optimalElevator = elevator;
                }
            } else {
                boolean occupiedElevatorWillPassMyFloor =
                        ((elevator.isGoingDown() && currentFloor > elevator.getDestinationFloor()) ||
                        (elevator.isGoingUp() && currentFloor < elevator.getDestinationFloor()));
                if (occupiedElevatorWillPassMyFloor) {
                    optimalElevator = elevator;
                    break;
                }
            }
        }

        return optimalElevator;
    }
}
