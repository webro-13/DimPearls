package com.rocco.dimpearls.event;

import net.minecraft.nbt.CompoundTag;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.player.PlayerEvent;

@EventBusSubscriber(modid = "dimpearls")
public class ModEvents
{
    @SubscribeEvent
    public static void onPlayerClone(PlayerEvent.Clone event)
    {
        if (event.isWasDeath())
        {
            CompoundTag oldData =
                event.getOriginal().getPersistentData();

            CompoundTag newData =
                event.getEntity().getPersistentData();

            newData.merge(oldData);
        }
    }
}