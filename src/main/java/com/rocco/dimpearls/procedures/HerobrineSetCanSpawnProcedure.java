package com.rocco.dimpearls.procedures;

import net.minecraft.world.level.LevelAccessor;

import com.rocco.dimpearls.network.DimpearlsModVariables;

public class HerobrineSetCanSpawnProcedure {
	public static void execute(LevelAccessor world) {
		DimpearlsModVariables.MapVariables.get(world).herobrinespawned = true;
		DimpearlsModVariables.MapVariables.get(world).markSyncDirty();
	}
}