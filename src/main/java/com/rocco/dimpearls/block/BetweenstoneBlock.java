package com.rocco.dimpearls.block;

import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.Block;

public class BetweenstoneBlock extends Block {
	public BetweenstoneBlock() {
		super(BlockBehaviour.Properties.of().strength(5f, 10f).requiresCorrectToolForDrops());
	}
}