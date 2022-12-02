package com.epam.container;

import com.epam.transport.Automobile;
import com.epam.transport.VehicleType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TransportListCopyOnWriteTest {

    private TransportListCopyOnWrite<Automobile> container;
    private Automobile expectedAuto;
    private Automobile changedAuto;

    @BeforeEach
    void initializeContainerWithElements() {
        container = new TransportListCopyOnWrite<>();

        expectedAuto = new Automobile(100, 100, VehicleType.LAND, "NotChanged");
        changedAuto = new Automobile(100, 100, VehicleType.LAND, "Changed");

        container.add(expectedAuto);
        container.add(expectedAuto);
        container.add(expectedAuto);
        container.add(expectedAuto);

    }

    @Test
    @DisplayName("iterate over list while adding every iteration to check if it modifies elements")
    void iterator() {
        boolean notInfiniteFor = true;
        for (Automobile automobile : container) {
            container.add(changedAuto);
        }
        assertTrue(notInfiniteFor);
    }
}