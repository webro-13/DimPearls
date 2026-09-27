package com.rocco.dimpearls.item;

import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Item;
import net.minecraft.world.entity.LivingEntity;

import com.rocco.dimpearls.procedures.BanishHammerEndAttackProcedure;
import com.rocco.dimpearls.init.DimpearlsModItems;

public class BanishHammerEndItem extends Item {
	public BanishHammerEndItem() {
		super(new Item.Properties().durability(200).rarity(Rarity.EPIC));
	}

	@Override
	public boolean isValidRepairItem(ItemStack itemstack, ItemStack repairitem) {
		return Ingredient.of(new ItemStack(Blocks.AIR), new ItemStack(DimpearlsModItems.BANISH_HAMMER_END.get()), new ItemStack(DimpearlsModItems.END_PEARL.get())).test(repairitem);
	}

	@Override
	public boolean hurtEnemy(ItemStack itemstack, LivingEntity entity, LivingEntity sourceentity) {
		boolean retval = super.hurtEnemy(itemstack, entity, sourceentity);
		BanishHammerEndAttackProcedure.execute(entity, itemstack);
		return retval;
	}
}