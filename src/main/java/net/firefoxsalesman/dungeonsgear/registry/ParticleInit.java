package net.firefoxsalesman.dungeonsgear.registry;

import net.minecraft.core.particles.ParticleType;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import static net.firefoxsalesman.dungeonsgear.DungeonsGear.MOD_ID;

public class ParticleInit {
	private static final DeferredRegister<ParticleType<?>> PARTICLES = DeferredRegister
			.create(ForgeRegistries.PARTICLE_TYPES, MOD_ID);

	public static final RegistryObject<SimpleParticleType> ELECTRIC_SHOCK = makeParticle("electric_shock");
	public static final RegistryObject<SimpleParticleType> SOUL_DUST = makeParticle("soul_dust");

	public static void register(IEventBus eventBus) {
		PARTICLES.register(eventBus);
	}

	private static RegistryObject<SimpleParticleType> makeParticle(String name) {
		return PARTICLES.register(name, () -> new SimpleParticleType(true));
	}
}
