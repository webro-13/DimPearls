package com.rocco.dimpearls.procedures;

import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.Level;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.resources.ResourceKey;
import net.minecraft.network.protocol.game.ClientboundUpdateMobEffectPacket;
import net.minecraft.network.protocol.game.ClientboundPlayerAbilitiesPacket;
import net.minecraft.network.protocol.game.ClientboundLevelEventPacket;
import net.minecraft.network.protocol.game.ClientboundGameEventPacket;
import net.minecraft.network.chat.Component;
import net.minecraft.core.BlockPos;

import java.util.Set;

import com.rocco.dimpearls.network.DimpearlsModVariables;

public class EndPearlCheckTpProcedure {
	public static void execute(LevelAccessor world, Entity entity, ItemStack itemstack) {
		if (entity == null)
			return;
		if (entity.isShiftKeyDown() && (entity.level().dimension()) == Level.END) {
			{
				DimpearlsModVariables.PlayerVariables _vars = entity.getData(DimpearlsModVariables.PLAYER_VARIABLES);
				_vars.endx = entity.getX();
				_vars.endy = entity.getY();
				_vars.endz = entity.getZ();
				_vars.markSyncDirty();
			}
			if (entity instanceof ServerPlayer _player)
				_player.sendSystemMessage(Component.literal("End location saved!"), false);
		} else {
			if (!((entity.level().dimension()) == Level.END)) {
				if (entity.getData(DimpearlsModVariables.PLAYER_VARIABLES).endx == 0 && entity.getData(DimpearlsModVariables.PLAYER_VARIABLES).endy == 0 && entity.getData(DimpearlsModVariables.PLAYER_VARIABLES).endz == 0) {
					if (entity instanceof ServerPlayer _player)
						_player.sendSystemMessage(Component.literal("No End location saved!"), false);
				} else {
					if (entity instanceof ServerPlayer _player && _player.level() instanceof ServerLevel _serverLevel) {
						ResourceKey<Level> destinationType = Level.END;
						if (_player.level().dimension() == destinationType)
							return;
						ServerLevel nextLevel = _serverLevel.getServer().getLevel(destinationType);
						if (nextLevel != null) {
							_player.connection.send(new ClientboundGameEventPacket(ClientboundGameEventPacket.WIN_GAME, 0));
							_player.teleportTo(nextLevel, _player.getX(), _player.getY(), _player.getZ(), Set.of(), _player.getYRot(), _player.getXRot(), true);
							_player.connection.send(new ClientboundPlayerAbilitiesPacket(_player.getAbilities()));
							for (MobEffectInstance _effectinstance : _player.getActiveEffects())
								_player.connection.send(new ClientboundUpdateMobEffectPacket(_player.getId(), _effectinstance, false));
							_player.connection.send(new ClientboundLevelEventPacket(1032, BlockPos.ZERO, 0, false));
						}
					}
					{
						Entity _ent = entity;
						double _tx = entity.getData(DimpearlsModVariables.PLAYER_VARIABLES).endx;
						double _ty = entity.getData(DimpearlsModVariables.PLAYER_VARIABLES).endy;
						double _tz = entity.getData(DimpearlsModVariables.PLAYER_VARIABLES).endz;
						_ent.teleportTo(_tx, _ty, _tz);
						if (_ent instanceof ServerPlayer _serverPlayer)
							_serverPlayer.connection.teleport(_tx, _ty, _tz, _ent.getYRot(), _ent.getXRot());
					}
					if (entity instanceof ServerPlayer _player)
						_player.sendSystemMessage(Component.literal("Teleported to End!"), false);
					if (entity instanceof Player _player)
						_player.getCooldowns().addCooldown(itemstack, 200);
					if (world instanceof ServerLevel _level) {
						itemstack.hurtAndBreak(1, _level, null, _stkprov -> {
						});
					}
					if (itemstack.getDamageValue() == 16) {
						itemstack.shrink(1);
					}
				}
			}
		}
	}
}