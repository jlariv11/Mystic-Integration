package jlariv11.mysticintegration.magic;

import net.minecraft.util.IStringSerializable;

public enum EnumMagicType implements IStringSerializable {

    LIGHT("light"),
    DARK("dark"),
    NONE("none");


    private String name;

    EnumMagicType(String name) {
        this.name = name;
    }


    @Override
    public String getSerializedName() {
        return this.name;
    }


    @Override
    public String toString() {
        return this.name;
    }
}
