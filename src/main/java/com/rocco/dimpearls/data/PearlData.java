package com.rocco.dimpearls.data;

import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerPlayer;

public class PearlData
{
    public static void saveLocation(
        ServerPlayer player,
        String key
    )
    {
        CompoundTag data = player.getPersistentData();

        CompoundTag location = new CompoundTag();

        location.putInt(
            "x",
            player.blockPosition().getX()
        );

        location.putInt(
            "y",
            player.blockPosition().getY()
        );

        location.putInt(
            "z",
            player.blockPosition().getZ()
        );

        data.put(key, location);
    }

public static BlockPos getLocation(
    ServerPlayer player,
    String key
)
{
    CompoundTag data = player.getPersistentData();

    if (!data.contains(key))
    {
        return null;
    }

    CompoundTag location =
        data.getCompound(key).orElse(null);

    if (location == null)
    {
        return null;
    }

    return new BlockPos(
    location.getInt("x").orElse(0),
    location.getInt("y").orElse(0),
    location.getInt("z").orElse(0)
);
}
}