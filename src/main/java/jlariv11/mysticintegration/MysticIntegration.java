package jlariv11.mysticintegration;

import jlariv11.mysticintegration.data.BlockModelGenerator;
import jlariv11.mysticintegration.data.BlockStateGenerator;
import jlariv11.mysticintegration.data.ItemModelGenerator;
import jlariv11.mysticintegration.magic.EnumMagicType;
import jlariv11.mysticintegration.registry.BlockRegistry;
import jlariv11.mysticintegration.registry.ItemRegistry;
import jlariv11.mysticintegration.registry.TileTypeRegistry;
import net.minecraft.data.DataGenerator;
import net.minecraft.state.EnumProperty;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.event.lifecycle.GatherDataEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.jline.utils.Log;
import org.lwjgl.system.CallbackI;

@Mod("mysticintegration")
public class MysticIntegration {

    public static final String MODID = "mysticintegration";
    public static final EnumProperty<EnumMagicType> MAGIC = EnumProperty.create("magic", EnumMagicType.class);


    public MysticIntegration() {
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::setup);
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::dataGen);
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

    public void dataGen(final GatherDataEvent evt){
        if(evt.includeClient()) {
            evt.getGenerator().addProvider(new BlockModelGenerator(evt.getGenerator(), evt.getExistingFileHelper()));
            evt.getGenerator().addProvider(new BlockStateGenerator(evt.getGenerator(), evt.getExistingFileHelper()));
            evt.getGenerator().addProvider(new ItemModelGenerator(evt.getGenerator(), evt.getExistingFileHelper()));
        }
    }

}
