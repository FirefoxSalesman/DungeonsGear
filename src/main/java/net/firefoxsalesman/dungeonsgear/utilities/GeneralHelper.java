package net.firefoxsalesman.dungeonsgear.utilities;

import net.minecraft.resources.ResourceLocation;

import static net.firefoxsalesman.dungeonsgear.DungeonsGear.MOD_ID;

public class GeneralHelper {
	public static ResourceLocation modLoc(String resource) {
		return new ResourceLocation(MOD_ID, resource);
	}
}
