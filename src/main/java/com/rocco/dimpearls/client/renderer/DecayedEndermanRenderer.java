package com.rocco.dimpearls.client.renderer;

import net.minecraft.resources.Identifier;
import net.minecraft.client.renderer.entity.state.LivingEntityRenderState;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;

import com.rocco.dimpearls.entity.DecayedEndermanEntity;
import com.rocco.dimpearls.client.model.Modeldecayed_enderman;

public class DecayedEndermanRenderer extends MobRenderer<DecayedEndermanEntity, LivingEntityRenderState, Modeldecayed_enderman> {
	private final Identifier entityTexture = Identifier.parse("dimpearls:textures/entities/decayed_enderman.png");

	public DecayedEndermanRenderer(EntityRendererProvider.Context context) {
		super(context, new Modeldecayed_enderman(context.bakeLayer(Modeldecayed_enderman.LAYER_LOCATION)), 0.5f);
	}

	@Override
	public LivingEntityRenderState createRenderState() {
		return new LivingEntityRenderState();
	}

	@Override
	public void extractRenderState(DecayedEndermanEntity entity, LivingEntityRenderState state, float partialTicks) {
		super.extractRenderState(entity, state, partialTicks);
	}

	@Override
	public Identifier getTextureLocation(LivingEntityRenderState state) {
		return entityTexture;
	}
}