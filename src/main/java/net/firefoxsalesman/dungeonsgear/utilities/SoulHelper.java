package net.firefoxsalesman.dungeonsgear.utilities;

import net.firefoxsalesman.dungeonsgear.config.DungeonsGearConfig;
import net.firefoxsalesman.dungeonsgear.tags.ItemTags;
import net.minecraft.world.item.ItemStack;

public class SoulHelper {
	public static boolean isSoulItem(ItemStack stack) {
		return !DungeonsGearConfig.RESTRICT_SOUL_ENCHANTMENTS.get() || stack.is(ItemTags.SOUL_ITEM);
	}
}
