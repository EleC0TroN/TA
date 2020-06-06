package com.electron.taigaexpansion;

import com.electron.taigaexpansion.blocks.FuelItemBlock;
import com.electron.taigaexpansion.items.TeaBowlItem;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.SpawnEggItem;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ModItems {
    public static final DeferredRegister<Item> ITEMS = new DeferredRegister<>(ForgeRegistries.ITEMS, TaigaExpansion.MODID);

    public static final RegistryObject<Item> SPRUCE_CONE = ITEMS.register("cone", () -> new Item(new Item.Properties().group(ItemGroup.MATERIALS)));
    public static final RegistryObject<Item> FUR = ITEMS.register("fur", () -> new Item(new Item.Properties().group(ItemGroup.MATERIALS)));
    public static final RegistryObject<Item> BEAR_EGG = ITEMS.register("bear_egg", () -> new SpawnEggItem(ModMobs.BROWN_BEAR, 7555121, 10051392, (new Item.Properties()).group(ItemGroup.MISC)));
    public static final RegistryObject<Item> DRIED_HERBS = ITEMS.register("dried_herbs", () -> new Item(new Item.Properties().group(ItemGroup.BREWING)));
    public static final RegistryObject<Item> TEA_BOWL = ITEMS.register("tea_bowl", () -> new TeaBowlItem(new Item.Properties().group(ItemGroup.BREWING).maxStackSize(1)));

    public static final RegistryObject<BlockItem> BERGENIA = ITEMS.register("bergenia", () -> new BlockItem(ModBlocks.BERGENIA.get(), new Item.Properties().group(ItemGroup.DECORATIONS)));
    public static final RegistryObject<BlockItem> GREEN_LICHEN = ITEMS.register("grey_lichen", () -> new BlockItem(ModBlocks.GREEN_LICHEN.get(), new Item.Properties().group(ItemGroup.DECORATIONS)));
    public static final RegistryObject<BlockItem> GREY_LICHEN = ITEMS.register("green_lichen", () -> new BlockItem(ModBlocks.GREY_LICHEN.get(), new Item.Properties().group(ItemGroup.DECORATIONS)));
    public static final RegistryObject<BlockItem> CONES_BUNCH = ITEMS.register("cones_bunch", () -> new FuelItemBlock(ModBlocks.CONES_BUNCH.get(), new Item.Properties().group(ItemGroup.BUILDING_BLOCKS)));
    public static final RegistryObject<BlockItem> MINI_CAULDRON = ITEMS.register("tea_pot", () -> new BlockItem(ModBlocks.TEA_POT.get(), new Item.Properties().group(ItemGroup.DECORATIONS)));
    public static final RegistryObject<BlockItem> ROTTEN_LOG = ITEMS.register("rotten_log", () -> new FuelItemBlock(ModBlocks.ROTTEN_LOG.get(), new Item.Properties().group(ItemGroup.BUILDING_BLOCKS)));

}
