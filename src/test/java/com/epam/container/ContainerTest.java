package com.epam.container;


import com.epam.transport.Automobile;
import com.epam.transport.Transport;
import com.epam.transport.VehicleType;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ContainerTest {

    @Test
    void testSet() {
        TransportList container = new TransportList();
        Automobile transport = new Automobile(200, 4, VehicleType.LAND, "Mazeratti");

        container.add(transport);

        Automobile changedTransport = new Automobile(50, 10, VehicleType.LAND, "Changed");
        container.set(0, changedTransport);
        assertEquals(changedTransport, container.get(0));
    }

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

    @Test
    void testisEmptyShouldReturnTrue() {
        TransportList container = new TransportList();
        assertTrue(container.isEmpty());
    }

    @Test
    void testContainsAutomobileThatExists() {
        Transport auto = new Automobile(100, 100, VehicleType.LAND, "Lada");
        TransportList container = new TransportList();
        container.add(auto);
        assertTrue(container.contains(auto));
    }

    @Test
    void testContainsAutomobileThatDoesNotExist() {
        Transport auto = new Automobile(100, 100, VehicleType.LAND, "Lada");
        TransportList container = new TransportList();
        assertFalse(container.contains(auto));
    }

    @Test
    void testToArrayWithoutGenerics() {
        Transport auto = new Automobile(100, 100, VehicleType.LAND, "Lada");
        TransportList container = new TransportList();
        container.add(auto);
        container.add(auto);
        container.add(auto);
        Transport[] testArrayExpected = new Transport[3];
        testArrayExpected[0] = auto;
        testArrayExpected[1] = auto;
        testArrayExpected[2] = auto;

        Object[] testArrayActual = container.toArray();

        boolean equals = true;
        for (int i = 0; i < 3; i++) {
            if (testArrayExpected[i] != testArrayActual[i]) {
                equals = false;
                break;
            }
        }
        assertTrue(equals);
    }

    @Test
    void testToArrayWithGenerics() {
        Transport auto = new Automobile(100, 100, VehicleType.LAND, "Lada");
        TransportList container = new TransportList();
        container.add(auto);
        container.add(auto);
        container.add(auto);
        Transport[] testArrayExpected = new Transport[3];
        testArrayExpected[0] = auto;
        testArrayExpected[1] = auto;
        testArrayExpected[2] = auto;

        Object[] testArrayActual = container.toArray(container.toArray());

        boolean equals = true;
        for (int i = 0; i < 3; i++) {
            if (testArrayExpected[i] != testArrayActual[i]) {
                equals = false;
                break;
            }
        }
        assertTrue(equals);
    }

    @Test
    void testContainsAllElementsFromCollectionShouldReturnTrue() {
        List<Transport> containedTransport = new ArrayList<>();

        Transport auto = new Automobile(100, 100, VehicleType.LAND, "Lada");

        containedTransport.add(auto);
        TransportList container = new TransportList();

        container.add(auto);
        container.add(auto);
        container.add(auto);

        auto = new Automobile(1500, 100, VehicleType.LAND, "Sedan");
        container.add(auto);
        containedTransport.add(auto);

        assertTrue(container.containsAll(containedTransport));
    }

    @Test
    void testAddAllElementsFromCollectionToTheEndOfList() {
        Transport auto = new Automobile(100, 100, VehicleType.LAND, "Lada");
        TransportList container = new TransportList();
        container.add(auto);
        container.add(auto);

        List<Transport> containedTransport = new ArrayList<>();
        Transport firstInsertedAuto = new Automobile(100, 100, VehicleType.LAND, "TestInsert_1");
        Transport secondInsertedAuto = new Automobile(100, 100, VehicleType.LAND, "TestInsert_2");
        containedTransport.add(firstInsertedAuto);
        containedTransport.add(secondInsertedAuto);

        container.addAll(containedTransport);

        assertEquals(firstInsertedAuto, container.get(2));
        assertEquals(secondInsertedAuto, container.get(3));

    }

    @Test
    void testAddAllElementsFromCollectionByIndex() {
        Transport auto = new Automobile(100, 100, VehicleType.LAND, "Lada");
        TransportList container = new TransportList();
        container.add(auto);
        container.add(auto);

        List<Transport> containedTransport = new ArrayList<>();

        Transport firstInsertedAuto = new Automobile(100, 100, VehicleType.LAND, "TestInsert_1");
        Transport secondInsertedAuto = new Automobile(100, 100, VehicleType.LAND, "TestInsert_2");

        containedTransport.add(firstInsertedAuto);
        containedTransport.add(secondInsertedAuto);

        container.addAll(0, containedTransport);

        assertEquals(firstInsertedAuto, container.get(0));
        assertEquals(secondInsertedAuto, container.get(1));
    }

    @Test
    void testRemoveAllElementsFromCollection() {
        Transport auto = new Automobile(100, 100, VehicleType.LAND, "Lada");
        TransportList container = new TransportList();
        container.add(auto);
        List<Transport> containedTransport = new ArrayList<>();

        Transport firstInsertedAuto = new Automobile(100, 100, VehicleType.LAND, "TestInsert_1");
        Transport secondInsertedAuto = new Automobile(100, 100, VehicleType.LAND, "TestInsert_2");

        container.add(firstInsertedAuto);
        container.add(secondInsertedAuto);

        containedTransport.add(firstInsertedAuto);
        containedTransport.add(secondInsertedAuto);

        container.removeAll(containedTransport);

        assertEquals(1, container.size());
        assertEquals(auto, container.get(0));
    }

    @Test
    void testRetainAllElementsFromCollection() {
        Transport auto = new Automobile(100, 100, VehicleType.LAND, "Lada");
        TransportList container = new TransportList();
        container.add(auto);
        List<Transport> containedTransport = new ArrayList<>();
        Transport firstInsertedAuto = new Automobile(100, 100, VehicleType.LAND, "TestInsert_1");
        Transport secondInsertedAuto = new Automobile(100, 100, VehicleType.LAND, "TestInsert_2");

        container.add(firstInsertedAuto);

        containedTransport.add(firstInsertedAuto);
        containedTransport.add(secondInsertedAuto);

        container.retainAll(containedTransport);

        assertEquals(1, container.size());
        assertEquals(firstInsertedAuto, container.get(0));
    }

    @Test
    void testClear() {
        Transport auto = new Automobile(100, 100, VehicleType.LAND, "Lada");

        TransportList container = new TransportList();

        container.add(auto);
        container.add(auto);
        container.add(auto);

        container.clear();

        assertEquals(0, container.size());
    }

    @Test
    void testIndexOfElementExists() {
        TransportList container = new TransportList();

        Automobile auto = new Automobile(10,10,VehicleType.LAND, "Exists");

        container.add(auto);

        assertEquals(0, container.indexOf(auto));

    }

    @Test
    void testIndexOfElementDoesNotExist() {
        TransportList container = new TransportList();

        Automobile auto = new Automobile(10,10,VehicleType.LAND, "Exists");

        container.add(auto);

        Automobile nonExistentAuto = new Automobile(1,1,VehicleType.LAND,"Doesn't");

        assertEquals(-1, container.indexOf(nonExistentAuto));
    }

    @Test
    void testLastIndexOfElementExists() {
        TransportList container = new TransportList();

        Automobile auto = new Automobile(10,10,VehicleType.LAND, "Exists");

        container.add(auto);
        container.add(auto);
        container.add(auto);
        container.add(auto);


        assertEquals(3, container.lastIndexOf(auto));

    }

    @Test
    void testLastIndexOfElementDoesNotExist() {
        TransportList container = new TransportList();

        Automobile auto = new Automobile(10,10,VehicleType.LAND, "Exists");

        container.add(auto);

        Automobile nonExistentAuto = new Automobile(1,1,VehicleType.LAND,"Doesn't");

        assertEquals(-1, container.lastIndexOf(nonExistentAuto));
    }


}