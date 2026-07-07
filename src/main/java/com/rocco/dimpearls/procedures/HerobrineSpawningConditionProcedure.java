package com.rocco.dimpearls.procedures;

import net.minecraft.world.level.LevelAccessor;

import com.rocco.dimpearls.network.DimpearlsModVariables;

public class HerobrineSpawningConditionProcedure {
	public static boolean execute(LevelAccessor world) {
		boolean canspawn = false;
		if (DimpearlsModVariables.MapVariables.get(world).herobrinespawned) {
			canspawn = false;
		} else {
			canspawn = true;
		}
		return canspawn;
	}
}