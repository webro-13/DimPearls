package com.rocco.dimpearls.item;

import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Item;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.tags.TagKey;
import net.minecraft.resources.Identifier;
import net.minecraft.core.registries.Registries;

import com.rocco.dimpearls.procedures.BanishHammerDirtAttackProcedure;

public class BanishHammerDirtItem extends Item {
	public BanishHammerDirtItem(Item.Properties properties) {
		super(properties.rarity(Rarity.EPIC).durability(275).repairable(TagKey.create(Registries.ITEM, Identifier.parse("dimpearls:banish_hammer_dirt_repair_items"))));
	}

	@Override
	public void hurtEnemy(ItemStack itemstack, LivingEntity entity, LivingEntity sourceentity) {
		super.hurtEnemy(itemstack, entity, sourceentity);
		BanishHammerDirtAttackProcedure.execute(entity.level(), entity, itemstack);
	}
}