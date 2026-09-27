package com.rocco.dimpearls.procedures;

import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.Level;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.util.RandomSource;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.resources.ResourceKey;
import net.minecraft.network.protocol.game.ClientboundUpdateMobEffectPacket;
import net.minecraft.network.protocol.game.ClientboundPlayerAbilitiesPacket;
import net.minecraft.network.protocol.game.ClientboundLevelEventPacket;
import net.minecraft.network.protocol.game.ClientboundGameEventPacket;
import net.minecraft.network.chat.Component;
import net.minecraft.core.BlockPos;

import com.rocco.dimpearls.network.DimpearlsModVariables;

public class DirtPearlCheckTpProcedure {
	public static void execute(LevelAccessor world, Entity entity, ItemStack itemstack) {
		if (entity == null)
			return;
		if (entity.isShiftKeyDown() && (entity.level().dimension()) == Level.OVERWORLD) {
			{
				var _playerVars = entity.getCapability(DimpearlsModVariables.PLAYER_VARIABLES).orElse(null);
				if (_playerVars != null) {
					_playerVars.overworldx = entity.getX();
					_playerVars.overworldy = entity.getY();
					_playerVars.overworldz = entity.getZ();
					_playerVars.markSyncDirty();
				}
			}
			if (entity instanceof Player _player && !_player.level().isClientSide())
				_player.displayClientMessage(Component.literal("Overworld location saved!"), false);
		} else {
			if (!((entity.level().dimension()) == Level.OVERWORLD)) {
				if (entity.getCapability(DimpearlsModVariables.PLAYER_VARIABLES).orElseGet(DimpearlsModVariables.PlayerVariables::new).overworldx == 0
						&& entity.getCapability(DimpearlsModVariables.PLAYER_VARIABLES).orElseGet(DimpearlsModVariables.PlayerVariables::new).overworldy == 0
						&& entity.getCapability(DimpearlsModVariables.PLAYER_VARIABLES).orElseGet(DimpearlsModVariables.PlayerVariables::new).overworldz == 0) {
					if (entity instanceof Player _player && !_player.level().isClientSide())
						_player.displayClientMessage(Component.literal("No Overworld location saved!"), false);
				} else {
					if (entity instanceof ServerPlayer _player && !_player.level().isClientSide()) {
						ResourceKey<Level> destinationType = Level.OVERWORLD;
						if (_player.level().dimension() == destinationType)
							return;
						ServerLevel nextLevel = _player.server.getLevel(destinationType);
						if (nextLevel != null) {
							_player.connection.send(new ClientboundGameEventPacket(ClientboundGameEventPacket.WIN_GAME, 0));
							_player.teleportTo(nextLevel, _player.getX(), _player.getY(), _player.getZ(), _player.getYRot(), _player.getXRot());
							_player.connection.send(new ClientboundPlayerAbilitiesPacket(_player.getAbilities()));
							for (MobEffectInstance _effectinstance : _player.getActiveEffects())
								_player.connection.send(new ClientboundUpdateMobEffectPacket(_player.getId(), _effectinstance));
							_player.connection.send(new ClientboundLevelEventPacket(1032, BlockPos.ZERO, 0, false));
						}
					}
					{
						Entity _ent = entity;
						double _tx = entity.getCapability(DimpearlsModVariables.PLAYER_VARIABLES).orElseGet(DimpearlsModVariables.PlayerVariables::new).overworldx;
						double _ty = entity.getCapability(DimpearlsModVariables.PLAYER_VARIABLES).orElseGet(DimpearlsModVariables.PlayerVariables::new).overworldy;
						double _tz = entity.getCapability(DimpearlsModVariables.PLAYER_VARIABLES).orElseGet(DimpearlsModVariables.PlayerVariables::new).overworldz;
						_ent.teleportTo(_tx, _ty, _tz);
						if (_ent instanceof ServerPlayer _serverPlayer)
							_serverPlayer.connection.teleport(_tx, _ty, _tz, _ent.getYRot(), _ent.getXRot());
					}
					if (entity instanceof Player _player && !_player.level().isClientSide())
						_player.displayClientMessage(Component.literal("Teleported to Overworld!"), false);
					{
						ItemStack _ist = itemstack;
						if (_ist.hurt(1, RandomSource.create(), null)) {
							_ist.shrink(1);
							_ist.setDamageValue(0);
						}
					}
					if (entity instanceof Player _player)
						_player.getCooldowns().addCooldown(itemstack.getItem(), 100);
					if (itemstack.getDamageValue() == 32) {
						itemstack.shrink(1);
					}
				}
			}
		}
	}
}