package com.epam.container;

import com.epam.transport.Automobile;
import com.epam.transport.VehicleType;
import org.junit.jupiter.api.Test;

import java.util.Iterator;
import java.util.function.Predicate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ContainerIteratorTest {

    @Test
    public void testIterateByBrand() {
        TransportList container = new TransportList();
        container.add(new Automobile(200, 4, VehicleType.LAND, "Mazeratti"));
        container.add(new Automobile(250, 4, VehicleType.LAND, "Lexus"));
        container.add(new Automobile(180, 4, VehicleType.LAND, "Lada"));

        Predicate<Automobile> predicate = auto -> auto.getBrand().equals("Lexus");

        Iterator<Automobile> iterator = container.iterator(predicate);

        assertTrue(iterator.hasNext());
        assertEquals(new Automobile(250, 4, VehicleType.LAND, "Lexus"), iterator.next());

    }

    @Test
    public void testIterateBySpeed() {
        TransportList container = new TransportList();
        container.add(new Automobile(200, 4, VehicleType.LAND, "Mazeratti"));
        container.add(new Automobile(250, 4, VehicleType.LAND, "Lexus"));
        container.add(new Automobile(180, 4, VehicleType.LAND, "Lada"));

        Predicate<Automobile> predicate = auto -> auto.getSpeed() > 180;

        Iterator<Automobile> iterator = container.iterator(predicate);

        assertTrue(iterator.hasNext());
        assertEquals(new Automobile(200, 4, VehicleType.LAND, "Mazeratti"), iterator.next());

        assertTrue(iterator.hasNext());
        assertEquals(new Automobile(250, 4, VehicleType.LAND, "Lexus"), iterator.next());

    }

}