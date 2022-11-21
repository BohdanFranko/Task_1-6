package com.epam.container;


import com.epam.transport.Automobile;
import com.epam.transport.VehicleType;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ContainerTest {

//    public static Stream<Arguments> provideAutomobiles() {
//        return Stream.of(
//                Arguments.of("")
//        )
//    }

//    @ParameterizedTest
//    @MethodSource("provideAutomobiles")
    @Test
    public void testAddAutomobile() {
        TransportList container = new TransportList();
        Automobile transport = new Automobile(200, 4, VehicleType.LAND, "Mazeratti");
        container.add(transport);
        assertEquals(transport, container.get(0));
    }


    @org.junit.jupiter.api.Test
    void testAddAutomobileByIndex() {
        TransportList container = new TransportList();
        Automobile transport = new Automobile(200, 4, VehicleType.LAND, "Mazeratti");
        container.add(transport);
        container.add(transport);
        container.add(transport);
        Automobile testAuto = new Automobile(200, 4, VehicleType.LAND, "TestInsert");
        container.add(1, testAuto);
        assertEquals(container.get(1), testAuto);
    }

    @org.junit.jupiter.api.Test
    void testRemoveAutomobile() {
        TransportList container = new TransportList();
        Automobile transport = new Automobile(200, 4, VehicleType.LAND, "Mazeratti");
        container.add(transport);
        assertTrue(container.remove(transport));
    }

    @org.junit.jupiter.api.Test
    void testRemoveByIndex() {
        TransportList container = new TransportList();
        container.add(new Automobile(200, 4, VehicleType.LAND, "Mazeratti"));
        container.add(new Automobile(200, 4, VehicleType.LAND, "Lexus"));
        container.add(new Automobile(200, 4, VehicleType.LAND, "Lada"));
        container.remove(1);
        assertEquals(container.get(1), new Automobile(200, 4, VehicleType.LAND, "Lada"));
    }

    @org.junit.jupiter.api.Test
    void testGetAutomobile() {
        TransportList container = new TransportList();
        Automobile transport = new Automobile(200, 4, VehicleType.LAND, "Mazeratti");
        container.add(transport);
        Automobile takenAutomobile = (Automobile) container.get(0);
        assertEquals(transport, takenAutomobile);
    }

}