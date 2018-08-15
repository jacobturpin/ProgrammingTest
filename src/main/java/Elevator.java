public class Elevator {
    private static final int MAX_TRIPS = 100;

    private int elevatorNumber;
    private int trips;
    private int floorsPassed;
    private int currentFloor;
    private int destinationFloor;
    private boolean inMaintenance;
    private boolean occupied;
    private Direction direction;

    public Elevator(int elevatorNumber, int currentFloor) {
        this.elevatorNumber = elevatorNumber;
        this.currentFloor = currentFloor;
        this.direction = Direction.STOPPED;
    }

    public void moveToFloor(int floorNumber) {
        setDestinationFloor(floorNumber);
        setOccupied(true);

        boolean goingDown = floorNumber < currentFloor;
        setDirection(goingDown ? Direction.DOWN : Direction.UP);

        int currentFloor = getCurrentFloor();
        for (int i = 0; i < floorsPassed; i++) {
            currentFloor = goingDown ? currentFloor-- : currentFloor++;
            passedFloor(currentFloor);
        }
        setCurrentFloor(currentFloor);

        int floorsPassed = Math.abs(currentFloor - floorNumber);
        addFloorsPassed(floorsPassed);

        openDoor();
        setOccupied(false);
        closeDoor();

        setDirection(Direction.STOPPED);

        updateElevatorMaintenance();
    }

    private void updateElevatorMaintenance() {
        addTrips(1);
        if (getTrips() >= MAX_TRIPS) {
            this.setInMaintenance(true);
        }
    }

    public void openDoor() {
        System.out.println("Elevator " + elevatorNumber + " door has opened");
    }

    public void closeDoor() {
        System.out.println("Elevator " + elevatorNumber + " door has closed");
    }

    public void passedFloor(int floorNumber) {
        System.out.println("Elevator " + elevatorNumber + " has passed floor " + floorNumber);
    }

    public void service() {
        this.setInMaintenance(false);
    }

    public boolean isInMaintenance() {
        return inMaintenance;
    }

    public void setInMaintenance(boolean inMaintenance) {
        this.inMaintenance = inMaintenance;
    }

    public void addFloorsPassed(int floorsPassed) {
        this.floorsPassed += floorsPassed;
    }

    public int getTrips() {
        return trips;
    }

    public int getCurrentFloor() {
        return currentFloor;
    }

    public void setCurrentFloor(int currentFloor) {
        this.currentFloor = currentFloor;
    }

    public void addTrips(int trips) {
        this.trips += trips;
    }

    public boolean isOccupied() {
        return occupied;
    }

    public void setOccupied(boolean occupied) {
        this.occupied = occupied;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    public boolean isGoingUp() {
        return this.direction == Direction.UP;
    }

    public boolean isGoingDown() {
        return this.direction == Direction.DOWN;
    }

    public int getDestinationFloor() {
        return destinationFloor;
    }

    public void setDestinationFloor(int destinationFloor) {
        this.destinationFloor = destinationFloor;
    }

    public int numberOfFloorsToCurrentFloor(int currentFloor) {
        return Math.abs(currentFloor + this.getCurrentFloor());
    }
}
