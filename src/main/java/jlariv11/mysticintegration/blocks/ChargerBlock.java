package jlariv11.mysticintegration.blocks;

import jlariv11.mysticintegration.blocks.tiles.ChargerTile;
import jlariv11.mysticintegration.magic.EnumMagicType;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import net.minecraftforge.common.Tags;

import javax.annotation.Nullable;

public class ChargerBlock extends BaseBlock{

    //Dark type will charge items with dark affinity
    //Light type will charge items with light affinity
    private EnumMagicType type = null;


    @Override
    public ActionResultType use(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockRayTraceResult hitRay) {

        ItemStack stack = player.getItemInHand(hand);

        if(stack.getItem() == Items.DIAMOND){
            player.getItemInHand(hand).shrink(1);
            type = EnumMagicType.LIGHT;
        }else if(stack.getItem() == Blocks.OBSIDIAN.asItem()){
            player.getItemInHand(hand).shrink(1);
            type = EnumMagicType.DARK;
        }

        return ActionResultType.PASS;
    }

    public EnumMagicType getType() {
        return type;
    }

    @Override
    public boolean hasTileEntity(BlockState state) {
        return type != null;
    }

    @Nullable
    @Override
    public TileEntity createTileEntity(BlockState state, IBlockReader world) {
        return type != null ? new ChargerTile() : null;
    }
}
