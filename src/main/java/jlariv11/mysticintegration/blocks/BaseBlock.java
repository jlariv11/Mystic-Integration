package jlariv11.mysticintegration.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraftforge.common.ToolType;

public class BaseBlock extends Block {

    public BaseBlock() {
        super(Properties.of(Material.STONE).harvestTool(ToolType.PICKAXE).harvestLevel(1));
    }



}
