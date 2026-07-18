package net.firefoxsalesman.dungeonsgear.client.renderer.totem;

import net.firefoxsalesman.dungeonsgear.client.models.totem.TotemOfShieldingModel;
import net.firefoxsalesman.dungeonsgear.entities.TotemOfShieldingEntity;
import net.firefoxsalesman.dungeonslibs.client.renderer.ProjectileRenderer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;

public class TotemOfShieldingRenderer extends ProjectileRenderer<TotemOfShieldingEntity> {
	public TotemOfShieldingRenderer(EntityRendererProvider.Context renderManager) {
		super(renderManager, new TotemOfShieldingModel());
	}

	@Override
	public RenderType getRenderType(TotemOfShieldingEntity animatable, ResourceLocation texture,
			MultiBufferSource bufferSource, float partialTick) {
		return RenderType.entityTranslucent(getTextureLocation(animatable));
	}
}
