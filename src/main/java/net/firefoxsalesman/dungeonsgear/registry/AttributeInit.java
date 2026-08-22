package net.firefoxsalesman.dungeonsgear.registry;

import net.firefoxsalesman.dungeonsgear.DungeonsGear;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.RangedAttribute;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import static net.firefoxsalesman.dungeonsgear.DungeonsGear.MOD_ID;

public class AttributeInit {

	private static final DeferredRegister<Attribute> ATTRIBUTES = DeferredRegister.create(
			ForgeRegistries.ATTRIBUTES,
			DungeonsGear.MOD_ID);

	public static final RegistryObject<Attribute> ATTACK_REACH = ATTRIBUTES.register("attack_reach",
			() -> new RangedAttribute(
					"attribute.name.generic." + MOD_ID + ".attackReach",
					3.0D,
					0.0D,
					1024.0D)
					.setSyncable(true));

	public static final RegistryObject<Attribute> ROLL_COOLDOWN = ATTRIBUTES.register("roll_cooldown",
			() -> new RangedAttribute(
					"attribute.name.generic." + MOD_ID + ".roll_cooldown",
					180.0D,
					0.0D,
					1024.0D)
					.setSyncable(true));

	public static final RegistryObject<Attribute> ROLL_LIMIT = ATTRIBUTES.register("roll_limit",
			() -> new RangedAttribute(
					"attribute.name.generic." + MOD_ID + ".roll_limit",
					1.0D,
					0.0D,
					1024.0D)
					.setSyncable(true));

	public static void register(IEventBus eventBus) {
		ATTRIBUTES.register(eventBus);
	}
}
