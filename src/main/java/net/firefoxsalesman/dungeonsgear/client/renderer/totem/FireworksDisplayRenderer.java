package net.firefoxsalesman.dungeonsgear.client.renderer.totem;

import net.firefoxsalesman.dungeonsgear.client.models.totem.FireworksDisplayModel;
import net.firefoxsalesman.dungeonsgear.entities.FireworksDisplayEntity;
import net.firefoxsalesman.dungeonslibs.client.renderer.ProjectileRenderer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;

public class FireworksDisplayRenderer extends ProjectileRenderer<FireworksDisplayEntity> {
	public FireworksDisplayRenderer(EntityRendererProvider.Context renderManager) {
		super(renderManager, new FireworksDisplayModel());
	}

	@Override
	public RenderType getRenderType(FireworksDisplayEntity animatable, ResourceLocation texture,
			MultiBufferSource bufferSource, float partialTick) {
		return RenderType.entityTranslucent(getTextureLocation(animatable));

	}
}
