package net.firefoxsalesman.dungeonsgear.enchantments.ranged;

import net.firefoxsalesman.dungeonsgear.enchantments.ModEnchantmentTypes;
import net.firefoxsalesman.dungeonsgear.enchantments.types.DungeonsEnchantment;
import net.firefoxsalesman.dungeonsgear.registry.EnchantmentInit;
import net.firefoxsalesman.dungeonsgear.utilities.ProjectileEffectHelper;
import net.firefoxsalesman.dungeonslibs.utils.RangedAttackHelper;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.BowItem;
import net.minecraft.world.item.CrossbowItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.EnchantmentHelper;

public class BurstBowstringEnchantment extends DungeonsEnchantment {

	public BurstBowstringEnchantment() {
		super(Rarity.RARE, ModEnchantmentTypes.RANGED, new EquipmentSlot[] {
				EquipmentSlot.MAINHAND });
	}

	public static void activateBurstBowString(LivingEntity jumper) {
		ItemStack mainhandStack = jumper.getMainHandItem();
		ItemStack offhandStack = jumper.getOffhandItem();
		int burstBowStringLevel = 0;
		float arrowVelocity = 0.0F;
		if (mainhandStack.getItem() instanceof BowItem || mainhandStack.getItem() instanceof CrossbowItem) {
			burstBowStringLevel = EnchantmentHelper
					.getItemEnchantmentLevel(EnchantmentInit.BURST_BOWSTRING.get(), mainhandStack);
			arrowVelocity = RangedAttackHelper.getCrossbowArrowVelocity(jumper, mainhandStack);
		} else if (offhandStack.getItem() instanceof BowItem
				|| offhandStack.getItem() instanceof CrossbowItem) {
			burstBowStringLevel = EnchantmentHelper
					.getItemEnchantmentLevel(EnchantmentInit.BURST_BOWSTRING.get(), offhandStack);
			arrowVelocity = RangedAttackHelper.getCrossbowArrowVelocity(jumper, offhandStack);
		}

		if (burstBowStringLevel > 0) {
			int arrowsToFire = burstBowStringLevel;
			ProjectileEffectHelper.fireBurstBowstringShots(jumper, 16, 0.4F, arrowVelocity, arrowsToFire);
		}
	}

	public int getMaxLevel() {
		return 3;
	}

}
