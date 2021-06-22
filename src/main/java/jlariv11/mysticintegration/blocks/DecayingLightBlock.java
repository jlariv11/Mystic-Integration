package jlariv11.mysticintegration.blocks;

import net.minecraft.block.BlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.model.IBakedModel;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockReader;

public class DecayingLightBlock extends BaseBlock{

    private int light = 0;

    public void setLight(int light){
        this.light = light;
    }



    @Override
    public int getLightValue(BlockState state, IBlockReader world, BlockPos pos) {
        return light;
    }

    private void setTexture(BlockState state){
        IBakedModel baseModel = Minecraft.getInstance().getBlockRenderer().getBlockModel(state);
    }


}
