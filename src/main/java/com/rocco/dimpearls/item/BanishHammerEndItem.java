package com.rocco.dimpearls.item;

import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Item;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.tags.TagKey;
import net.minecraft.resources.Identifier;
import net.minecraft.core.registries.Registries;

import com.rocco.dimpearls.procedures.BanishHammerEndAttackProcedure;

public class BanishHammerEndItem extends Item {
	public BanishHammerEndItem(Item.Properties properties) {
		super(properties.rarity(Rarity.EPIC).durability(200).repairable(TagKey.create(Registries.ITEM, Identifier.parse("dimpearls:banish_hammer_end_repair_items"))));
	}

	@Override
	public void hurtEnemy(ItemStack itemstack, LivingEntity entity, LivingEntity sourceentity) {
		super.hurtEnemy(itemstack, entity, sourceentity);
		BanishHammerEndAttackProcedure.execute(entity.level(), entity, itemstack);
	}
}