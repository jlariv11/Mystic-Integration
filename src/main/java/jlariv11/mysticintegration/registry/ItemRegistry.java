package jlariv11.mysticintegration.registry;

import jlariv11.mysticintegration.MysticIntegration;
import jlariv11.mysticintegration.items.MysticHeadgearArmorItem;
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




}
