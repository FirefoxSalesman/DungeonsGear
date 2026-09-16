package net.firefoxsalesman.dungeonsgear.tags;

import net.firefoxsalesman.dungeonsgear.utilities.GeneralHelper;
import net.minecraft.core.registries.Registries;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;

public class ItemTags {
	public static final TagKey<Item> SOUL_ITEM = tag("soul_item");

	private static TagKey<Item> tag(String name) {
		return TagKey.create(Registries.ITEM, GeneralHelper.modLoc(name));
	}
}
