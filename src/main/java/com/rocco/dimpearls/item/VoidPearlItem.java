package com.rocco.dimpearls.item;

import net.minecraft.world.level.Level;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.Item;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.InteractionHand;

import com.rocco.dimpearls.procedures.VoidPearlCheckTpProcedure;

public class VoidPearlItem extends Item {
	public VoidPearlItem(Item.Properties properties) {
		super(properties.rarity(Rarity.EPIC).durability(8));
	}

	@Override
	public InteractionResult use(Level world, Player entity, InteractionHand hand) {
		InteractionResult ar = super.use(world, entity, hand);
		VoidPearlCheckTpProcedure.execute(world, entity, entity.getItemInHand(hand));
		return ar;
	}
}