package net.firefoxsalesman.dungeonsgear.attributes;

import net.firefoxsalesman.dungeonsgear.DungeonsGear;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraftforge.event.entity.EntityAttributeModificationEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.List;
import java.util.stream.Collectors;

import static net.firefoxsalesman.dungeonsgear.registry.AttributeInit.ROLL_COOLDOWN;
import static net.firefoxsalesman.dungeonsgear.registry.AttributeInit.ROLL_LIMIT;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD, modid = DungeonsGear.MOD_ID)
public class AttributeEvents {

	@SubscribeEvent
	public static void onEntityAttributeModificationEvent(EntityAttributeModificationEvent event) {
		addAttributeToPlayer(event, ROLL_COOLDOWN.get());
		addAttributeToPlayer(event, ROLL_LIMIT.get());
	}

	private static void addAttributeToAll(EntityAttributeModificationEvent event, Attribute attribute) {
		List<EntityType<? extends LivingEntity>> entitiesWithoutAttribute = event.getTypes().stream()
				.filter(entityType -> !event.has(entityType, attribute)).collect(Collectors.toList());
		entitiesWithoutAttribute
				.forEach(entityType -> event.add(entityType, attribute, attribute.getDefaultValue()));
	}

	private static void addAttributeToPlayer(EntityAttributeModificationEvent event, Attribute attribute) {
		List<EntityType<? extends LivingEntity>> entitiesWithoutAttribute = event.getTypes().stream().filter(
				entityType -> !event.has(entityType, attribute) && entityType == EntityType.PLAYER)
				.collect(Collectors.toList());
		entitiesWithoutAttribute
				.forEach(entityType -> event.add(entityType, attribute, attribute.getDefaultValue()));
	}
}
