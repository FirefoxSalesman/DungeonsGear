package net.firefoxsalesman.dungeonsgear.items;

import net.firefoxsalesman.dungeonsgear.registry.ItemInit;
import net.firefoxsalesman.dungeonslibs.items.RangedItemModelProperties;

public class GearRangedItemModelProperties {
	public static void init() {
		ItemInit.RANGED_WEAPONS.forEach((resourceLocation, itemRegistryObject) -> RangedItemModelProperties
				.addRangedModelProperties(itemRegistryObject));
	}
}
