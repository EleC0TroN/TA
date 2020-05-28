package com.electron.taigaexpansion;

import com.electron.taigaexpansion.blocks.LichenBlock;
import net.minecraft.block.Block;
import net.minecraft.block.FlowerBlock;
import net.minecraft.block.LogBlock;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialColor;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.RenderTypeLookup;
import net.minecraft.potion.Effects;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ModBlocks {
    public static final DeferredRegister<Block> BLOCKS = new DeferredRegister<>(ForgeRegistries.BLOCKS, TaigaExpansion.MODID);

    public static final RegistryObject<Block> BERGENIA = BLOCKS.register("bergenia", () -> new FlowerBlock(Effects.LEVITATION, 2, Block.Properties.create(Material.PLANTS, MaterialColor.MAGENTA).doesNotBlockMovement()));
    public static final RegistryObject<Block> GREY_LICHEN = BLOCKS.register("grey_lichen", () -> new LichenBlock(Block.Properties.create(Material.PLANTS, MaterialColor.MAGENTA).doesNotBlockMovement()));
    public static final RegistryObject<Block> GREEN_LICHEN = BLOCKS.register("green_lichen", () -> new LichenBlock(Block.Properties.create(Material.PLANTS, MaterialColor.MAGENTA).doesNotBlockMovement()));
    public static final RegistryObject<Block> CONES_BUNCH = BLOCKS.register("cones_bunch", () -> new Block(Block.Properties.create(Material.WOOD, MaterialColor.BROWN).hardnessAndResistance(2.0F)));
    public static final RegistryObject<Block> MINI_CAULDRON = BLOCKS.register("mini_cauldron", () -> new Block(Block.Properties.create(Material.ROCK, MaterialColor.BROWN).hardnessAndResistance(2.0F).notSolid()));
    public static final RegistryObject<Block> ROTTEN_LOG = BLOCKS.register("rotten_log", () -> new LogBlock(MaterialColor.ADOBE, Block.Properties.create(Material.WOOD, MaterialColor.GREEN_TERRACOTTA).hardnessAndResistance(2.0F)));

    public static void initRender(FMLClientSetupEvent event) {
        renderCutout(BERGENIA.get());
        renderCutout(GREY_LICHEN.get());
        renderCutout(GREEN_LICHEN.get());
        renderCutout(MINI_CAULDRON.get());
    }
    private static void renderCutout(Block block) {
        RenderTypeLookup.setRenderLayer(block, RenderType.getCutout());
    }
}
