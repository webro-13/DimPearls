package com.rocco.dimpearls.data;

import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerPlayer;

public class PearlData {

    public static void saveLocation(ServerPlayer player, String key) {
        CompoundTag data = player.getPersistentData();

        BlockPos pos = player.blockPosition();

        data.putInt(key + "_x", pos.getX());
        data.putInt(key + "_y", pos.getY());
        data.putInt(key + "_z", pos.getZ());
    }

    public static BlockPos getLocation(ServerPlayer player, String key) {
        CompoundTag data = player.getPersistentData();

        if (!data.contains(key + "_x")) {
            return null;
        }

        return new BlockPos(
    data.getInt(key + "_x").orElse(0),
    data.getInt(key + "_y").orElse(0),
    data.getInt(key + "_z").orElse(0)
);
    }
}