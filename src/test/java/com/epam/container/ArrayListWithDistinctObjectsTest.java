package com.epam.container;

import com.epam.transport.Automobile;
import com.epam.transport.VehicleType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ArrayListWithDistinctObjectsTest {

    private static ArrayListWithDistinctObjects<Automobile> automobiles;
    private static List<Automobile> list;

    private Automobile maserati;
    private Automobile sedan;
    private Automobile lada;

    @BeforeEach
    void initializeContainer() {
        automobiles = new ArrayListWithDistinctObjects<>();
        list = new ArrayList<>();

        maserati = new Automobile(200, 4, VehicleType.LAND, "Mazeratti");
        sedan = new Automobile(1500, 100, VehicleType.LAND, "Sedan");
        lada = new Automobile(1500, 120, VehicleType.LAND, "Lada");
    }

    @Test
    void set_SetNewValueCorrectly() {
        automobiles.add(sedan);

        automobiles.set(0, maserati);

        assertEquals(maserati, automobiles.get(0));
    }

    @Test
    void set_ReturnsFalse_ObjectInstanceAlreadyExists() {
        automobiles.add(sedan);
        automobiles.add(lada);

        assertNull(automobiles.set(1, sedan));
    }

    @Test
    void add_AddNewValueCorrectly() {
        assertTrue(automobiles.add(maserati));
    }

    @Test
    void add_ReturnsFalse_ObjectInstanceAlreadyExists() {
        automobiles.add(sedan);
        assertFalse(automobiles.add(sedan));
    }

    @Test
    void add_AddNewValueByIndexCorrectly() {
        automobiles.add(0, maserati);

        assertEquals(maserati, automobiles.get(0));
    }

    @Test
    void add_FailAddByIndex_ObjectInstanceAlreadyExists() {
        automobiles.add(maserati);

        automobiles.add(0, maserati);

        assertEquals(1, automobiles.size());
    }

    @Test
    void addAll_ReturnsTrue_AllNewObjects() {
        list.add(maserati);
        list.add(sedan);
        list.add(lada);

        assertTrue(automobiles.addAll(list));
        assertEquals(3, automobiles.size());
    }

    @Test
    void addAll_ReturnsFalse_NewCollectionSeveralSameInstances() {
        list.add(sedan);
        list.add(maserati);
        list.add(maserati);

        automobiles.addAll(list);

        assertFalse(automobiles.addAll(list));
        assertEquals(2, automobiles.size());
    }

    @Test
    void addAllByIndex_ReturnsTrue_AllNewObjects() {
        list.add(maserati);
        list.add(sedan);
        list.add(lada);

        assertTrue(automobiles.addAll(0, list));
        assertEquals(3, automobiles.size());
    }

    @Test
    void addAllByIndex_ReturnsFalse_NewCollectionSeveralSameInstances() {
        automobiles.add(lada);
        list.add(lada);
        list.add(lada);

        automobiles.addAll(0, list);

        assertFalse(automobiles.addAll(list));
        assertEquals(1, automobiles.size());
    }
}