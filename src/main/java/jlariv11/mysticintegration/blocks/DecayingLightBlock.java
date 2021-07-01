package jlariv11.mysticintegration.blocks;

import javafx.beans.property.StringProperty;
import net.minecraft.block.BlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.model.IBakedModel;
import net.minecraft.state.EnumProperty;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockReader;

public class DecayingLightBlock extends BaseBlock{

    private int light = 0;

    public void setLight(int light){
        this.light = light;
    }

    public void decayLight(int amount){
        this.light -= amount;
    }

    @Override
    public int getLightValue(BlockState state, IBlockReader world, BlockPos pos) {
        return light;
    }

    

}
