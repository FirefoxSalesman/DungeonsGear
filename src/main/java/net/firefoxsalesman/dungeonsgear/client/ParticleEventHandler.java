package net.firefoxsalesman.dungeonsgear.client;

import net.minecraft.client.Minecraft;
import net.minecraft.client.particle.ParticleEngine;
import net.minecraft.core.particles.ParticleType;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.RegisterParticleProvidersEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import static net.firefoxsalesman.dungeonsgear.DungeonsGear.MOD_ID;

import net.firefoxsalesman.dungeonsgear.client.particles.ElectricShockParticle;
import net.firefoxsalesman.dungeonsgear.client.particles.SoulDustParticle;
import net.firefoxsalesman.dungeonsgear.registry.ParticleInit;

@Mod.EventBusSubscriber(modid = MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ParticleEventHandler {

	@SubscribeEvent(priority = EventPriority.LOWEST)
	public static void onParticleFactory(RegisterParticleProvidersEvent event) {
		register(ParticleInit.ELECTRIC_SHOCK.get(),
				ElectricShockParticle.Factory::new);
		register(ParticleInit.SOUL_DUST.get(), SoulDustParticle.Factory::new);
	}

	private static void register(ParticleType<SimpleParticleType> particleType,
			ParticleEngine.SpriteParticleRegistration<SimpleParticleType> factory) {
		Minecraft.getInstance().particleEngine.register(particleType, factory);
	}
}
