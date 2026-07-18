package net.firefoxsalesman.dungeonsgear.capabilities.combo;

import net.minecraft.world.entity.Entity;

import static net.firefoxsalesman.dungeonsgear.capabilities.GearCapabilities.COMBO_CAPABILITY;

public class ComboHelper {

	public static Combo getComboCapability(Entity entity) {
		return entity.getCapability(COMBO_CAPABILITY).orElse(new Combo());
	}
}
