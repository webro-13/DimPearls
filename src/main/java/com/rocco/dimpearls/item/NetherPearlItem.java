package com.rocco.dimpearls.item;

import net.minecraft.world.level.Level;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.ItemUseAnimation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Item;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.InteractionHand;
import net.minecraft.tags.TagKey;
import net.minecraft.resources.Identifier;
import net.minecraft.core.registries.Registries;

import com.rocco.dimpearls.procedures.NetherPearlCheckTpProcedure;

public class NetherPearlItem extends Item {
	public NetherPearlItem(Item.Properties properties) {
		super(properties.rarity(Rarity.EPIC).durability(32).repairable(TagKey.create(Registries.ITEM, Identifier.parse("dimpearls:nether_pearl_repair_items"))));
	}

	@Override
	public ItemUseAnimation getUseAnimation(ItemStack itemstack) {
		return ItemUseAnimation.BLOCK;
	}

	@Override
	public InteractionResult use(Level world, Player entity, InteractionHand hand) {
		InteractionResult ar = super.use(world, entity, hand);
		NetherPearlCheckTpProcedure.execute(world, entity, entity.getItemInHand(hand));
		return ar;
	}
}