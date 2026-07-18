package net.firefoxsalesman.dungeonsgear.registry;

import static net.minecraft.core.registries.Registries.DAMAGE_TYPE;

import net.firefoxsalesman.dungeonsgear.utilities.GeneralHelper;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.damagesource.DamageType;
import net.minecraftforge.event.server.ServerStartedEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class DamageSourceInit {
	public static ResourceKey<DamageType> OFFHAND;

	@SubscribeEvent
	public void registerDamageTypes(ServerStartedEvent event) {
		OFFHAND = mkDamageType("offhand");
	}

	private ResourceKey<DamageType> mkDamageType(String name) {
		return ResourceKey.create(DAMAGE_TYPE, GeneralHelper.modLoc(name));
	}
}
