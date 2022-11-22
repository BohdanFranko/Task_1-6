package com.epam.container;


import com.epam.transport.Automobile;
import com.epam.transport.Vehicle;
import com.epam.transport.VehicleType;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

@TestClassOrder(ClassOrderer.OrderAnnotation.class)
class ContainerTest {
    private static TransportList<Automobile> container;
    private static Automobile maserati;
    private static Automobile sedan;
    private static Automobile lada;

    @BeforeAll
    static void initializeAutos() {
        maserati = new Automobile(200, 4, VehicleType.LAND, "Mazeratti");
        sedan = new Automobile(1500, 100, VehicleType.LAND, "Sedan");
        lada = new Automobile(1500, 120, VehicleType.LAND, "Lada");
    }

    @BeforeEach
    void initializeContainer() {
        container = new TransportList<>();
    }

    @Test
    void set_ChangeElement_IfDataValid() {
        //given
        container.add(maserati);

        Automobile changedToTransport = new Automobile(50, 10, VehicleType.LAND, "Changed");

        assertEquals(maserati, container.get(0));

        //when
        container.set(0, changedToTransport);

        //then
        assertEquals(changedToTransport, container.get(0));
    }

    @Test
    public void addElement() {
        assertTrue(container.isEmpty());

        container.add(maserati);

        assertEquals(1, container.size());
        assertEquals(maserati, container.get(0));
    }


    @Test
    void addElementByIndex() {
        container.add(maserati);
        container.add(maserati);
        container.add(maserati);

        assertEquals(container.get(1), maserati);

        Automobile testAuto = new Automobile(200, 4, VehicleType.LAND, "TestInsert");

        container.add(1, testAuto);

        assertEquals(container.get(1), testAuto);
    }

    @Test
    void removeElement() {
        assertTrue(container.isEmpty());

        container.add(maserati);

        assertEquals(1, container.size());

        assertTrue(container.remove(maserati));
        assertTrue(container.isEmpty());
    }

    @ParameterizedTest
    @MethodSource("provideAutos")
    void removeElementByIndex(Automobile auto, int index) {
        initializeContainerWithThreeAuto();

        assertEquals(auto, container.remove(index));
    }

    private static Stream<Arguments> provideAutos() {
        return Stream.of(Arguments.of(maserati, 0),
                Arguments.of(sedan, 1),
                Arguments.of(lada, 2));
    }

    private void initializeContainerWithThreeAuto() {
        container.add(maserati);
        container.add(sedan);
        container.add(lada);
    }

    @Nested
    @Order(1)
    @TestMethodOrder(MethodOrderer.OrderAnnotation.class)
    @DisplayName("Tests for get method")
    class TestForGet {
        @Test
        @Order(1)
        void getElementIfExists() {
            container.add(maserati);

            assertEquals(null, container.get(0), "Message failed because");
        }

        @ParameterizedTest
        @Order(2)
        @ValueSource(ints = {-1, 100})
        void get_ThrowsIndexOutOfBoundException_WhenIndexOutOfBound(int index) {
            assertThrows(IndexOutOfBoundsException.class, () -> container.get(index));
        }

        @Test
        @Order(3)
        void getElementIfDoesNotExist_ShouldThrowIndexOutOfBounds() throws IndexOutOfBoundsException {
            assertThrows(IndexOutOfBoundsException.class, () -> container.get(0));
        }

    }

    @Test
    void isEmpty_ReturnsTrue_ListIsEmpty() {
        assertTrue(container.isEmpty());
    }

    @Nested
    @Order(2)
    @TestMethodOrder(MethodOrderer.OrderAnnotation.class)
    @DisplayName("Tests for contains method")
    class TestForContains {
        @Test
        void contains_ReturnsFalse_AutoDoesNotExistInList() {
            assertFalse(container.contains(lada));
        }

    }

    @Test
    void contains_ReturnsTrue_AutoExistsInList() {
        container.add(lada);

        assertTrue(container.contains(lada));
    }

    @Test
    void toArray_getArraySuccessfully() {
        assertTrue(container.isEmpty());

        initializeAutos();

        container.add(maserati);
        container.add(sedan);
        container.add(lada);

        Automobile[] testArrayExpected = initializeCollectionAutos();

        Object[] testArrayActual = container.toArray();

        assertTrue(equalsArray(testArrayExpected, testArrayActual));
    }

    private Automobile[] initializeCollectionAutos() {
        Automobile[] testArrayExpected = new Automobile[3];

        testArrayExpected[0] = maserati;
        testArrayExpected[1] = sedan;
        testArrayExpected[2] = lada;

        return testArrayExpected;
    }

    private boolean equalsArray(Automobile[] array, Object[] convertedArray) {
        for (int i = 0; i < array.length; i++) {
            if (!array[i].equals(convertedArray[i])) {
                return false;
            }
        }
        return true;
    }

    @Test
    @DisplayName("Generified toArray size of new array is the same as list")
    void toArrayGenerified_GetArraySuccessfully_SameArraySize() {
        assertTrue(container.isEmpty());

        initializeAutos();

        container.add(maserati);
        container.add(sedan);
        container.add(lada);

        Automobile[] testArrayExpected = initializeCollectionAutos();

        Vehicle[] testArrayActual = container.toArray(new Vehicle[3]);

        assertTrue(equalsArray(testArrayExpected, testArrayActual));
    }

    @Test
    @DisplayName("Generified toArray size of new array is bigger than list's")
    void toArrayGenerified_GetArraySuccessfully_BiggerArraySize() {
        assertTrue(container.isEmpty());

        initializeAutos();

        container.add(maserati);
        container.add(sedan);
        container.add(lada);

        Automobile[] testArrayExpected = initializeCollectionAutos();

        Vehicle[] testArrayActual = container.toArray(new Vehicle[100]);

        assertTrue(equalsArray(testArrayExpected, testArrayActual));
    }

    @Test
    @DisplayName("Generified toArray size of new array is smaller than list's")
    void toArrayGenerified_GetArraySuccessfully_SmallerArraySize() {
        assertTrue(container.isEmpty());

        container.add(maserati);
        container.add(sedan);
        container.add(lada);

        Automobile[] testArrayExpected = initializeCollectionAutos();

        Vehicle[] testArrayActual = container.toArray(new Vehicle[0]);

        assertTrue(equalsArray(testArrayExpected, testArrayActual));
    }

    @Test
    @DisplayName("Returns true because contains all elements from collection")
    void containsAll_ReturnsTrue_ContainsAllElements() {
        assertTrue(container.isEmpty());

        List<Automobile> collectionContainer = initializeCollection();

        container.add(maserati);
        container.add(sedan);
        container.add(lada);

        assertTrue(container.containsAll(collectionContainer));
    }

    private List<Automobile> initializeCollection() {
        List<Automobile> list = new ArrayList<>();
        list.add(sedan);
        list.add(maserati);
        return list;
    }

    @Test
    @DisplayName("Add all elements from collection to the end of list")
    void addAll_AddElementsSuccessfullyToTheEnd() {
        assertTrue(container.isEmpty());

        initializeAutos();
        container.add(lada);
        container.add(sedan);

        List<Automobile> collection = new ArrayList<>();
        Automobile firstInsertedAuto = new Automobile(100, 100, VehicleType.LAND, "TestInsert_1");
        Automobile secondInsertedAuto = new Automobile(100, 100, VehicleType.LAND, "TestInsert_2");

        collection.add(firstInsertedAuto);
        collection.add(secondInsertedAuto);

        container.addAll(collection);

        assertEquals(firstInsertedAuto, container.get(2));
        assertEquals(secondInsertedAuto, container.get(3));

    }

    @Test
    @DisplayName("Add all elements by index from collection to the beginning of list")
    void addAll_AddElementsByIndexSuccessfullyToTheEnd() {
        assertTrue(container.isEmpty());

        container.add(maserati);
        container.add(sedan);

        List<Automobile> collection = new ArrayList<>();
        Automobile firstInsertedAuto = new Automobile(100, 100, VehicleType.LAND, "TestInsert_1");
        Automobile secondInsertedAuto = new Automobile(100, 100, VehicleType.LAND, "TestInsert_2");

        collection.add(firstInsertedAuto);
        collection.add(secondInsertedAuto);

        container.addAll(0, collection);

        assertEquals(firstInsertedAuto, container.get(0));
        assertEquals(secondInsertedAuto, container.get(1));
        assertEquals(maserati, container.get(2));
        assertEquals(sedan, container.get(3));
    }

    @Test
    @DisplayName("addAll elements from collection in the middle of the list")
    void addAll_AddAllElementsFromCollectionInBetween() {
        assertTrue(container.isEmpty());

        container.add(lada);
        container.add(sedan);
        container.add(sedan);

        List<Automobile> collection = new ArrayList<>();
        Automobile firstInsertedAuto = new Automobile(100, 100, VehicleType.LAND, "TestInsert_1");
        Automobile secondInsertedAuto = new Automobile(100, 100, VehicleType.LAND, "TestInsert_2");

        collection.add(firstInsertedAuto);
        collection.add(secondInsertedAuto);

        container.addAll(1, collection);

        assertEquals(lada, container.get(0));
        assertEquals(firstInsertedAuto, container.get(1));
        assertEquals(secondInsertedAuto, container.get(2));
        assertEquals(sedan, container.get(3));
        assertEquals(sedan, container.get(4));
    }

    @Test
    @DisplayName("addAll elements from collection in the beginning of the list")
    void addAll_AddAllElementsFromCollectionAtTheBeginning() {
        assertTrue(container.isEmpty());

        container.add(lada);
        container.add(sedan);
        container.add(sedan);

        List<Automobile> collection = new ArrayList<>();
        Automobile firstInsertedAuto = new Automobile(100, 100, VehicleType.LAND, "TestInsert_1");
        Automobile secondInsertedAuto = new Automobile(100, 100, VehicleType.LAND, "TestInsert_2");

        collection.add(firstInsertedAuto);
        collection.add(secondInsertedAuto);

        container.addAll(0, collection);

        assertEquals(lada, container.get(2));
        assertEquals(firstInsertedAuto, container.get(0));
        assertEquals(secondInsertedAuto, container.get(1));
        assertEquals(sedan, container.get(3));
        assertEquals(sedan, container.get(4));
    }

    @Test
    @DisplayName("removeAll elements in specified Collection from list")
    void removeAll_ReturnsTrue_ElementsExistedInList() {
        assertTrue(container.isEmpty());

        container.add(sedan);

        List<Automobile> collection = new ArrayList<>();
        Automobile firstInsertedAuto = new Automobile(100, 100, VehicleType.LAND, "TestInsert_1");
        Automobile secondInsertedAuto = new Automobile(100, 100, VehicleType.LAND, "TestInsert_2");

        container.add(firstInsertedAuto);
        container.add(secondInsertedAuto);

        collection.add(firstInsertedAuto);
        collection.add(secondInsertedAuto);

        assertTrue(container.removeAll(collection));
        assertEquals(1, container.size());
        assertEquals(sedan, container.get(0));
    }

    @Nested
    @Order(3)
    @TestMethodOrder(MethodOrderer.OrderAnnotation.class)
    @DisplayName("Tests for removeAll method")
    class TestRemoveAll {
        @Test
        @DisplayName("index of element fail to get index element doesn't exist")
        void removeAll_ReturnsFalse_ElementsDoesNotExistInList() {
            assertTrue(container.isEmpty());

            container.add(sedan);

            List<Automobile> collection = new ArrayList<>();
            Automobile firstInsertedAuto = new Automobile(100, 100, VehicleType.LAND, "TestInsert_1");
            Automobile secondInsertedAuto = new Automobile(100, 100, VehicleType.LAND, "TestInsert_2");

            collection.add(firstInsertedAuto);
            collection.add(secondInsertedAuto);

            assertFalse(container.removeAll(collection));
            assertEquals(1, container.size());
            assertEquals(sedan, container.get(0));
        }

    }


    @Test
    @DisplayName("retains all elements from collection in list")
    void retainAll_RetainsAllElementsFromCollection() {
        assertTrue(container.isEmpty());

        container.add(sedan);

        List<Automobile> collection = new ArrayList<>();
        Automobile firstInsertedAuto = new Automobile(100, 100, VehicleType.LAND, "TestInsert_1");
        Automobile secondInsertedAuto = new Automobile(100, 100, VehicleType.LAND, "TestInsert_2");

        container.add(firstInsertedAuto);

        collection.add(firstInsertedAuto);
        collection.add(secondInsertedAuto);

        container.retainAll(collection);

        assertEquals(1, container.size());
        assertEquals(firstInsertedAuto, container.get(0));
    }

    @Test
    void clear() {
        container.add(sedan);
        container.add(maserati);
        container.add(lada);

        container.clear();

        assertEquals(0, container.size());
    }

    @Test
    void indexOf_GetIndexOfElement_ElementExists() {
        assertTrue(container.isEmpty());

        container.add(maserati);

        assertEquals(0, container.indexOf(maserati));
    }

    @Nested
    @Order(4)
    @TestMethodOrder(MethodOrderer.OrderAnnotation.class)
    @DisplayName("Tests for IndexOf method")
    class TestIndexOf {
        @Test
        @DisplayName("index of element fail to get index element doesn't exist")
        void indexOf_FailToGetIndex_ElementDoesNotExist() {
            assertTrue(container.isEmpty());

            container.add(sedan);

            assertEquals(-1, container.indexOf(lada));
        }

    }

    @Test
    @DisplayName("get last index of element in list, element exists")
    void lastIndexOf_SuccessfullyGetIndex_ElementExists() {
        assertTrue(container.isEmpty());

        container.add(sedan);
        container.add(maserati);
        container.add(sedan);
        container.add(lada);

        assertEquals(2, container.lastIndexOf(sedan));
    }

    @Nested
    @Order(5)
    @TestMethodOrder(MethodOrderer.OrderAnnotation.class)
    @DisplayName("Tests for lastIndexOf method")
    class TestLastIndexOf {
        @Test
        @DisplayName("Last index of element in list, element doesn't exist returns -1")
        void lastIndexOf_FailToGenIndex_ElementDoesNotExist() {
            assertTrue(container.isEmpty());

            container.add(maserati);

            assertEquals(-1, container.lastIndexOf(sedan));
        }

    }


}