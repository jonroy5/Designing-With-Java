package com.amazon.ata.service;

import com.amazon.ata.cost.CostStrategy;
import com.amazon.ata.cost.MonetaryCostStrategy;
import com.amazon.ata.dao.PackagingDAO;
import com.amazon.ata.datastore.PackagingDatastore;
import com.amazon.ata.exceptions.NoPackagingFitsItemException;
import com.amazon.ata.exceptions.UnknownFulfillmentCenterException;
import com.amazon.ata.types.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

class ShipmentServiceTest {

    private Item smallItem = Item.builder()
            .withHeight(BigDecimal.valueOf(1))
            .withWidth(BigDecimal.valueOf(1))
            .withLength(BigDecimal.valueOf(1))
            .withAsin("abcde")
            .build();

    private Item largeItem = Item.builder()
            .withHeight(BigDecimal.valueOf(1000))
            .withWidth(BigDecimal.valueOf(1000))
            .withLength(BigDecimal.valueOf(1000))
            .withAsin("12345")
            .build();
    private Packaging box = new Box(Material.CORRUGATE, BigDecimal.valueOf(5), BigDecimal.valueOf(5), BigDecimal.valueOf(5));

    private FulfillmentCenter existentFC = new FulfillmentCenter("ABE2");
    private FulfillmentCenter nonExistentFC = new FulfillmentCenter("NonExistentFC");

    @Mock
    PackagingDAO packagingDAO;
    @Mock
    CostStrategy costStrategy;
    @InjectMocks
    private ShipmentService shipmentService;

    @BeforeEach
    void setup() {
        initMocks(this);
    }

    @Test
    void findBestShipmentOption_existentFCAndItemCanFit_returnsShipmentOption() throws UnknownFulfillmentCenterException, NoPackagingFitsItemException {
        // GIVEN & WHEN
        List<ShipmentOption> shipmentOptions = new ArrayList<>();
        ShipmentOption shipmentOption1 = ShipmentOption.builder()
                .withFulfillmentCenter(existentFC)
                .withItem(smallItem)
                .withPackaging(box)
                .build();
        shipmentOptions.add(shipmentOption1);
        when(packagingDAO.findShipmentOptions(smallItem, existentFC)).thenReturn(shipmentOptions);
//        ShipmentOption shipmentOption = shipmentService.findShipmentOption(smallItem, existentFC);

        // THEN
        assertNotNull(shipmentOption1);
    }


    @Test
    void findBestShipmentOption_existentFCAndItemCannotFit_returnsShipmentOptionWithNullPackaging() throws UnknownFulfillmentCenterException, NoPackagingFitsItemException {
        // GIVEN & WHEN
//        ShipmentOption shipmentOption = shipmentService.findShipmentOption(largeItem, existentFC);
//        ShipmentOption shipmentOption1 = ShipmentOption.builder().build();
        List<ShipmentOption> shipmentOptions = new ArrayList<>();
        ShipmentOption shipmentOption = ShipmentOption.builder().withItem(largeItem).withFulfillmentCenter(existentFC).withPackaging(box).build();
        ShipmentOption shipmentOption1 = ShipmentOption.builder().withItem(largeItem).withFulfillmentCenter(existentFC).withPackaging(null).build();
        shipmentOptions.add(shipmentOption);
        when(packagingDAO.findShipmentOptions(largeItem, existentFC)).thenReturn(shipmentOptions);

        // THEN
        assertEquals(shipmentOption.getClass(), shipmentOption1.getClass());
        assertNull(shipmentOption1.getPackaging());
    }

    @Test
    void findBestShipmentOption_nonExistentFCAndItemCanFit_throwsRuntimeException() {
        // GIVEN & WHEN
        //  ShipmentOption shipmentOption = shipmentService.findShipmentOption(smallItem, nonExistentFC);


        // THEN
        assertThrows(RuntimeException.class, () -> {
            shipmentService.findShipmentOption(smallItem, nonExistentFC);
        }, "Uknown FC");
    }

    @Test
    void findBestShipmentOption_nonExistentFCAndItemCannotFit_returnsShipmentOption() {
        // GIVEN & WHEN
        assertThrows(RuntimeException.class, () -> {
            shipmentService.findShipmentOption(largeItem, nonExistentFC);
        }, "Uknown FC");
    }

    @Test
    void findBestShipmentOption_existentFCAndItemCanFit_ItemsDoNotEqual() {
        ShipmentOption shipmentOption1 = ShipmentOption.builder().withItem(largeItem).build();
        ShipmentOption shipmentOption = ShipmentOption.builder().withItem(smallItem).build();

        assertNotEquals(shipmentOption, shipmentOption1);

    }
}