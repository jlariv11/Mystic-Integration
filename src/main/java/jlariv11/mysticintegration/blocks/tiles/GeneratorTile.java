package jlariv11.mysticintegration.blocks.tiles;

import jlariv11.mysticintegration.blocks.DecayingLightBlock;
import jlariv11.mysticintegration.magic.EnumMagicType;
import jlariv11.mysticintegration.registry.TileTypeRegistry;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.merchant.villager.VillagerEntity;
import net.minecraft.entity.monster.*;
import net.minecraft.entity.passive.PigEntity;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.DamageSource;
import net.minecraft.util.Direction;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.common.Tags;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.energy.CapabilityEnergy;
import net.minecraftforge.energy.EnergyStorage;
import net.minecraftforge.energy.IEnergyStorage;
import org.jline.utils.Log;
import org.lwjgl.system.CallbackI;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;

import static jlariv11.mysticintegration.MysticIntegration.MAGIC;

public class GeneratorTile extends TileEntity implements ITickableTileEntity {

    private int consumeTime;
    private final int CONSUME_TIME_MAX = 20;
    private boolean isRunning = false;
    private int energyPerTick = 20;

    LazyOptional<EnergyStorage> capabilityEnergyLazyOptional;

    public GeneratorTile() {
        super(TileTypeRegistry.GENERATOR_TILE.get());
        this.consumeTime = CONSUME_TIME_MAX;
        capabilityEnergyLazyOptional = LazyOptional.of(() -> new EnergyStorage(1000, Integer.MAX_VALUE, 20));
    }


    @Override
    protected void invalidateCaps() {
        super.invalidateCaps();
        capabilityEnergyLazyOptional.invalidate();
    }

    @Nonnull
    @Override
    public <T> LazyOptional<T> getCapability(@Nonnull Capability<T> cap, @Nullable Direction side) {

        if(cap == CapabilityEnergy.ENERGY){
            return capabilityEnergyLazyOptional.cast();
        }

        return super.getCapability(cap, side);
    }

    private List<BlockPos> getLightBlocks(){
        List<BlockPos> lightBlocks = new ArrayList<>();
        for(int x = 2; x > -3; x--){
            for(int y = 4; y > -1; y--){
                for(int z = 2; z > -3; z--){
                    BlockPos pos = this.getBlockPos().offset(x, y, z);
                    if(pos == this.getBlockPos())
                        continue;
                    int light = this.getLevel().getLightEmission(pos);
                    if(light > 0){
                        lightBlocks.add(pos);
                        if(!(this.getLevel().getBlockState(pos).getBlock() instanceof DecayingLightBlock)) {
                            DecayingLightBlock dlb = new DecayingLightBlock();
                            dlb.setLight(light);
                            this.getLevel().setBlock(pos, dlb.defaultBlockState(), 0);
                        }
                    }
                }
            }
        }


        return lightBlocks;
    }

    private List<MonsterEntity> findAllEvil(){
        AxisAlignedBB aabb = new AxisAlignedBB(this.getBlockPos().offset(5, 5, 5), this.getBlockPos().offset(-5, 0, -5));
        return this.getLevel().getEntitiesOfClass(MonsterEntity.class, aabb);
    }

    @Override
    public void tick() {
        if(this.getLevel() == null)
            return;
        consumeTime--;
        if(consumeTime <= 0) {
            if(getBlockState().getValue(MAGIC) == EnumMagicType.LIGHT) {
                List<BlockPos> lbs = getLightBlocks();
                this.energyPerTick = 20 * lbs.size();
                for (BlockPos lb : lbs) {
                    if(this.getLevel().getBlockState(lb).getBlock() instanceof DecayingLightBlock) {
                        DecayingLightBlock dlb = (DecayingLightBlock) this.getLevel().getBlockState(lb).getBlock();
                        dlb.decayLight(1);
                        this.getLevel().getBlockTicks().scheduleTick(lb, dlb, 4);
                    }
                }
            }else{
                List<MonsterEntity> evil = findAllEvil();
                this.energyPerTick *= evil.size();
                for(MonsterEntity e : evil){
                    if(e.getHealth() - 1 <= 0){
                        if(e instanceof CreeperEntity){
                            PigEntity pig = new PigEntity(EntityType.PIG, this.getLevel());
                            pig.setPos(e.position().x, e.position().y, e.position().z);
                            this.getLevel().addFreshEntity(pig);
                        }else if(e instanceof HuskEntity || e instanceof DrownedEntity) {
                            ZombieEntity zombie = new ZombieEntity(EntityType.ZOMBIE, this.getLevel());
                            zombie.setPos(e.position().x, e.position().y, e.position().z);
                            this.getLevel().addFreshEntity(zombie);
                        }else if(e instanceof ZombieVillagerEntity){
                            VillagerEntity villager = new VillagerEntity(EntityType.VILLAGER, this.getLevel());
                            villager.setVillagerData(((ZombieVillagerEntity) e).getVillagerData());
                            villager.setPos(e.position().x, e.position().y, e.position().z);
                            this.getLevel().addFreshEntity(villager);
                        }else if(e instanceof ZombieEntity){
                            VillagerEntity villager = new VillagerEntity(EntityType.VILLAGER, this.getLevel());
                            villager.setPos(e.position().x, e.position().y, e.position().z);
                            this.getLevel().addFreshEntity(villager);
                        }else if(e instanceof WitherSkeletonEntity){
                            SkeletonEntity skelly = new SkeletonEntity(EntityType.SKELETON, this.getLevel());
                            skelly.setPos(e.position().x, e.position().y, e.position().z);
                            this.getLevel().addFreshEntity(skelly);
                        }
                    }
                    e.hurt(DamageSource.GENERIC, 1);
                }
            }
            consumeTime = CONSUME_TIME_MAX;
        }

        getCapability(CapabilityEnergy.ENERGY, null).ifPresent(cap ->{
            cap.receiveEnergy(energyPerTick, false);

        });

        for(Direction dir : Direction.values()){
            BlockPos tilePos = this.getBlockPos().relative(dir);
            TileEntity energyTile = this.getLevel().getBlockEntity(tilePos);
            if(energyTile != null){
                energyTile.getCapability(CapabilityEnergy.ENERGY).ifPresent(tileEnergy ->{
                    this.getCapability(CapabilityEnergy.ENERGY).ifPresent(genEnergy ->{
                        if(tileEnergy.canReceive()){
                            genEnergy.extractEnergy(tileEnergy.receiveEnergy(20, false), false);
                        }
                    });
                });
            }
        }


    }
}
