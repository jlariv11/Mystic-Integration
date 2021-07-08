package jlariv11.mysticintegration.blocks;

import jlariv11.mysticintegration.blocks.tiles.GeneratorTile;
import jlariv11.mysticintegration.magic.EnumMagicType;
import net.minecraft.block.BlockState;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.IBlockReader;

import javax.annotation.Nullable;

public class GeneratorBlock extends TestPowerBlock{

    private EnumMagicType type;

    public GeneratorBlock(EnumMagicType type) {
        this.type = type;
    }

    @Nullable
    @Override
    public TileEntity createTileEntity(BlockState state, IBlockReader world) {
        GeneratorTile tile = new GeneratorTile();
        tile.setMagicType(type);
        return tile;
    }
}
