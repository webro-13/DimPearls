/*
 *    MCreator note: This file will be REGENERATED on each build.
 */
package com.rocco.dimpearls.init;

import net.minecraftforge.registries.RegistryObject;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.common.ForgeSpawnEggItem;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.BlockItem;

import com.rocco.dimpearls.item.*;
import com.rocco.dimpearls.DimpearlsMod;

public class DimpearlsModItems {
	public static final DeferredRegister<Item> REGISTRY = DeferredRegister.create(ForgeRegistries.ITEMS, DimpearlsMod.MODID);
	public static final RegistryObject<Item> DIRT_PEARL;
	public static final RegistryObject<Item> NETHER_PEARL;
	public static final RegistryObject<Item> END_PEARL;
	public static final RegistryObject<Item> BETWEENSTONE;
	public static final RegistryObject<Item> VOID_PEARL;
	public static final RegistryObject<Item> BANISH_HAMMER_NETHER;
	public static final RegistryObject<Item> BANISH_HAMMER_END;
	public static final RegistryObject<Item> BANISH_HAMMER_DIRT;
	public static final RegistryObject<Item> DECAYED_ENDERMAN_SPAWN_EGG;
	public static final RegistryObject<Item> BANISH_HAMMER_VOID;
	public static final RegistryObject<Item> HEROBRINE_SPAWN_EGG;
	public static final RegistryObject<Item> BETWEENCRYSTAL;
	static {
		DIRT_PEARL = REGISTRY.register("dirt_pearl", DirtPearlItem::new);
		NETHER_PEARL = REGISTRY.register("nether_pearl", NetherPearlItem::new);
		END_PEARL = REGISTRY.register("end_pearl", EndPearlItem::new);
		BETWEENSTONE = block(DimpearlsModBlocks.BETWEENSTONE, new Item.Properties().rarity(Rarity.UNCOMMON));
		VOID_PEARL = REGISTRY.register("void_pearl", VoidPearlItem::new);
		BANISH_HAMMER_NETHER = REGISTRY.register("banish_hammer_nether", BanishHammerNetherItem::new);
		BANISH_HAMMER_END = REGISTRY.register("banish_hammer_end", BanishHammerEndItem::new);
		BANISH_HAMMER_DIRT = REGISTRY.register("banish_hammer_dirt", BanishHammerDirtItem::new);
		DECAYED_ENDERMAN_SPAWN_EGG = REGISTRY.register("decayed_enderman_spawn_egg", () -> new ForgeSpawnEggItem(DimpearlsModEntities.DECAYED_ENDERMAN, -1, -1, new Item.Properties()));
		BANISH_HAMMER_VOID = REGISTRY.register("banish_hammer_void", BanishHammerVoidItem::new);
		HEROBRINE_SPAWN_EGG = REGISTRY.register("herobrine_spawn_egg", () -> new ForgeSpawnEggItem(DimpearlsModEntities.HEROBRINE, -1, -1, new Item.Properties()));
		BETWEENCRYSTAL = block(DimpearlsModBlocks.BETWEENCRYSTAL, new Item.Properties().rarity(Rarity.EPIC));
	}

	// Start of user code block custom items
	// End of user code block custom items
	private static RegistryObject<Item> block(RegistryObject<Block> block) {
		return block(block, new Item.Properties());
	}

	private static RegistryObject<Item> block(RegistryObject<Block> block, Item.Properties properties) {
		return REGISTRY.register(block.getId().getPath(), () -> new BlockItem(block.get(), properties));
	}
}