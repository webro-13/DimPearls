package com.rocco.dimpearls.procedures;

import net.minecraft.world.phys.Vec3;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.LightningBolt;
import net.minecraft.world.entity.EntityTypes;
import net.minecraft.world.entity.EntitySpawnReason;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.core.BlockPos;

public class HerobrineEntityDiesProcedure {
	public static void execute(LevelAccessor world, Entity sourceentity) {
		if (sourceentity == null)
			return;
		if (world instanceof ServerLevel _level && _level.getServer() != null) {
			_level.getServer().setWeatherParameters(0, ServerLevel.RAIN_DURATION.sample(_level.getRandom()), true, false);
		}
		if (world instanceof ServerLevel _level) {
			LightningBolt entityToSpawn = EntityTypes.LIGHTNING_BOLT.create(_level, EntitySpawnReason.TRIGGERED);
			entityToSpawn.snapTo(Vec3.atBottomCenterOf(BlockPos.containing(sourceentity.getX(), sourceentity.getY(), sourceentity.getZ())));
			entityToSpawn.setVisualOnly(true);
			_level.addFreshEntity(entityToSpawn);
		}
		if (sourceentity instanceof LivingEntity _entity)
			_entity.setHealth(0);
		{
			Entity _ent = sourceentity;
			if (_ent.level() instanceof ServerLevel _serverLevel) {
				_ent.hurtServer(_serverLevel, new DamageSource(world.holderOrThrow(DamageTypes.GENERIC_KILL)),
						(float) (sourceentity instanceof LivingEntity _livingEntity6 && _livingEntity6.getAttributes().hasAttribute(Attributes.MAX_HEALTH) ? _livingEntity6.getAttribute(Attributes.MAX_HEALTH).getValue() : 0));
			}
		}
		if (sourceentity instanceof LivingEntity _entity)
			_entity.setHealth(0);
	}
}