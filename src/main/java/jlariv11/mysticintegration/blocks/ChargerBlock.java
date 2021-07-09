package jlariv11.mysticintegration.blocks;

import jlariv11.mysticintegration.blocks.tiles.ChargerTile;
import jlariv11.mysticintegration.magic.EnumMagicType;
import jlariv11.mysticintegration.registry.ItemRegistry;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.state.BooleanProperty;
import net.minecraft.state.EnumProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import net.minecraftforge.common.Tags;
import org.jline.utils.Log;

import javax.annotation.Nullable;

import static jlariv11.mysticintegration.MysticIntegration.MAGIC;

public class ChargerBlock extends TestPowerBlock{

    //Dark type will charge items with dark affinity
    //Light type will charge items with light affinity

    public ChargerBlock() {
        this.registerDefaultState(this.defaultBlockState().setValue(MAGIC, EnumMagicType.NONE));
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

        return ActionResultType.PASS;
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
        return getType(state) != EnumMagicType.NONE ? new ChargerTile() : null;
    }

    @Override
    protected void createBlockStateDefinition(StateContainer.Builder<Block, BlockState> stateBuilder) {
        stateBuilder.add(MAGIC);
    }
}
