package jlariv11.mysticintegration.items;

import jlariv11.mysticintegration.registry.ItemRegistry;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.ArmorMaterial;
import net.minecraft.item.IArmorMaterial;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

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
}
