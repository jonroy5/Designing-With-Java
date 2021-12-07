package com.amazon.ata.types;

import com.amazon.ata.types.Item;
import com.amazon.ata.types.Packaging;

import java.math.BigDecimal;

public class Box extends Packaging {


    /**
     * Instantiates a new Packaging object.
     *
     * @param material - the Material of the package
     * @param length   - the length of the package
     * @param width    - the width of the package
     * @param height   - the height of the package
     *
     */

   private BigDecimal length;
   private BigDecimal width;
   private BigDecimal height;

    public Box(Material material, BigDecimal length, BigDecimal width, BigDecimal height) {
        super(material,length,width,height);
        this.length = length;
        this.width = width;
        this.height = height;

    }

    @Override
    public boolean canFitItem(Item item) {
        return super.canFitItem(item);
    }

    @Override
    public BigDecimal getMass() {
        return super.getMass();
    }

    @Override
    public boolean equals(Object o) {
        return super.equals(o);
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }
}
