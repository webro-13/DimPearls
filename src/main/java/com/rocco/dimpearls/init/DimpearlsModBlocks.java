/*
 *    MCreator note: This file will be REGENERATED on each build.
 */
package com.rocco.dimpearls.init;

import net.minecraftforge.registries.RegistryObject;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.DeferredRegister;

import net.minecraft.world.level.block.Block;

import com.rocco.dimpearls.block.BetweenstoneBlock;
import com.rocco.dimpearls.block.BetweencrystalBlock;
import com.rocco.dimpearls.DimpearlsMod;

public class DimpearlsModBlocks {
	public static final DeferredRegister<Block> REGISTRY = DeferredRegister.create(ForgeRegistries.BLOCKS, DimpearlsMod.MODID);
	public static final RegistryObject<Block> BETWEENSTONE;
	public static final RegistryObject<Block> BETWEENCRYSTAL;
	static {
		BETWEENSTONE = REGISTRY.register("betweenstone", BetweenstoneBlock::new);
		BETWEENCRYSTAL = REGISTRY.register("betweencrystal", BetweencrystalBlock::new);
	}
	// Start of user code block custom blocks
	// End of user code block custom blocks
}