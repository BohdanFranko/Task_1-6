package com.epam.store;

import com.epam.transport.Automobile;
import com.epam.transport.VehicleType;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class BucketTest {
    private Bucket bucket;
    private static ListOfGoods listOfGoods;

    @BeforeAll
    static void initializeList() {
        List<Automobile> list = new ArrayList<>();
        list.add(new Automobile(200, 4, VehicleType.LAND, "Mazeratti", 20));
        list.add(new Automobile(250, 4, VehicleType.LAND, "Lexus", 40));
        listOfGoods = new ListOfGoods(list);
    }

    @BeforeEach
    void initializeBucket() {
        bucket = new Bucket();
    }

    @Test
    void add_ReturnsTrue_ProductWithIdExists() {
        assertTrue(bucket.add(1, listOfGoods));
    }

    @Test
    void add_ReturnsFalse_ProductWithIdDoesntExists() {
        assertFalse(bucket.add(100, listOfGoods));
    }

    @Test
    void totalPrice() {
        bucket.add(1, listOfGoods);
        bucket.add(1, listOfGoods);
        bucket.add(1, listOfGoods);
        bucket.add(1, listOfGoods);
        int expected = 80;
        assertEquals(expected, bucket.totalPrice(listOfGoods));
    }

}