package com.rocco.dimpearls.block;

import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.Block;

public class BetweenstoneBlock extends Block {
	public BetweenstoneBlock(BlockBehaviour.Properties properties) {
		super(properties.strength(5f, 10f).requiresCorrectToolForDrops());
	}
}