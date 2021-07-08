package jlariv11.mysticintegration.data;

import jlariv11.mysticintegration.MysticIntegration;
import jlariv11.mysticintegration.registry.BlockRegistry;
import net.minecraft.block.Block;
import net.minecraft.data.DataGenerator;
import net.minecraftforge.client.model.generators.BlockStateProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.fml.RegistryObject;

public class BlockStateGenerator extends BlockStateProvider {

    public BlockStateGenerator(DataGenerator gen, ExistingFileHelper exFileHelper) {
        super(gen, MysticIntegration.MODID, exFileHelper);
    }

    @Override
    protected void registerStatesAndModels() {
        for(RegistryObject<Block> b : BlockRegistry.BLOCK_REGISTER.getEntries()){
            this.simpleBlock(b.get(), this.cubeAll(b.get()));
        }
    }
}
