package jlariv11.mysticintegration.data;

import jlariv11.mysticintegration.MysticIntegration;
import jlariv11.mysticintegration.blocks.ChargerBlock;
import jlariv11.mysticintegration.blocks.GeneratorBlock;
import jlariv11.mysticintegration.registry.BlockRegistry;
import net.minecraft.block.Block;
import net.minecraft.data.DataGenerator;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.generators.BlockModelProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.fml.RegistryObject;

public class BlockModelGenerator extends BlockModelProvider {

    public BlockModelGenerator(DataGenerator generator, ExistingFileHelper existingFileHelper) {
        super(generator, MysticIntegration.MODID, existingFileHelper);
    }

    @Override
    protected void registerModels() {
        for(RegistryObject<Block> b : BlockRegistry.BLOCK_REGISTER.getEntries()){
            if(b.get() instanceof GeneratorBlock || b.get() instanceof ChargerBlock){
                continue;
            }
            String name = b.getId().toString().split(":")[1];
            this.cubeAll(b.getId().toString(), new ResourceLocation(MysticIntegration.MODID, "block/" + name));
        }
    }






}
