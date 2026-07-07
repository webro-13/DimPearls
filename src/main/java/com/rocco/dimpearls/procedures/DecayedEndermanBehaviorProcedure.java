package com.rocco.dimpearls.procedures;

import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Entity;
import net.minecraft.util.RandomSource;
import net.minecraft.util.Mth;
import net.minecraft.server.level.ServerPlayer;

import java.util.Comparator;

public class DecayedEndermanBehaviorProcedure {
	public static void execute(LevelAccessor world, double x, double y, double z, Entity entity) {
		if (entity == null)
			return;
		double actionchoice = 0;
		boolean unstable_aggro = false;
		actionchoice = Mth.nextInt(RandomSource.create(), (int) 0.1, 100);
		if (entity instanceof LivingEntity _livingEntity1 && _livingEntity1.getAttributes().hasAttribute(Attributes.MOVEMENT_SPEED))
			_livingEntity1.getAttribute(Attributes.MOVEMENT_SPEED).setBaseValue(0.25);
		if (actionchoice == 1) {
			{
				Entity _ent = entity;
				double _tx = (entity.getX() + Mth.nextInt(RandomSource.create(), 3, 6));
				double _ty = (entity.getY() + Mth.nextInt(RandomSource.create(), 3, 6));
				double _tz = (entity.getZ() + Mth.nextInt(RandomSource.create(), 3, 6));
				_ent.teleportTo(_tx, _ty, _tz);
				if (_ent instanceof ServerPlayer _serverPlayer)
					_serverPlayer.connection.teleport(_tx, _ty, _tz, _ent.getYRot(), _ent.getXRot());
			}
		}
		if (actionchoice == 0.5) {
			if (entity instanceof LivingEntity _livingEntity9 && _livingEntity9.getAttributes().hasAttribute(Attributes.MOVEMENT_SPEED))
				_livingEntity9.getAttribute(Attributes.MOVEMENT_SPEED).setBaseValue(0);
		}
		if (actionchoice == 0.2) {
			unstable_aggro = !unstable_aggro;
			if (unstable_aggro == true) {
				if (entity instanceof Mob _entity && (findEntityInWorldRange(world, ServerPlayer.class, x, y, z, 10)) instanceof LivingEntity _ent)
					_entity.setTarget(_ent);
				if (((findEntityInWorldRange(world, ServerPlayer.class, x, y, z, 10)) instanceof LivingEntity _livEnt ? _livEnt.getHealth() : -1) == 7) {
					if (entity instanceof Mob _entity)
						_entity.setTarget(null);
				}
			} else {
				if (entity instanceof Mob _entity)
					_entity.setTarget(null);
			}
		}
	}

	private static Entity findEntityInWorldRange(LevelAccessor world, Class<? extends Entity> clazz, double x, double y, double z, double range) {
		return (Entity) world.getEntitiesOfClass(clazz, AABB.ofSize(new Vec3(x, y, z), range, range, range), e -> true).stream().sorted(Comparator.comparingDouble(e -> e.distanceToSqr(x, y, z))).findFirst().orElse(null);
	}
}