package org.iesvdm.tddjava.ship;

import org.testng.annotations.*;
import static org.testng.Assert.*;

@Test
public class DirectionSpec {

    public void whenGetFromShortNameNThenReturnDirectionN() {
        char shortName = 'N';

        Direction direction = Direction.getFromShortName(shortName);

        assertEquals(Direction.NORTH, direction);
    }

    public void whenGetFromShortNameWThenReturnDirectionW() {
        char shortName = 'W';

        Direction direction = Direction.getFromShortName(shortName);

        assertEquals(Direction.WEST, direction);
    }

    public void whenGetFromShortNameBThenReturnNone() {
        char shortName = 'B';

        Direction direction = Direction.getFromShortName(shortName);

        assertEquals(Direction.NONE, direction);
    }

    public void givenSWhenLeftThenE() {
        Direction south = Direction.SOUTH;

        Direction result = south.turnLeft();

        assertEquals(Direction.EAST, result);
    }

    public void givenNWhenLeftThenW() {
        Direction north = Direction.NORTH;

        Direction result = north.turnLeft();

        assertEquals(Direction.WEST, result);
    }

    public void givenSWhenRightThenW() {
        Direction south = Direction.SOUTH;

        Direction result = south.turnRight();

        assertEquals(Direction.WEST, result);
    }

    public void givenWWhenRightThenN() {
        Direction west = Direction.WEST;

        Direction result = west.turnRight();

        assertEquals(Direction.NORTH, result);
    }

}
