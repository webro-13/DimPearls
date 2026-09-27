/*
 *    MCreator note: This file will be REGENERATED on each build.
 */
package com.rocco.dimpearls.init;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.api.distmarker.Dist;

import com.rocco.dimpearls.client.renderer.HerobrineRenderer;
import com.rocco.dimpearls.client.renderer.DecayedEndermanRenderer;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class DimpearlsModEntityRenderers {
	@SubscribeEvent
	public static void registerEntityRenderers(EntityRenderersEvent.RegisterRenderers event) {
		event.registerEntityRenderer(DimpearlsModEntities.DECAYED_ENDERMAN.get(), DecayedEndermanRenderer::new);
		event.registerEntityRenderer(DimpearlsModEntities.HEROBRINE.get(), HerobrineRenderer::new);
	}
}