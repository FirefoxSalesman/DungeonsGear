package net.firefoxsalesman.dungeonsgear.client.models.totem;

import static net.firefoxsalesman.dungeonsgear.utilities.GeneralHelper.modLoc;

import net.firefoxsalesman.dungeonsgear.entities.TotemOfRegenerationEntity;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;

public class TotemOfRegenerationModel extends GeoModel<TotemOfRegenerationEntity> {

	@Override
	public ResourceLocation getAnimationResource(TotemOfRegenerationEntity entity) {
		return modLoc("animations/totem_of_regeneration.animation.json");
	}

	@Override
	public ResourceLocation getModelResource(TotemOfRegenerationEntity entity) {
		return modLoc("geo/totem_of_regeneration.geo.json");
	}

	@Override
	public ResourceLocation getTextureResource(TotemOfRegenerationEntity entity) {
		return modLoc("textures/entity/totem_of_regeneration.png");
	}
}
