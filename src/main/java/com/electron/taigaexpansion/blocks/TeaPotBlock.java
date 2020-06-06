package com.electron.taigaexpansion.blocks;

import com.electron.taigaexpansion.ModItems;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.state.IntegerProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.stats.Stats;
import net.minecraft.util.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;

public class TeaPotBlock extends Block {
    public static final IntegerProperty LEVEL = BlockStateProperties.LEVEL_0_3;
    protected static final VoxelShape TEA_POT_AABB = Block.makeCuboidShape(3.0D, 0.0D, 3.0D, 13.0D, 10.0D, 13.0D);
    public TeaPotBlock(Properties properties) {
        super(properties);
    }
    //1 - water, 2 - tea
    public ActionResultType onBlockActivated(BlockState state, World worldIn, BlockPos pos, PlayerEntity player, Hand handIn, BlockRayTraceResult hit) {
        ItemStack itemstack = player.getHeldItem(handIn);
        if (itemstack.isEmpty()) {
            return ActionResultType.PASS;
        } else {
            int i = state.get(LEVEL);
            Item item = itemstack.getItem();
            if (i == 0 && item == Items.WATER_BUCKET) {
                if (!worldIn.isRemote) {
                    if (!player.abilities.isCreativeMode) {
                        player.setHeldItem(handIn, new ItemStack(Items.BUCKET));
                    }

                    player.addStat(Stats.FILL_CAULDRON);
                    worldIn.playSound((PlayerEntity) null, pos, SoundEvents.ITEM_BUCKET_EMPTY, SoundCategory.BLOCKS, 1.0F, 1.0F);
                }
                this.setLevel(worldIn, pos, state, 1);
                return ActionResultType.SUCCESS;
            } else if (i == 1 && item == Items.BUCKET) {
                if (!worldIn.isRemote) {
                    if (!player.abilities.isCreativeMode) {
                        itemstack.shrink(1);
                        if (itemstack.isEmpty()) {
                            player.setHeldItem(handIn, new ItemStack(Items.WATER_BUCKET));
                        } else if (!player.inventory.addItemStackToInventory(new ItemStack(Items.WATER_BUCKET))) {
                            player.dropItem(new ItemStack(Items.WATER_BUCKET), false);
                        }
                    }

                    player.addStat(Stats.USE_CAULDRON);

                    worldIn.playSound((PlayerEntity) null, pos, SoundEvents.ITEM_BUCKET_FILL, SoundCategory.BLOCKS, 1.0F, 1.0F);
                }
                this.setLevel(worldIn, pos, state, 0);
                return ActionResultType.SUCCESS;
            } else if (i == 1 && item == ModItems.DRIED_HERBS.get()) {
                if (!worldIn.isRemote) {
                    if (!player.abilities.isCreativeMode) {
                        itemstack.shrink(1);
                    }

                    player.addStat(Stats.USE_CAULDRON);

                    worldIn.playSound((PlayerEntity) null, pos, SoundEvents.ITEM_BUCKET_FILL, SoundCategory.BLOCKS, 1.0F, 1.0F);
                }
                this.setLevel(worldIn, pos, state, 2);
                return ActionResultType.SUCCESS;
            } else if (item == Items.BOWL) {
                if (i == 2 && !worldIn.isRemote) {
                    if (!player.abilities.isCreativeMode) {
                        itemstack.shrink(1);
                    }
                    if (itemstack.isEmpty()) {
                        player.setHeldItem(handIn, new ItemStack(ModItems.TEA_BOWL.get()));
                    } else if (!player.inventory.addItemStackToInventory(new ItemStack(ModItems.TEA_BOWL.get()))) {
                        player.dropItem(new ItemStack(ModItems.TEA_BOWL.get()), false);
                    }
                    player.addStat(Stats.USE_CAULDRON);

                    worldIn.playSound((PlayerEntity) null, pos, SoundEvents.ITEM_BUCKET_FILL, SoundCategory.BLOCKS, 1.0F, 1.0F);
                }
                this.setLevel(worldIn, pos, state, 0);
                return ActionResultType.SUCCESS;
            } else {
                return ActionResultType.PASS;
            }
        }
    }
    public void setLevel(World worldIn, BlockPos pos, BlockState state, int level) {
        worldIn.setBlockState(pos, state.with(LEVEL, Integer.valueOf(MathHelper.clamp(level, 0, 3))), 2);
        worldIn.updateComparatorOutputLevel(pos, this);
    }
    public boolean hasComparatorInputOverride(BlockState state) {
        return true;
    }

    public int getComparatorInputOverride(BlockState blockState, World worldIn, BlockPos pos) {
        return blockState.get(LEVEL);
    }

    protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder) {
        builder.add(LEVEL);
    }
    public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
                return TEA_POT_AABB;
    }
}
