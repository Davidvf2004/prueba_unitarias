package org.iesvdm.tddjava.ship;

import org.testng.annotations.*;
import static org.testng.Assert.*;

@Test
public class PointSpec {

    private Point point;
    private final int x = 12;
    private final int y = 21;

    @BeforeMethod
    public void beforeTest() {
        point = new Point(x, y);
    }

    public void whenInstantiatedThenXIsSet() {
        int x = 5;
        int y = 10;

        Point point = new Point(x, y);

        assertEquals(x, point.getX());
    }

    public void whenInstantiatedThenYIsSet() {
        int x = 5;
        int y = 10;

        Point point = new Point(x, y);

        assertEquals(y, point.getY());
    }

}
