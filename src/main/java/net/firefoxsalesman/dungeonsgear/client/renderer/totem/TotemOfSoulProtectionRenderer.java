package net.firefoxsalesman.dungeonsgear.client.renderer.totem;

import net.firefoxsalesman.dungeonsgear.entities.TotemOfSoulProtectionEntity;
import net.firefoxsalesman.dungeonslibs.client.renderer.ProjectileRenderer;
import net.firefoxsalesman.dungeonsgear.client.models.totem.TotemOfSoulProtectionModel;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;

public class TotemOfSoulProtectionRenderer extends ProjectileRenderer<TotemOfSoulProtectionEntity> {
	public TotemOfSoulProtectionRenderer(EntityRendererProvider.Context renderManager) {
		super(renderManager, new TotemOfSoulProtectionModel());
	}

	@Override
	public RenderType getRenderType(TotemOfSoulProtectionEntity animatable, ResourceLocation texture,
			MultiBufferSource bufferSource, float partialTick) {
		return RenderType.entityTranslucent(getTextureLocation(animatable));
	}
}
