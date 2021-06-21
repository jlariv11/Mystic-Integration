package jlariv11.mysticintegration;

import jlariv11.mysticintegration.registry.BlockRegistry;
import jlariv11.mysticintegration.registry.ItemRegistry;
import jlariv11.mysticintegration.registry.TileTypeRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod("mysticintegration")
public class MysticIntegration {

    public static final String MODID = "mysticintegration";

    public MysticIntegration() {
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::setup);
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::clientSetup);
        BlockRegistry.BLOCK_REGISTER.register(FMLJavaModLoadingContext.get().getModEventBus());
        BlockRegistry.registerBlockItems();
        ItemRegistry.ITEM_REGISTER.register(FMLJavaModLoadingContext.get().getModEventBus());
        TileTypeRegistry.TILE_TYPES.register(FMLJavaModLoadingContext.get().getModEventBus());
    }

    private void setup(final FMLCommonSetupEvent evt){

    }

    private void clientSetup(final FMLClientSetupEvent evt){

    }

}
