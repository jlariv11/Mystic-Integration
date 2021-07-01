package jlariv11.mysticintegration.items;

import jlariv11.mysticintegration.registry.ItemRegistry;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.ArmorMaterial;
import net.minecraft.item.IArmorMaterial;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.world.World;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.energy.CapabilityEnergy;
import net.minecraftforge.energy.EnergyStorage;

import javax.annotation.Nullable;

public class MysticHeadgearArmorItem extends ArmorItem {


    public MysticHeadgearArmorItem() {
        super(ArmorMaterial.IRON, EquipmentSlotType.HEAD, ItemRegistry.BASE_ITEM_PROPERTIES);
    }

    @Override
    public void onArmorTick(ItemStack stack, World world, PlayerEntity player) {
        //do stuff based on "magic"
        //healing
        //defence
        //stuff
    }

    @Nullable
    @Override
    public ICapabilityProvider initCapabilities(ItemStack stack, @Nullable CompoundNBT nbt) {
        return new CapabilityProviderEnergy(8000, 100, 0);
    }
}
