package com.rocco.dimpearls.client.renderer;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;

import com.rocco.dimpearls.entity.DecayedEndermanEntity;
import com.rocco.dimpearls.client.model.Modeldecayed_enderman;

public class DecayedEndermanRenderer extends MobRenderer<DecayedEndermanEntity, Modeldecayed_enderman<DecayedEndermanEntity>> {
	private final ResourceLocation entityTexture = new ResourceLocation("dimpearls:textures/entities/decayed_enderman.png");

	public DecayedEndermanRenderer(EntityRendererProvider.Context context) {
		super(context, new Modeldecayed_enderman<DecayedEndermanEntity>(context.bakeLayer(Modeldecayed_enderman.LAYER_LOCATION)), 0.5f);
	}

	@Override
	public ResourceLocation getTextureLocation(DecayedEndermanEntity entity) {
		return entityTexture;
	}
}