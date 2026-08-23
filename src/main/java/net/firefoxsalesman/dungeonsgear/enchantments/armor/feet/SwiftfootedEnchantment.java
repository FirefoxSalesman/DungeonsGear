package net.firefoxsalesman.dungeonsgear.enchantments.armor.feet;

import static net.firefoxsalesman.dungeonsgear.enchantments.ModEnchantmentTypes.ARMOR_SLOT;

import net.firefoxsalesman.dungeonsgear.enchantments.types.JumpingEnchantment;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentCategory;

public class SwiftfootedEnchantment extends JumpingEnchantment {

	public SwiftfootedEnchantment() {
		super(Rarity.RARE, EnchantmentCategory.ARMOR_FEET, ARMOR_SLOT);
	}

	public int getMaxLevel() {
		return 3;
	}

	@Override
	public boolean checkCompatibility(Enchantment enchantment) {
		return !(enchantment instanceof JumpingEnchantment);
	}
}
