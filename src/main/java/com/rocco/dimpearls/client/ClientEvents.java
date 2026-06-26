package com.rocco.dimpearls.client;

import com.rocco.dimpearls.DimPearls;

import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.EntityRenderersEvent;
import net.neoforged.api.distmarker.Dist;

@EventBusSubscriber(
    modid = DimPearls.MODID,
    value = Dist.CLIENT
)
public class ClientEvents
{
    @SubscribeEvent
    public static void registerRenderers(
        EntityRenderersEvent.RegisterRenderers event
    )
    {
        event.registerEntityRenderer(
            DimPearls.DECAYED_ENDERMAN.get(),
            DecayedEndermanRenderer::new
        );
    }
}