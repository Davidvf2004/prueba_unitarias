package org.iesvdm.tddjava.ship;

import org.testng.annotations.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.testng.Assert.*;

@Test
public class LocationSpec {

    private final int x = 12;
    private final int y = 32;
    private final Direction direction = Direction.NORTH;
    private Point max;
    private Location location;
    private List<Point> obstacles;

    @BeforeMethod
    public void beforeTest() {
        max = new Point(50, 50);
        location = new Location(new Point(x, y), direction);
        obstacles = new ArrayList<Point>();
    }

    public void whenInstantiatedThenXIsStored() {
        Point initialPoint = new Point(5, 10);
        Location location = new Location(initialPoint, Direction.NORTH);

        int x = location.getX();

        assertEquals(initialPoint.getX(), x);
    }

    public void whenInstantiatedThenYIsStored() {
        Point initialPoint = new Point(5, 10);
        Location location = new Location(initialPoint, Direction.NORTH);

        int y = location.getY();

        assertEquals(initialPoint.getY(), y);
    }

    public void whenInstantiatedThenDirectionIsStored() {
        Point initialPoint = new Point(5, 10);
        Direction initialDirection = Direction.NORTH;
        Location location = new Location(initialPoint, initialDirection);

        Direction direction = location.getDirection();

        assertEquals(initialDirection, direction);
    }

    public void givenDirectionNWhenForwardThenYDecreases() {
        Point initialPoint = new Point(5, 5);
        Location location = new Location(initialPoint, Direction.NORTH);

        boolean result = location.forward();

        assertTrue(result);
        assertEquals(4, location.getY());
    }

    public void givenDirectionSWhenForwardThenYIncreases() {
        Point initialPoint = new Point(5, 5);
        Location location = new Location(initialPoint, Direction.SOUTH);

        boolean result = location.forward();

        assertTrue(result);
        assertEquals(6, location.getY());
    }

    public void givenDirectionEWhenForwardThenXIncreases() {
        Point initialPoint = new Point(5 , 5);
        Location location = new Location(initialPoint, Direction.EAST);

        boolean result = location.forward();

        assertEquals(6, location.getX());
    }

    public void givenDirectionWWhenForwardThenXDecreases() {
        Point initialPoint = new Point(5, 5);
        Location location = new Location(initialPoint, Direction.WEST);

        boolean result = location.forward();

        assertEquals(4, location.getX());
    }

    public void givenDirectionNWhenBackwardThenYIncreases() {
        Point initialPoint = new Point(5, 5);
        Location location = new Location(initialPoint, Direction.NORTH);

        boolean result = location.backward();

        assertEquals(6, location.getY());
    }

    public void givenDirectionSWhenBackwardThenYDecreases() {
        Point initialPoint = new Point(5, 5);
        Location location = new Location(initialPoint, Direction.SOUTH);

        boolean result = location.backward();

        assertEquals(4, location.getY());
    }

    public void givenDirectionEWhenBackwardThenXDecreases() {
        Point initialPoint = new Point(5, 5);
        Location location = new Location(initialPoint, Direction.EAST);

        boolean result = location.backward();

        assertEquals(4, location.getX());
    }

    public void givenDirectionWWhenBackwardThenXIncreases() {
        Point initialPoint = new Point(5, 5);
        Location location = new Location(initialPoint, Direction.WEST);

        boolean result = location.backward();

        assertEquals(6, location.getX());
    }

    public void whenTurnLeftThenDirectionIsSet() {
        Point initialPoint = new Point(5, 5);
        Location location = new Location(initialPoint, Direction.NORTH);

        location.turnLeft();

        assertEquals(Direction.WEST, location.getDirection());
    }

    public void whenTurnRightThenDirectionIsSet() {
        Point initialPoint = new Point(5, 5);
        Location location = new Location(initialPoint, Direction.NORTH);

        location.turnRight();

        assertEquals(Direction.EAST, location.getDirection());
    }

    public void givenSameObjectsWhenEqualsThenTrue() {
        Point point = new Point(5, 5);
        Location location1 = new Location(point, Direction.NORTH);
        Location location2 = new Location(point, Direction.NORTH);

        boolean result = location1.equals(location2);

        assertTrue(result);
    }

    public void givenDifferentObjectWhenEqualsThenFalse() {
        Location location1 = new Location(new Point(5, 5), Direction.NORTH);
        Location location2 = new Location(new Point(10, 10), Direction.SOUTH);

        boolean result = location1.equals(location2);

        assertFalse(result);
    }

    public void givenDifferentXWhenEqualsThenFalse() {
        Location location1 = new Location(new Point(5, 5), Direction.NORTH);
        Location location2 = new Location(new Point(10, 5), Direction.NORTH);

        boolean result = location1.equals(location2);

        assertFalse(result);
    }

    public void givenDifferentYWhenEqualsThenFalse() {
        Location location1 = new Location(new Point(5, 5), Direction.NORTH);
        Location location2 = new Location(new Point(5, 10), Direction.NORTH);

        boolean result = location1.equals(location2);

        assertFalse(result);
    }

    public void givenDifferentDirectionWhenEqualsThenFalse() {
        Location location1 = new Location(new Point(5, 5), Direction.NORTH);
        Location location2 = new Location(new Point(5, 5), Direction.EAST);

        boolean result = location1.equals(location2);

        assertFalse(result);
    }

    public void givenSameXYDirectionWhenEqualsThenTrue() {
        Location location1 = new Location(new Point(5, 5), Direction.NORTH);
        Location location2 = new Location(new Point(5, 5), Direction.NORTH);

        boolean result = location1.equals(location2);

        assertTrue(result);
    }

    public void whenCopyThenDifferentObject() {
        Location originalLocation = new Location(new Point(5, 5), Direction.NORTH);

        Location copiedLocation = originalLocation.copy();

        assertNotSame(originalLocation, copiedLocation);
    }

    public void whenCopyThenEquals() {
        Location originalLocation = new Location(new Point(5, 5), Direction.NORTH);

        Location copiedLocation = originalLocation.copy();

        assertEquals(originalLocation, copiedLocation);
    }

    public void givenDirectionEAndXEqualsMaxXWhenForwardThen1() {
        int maxX = 10;
        Location location = new Location(new Point(maxX, 5), Direction.EAST);

        boolean result = location.forward(new Point(maxX, 10));

        assertTrue(result);
        assertEquals(1, location.getX());
    }

    public void givenDirectionWAndXEquals1WhenForwardThenMaxX() {
        Location location = new Location(new Point(1, 5), Direction.WEST);
        int maxX = 10;

        boolean result = location.forward(new Point(maxX, 10));

        assertTrue(result);
        assertEquals(maxX, location.getX());
    }

    public void givenDirectionNAndYEquals1WhenForwardThenMaxY() {
        Location location = new Location(new Point(1, 5), Direction.NORTH);
        int maxY = 4;

        boolean result = location.forward(new Point(maxY, 10));

        assertTrue(result);
        assertEquals(maxY, location.getY());
    }

    public void givenDirectionSAndYEqualsMaxYWhenForwardThen1() {
        int maxY = 10;
        Location location = new Location(new Point(maxY, 5), Direction.SOUTH);

        boolean result = location.forward(new Point(maxY, 10));

        assertTrue(result);
        assertEquals(6, location.getY());
    }

    public void givenObstacleWhenForwardThenReturnFalse() {
        Point obstacle = new Point(3, 5);
        Location location = new Location(new Point(2, 5), Direction.EAST);
        List<Point> obstacles = Collections.singletonList(obstacle);

        boolean result = location.forward(new Point(5, 5), obstacles);

        assertFalse(result);
    }

    public void givenObstacleWhenBackwardThenReturnFalse() {
        Point obstacle = new Point(3, 5);
        Location location = new Location(new Point(2, 5), Direction.WEST);
        List<Point> obstacles = Collections.singletonList(obstacle);

        // Act
        boolean result = location.backward(new Point(5, 5), obstacles);

        // Assert
        assertFalse(result);
    }

}
