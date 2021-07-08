package jlariv11.mysticintegration.blocks;

import com.sun.org.apache.xpath.internal.operations.String;
import jlariv11.mysticintegration.blocks.tiles.GeneratorTile;
import net.minecraft.block.BlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Direction;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorldReader;
import net.minecraft.world.World;
import net.minecraftforge.energy.CapabilityEnergy;

import javax.annotation.Nullable;

public class TestPowerBlock extends BaseBlock{

    @Override
    public boolean hasTileEntity(BlockState state) {
        return true;
    }

    @Override
    public void stepOn(World world, BlockPos pos, Entity entity) {
        if(entity instanceof PlayerEntity) {
            PlayerEntity player = (PlayerEntity)entity;
            TileEntity tile = world.getBlockEntity(pos);
            if (tile != null) {
                tile.getCapability(CapabilityEnergy.ENERGY).ifPresent(energy -> {
                    if (world.isClientSide()) {
                        player.displayClientMessage(new StringTextComponent(this.getRegistryName().toString() +": " + energy.getEnergyStored() + "/" + energy.getMaxEnergyStored()), false);
                    }
                });
            }
        }
    }

}
