/*
 *    MCreator note: This file will be REGENERATED on each build.
 */
package com.rocco.dimpearls.init;

import net.neoforged.neoforge.client.event.EntityRenderersEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.api.distmarker.Dist;

import com.rocco.dimpearls.client.model.Modeldecayed_enderman;

@EventBusSubscriber(Dist.CLIENT)
public class DimpearlsModModels {
	@SubscribeEvent
	public static void registerLayerDefinitions(EntityRenderersEvent.RegisterLayerDefinitions event) {
		event.registerLayerDefinition(Modeldecayed_enderman.LAYER_LOCATION, Modeldecayed_enderman::createBodyLayer);
	}
}