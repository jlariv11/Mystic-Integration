package jlariv11.mysticintegration.blocks.tiles;

import jlariv11.mysticintegration.blocks.DecayingLightBlock;
import jlariv11.mysticintegration.magic.EnumMagicType;
import jlariv11.mysticintegration.registry.TileTypeRegistry;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.common.Tags;
import org.jline.utils.Log;

import java.util.ArrayList;
import java.util.List;

public class GeneratorTile extends TileEntity implements ITickableTileEntity {

    private EnumMagicType type;

    public GeneratorTile() {
        super(TileTypeRegistry.GENERATOR_TILE.get());
        this.type = null;
    }

    public EnumMagicType getMagicType() {
        return type;
    }

    public void setMagicType(EnumMagicType type) {
        this.type = type;
    }

    private List<BlockPos> getLightBlocks(){
        List<BlockPos> lightBlocks = new ArrayList<>();

        for(int x = 0; x < 5; x++){
            for(int y = 0; y < 5; y++){
                for(int z = 0; z < 5; z++){
                    BlockPos pos = this.getBlockPos().offset(x, y, z);
                    int light = this.getLevel().getLightEmission(pos);
                    if(light > 0){
//                        Log.info("Found light block at: " + pos.getX() + " " + pos.getY() + " " + pos.getZ());
//                        Log.info("Light level: " + light);
                        lightBlocks.add(pos);
                        DecayingLightBlock dlb = new DecayingLightBlock();
                        dlb.setLight(light);
                        this.getLevel().setBlock(pos, dlb.defaultBlockState(), 0);
                    }
                }
            }
        }


        return lightBlocks;
    }

    @Override
    public void tick() {
        for(BlockPos lb : getLightBlocks()){

        }

    }
}
