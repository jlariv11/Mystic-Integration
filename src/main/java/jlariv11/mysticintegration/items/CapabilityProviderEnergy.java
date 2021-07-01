package jlariv11.mysticintegration.items;

import net.minecraft.util.Direction;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.energy.CapabilityEnergy;
import net.minecraftforge.energy.EnergyStorage;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class CapabilityProviderEnergy implements ICapabilityProvider {


    LazyOptional<EnergyStorage> energyStorageCapability;

    public CapabilityProviderEnergy(int capacity, int maxReceive, int maxExtract) {
        energyStorageCapability = LazyOptional.of(() -> new EnergyStorage(capacity, maxReceive, maxExtract));
    }

    @Nonnull
    @Override
    public <T> LazyOptional<T> getCapability(@Nonnull Capability<T> cap, @Nullable Direction side) {

        if(cap == CapabilityEnergy.ENERGY){
            return energyStorageCapability.cast();
        }

        return null;
    }

    @Nonnull
    @Override
    public <T> LazyOptional<T> getCapability(@Nonnull Capability<T> cap) {
        return getCapability(cap, null);
    }
}
