package com.rocco.dimpearls.item;

import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Item;
import net.minecraft.world.entity.LivingEntity;

import com.rocco.dimpearls.procedures.BanishHammerDirtAttackProcedure;
import com.rocco.dimpearls.init.DimpearlsModItems;

public class BanishHammerDirtItem extends Item {
	public BanishHammerDirtItem() {
		super(new Item.Properties().durability(275).rarity(Rarity.EPIC));
	}

	@Override
	public boolean isValidRepairItem(ItemStack itemstack, ItemStack repairitem) {
		return Ingredient.of(new ItemStack(Blocks.AIR), new ItemStack(DimpearlsModItems.DIRT_PEARL.get()), new ItemStack(DimpearlsModItems.BANISH_HAMMER_DIRT.get())).test(repairitem);
	}

	@Override
	public boolean hurtEnemy(ItemStack itemstack, LivingEntity entity, LivingEntity sourceentity) {
		boolean retval = super.hurtEnemy(itemstack, entity, sourceentity);
		BanishHammerDirtAttackProcedure.execute(entity, itemstack);
		return retval;
	}
}