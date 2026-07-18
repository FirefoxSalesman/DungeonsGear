package net.firefoxsalesman.dungeonsgear.enchantments.armor.legs;

import static net.firefoxsalesman.dungeonsgear.enchantments.ModEnchantmentTypes.ARMOR_SLOT;
import static net.firefoxsalesman.dungeonsgear.registry.EnchantmentInit.SNOWBALL;

import net.firefoxsalesman.dungeonsgear.DungeonsGear;
import net.firefoxsalesman.dungeonsgear.enchantments.types.PulseEnchantment;
import net.firefoxsalesman.dungeonsgear.utilities.ModEnchantmentHelper;
import net.firefoxsalesman.dungeonsgear.utilities.ProjectileEffectHelper;
import net.firefoxsalesman.dungeonslibs.capabilities.timers.Timers;
import net.firefoxsalesman.dungeonslibs.capabilities.timers.TimersHelper;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentCategory;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = DungeonsGear.MOD_ID)
public class SnowballEnchantment extends PulseEnchantment {

	public SnowballEnchantment() {
		super(Rarity.RARE, EnchantmentCategory.ARMOR_LEGS, ARMOR_SLOT);
	}

	@SubscribeEvent
	public static void onLivingUpdate(LivingEvent.LivingTickEvent event) {
		if (ModEnchantmentHelper.canEnchantmentTrigger(event.getEntity())) {
			triggerEffect(event.getEntity());
		}
	}

	public static void triggerEffect(LivingEntity livingEntity) {
		if (ModEnchantmentHelper.hasEnchantment(livingEntity, SNOWBALL.get())) {
			int enchantmentLevel = EnchantmentHelper.getEnchantmentLevel(SNOWBALL.get(),
					livingEntity);
			Timers timers = TimersHelper.getTimersCapability(livingEntity);
			if (timers == null)
				return;
			int currentTimer = timers.getEnchantmentTimer(SNOWBALL.get());
			if (currentTimer < 0) {
				timers.setEnchantmentTimer(SNOWBALL.get(),
						Math.max(100 - (enchantmentLevel - 1) * 40, 20));
			} else if (currentTimer == 0) {
				ProjectileEffectHelper.fireSnowballAtNearbyEnemy(livingEntity, 10);
				timers.setEnchantmentTimer(SNOWBALL.get(),
						Math.max(100 - (enchantmentLevel - 1) * 40, 20));
			}
		}
	}

	public int getMaxLevel() {
		return 3;
	}

	@Override
	public boolean checkCompatibility(Enchantment enchantment) {
		return !(enchantment instanceof PulseEnchantment);
	}
}
