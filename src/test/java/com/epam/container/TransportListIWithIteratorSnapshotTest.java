package com.epam.container;

import com.epam.transport.Automobile;
import com.epam.transport.VehicleType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Iterator;

import static org.junit.jupiter.api.Assertions.*;

class TransportListIWithIteratorSnapshotTest {

    private static TransportListIWithIteratorSnapshot<Automobile> container;
    private static Automobile expectedAuto;
    private static Automobile changedAuto;

    @BeforeEach
    void initializeContainerWithElements() {
        container = new TransportListIWithIteratorSnapshot<>();

        expectedAuto = new Automobile(100, 100, VehicleType.LAND, "NotChanged");
        changedAuto = new Automobile(100, 100, VehicleType.LAND, "Changed");

        container.add(expectedAuto);
        container.add(expectedAuto);
        container.add(expectedAuto);
        container.add(expectedAuto);

    }

    @Test
    void set_ChecksIfContainerChangesWhileIterating_AutoNotChanged() {
        Iterator<Automobile> iterator = container.iterator();
        for (int i = 0; i < container.size(); i++) {
            container.set(i, changedAuto);
        }
        while (iterator.hasNext()) {
            assertEquals(expectedAuto, iterator.next());
        }
    }

    @Test
    void set_ChecksIfContainerChangesWhileIterating_AutoChanged() {
        for (int i = 0; i < container.size(); i++) {
            container.set(i, changedAuto);
        }
        for (Automobile automobile : container) {
            assertEquals(changedAuto, automobile);
        }
    }

}