package com.rocco.dimpearls.procedures;

import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.LightningBolt;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.EntitySpawnReason;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.util.RandomSource;
import net.minecraft.util.Mth;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.core.BlockPos;
import net.minecraft.commands.arguments.EntityAnchorArgument;

import java.util.Comparator;

public class HerobrineOnEntityTickUpdateProcedure {
	public static void execute(LevelAccessor world, double x, double y, double z, Entity entity) {
		if (entity == null)
			return;
		Entity ClosestEntity = null;
		Entity ClosestPlayerEntity = null;
		double attack = 0;
		attack = Mth.nextInt(RandomSource.create(), 1, 100);
		if (world instanceof ServerLevel _level)
			_level.sendParticles(ParticleTypes.LARGE_SMOKE, x, y, z, 50, 3, 3, 3, 1);
		if (findEntityInWorldRange(world, Monster.class, x, y, z, 32) != null && findEntityInWorldRange(world, ServerPlayer.class, x, y, z, 32) != null) {
			ClosestEntity = findEntityInWorldRange(world, Monster.class, x, y, z, 32);
			ClosestPlayerEntity = findEntityInWorldRange(world, ServerPlayer.class, x, y, z, 32);
		}
		if (attack == 10) {
			if (ClosestEntity != null && !(entity == ClosestEntity) && !(ClosestEntity == ClosestPlayerEntity)) {
				entity.lookAt(EntityAnchorArgument.Anchor.EYES, new Vec3((ClosestEntity.getX()), (ClosestEntity.getY()), (ClosestEntity.getZ() + 1)));
				if (world instanceof ServerLevel _level) {
					LightningBolt entityToSpawn = EntityType.LIGHTNING_BOLT.create(_level, EntitySpawnReason.TRIGGERED);
					entityToSpawn.snapTo(Vec3.atBottomCenterOf(BlockPos.containing(ClosestEntity.getX(), ClosestEntity.getY(), ClosestEntity.getZ())));;
					_level.addFreshEntity(entityToSpawn);
				}
				{
					Entity _ent = ClosestEntity;
					if (_ent.level() instanceof ServerLevel _serverLevel) {
						_ent.hurtServer(_serverLevel, new DamageSource(world.holderOrThrow(DamageTypes.LIGHTNING_BOLT)), 10);
					}
				}
			}
		}
	}

	private static Entity findEntityInWorldRange(LevelAccessor world, Class<? extends Entity> clazz, double x, double y, double z, double range) {
		return (Entity) world.getEntitiesOfClass(clazz, AABB.ofSize(new Vec3(x, y, z), range, range, range), e -> true).stream().sorted(Comparator.comparingDouble(e -> e.distanceToSqr(x, y, z))).findFirst().orElse(null);
	}
}