package com.rocco.dimpearls.item;

import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Item;
import net.minecraft.world.entity.LivingEntity;

import com.rocco.dimpearls.procedures.BanishHammerVoidAttackProcedure;

public class BanishHammerVoidItem extends Item {
	public BanishHammerVoidItem(Item.Properties properties) {
		super(properties.rarity(Rarity.EPIC).durability(175));
	}

	@Override
	public void hurtEnemy(ItemStack itemstack, LivingEntity entity, LivingEntity sourceentity) {
		super.hurtEnemy(itemstack, entity, sourceentity);
		BanishHammerVoidAttackProcedure.execute(entity.level(), entity, itemstack);
	}
}