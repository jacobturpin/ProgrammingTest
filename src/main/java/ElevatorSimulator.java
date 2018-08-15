import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.List;

public class ElevatorSimulator {
    private static final int GROUND_FLOOR = 1;
    private int numberOfFloors;

    private List<Elevator> elevators;

    private ElevatorService elevatorService;

    public ElevatorSimulator(int numberOfElevators, int numberOfFloors) {
        elevators = new ArrayList<>(numberOfElevators);

        for (int i=0; i<numberOfElevators; i++) {
            Elevator elevator = new Elevator(i, GROUND_FLOOR);
            elevators.add(elevator);
        }

        this.numberOfFloors = numberOfFloors;
    }

    public void requestElevator(int currentFloor, int destinationFloor) {
        if (destinationFloor < GROUND_FLOOR || destinationFloor > getNumberOfFloors()) {
            throw new InvalidParameterException("Invalid Destination Floor Number: " + destinationFloor);
        }

        elevatorService.requestElevator(currentFloor, destinationFloor, elevators);
    }

    public int getNumberOfFloors() {
        return numberOfFloors;
    }
}
