package org.iesvdm.tddjava.ship;

import org.mockito.Mockito;
import org.testng.annotations.*;

import java.util.ArrayList;
import java.util.List;

import static org.testng.Assert.*;

@Test
public class ShipSpec {

    private Ship ship;
    private Location location;
    private Planet planet;

    @BeforeMethod
    public void beforeTest() {
        Point max = new Point(50, 50);
        location = new Location(new Point(21, 13), Direction.NORTH);
        List<Point> obstacles = new ArrayList<>();
        obstacles.add(new Point(44, 44));
        obstacles.add(new Point(45, 46));
        planet = new Planet(max, obstacles);
//        ship = new Ship(location);
        ship = new Ship(location, planet);
    }

    public void whenInstantiatedThenLocationIsSet() {
        Location location = new Location(new Point(21, 13), Direction.NORTH);
        Ship ship = new Ship(location);

    }

    public void givenNorthWhenMoveForwardThenYDecreases() {
        ship.moveForward();
        assertEquals(ship.getLocation().getPoint().getY(), 12);
    }

    public void givenEastWhenMoveForwardThenXIncreases() {
        ship.getLocation().setDirection(Direction.EAST);
        ship.moveForward();
        assertEquals(ship.getLocation().getPoint().getX(), 22);
    }

    public void whenMoveForwardThenForward() {
        Location locationMock = Mockito.mock(Location.class);
        Planet planetMock = Mockito.mock(Planet.class);
        Mockito.when(planetMock.getMax()).thenReturn(new Point(100, 100));
        Mockito.when(planetMock.getObstacles()).thenReturn(new ArrayList<>());

        Ship ship = new Ship(locationMock, planetMock);

        ship.moveForward();

        Mockito.verify(locationMock).forward(planetMock.getMax(), planetMock.getObstacles());
    }

    public void whenMoveBackwardThenBackward() {
        Location locationMock = Mockito.mock(Location.class);
        Planet planetMock = Mockito.mock(Planet.class);
        Mockito.when(planetMock.getMax()).thenReturn(new Point(100, 100));
        Mockito.when(planetMock.getObstacles()).thenReturn(new ArrayList<>());

        Ship ship = new Ship(locationMock, planetMock);

        ship.moveBackward();

        Mockito.verify(locationMock).backward(planetMock.getMax(), planetMock.getObstacles());
    }

    public void whenTurnLeftThenLeft() {
        Point initialPoint = new Point(50, 50);
        Location initialLocation = new Location(initialPoint, Direction.NORTH);
        Ship ship = new Ship(initialLocation);

        ship.turnLeft();

        assertEquals(Direction.WEST, ship.getLocation().getDirection());
    }

    public void whenTurnRightThenRight() {
        Point initialPoint = new Point(50, 50);
        Location initialLocation = new Location(initialPoint, Direction.NORTH);
        Ship ship = new Ship(initialLocation);

        ship.turnRight();

        assertEquals(Direction.EAST, ship.getLocation().getDirection());
    }

    public void whenReceiveCommandsFThenForward() {
        Point initialPoint = new Point(50, 50);
        Location initialLocation = new Location(initialPoint, Direction.NORTH);
        Planet planet = new Planet(new Point(100, 100));
        Ship ship = new Ship(initialLocation, planet);

        String result = ship.receiveCommands("F");

        assertEquals("O", result);
    }

    public void whenReceiveCommandsBThenBackward() {
        Point initialPoint = new Point(50, 50);
        Location initialLocation = new Location(initialPoint, Direction.SOUTH);
        Planet planet = new Planet(new Point(100, 100));
        Ship ship = new Ship(initialLocation, planet);

        String result = ship.receiveCommands("B");

        assertEquals("O", result);
    }

    public void whenReceiveCommandsLThenTurnLeft() {
        Point initialPoint = new Point(50, 50);
        Location initialLocation = new Location(initialPoint, Direction.WEST);
        Planet planet = new Planet(new Point(100, 100));
        Ship ship = new Ship(initialLocation, planet);

        String result = ship.receiveCommands("L");

        assertEquals("O", result);
    }

    public void whenReceiveCommandsRThenTurnRight() {
        Point initialPoint = new Point(50, 50);
        Location initialLocation = new Location(initialPoint, Direction.EAST);
        Planet planet = new Planet(new Point(100, 100));
        Ship ship = new Ship(initialLocation, planet);

        String result = ship.receiveCommands("R");

        assertEquals("O", result);
    }

    public void whenReceiveCommandsThenAllAreExecuted() {
        Point initialPoint = new Point(50, 50);
        Location initialLocation = new Location(initialPoint, Direction.NORTH);
        Planet planet = new Planet(new Point(100, 100));
        Ship ship = new Ship(initialLocation, planet);

        String result = ship.receiveCommands("FLBR");

        assertEquals("OOOO", result);
    }

    public void whenInstantiatedThenPlanetIsStored() {
        Point max = new Point(50, 50);
        Planet planet = new Planet(max);
        ship = new Ship(location, planet);

    }

    public void givenDirectionEAndXEqualsMaxXWhenReceiveCommandsFThenWrap() {
        Point initialPoint = new Point(99, 50);
        Location initialLocation = new Location(initialPoint, Direction.EAST);
        Planet planet = new Planet(new Point(100, 100));
        Ship ship = new Ship(initialLocation, planet);

        String result = ship.receiveCommands("F");

        assertEquals("O", result);
        assertEquals(99, ship.getLocation().getX());
    }

    public void givenDirectionEAndXEquals1WhenReceiveCommandsBThenWrap() {
        Point initialPoint = new Point(1, 50);
        Location initialLocation = new Location(initialPoint, Direction.EAST);
        Planet planet = new Planet(new Point(100, 100));
        Ship ship = new Ship(initialLocation, planet);

        String result = ship.receiveCommands("B");

        assertEquals("O", result);
        assertEquals(1, ship.getLocation().getX());
    }

    public void whenReceiveCommandsThenStopOnObstacle() {
        Point initialPoint = new Point(1, 1);
        Location initialLocation = new Location(initialPoint, Direction.EAST);

        List<Point> obstacles = new ArrayList<>();
        obstacles.add(new Point(2, 1));

        Planet planet = new Planet(new Point(100, 100), obstacles);

        Ship ship = new Ship(initialLocation, planet);

        String result = ship.receiveCommands("F");

        assertEquals("O", result); // El barco debe detenerse en el obstáculo
        assertEquals(1, ship.getLocation().getX());
    }

    public void whenReceiveCommandsThenOForOkAndXForObstacle() {
        Point initialPoint = new Point(1, 1);
        Location initialLocation = new Location(initialPoint, Direction.EAST);

        List<Point> obstacles = new ArrayList<>();
        obstacles.add(new Point(2, 1));

        Planet planet = new Planet(new Point(100, 100), obstacles);

        Ship ship = new Ship(initialLocation, planet);


        String result = ship.receiveCommands("FF");
        assertEquals("OO", result);
    }

}
