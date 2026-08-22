package net.firefoxsalesman.dungeonsgear.utilities;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EquipmentSlot;

import static net.firefoxsalesman.dungeonsgear.DungeonsGear.MOD_ID;

import java.util.AbstractMap;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class GeneralHelper {
	public static ResourceLocation modLoc(String resource) {
		return new ResourceLocation(MOD_ID, resource);
	}

	public static Map<EquipmentSlot, UUID> genArmorAttributeMap(String headId, String chestId, String legsId,
			String feetId) {
		return Stream.of(
				mkEntry(EquipmentSlot.HEAD, headId),
				mkEntry(EquipmentSlot.CHEST, chestId),
				mkEntry(EquipmentSlot.LEGS, legsId),
				mkEntry(EquipmentSlot.FEET, feetId))
				.collect(Collectors.toMap(AbstractMap.SimpleImmutableEntry::getKey,
						AbstractMap.SimpleImmutableEntry::getValue));
	}

	public static Map<EquipmentSlot, UUID> genTotalAttributeMap(String headId, String chestId, String legsId,
			String feetId, String mainHandId, String offHandId) {
		return Stream.of(
				mkEntry(EquipmentSlot.HEAD, headId),
				mkEntry(EquipmentSlot.CHEST, chestId),
				mkEntry(EquipmentSlot.LEGS, legsId),
				mkEntry(EquipmentSlot.FEET, feetId),
				mkEntry(EquipmentSlot.MAINHAND, mainHandId),
				mkEntry(EquipmentSlot.OFFHAND, offHandId))
				.collect(Collectors.toMap(AbstractMap.SimpleImmutableEntry::getKey,
						AbstractMap.SimpleImmutableEntry::getValue));
	}

	private static AbstractMap.SimpleImmutableEntry<EquipmentSlot, UUID> mkEntry(EquipmentSlot slot, String id) {
		return new AbstractMap.SimpleImmutableEntry<>(slot,
				UUID.fromString(id));
	}
}
