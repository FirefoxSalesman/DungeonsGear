package net.firefoxsalesman.dungeonsgear.enchantments.armor.legs;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentCategory;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import static net.firefoxsalesman.dungeonsgear.enchantments.ModEnchantmentTypes.ARMOR_SLOT;

import net.firefoxsalesman.dungeonsgear.DungeonsGear;
import net.firefoxsalesman.dungeonsgear.enchantments.types.PulseEnchantment;
import net.firefoxsalesman.dungeonsgear.registry.EnchantmentInit;
import net.firefoxsalesman.dungeonsgear.utilities.AreaOfEffectHelper;
import net.firefoxsalesman.dungeonsgear.utilities.ModEnchantmentHelper;
import net.firefoxsalesman.dungeonslibs.capabilities.timers.Timers;
import net.firefoxsalesman.dungeonslibs.capabilities.timers.TimersHelper;

@Mod.EventBusSubscriber(modid = DungeonsGear.MOD_ID)
public class ChillingEnchantment extends PulseEnchantment {

	public ChillingEnchantment() {
		super(Rarity.RARE, EnchantmentCategory.ARMOR_LEGS, ARMOR_SLOT);
	}

	public int getMaxLevel() {
		return 3;
	}

	@Override
	public boolean checkCompatibility(Enchantment enchantment) {
		return !(enchantment instanceof PulseEnchantment);
	}

	@SubscribeEvent
	public static void onLivingUpdate(LivingEvent.LivingTickEvent event) {
		if (ModEnchantmentHelper.canEnchantmentTrigger(event.getEntity())) {
			triggerEffect(event.getEntity());
		}
	}

	public static void triggerEffect(LivingEntity livingEntity) {
		if (ModEnchantmentHelper.hasEnchantment(livingEntity, EnchantmentInit.CHILLING.get())) {
			Timers timers = TimersHelper.getTimersCapability(livingEntity);
			if (timers == null)
				return;
			int currentTimer = timers.getEnchantmentTimer(EnchantmentInit.CHILLING.get());
			if (currentTimer < 0) {
				timers.setEnchantmentTimer(EnchantmentInit.CHILLING.get(), 40);
			} else if (currentTimer == 0) {
				int enchantmentLevel = EnchantmentHelper
						.getEnchantmentLevel(EnchantmentInit.CHILLING.get(), livingEntity);
				AreaOfEffectHelper.freezeNearbyEnemies(livingEntity, enchantmentLevel - 1, 1.5F, 1);
				timers.setEnchantmentTimer(EnchantmentInit.CHILLING.get(), 40);
			}
		}
	}

}
