package com.epam.store;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Hashtable;

import static org.junit.jupiter.api.Assertions.*;

class OrdersTest {
    private Orders orders;
    @BeforeEach
    void initializeOrders() {
        orders = new Orders();
    }
    @Test
    void add() {
        LocalDateTime date = LocalDateTime.now();
        assertTrue(orders.add(date, new HashMap<>()));
    }
}