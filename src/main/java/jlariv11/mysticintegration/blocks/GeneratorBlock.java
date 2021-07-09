package jlariv11.mysticintegration.blocks;

import jlariv11.mysticintegration.blocks.tiles.ChargerTile;
import jlariv11.mysticintegration.blocks.tiles.GeneratorTile;
import jlariv11.mysticintegration.magic.EnumMagicType;
import jlariv11.mysticintegration.registry.ItemRegistry;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.item.ItemStack;
import net.minecraft.state.StateContainer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;

import javax.annotation.Nullable;

import static jlariv11.mysticintegration.MysticIntegration.MAGIC;
import static net.minecraft.block.HorizontalBlock.FACING;

public class GeneratorBlock extends TestPowerBlock{

    public GeneratorBlock() {
        this.registerDefaultState(this.defaultBlockState().setValue(MAGIC, EnumMagicType.NONE).setValue(FACING, Direction.NORTH));
    }

    @Override
    public ActionResultType use(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockRayTraceResult hitRay) {

        ItemStack stack = player.getItemInHand(hand);
        if(stack.getItem() == ItemRegistry.MAGIC_CRYSTAL_LIGHT.get()){
            player.getItemInHand(hand).shrink(1);
            world.setBlock(pos, state.setValue(MAGIC, EnumMagicType.LIGHT), 2);
        }else if(stack.getItem() == ItemRegistry.MAGIC_CRYSTAL_DARK.get()){
            player.getItemInHand(hand).shrink(1);
            world.setBlock(pos, state.setValue(MAGIC, EnumMagicType.DARK), 2);
        }

        return ActionResultType.SUCCESS;
    }

    public EnumMagicType getType(BlockState state) {
        return state.getValue(MAGIC);
    }

    @Override
    public boolean hasTileEntity(BlockState state) {
        return getType(state) != EnumMagicType.NONE;
    }

    @Nullable
    @Override
    public TileEntity createTileEntity(BlockState state, IBlockReader world) {
        return getType(state) != EnumMagicType.NONE ? new GeneratorTile() : null;
    }

    @Override
    public BlockState rotate(BlockState state, Rotation rotation) {
        return state.setValue(FACING, rotation.rotate(state.getValue(FACING)));
    }

    @Override
    public BlockState mirror(BlockState state, Mirror mirror) {
        return state.rotate(mirror.getRotation(state.getValue(FACING)));
    }

    @Override
    public BlockState getStateForPlacement(BlockItemUseContext ctx) {
        return this.defaultBlockState().setValue(FACING, ctx.getHorizontalDirection().getOpposite());
    }

    @Override
    protected void createBlockStateDefinition(StateContainer.Builder<Block, BlockState> stateBuilder) {
        stateBuilder.add(MAGIC, FACING);
    }
}
