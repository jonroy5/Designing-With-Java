package com.amazon.ata.cost;

import com.amazon.ata.types.ShipmentCost;
import com.amazon.ata.types.ShipmentOption;

import java.math.BigDecimal;

public class WeightedCostStrategy implements CostStrategy {

    CarbonCostStrategy carbonStrat = new CarbonCostStrategy();
    MonetaryCostStrategy monetaryStrat = new MonetaryCostStrategy();

    public WeightedCostStrategy(MonetaryCostStrategy monetaryStrat, CarbonCostStrategy carbonStrat) {
        this.carbonStrat = carbonStrat;
        this.monetaryStrat = monetaryStrat;
    }

    public WeightedCostStrategy() {

    }



    @Override
    public ShipmentCost getCost(ShipmentOption shipmentOption) {
        BigDecimal monetaryCost = monetaryStrat.getCost(shipmentOption).getCost().multiply(BigDecimal.valueOf(.8));
        BigDecimal carbonCost = carbonStrat.getCost(shipmentOption).getCost().multiply(BigDecimal.valueOf(.2));

        BigDecimal totalCost = monetaryCost.add(carbonCost);

        return new ShipmentCost(shipmentOption, totalCost);
    }
}
