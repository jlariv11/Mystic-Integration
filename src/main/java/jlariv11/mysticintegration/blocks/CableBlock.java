package jlariv11.mysticintegration.blocks;

import jlariv11.mysticintegration.blocks.tiles.CableTile;
import net.minecraft.block.BlockState;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.IBlockReader;

import javax.annotation.Nullable;

public class CableBlock extends TestPowerBlock{


    @Nullable
    @Override
    public TileEntity createTileEntity(BlockState state, IBlockReader world) {
        return new CableTile();
    }
}
