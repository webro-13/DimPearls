/*
 *    MCreator note: This file will be REGENERATED on each build.
 */
package com.rocco.dimpearls.init;

import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredHolder;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.item.SpawnEggItem;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.BlockItem;

import java.util.function.Function;

import com.rocco.dimpearls.item.*;
import com.rocco.dimpearls.DimpearlsMod;

public class DimpearlsModItems {
	public static final DeferredRegister.Items REGISTRY = DeferredRegister.createItems(DimpearlsMod.MODID);
	public static final DeferredItem<Item> DIRT_PEARL;
	public static final DeferredItem<Item> NETHER_PEARL;
	public static final DeferredItem<Item> END_PEARL;
	public static final DeferredItem<Item> BETWEENSTONE;
	public static final DeferredItem<Item> BETWEEN_CRYSTAL;
	public static final DeferredItem<Item> VOID_PEARL;
	public static final DeferredItem<Item> BANISH_HAMMER_NETHER;
	public static final DeferredItem<Item> BANISH_HAMMER_END;
	public static final DeferredItem<Item> BANISH_HAMMER_DIRT;
	public static final DeferredItem<Item> DECAYED_ENDERMAN_SPAWN_EGG;
	public static final DeferredItem<Item> BANISH_HAMMER_VOID;
	public static final DeferredItem<Item> HEROBRINE_SPAWN_EGG;
	static {
		DIRT_PEARL = register("dirt_pearl", DirtPearlItem::new);
		NETHER_PEARL = register("nether_pearl", NetherPearlItem::new);
		END_PEARL = register("end_pearl", EndPearlItem::new);
		BETWEENSTONE = block(DimpearlsModBlocks.BETWEENSTONE, new Item.Properties().rarity(Rarity.UNCOMMON));
		BETWEEN_CRYSTAL = block(DimpearlsModBlocks.BETWEEN_CRYSTAL, new Item.Properties().rarity(Rarity.EPIC));
		VOID_PEARL = register("void_pearl", VoidPearlItem::new);
		BANISH_HAMMER_NETHER = register("banish_hammer_nether", BanishHammerNetherItem::new);
		BANISH_HAMMER_END = register("banish_hammer_end", BanishHammerEndItem::new);
		BANISH_HAMMER_DIRT = register("banish_hammer_dirt", BanishHammerDirtItem::new);
		DECAYED_ENDERMAN_SPAWN_EGG = register("decayed_enderman_spawn_egg", properties -> new SpawnEggItem(properties.spawnEgg(DimpearlsModEntities.DECAYED_ENDERMAN.get())));
		BANISH_HAMMER_VOID = register("banish_hammer_void", BanishHammerVoidItem::new);
		HEROBRINE_SPAWN_EGG = register("herobrine_spawn_egg", properties -> new SpawnEggItem(properties.spawnEgg(DimpearlsModEntities.HEROBRINE.get())));
	}

	// Start of user code block custom items
	// End of user code block custom items
	private static <I extends Item> DeferredItem<I> register(String name, Function<Item.Properties, ? extends I> supplier) {
		return REGISTRY.registerItem(name, supplier, Item.Properties::new);
	}

	private static DeferredItem<Item> block(DeferredHolder<Block, Block> block) {
		return block(block, new Item.Properties());
	}

	private static DeferredItem<Item> block(DeferredHolder<Block, Block> block, Item.Properties properties) {
		return REGISTRY.registerItem(block.getId().getPath(), prop -> new BlockItem(block.get(), prop), () -> properties);
	}
}