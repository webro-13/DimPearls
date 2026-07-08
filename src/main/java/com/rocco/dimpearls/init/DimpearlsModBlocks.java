/*
 *    MCreator note: This file will be REGENERATED on each build.
 */
package com.rocco.dimpearls.init;

import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.DeferredBlock;

import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.Block;

import java.util.function.Function;

import com.rocco.dimpearls.block.BetweenstoneBlock;
import com.rocco.dimpearls.block.BetweencrystalBlock;
import com.rocco.dimpearls.DimpearlsMod;

public class DimpearlsModBlocks {
	public static final DeferredRegister.Blocks REGISTRY = DeferredRegister.createBlocks(DimpearlsMod.MODID);
	public static final DeferredBlock<Block> BETWEENSTONE;
	public static final DeferredBlock<Block> BETWEENCRYSTAL;
	static {
		BETWEENSTONE = register("betweenstone", BetweenstoneBlock::new);
		BETWEENCRYSTAL = register("betweencrystal", BetweencrystalBlock::new);
	}

	// Start of user code block custom blocks
	// End of user code block custom blocks
	private static <B extends Block> DeferredBlock<B> register(String name, Function<BlockBehaviour.Properties, ? extends B> supplier) {
		return REGISTRY.registerBlock(name, supplier);
	}
}