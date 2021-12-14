package com.amazon.ata.types;

import com.amazon.ata.types.Item;
import com.amazon.ata.types.Packaging;

import java.math.BigDecimal;
import java.util.Objects;

public class PolyBag extends Packaging {
    /**
     * Instantiates a new Packaging object.
     *
     * @param material - the Material of the package
     * @param length   - the length of the package
     * @param width    - the width of the package
     * @param height   - the height of the package
     */
    private BigDecimal volume;

    public PolyBag(Material material, BigDecimal volume) {
        super(material);
        this.volume = volume;

    }

    public BigDecimal getVolume() {
        return volume;
    }

    @Override
    public boolean canFitItem(Item item) {
        BigDecimal itemVolume = item.getHeight().multiply(item.getLength().multiply(item.getWidth()));
        return (itemVolume.doubleValue() <= volume.doubleValue());
    }

    @Override
    public BigDecimal getMass() {

        return BigDecimal.valueOf(Math.ceil(Math.sqrt(volume.doubleValue()) * 0.6));
    }

    @Override
    public boolean equals(Object o) {

        if (!(o instanceof PolyBag)) {
            return false;
        }
        return super.equals(o) && this.getVolume().equals(((PolyBag) o).getVolume());
    }

    @Override
    public int hashCode() {

        return Objects.hash(getVolume(), super.hashCode());
    }
}
