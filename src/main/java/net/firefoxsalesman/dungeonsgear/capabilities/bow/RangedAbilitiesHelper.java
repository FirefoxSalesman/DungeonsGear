package net.firefoxsalesman.dungeonsgear.capabilities.bow;

import net.minecraft.world.item.ItemStack;

import static net.firefoxsalesman.dungeonsgear.capabilities.GearCapabilities.RANGED_ABILITIES_CAPABILITY;

public class RangedAbilitiesHelper {

	public static RangedAbilities getRangedAbilitiesCapability(ItemStack itemStack) {
		return itemStack.getCapability(RANGED_ABILITIES_CAPABILITY).orElse(new RangedAbilities());
	}

}
