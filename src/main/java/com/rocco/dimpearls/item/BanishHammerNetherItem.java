package com.rocco.dimpearls.item;

import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Item;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.tags.TagKey;
import net.minecraft.resources.Identifier;
import net.minecraft.core.registries.Registries;

import com.rocco.dimpearls.procedures.BanishHammerNetherAttackProcedure;

public class BanishHammerNetherItem extends Item {
	public BanishHammerNetherItem(Item.Properties properties) {
		super(properties.rarity(Rarity.EPIC).durability(275).repairable(TagKey.create(Registries.ITEM, Identifier.parse("dimpearls:banish_hammer_nether_repair_items"))));
	}

	@Override
	public void hurtEnemy(ItemStack itemstack, LivingEntity entity, LivingEntity sourceentity) {
		super.hurtEnemy(itemstack, entity, sourceentity);
		BanishHammerNetherAttackProcedure.execute(entity.level(), entity, itemstack);
	}
}