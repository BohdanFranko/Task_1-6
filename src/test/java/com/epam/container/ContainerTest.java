package com.epam.container;


import com.epam.transport.Automobile;
import com.epam.transport.VehicleType;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class ContainerTest {
    private static TransportList<Automobile> container;
    private static List<Automobile> collectionContainer;

    @BeforeAll
    static void initializeContainer() {
        container = new TransportList<>();
        collectionContainer = new ArrayList<>();
    }

    @AfterEach
    void clearContainer() {
        container.clear();
        collectionContainer.clear();
    }

    @ParameterizedTest
    @MethodSource("getMazerattiAuto")
    void setShouldChangeElement(Automobile auto) {
        container.add(auto);

        Automobile changedToTransport = new Automobile(50, 10, VehicleType.LAND, "Changed");

        container.set(0, changedToTransport);

        assertEquals(changedToTransport, container.get(0));
    }

    @ParameterizedTest
    @MethodSource("getMazerattiAuto")
    public void addElement(Automobile auto) {
        container.add(auto);

        assertEquals(auto, container.get(0));
    }


    @ParameterizedTest
    @MethodSource("getMazerattiAuto")
    void addElementByIndex(Automobile auto) {
        container.add(auto);
        container.add(auto);
        container.add(auto);

        Automobile testAuto = new Automobile(200, 4, VehicleType.LAND, "TestInsert");

        container.add(1, testAuto);

        assertEquals(container.get(1), testAuto);
    }

    @ParameterizedTest
    @MethodSource("getMazerattiAuto")
    void removeElement(Automobile auto) {
        container.add(auto);

        assertTrue(container.remove(auto));
    }

    @ParameterizedTest
    @MethodSource("getMazerattiAuto")
    void removeElementByIndex(Automobile auto) {
        container.add(auto);

        assertEquals(auto, container.remove(0));
    }

    @ParameterizedTest
    @MethodSource("getMazerattiAuto")
    void getElementIfExists(Automobile auto) {
        container.add(auto);

        assertEquals(auto, container.get(0));
    }

    @Test
    void getElementIfDoesNotExist_ShouldThrowIndexOutOfBounds() throws IndexOutOfBoundsException {
        assertThrows(IndexOutOfBoundsException.class, () -> container.get(0));
    }

    private static Stream<Arguments> getMazerattiAuto() {
        return Stream.of(Arguments.of(new Automobile(200, 4, VehicleType.LAND, "Mazeratti")));
    }

    @Test
    void isEmptyShouldReturnTrue() {
        assertTrue(container.isEmpty());
    }

    @ParameterizedTest
    @MethodSource("getLadaAuto")
    void containsElementThatExists(Automobile auto) {
        container.add(auto);

        assertTrue(container.contains(auto));
    }

    @ParameterizedTest
    @MethodSource("getLadaAuto")
    void containsElementThatDoesNotExist(Automobile auto) {
        assertFalse(container.contains(auto));
    }

    @ParameterizedTest
    @MethodSource("getLadaAuto")
    void toArrayWithoutGenerics(Automobile auto) {
        container.add(auto);
        container.add(auto);
        container.add(auto);

        Automobile[] testArrayExpected = new Automobile[3];

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

    @ParameterizedTest
    @MethodSource("getLadaAuto")
    void toArrayWithGenericsNewSizeEqualsOldSize(Automobile auto) {
        container.add(auto);
        container.add(auto);
        container.add(auto);

        Automobile[] testArrayExpected = new Automobile[3];

        testArrayExpected[0] = auto;
        testArrayExpected[1] = auto;
        testArrayExpected[2] = auto;

        Object[] testArrayActual = container.toArray(new Automobile[3]);

        boolean equals = true;
        for (int i = 0; i < 3; i++) {
            if (testArrayExpected[i] != testArrayActual[i]) {
                equals = false;
                break;
            }
        }
        assertTrue(equals);
    }

    @ParameterizedTest
    @MethodSource("getLadaAuto")
    void toArrayWithGenericsNewSizeBiggerThanOldSize(Automobile auto) {
        container.add(auto);
        container.add(auto);
        container.add(auto);

        Automobile[] testArrayExpected = new Automobile[3];

        testArrayExpected[0] = auto;
        testArrayExpected[1] = auto;
        testArrayExpected[2] = auto;

        Object[] testArrayActual = container.toArray(new Automobile[100]);

        boolean equals = true;
        for (int i = 0; i < 3; i++) {
            if (testArrayExpected[i] != testArrayActual[i]) {
                equals = false;
                break;
            }
        }
        assertTrue(equals);
    }

    @ParameterizedTest
    @MethodSource("getLadaAuto")
    void toArrayWithGenericsNewSizeSmallerThanOldSize(Automobile auto) {
        container.add(auto);
        container.add(auto);
        container.add(auto);

        Automobile[] testArrayExpected = new Automobile[3];

        testArrayExpected[0] = auto;
        testArrayExpected[1] = auto;
        testArrayExpected[2] = auto;

        Object[] testArrayActual = container.toArray(new Automobile[0]);

        boolean equals = true;
        for (int i = 0; i < 3; i++) {
            if (testArrayExpected[i] != testArrayActual[i]) {
                equals = false;
                break;
            }
        }
        assertTrue(equals);
    }

    @ParameterizedTest
    @MethodSource("getLadaAuto")
    void containsAllElementsFromCollectionShouldReturnTrue(Automobile auto) {
        collectionContainer.add(auto);

        container.add(auto);
        container.add(auto);
        container.add(auto);

        auto = new Automobile(1500, 100, VehicleType.LAND, "Sedan");

        container.add(auto);

        collectionContainer.add(auto);

        assertTrue(container.containsAll(collectionContainer));
    }

    @ParameterizedTest
    @MethodSource("getLadaAuto")
    void addAllElementsFromCollectionToTheEndOfList(Automobile auto) {
        container.add(auto);
        container.add(auto);

        Automobile firstInsertedAuto = new Automobile(100, 100, VehicleType.LAND, "TestInsert_1");
        Automobile secondInsertedAuto = new Automobile(100, 100, VehicleType.LAND, "TestInsert_2");

        collectionContainer.add(firstInsertedAuto);
        collectionContainer.add(secondInsertedAuto);

        container.addAll(collectionContainer);

        assertEquals(firstInsertedAuto, container.get(2));
        assertEquals(secondInsertedAuto, container.get(3));

    }

    @ParameterizedTest
    @MethodSource("getLadaAuto")
    void addAllElementsFromCollectionByIndexAtTheBeginning(Automobile auto) {
        container.add(auto);
        container.add(auto);

        Automobile firstInsertedAuto = new Automobile(100, 100, VehicleType.LAND, "TestInsert_1");
        Automobile secondInsertedAuto = new Automobile(100, 100, VehicleType.LAND, "TestInsert_2");

        collectionContainer.add(firstInsertedAuto);
        collectionContainer.add(secondInsertedAuto);

        container.addAll(0, collectionContainer);

        assertEquals(firstInsertedAuto, container.get(0));
        assertEquals(secondInsertedAuto, container.get(1));
    }

    @ParameterizedTest
    @MethodSource("getLadaAuto")
    void addAllElementsFromCollectionByIndexInBetween(Automobile auto) {
        container.add(auto);
        container.add(auto);
        container.add(auto);

        Automobile firstInsertedAuto = new Automobile(100, 100, VehicleType.LAND, "TestInsert_1");
        Automobile secondInsertedAuto = new Automobile(100, 100, VehicleType.LAND, "TestInsert_2");

        collectionContainer.add(firstInsertedAuto);
        collectionContainer.add(secondInsertedAuto);

        container.addAll(1, collectionContainer);

        assertEquals(firstInsertedAuto, container.get(1));
        assertEquals(secondInsertedAuto, container.get(2));
    }

    @ParameterizedTest
    @MethodSource("getLadaAuto")
    void addAllElementsFromCollectionByIndexAtTheEnd(Automobile auto) {
        container.add(auto);
        container.add(auto);

        Automobile firstInsertedAuto = new Automobile(100, 100, VehicleType.LAND, "TestInsert_1");
        Automobile secondInsertedAuto = new Automobile(100, 100, VehicleType.LAND, "TestInsert_2");

        collectionContainer.add(firstInsertedAuto);
        collectionContainer.add(secondInsertedAuto);

        container.addAll(2, collectionContainer);

        assertEquals(firstInsertedAuto, container.get(2));
        assertEquals(secondInsertedAuto, container.get(3));
    }

    @ParameterizedTest
    @MethodSource("getLadaAuto")
    void removeAllElementsFromCollection(Automobile auto) {
        container.add(auto);

        Automobile firstInsertedAuto = new Automobile(100, 100, VehicleType.LAND, "TestInsert_1");
        Automobile secondInsertedAuto = new Automobile(100, 100, VehicleType.LAND, "TestInsert_2");

        container.add(firstInsertedAuto);
        container.add(secondInsertedAuto);

        collectionContainer.add(firstInsertedAuto);
        collectionContainer.add(secondInsertedAuto);

        container.removeAll(collectionContainer);

        assertEquals(1, container.size());
        assertEquals(auto, container.get(0));
    }

    @ParameterizedTest
    @MethodSource("getLadaAuto")
    void retainAllElementsFromCollection(Automobile auto) {
        container.add(auto);

        Automobile firstInsertedAuto = new Automobile(100, 100, VehicleType.LAND, "TestInsert_1");
        Automobile secondInsertedAuto = new Automobile(100, 100, VehicleType.LAND, "TestInsert_2");

        container.add(firstInsertedAuto);

        collectionContainer.add(firstInsertedAuto);
        collectionContainer.add(secondInsertedAuto);

        container.retainAll(collectionContainer);

        assertEquals(1, container.size());
        assertEquals(firstInsertedAuto, container.get(0));
    }

    @ParameterizedTest
    @MethodSource("getLadaAuto")
    void clearShouldReturnTrue(Automobile auto) {
        container.add(auto);
        container.add(auto);
        container.add(auto);

        container.clear();

        assertEquals(0, container.size());
    }

    private static Stream<Arguments> getLadaAuto() {
        return Stream.of(Arguments.of(new Automobile(100, 100, VehicleType.LAND, "Lada")));
    }


    @Test
    void indexOfElementExists() {
        Automobile auto = new Automobile(10, 10, VehicleType.LAND, "Exists");

        container.add(auto);

        assertEquals(0, container.indexOf(auto));
    }

    @Test
    void indexOfElementDoesNotExist() {
        Automobile auto = new Automobile(10, 10, VehicleType.LAND, "Exists");

        container.add(auto);

        Automobile nonExistentAuto = new Automobile(1, 1, VehicleType.LAND, "Doesn't");

        assertEquals(-1, container.indexOf(nonExistentAuto));
    }

    @Test
    void lastIndexOfElementExists() {
        Automobile auto = new Automobile(10, 10, VehicleType.LAND, "Exists");

        container.add(auto);
        container.add(auto);
        container.add(auto);
        container.add(auto);


        assertEquals(3, container.lastIndexOf(auto));
    }

    @Test
    void lastIndexOfElementDoesNotExist() {
        Automobile auto = new Automobile(10, 10, VehicleType.LAND, "Exists");

        container.add(auto);

        Automobile nonExistentAuto = new Automobile(1, 1, VehicleType.LAND, "Doesn't");

        assertEquals(-1, container.lastIndexOf(nonExistentAuto));
    }


}