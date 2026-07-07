/*
 *    MCreator note: This file will be REGENERATED on each build.
 */
package com.rocco.dimpearls.init;

import net.neoforged.neoforge.client.event.EntityRenderersEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.api.distmarker.Dist;

import com.rocco.dimpearls.client.renderer.HerobrineRenderer;
import com.rocco.dimpearls.client.renderer.DecayedEndermanRenderer;

@EventBusSubscriber(Dist.CLIENT)
public class DimpearlsModEntityRenderers {
	@SubscribeEvent
	public static void registerEntityRenderers(EntityRenderersEvent.RegisterRenderers event) {
		event.registerEntityRenderer(DimpearlsModEntities.DECAYED_ENDERMAN.get(), DecayedEndermanRenderer::new);
		event.registerEntityRenderer(DimpearlsModEntities.HEROBRINE.get(), HerobrineRenderer::new);
	}
}