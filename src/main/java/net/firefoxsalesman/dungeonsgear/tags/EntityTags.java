package net.firefoxsalesman.dungeonsgear.tags;

import net.firefoxsalesman.dungeonsgear.utilities.GeneralHelper;
import net.minecraft.core.registries.Registries;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.EntityType;

public class EntityTags {
	public static final TagKey<EntityType<?>> VOID_STRIKE_IMMUNE = tag("void_strike_immune");

	private static TagKey<EntityType<?>> tag(String name) {
		return TagKey.create(Registries.ENTITY_TYPE, GeneralHelper.modLoc(name));
	}
}
