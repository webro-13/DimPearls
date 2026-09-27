package com.rocco.dimpearls.item;

import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Item;
import net.minecraft.world.entity.LivingEntity;

import com.rocco.dimpearls.procedures.BanishHammerVoidAttackProcedure;

public class BanishHammerVoidItem extends Item {
	public BanishHammerVoidItem() {
		super(new Item.Properties().durability(175).rarity(Rarity.EPIC));
	}

	@Override
	public boolean hurtEnemy(ItemStack itemstack, LivingEntity entity, LivingEntity sourceentity) {
		boolean retval = super.hurtEnemy(itemstack, entity, sourceentity);
		BanishHammerVoidAttackProcedure.execute(entity, itemstack);
		return retval;
	}
}