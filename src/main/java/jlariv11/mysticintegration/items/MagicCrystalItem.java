package jlariv11.mysticintegration.items;

import jlariv11.mysticintegration.magic.EnumMagicType;

public class MagicCrystalItem extends BaseItem{

    private EnumMagicType type;

    public MagicCrystalItem(EnumMagicType type) {
        this.type = type;
    }

    public EnumMagicType getType() {
        return type;
    }
}
