package jlariv11.mysticintegration.blocks.tiles;

import jlariv11.mysticintegration.registry.TileTypeRegistry;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.energy.CapabilityEnergy;
import net.minecraftforge.energy.EnergyStorage;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class CableTile extends TileEntity implements ITickableTileEntity {

    LazyOptional<EnergyStorage> energyCapability;

    public CableTile() {
        super(TileTypeRegistry.CABLE_TILE.get());
        energyCapability = LazyOptional.of(() -> new EnergyStorage(1000, 250, 250));
    }


    @Override
    protected void invalidateCaps() {
        super.invalidateCaps();
        energyCapability.invalidate();
    }

    @Nonnull
    @Override
    public <T> LazyOptional<T> getCapability(@Nonnull Capability<T> cap, @Nullable Direction side) {

        if(cap == CapabilityEnergy.ENERGY){
            return energyCapability.cast();
        }

        return super.getCapability(cap, side);
    }

    @Override
    public void tick() {
        if(this.getLevel() == null)
            return;

        for(Direction dir : Direction.values()){
            BlockPos tilePos = this.getBlockPos().relative(dir);
            TileEntity energyTile = this.getLevel().getBlockEntity(tilePos);
            if(energyTile != null){
                energyTile.getCapability(CapabilityEnergy.ENERGY).ifPresent(tileEnergy ->{
                    this.getCapability(CapabilityEnergy.ENERGY).ifPresent(cableEnergy ->{
                        if(tileEnergy.canReceive()){
                            cableEnergy.extractEnergy(tileEnergy.receiveEnergy(250, false), false);
                        }
                    });
                });
            }
        }
    }
}
