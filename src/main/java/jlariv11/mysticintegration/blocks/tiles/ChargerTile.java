package jlariv11.mysticintegration.blocks.tiles;

import jlariv11.mysticintegration.MysticIntegration;
import jlariv11.mysticintegration.blocks.ChargerBlock;
import jlariv11.mysticintegration.items.MagicCrystalItem;
import jlariv11.mysticintegration.registry.TileTypeRegistry;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.Direction;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.energy.CapabilityEnergy;
import net.minecraftforge.energy.EnergyStorage;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemStackHandler;
import net.minecraftforge.items.wrapper.InvWrapper;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class ChargerTile extends TileEntity implements ITickableTileEntity {

    LazyOptional<IItemHandler> itemHandlerCapability;
    LazyOptional<EnergyStorage> energyStorageCapability;

    private final int rfCrystalConsume = 500;
    private int rfCrystalConsumeCount;

    private int maxReceive;
    private int maxExtract;

    public ChargerTile() {
        super(TileTypeRegistry.CHARGER_TILE.get());
        this.maxExtract = 50;
        this.maxReceive = 50;
        itemHandlerCapability = LazyOptional.of(() -> new ItemStackHandler(2));
        energyStorageCapability = LazyOptional.of(() -> new EnergyStorage(5000, maxReceive, maxExtract));
        this.rfCrystalConsumeCount = rfCrystalConsume;
    }

    @Nonnull
    @Override
    public <T> LazyOptional<T> getCapability(@Nonnull Capability<T> cap, @Nullable Direction side) {

        if(cap == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY){
            return itemHandlerCapability.cast();
        }
        if(cap == CapabilityEnergy.ENERGY){
            return energyStorageCapability.cast();
        }

        return null;
    }

    @Override
    protected void invalidateCaps() {
        super.invalidateCaps();
        itemHandlerCapability.invalidate();
        energyStorageCapability.invalidate();

    }

    @Override
    public void tick() {
        getCapability(CapabilityEnergy.ENERGY).ifPresent(iEnergyStorage -> {

            if(!iEnergyStorage.canExtract())
                return;

            getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY).ifPresent(storage ->{
                ItemStack chargable = storage.getStackInSlot(0);
                ItemStack crystal = storage.getStackInSlot(1);

                if(crystal.getItem().getItem() instanceof MagicCrystalItem){
                    MagicCrystalItem crystalItem = (MagicCrystalItem) crystal.getItem().getItem();

                    if(crystalItem.getType() == this.getBlockState().getValue(MysticIntegration.MAGIC)){
                        chargable.getCapability(CapabilityEnergy.ENERGY).ifPresent(itemEnergy ->{

                            if(!itemEnergy.canReceive())
                                return;

                            rfCrystalConsumeCount -= itemEnergy.receiveEnergy(iEnergyStorage.extractEnergy(maxExtract, false), false);
                            if(rfCrystalConsumeCount <= 0){
                                crystal.shrink(1);
                            }
                        });
                    }
                }

            });
        });
    }
}
