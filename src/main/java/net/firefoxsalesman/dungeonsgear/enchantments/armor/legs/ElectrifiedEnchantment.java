package net.firefoxsalesman.dungeonsgear.enchantments.armor.legs;

import static net.firefoxsalesman.dungeonsgear.enchantments.ModEnchantmentTypes.ARMOR_SLOT;

import net.firefoxsalesman.dungeonsgear.enchantments.types.JumpingEnchantment;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentCategory;

public class ElectrifiedEnchantment extends JumpingEnchantment {

	public ElectrifiedEnchantment() {
		super(Rarity.RARE, EnchantmentCategory.ARMOR_LEGS, ARMOR_SLOT);
	}

	public int getMaxLevel() {
		return 1;
	}

	@Override
	public boolean checkCompatibility(Enchantment enchantment) {
		return !(enchantment instanceof JumpingEnchantment);
	}

}
