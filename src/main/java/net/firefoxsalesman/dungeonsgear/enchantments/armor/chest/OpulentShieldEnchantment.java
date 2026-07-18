package net.firefoxsalesman.dungeonsgear.enchantments.armor.chest;

import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.enchantment.EnchantmentCategory;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraftforge.event.entity.player.PlayerXpEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import static net.firefoxsalesman.dungeonsgear.enchantments.ModEnchantmentTypes.ARMOR_SLOT;

import net.firefoxsalesman.dungeonsgear.DungeonsGear;
import net.firefoxsalesman.dungeonsgear.enchantments.types.FocusEnchantment;
import net.firefoxsalesman.dungeonsgear.registry.EnchantmentInit;

@Mod.EventBusSubscriber(modid = DungeonsGear.MOD_ID)
public class OpulentShieldEnchantment extends FocusEnchantment {

	public OpulentShieldEnchantment() {
		super(Rarity.RARE, EnchantmentCategory.ARMOR_CHEST, ARMOR_SLOT);
	}

	@Override
	public int getMaxLevel() {
		return 3;
	}

	@SubscribeEvent
	public static void onPickupXp(PlayerXpEvent.PickupXp event) {
		Player player = event.getEntity();
		if (player.level().isClientSide)
			return;
		int opulentShieldLevel = EnchantmentHelper.getEnchantmentLevel(EnchantmentInit.OPULENT_SHIELD.get(),
				player);
		int invulnerableTime = 20 * opulentShieldLevel;
		if (player.invulnerableTime >= invulnerableTime)
			return;
		player.invulnerableTime = invulnerableTime;
	}

}
