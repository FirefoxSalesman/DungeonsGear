package net.firefoxsalesman.dungeonsgear.enchantments.armor.legs;

import static net.firefoxsalesman.dungeonsgear.enchantments.ModEnchantmentTypes.ARMOR_SLOT;
import static net.firefoxsalesman.dungeonsgear.registry.AttributeInit.ROLL_LIMIT;
import static net.firefoxsalesman.dungeonsgear.registry.EnchantmentInit.MULTI_ROLL;

import java.util.Map;
import java.util.UUID;

import net.firefoxsalesman.dungeonsgear.DungeonsGear;
import net.firefoxsalesman.dungeonsgear.enchantments.types.JumpingEnchantment;
import net.firefoxsalesman.dungeonsgear.utilities.GeneralHelper;
import net.firefoxsalesman.dungeonslibs.utils.ModHelper;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeInstance;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentCategory;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraftforge.event.entity.living.LivingEquipmentChangeEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = DungeonsGear.MOD_ID)
public class MultiRollEnchantment extends JumpingEnchantment {
	private final static Map<EquipmentSlot, UUID> EQUIPMENT_ATTRIBUTE_UUID_MAP = GeneralHelper.genArmorAttributeMap(
			"cbbf06ac-3c9c-4edb-a7cb-860ff4c4264f", "4084f142-000f-4311-ab9c-0491682f26e4",
			"afff129c-2a8a-48b5-9548-865f148d88f0", "70c675d6-e38c-451c-badd-4388755df31a");

	public MultiRollEnchantment() {
		super(Rarity.RARE, EnchantmentCategory.ARMOR_LEGS, ARMOR_SLOT);
	}

	@Override
	public boolean canEnchant(ItemStack stack) {
		return super.canEnchant(stack)
				&& (!ModHelper.hasMod("combatroll") || BuiltInRegistries.ITEM.getKey(stack.getItem())
						.equals(GeneralHelper.modLoc("goat_leggings")));
	}

	@Override
	public boolean canApplyAtEnchantingTable(ItemStack stack) {
		return super.canApplyAtEnchantingTable(stack)
				&& (!ModHelper.hasMod("combatroll") || BuiltInRegistries.ITEM.getKey(stack.getItem())
						.equals(GeneralHelper.modLoc("goat_leggings")));
	}

	public int getMaxLevel() {
		return 3;
	}

	@Override
	public boolean checkCompatibility(Enchantment enchantment) {
		return !(enchantment instanceof JumpingEnchantment);
	}

	@SubscribeEvent
	public static void onLivingEquipmentChange(LivingEquipmentChangeEvent event) {
		removeAttribute(event.getFrom(), event.getEntity(), EQUIPMENT_ATTRIBUTE_UUID_MAP.get(event.getSlot()));
		addAttribute(event.getTo(), event.getEntity(), EQUIPMENT_ATTRIBUTE_UUID_MAP.get(event.getSlot()));
	}

	private static void removeAttribute(ItemStack itemStack, LivingEntity livingEntity,
			UUID attributeModifierUUID) {
		if (EnchantmentHelper.getItemEnchantmentLevel(MULTI_ROLL.get(), itemStack) > 0) {
			AttributeInstance attributeInstance = livingEntity.getAttribute(ROLL_LIMIT.get());
			if (attributeInstance != null && attributeInstance.getModifier(attributeModifierUUID) != null) {
				attributeInstance.removeModifier(attributeModifierUUID);
			}
		}
	}

	private static void addAttribute(ItemStack itemStack, LivingEntity livingEntity, UUID attributeModifierUUID) {
		int itemEnchantmentLevel = EnchantmentHelper.getItemEnchantmentLevel(MULTI_ROLL.get(), itemStack);
		if (itemEnchantmentLevel > 0) {
			AttributeInstance attributeInstance = livingEntity.getAttribute(ROLL_LIMIT.get());
			if (attributeInstance != null && attributeInstance.getModifier(attributeModifierUUID) == null) {
				attributeInstance.addTransientModifier(new AttributeModifier(attributeModifierUUID,
						"Enchantment MultiRoll", 1 * itemEnchantmentLevel,
						AttributeModifier.Operation.ADDITION));
			}
		}
	}

}
