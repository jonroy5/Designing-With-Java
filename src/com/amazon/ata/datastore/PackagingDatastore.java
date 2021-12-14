package com.amazon.ata.datastore;

import com.amazon.ata.types.*;


import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

/**
 * Stores all configured packaging pairs for all fulfillment centers.
 */
public class PackagingDatastore {

    /**
     * The stored pairs of fulfillment centers to the packaging options they support.
     */
    private final List<FcPackagingOption> fcPackagingOptions = Arrays.asList(
            createFcBoxOption("IND1", Material.CORRUGATE, "10", "10", "10"),
            createFcBoxOption("ABE2", Material.CORRUGATE, "20", "20", "20"),
            createFcBoxOption("ABE2", Material.CORRUGATE, "40", "40", "40"),
            createFcBoxOption("YOW4", Material.CORRUGATE, "10", "10", "10"),
            createFcBoxOption("YOW4", Material.CORRUGATE, "20", "20", "20"),
            createFcBoxOption("YOW4", Material.CORRUGATE, "60", "60", "60"),
            createFcBoxOption("IAD2", Material.CORRUGATE, "20", "20", "20"),
            createFcBoxOption("IAD2", Material.CORRUGATE, "20", "20", "20"),
            createFcBoxOption("PDX1", Material.CORRUGATE, "40", "40", "40"),
            createFcBoxOption("PDX1", Material.CORRUGATE, "60", "60", "60"),
            createFcBoxOption("PDX1", Material.CORRUGATE, "60", "60", "60"),
            createFcPolyBagOption("IAD2", Material.LAMINATED_PLASTIC, "2000"),
            createFcPolyBagOption("IAD2", Material.LAMINATED_PLASTIC, "5000"),
            createFcPolyBagOption("YOW4", Material.LAMINATED_PLASTIC, "2000"),
            createFcPolyBagOption("YOW4", Material.LAMINATED_PLASTIC, "5000"),
            createFcPolyBagOption("YOW4", Material.LAMINATED_PLASTIC, "10000"),
            createFcPolyBagOption("IND1", Material.LAMINATED_PLASTIC, "2000"),
            createFcPolyBagOption("IND1", Material.LAMINATED_PLASTIC, "5000"),
            createFcPolyBagOption("ABE2", Material.LAMINATED_PLASTIC, "2000"),
            createFcPolyBagOption("ABE2", Material.LAMINATED_PLASTIC, "6000"),
            createFcPolyBagOption("PDX1", Material.LAMINATED_PLASTIC, "5000"),
            createFcPolyBagOption("PDX1", Material.LAMINATED_PLASTIC, "10000"),
            createFcPolyBagOption("YOW4", Material.LAMINATED_PLASTIC, "5000")
    );

    /**
     * Create fulfillment center packaging option from provided parameters.
     */
    private FcPackagingOption createFcBoxOption(String fcCode, Material material,
                                                      String length, String width, String height) {
        FulfillmentCenter fulfillmentCenter = new FulfillmentCenter(fcCode);
        Packaging packaging = new Box(material, new BigDecimal(length), new BigDecimal(width),
                new BigDecimal(height));

        return new FcPackagingOption(fulfillmentCenter, packaging);
    }
    private FcPackagingOption createFcPolyBagOption(String fcCode, Material material, String volume) {
        FulfillmentCenter fulfillmentCenter = new FulfillmentCenter(fcCode);
        Packaging packaging = new PolyBag(material, new BigDecimal(volume));

        return new FcPackagingOption(fulfillmentCenter, packaging);
    }

    public List<FcPackagingOption> getFcPackagingOptions() {

        return fcPackagingOptions;
    }
}
