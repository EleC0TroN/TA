package com.electron.taigaexpansion.blocks;

import net.minecraft.block.Block;
import net.minecraft.item.BlockItem;
import net.minecraft.item.ItemStack;

public class FuelItemBlock extends BlockItem {
    public FuelItemBlock(Block blockIn, Properties builder) {
        super(blockIn, builder);
    }
    @Override
    public int getBurnTime(ItemStack itemStack) {
        return 500;
    }
}
