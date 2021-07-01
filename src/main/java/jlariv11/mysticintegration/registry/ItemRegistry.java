package jlariv11.mysticintegration.registry;

import jlariv11.mysticintegration.MysticIntegration;
import jlariv11.mysticintegration.items.BaseItem;
import jlariv11.mysticintegration.items.MagicCrystalItem;
import jlariv11.mysticintegration.items.MysticHeadgearArmorItem;
import jlariv11.mysticintegration.magic.EnumMagicType;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryBuilder;

public class ItemRegistry {

    public static final Item.Properties BASE_ITEM_PROPERTIES = new Item.Properties().tab(ItemGroup.TAB_MISC);

    public static final DeferredRegister<Item> ITEM_REGISTER = DeferredRegister.create(ForgeRegistries.ITEMS, MysticIntegration.MODID);

    public static final RegistryObject<Item> MYSTIC_HEADGEAR = ITEM_REGISTER.register("mystic_headgear", MysticHeadgearArmorItem::new);
    public static final RegistryObject<Item> MAGIC_CRYSTAL_LIGHT = ITEM_REGISTER.register("magic_crystal_light", () -> new MagicCrystalItem(EnumMagicType.LIGHT));
    public static final RegistryObject<Item> MAGIC_CRYSTAL_DARK = ITEM_REGISTER.register("magic_crystal_dark", () -> new MagicCrystalItem(EnumMagicType.DARK));




}
