package jlariv11.mysticintegration.registry;

import jlariv11.mysticintegration.MysticIntegration;
import jlariv11.mysticintegration.blocks.CableBlock;
import jlariv11.mysticintegration.blocks.ChargerBlock;
import jlariv11.mysticintegration.blocks.DecayingLightBlock;
import jlariv11.mysticintegration.blocks.GeneratorBlock;
import jlariv11.mysticintegration.items.MysticHeadgearArmorItem;
import jlariv11.mysticintegration.magic.EnumMagicType;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialColor;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraftforge.common.ToolType;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class BlockRegistry {

    public static final Block.Properties BASE_BLOCK_PROPERTIES = AbstractBlock.Properties.of(Material.STONE).harvestTool(ToolType.PICKAXE).harvestLevel(1);

    public static final DeferredRegister<Block> BLOCK_REGISTER = DeferredRegister.create(ForgeRegistries.BLOCKS, MysticIntegration.MODID);

    public static final RegistryObject<Block> LIGHT_GENERATOR = BLOCK_REGISTER.register("light_generator", () -> new GeneratorBlock(EnumMagicType.LIGHT));
    public static final RegistryObject<Block> DARK_GENERATOR = BLOCK_REGISTER.register("dark_generator", () -> new GeneratorBlock(EnumMagicType.DARK));
    public static final RegistryObject<Block> CHARGER = BLOCK_REGISTER.register("charger", ChargerBlock::new);
    public static final RegistryObject<Block> TEMP_CABLE = BLOCK_REGISTER.register("cable", CableBlock::new);
    public static final RegistryObject<Block> DECAYING_LIGHT_BLOCK = BLOCK_REGISTER.register("decaying_light", DecayingLightBlock::new);


    public static void registerBlockItems(){
        for(RegistryObject<Block> b : BLOCK_REGISTER.getEntries()){
            ItemRegistry.ITEM_REGISTER.register(b.getId().toString().split(":")[1], () -> new BlockItem(b.get(), ItemRegistry.BASE_ITEM_PROPERTIES));

        }
    }

}
