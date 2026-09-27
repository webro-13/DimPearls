package com.rocco.dimpearls.item;

import net.minecraft.world.level.Level;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Item;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.InteractionHand;

import com.rocco.dimpearls.procedures.VoidPearlCheckTpProcedure;

public class VoidPearlItem extends Item {
	public VoidPearlItem() {
		super(new Item.Properties().durability(8).rarity(Rarity.EPIC));
	}

	@Override
	public InteractionResultHolder<ItemStack> use(Level world, Player entity, InteractionHand hand) {
		InteractionResultHolder<ItemStack> ar = super.use(world, entity, hand);
		VoidPearlCheckTpProcedure.execute(world, entity, ar.getObject());
		return ar;
	}
}