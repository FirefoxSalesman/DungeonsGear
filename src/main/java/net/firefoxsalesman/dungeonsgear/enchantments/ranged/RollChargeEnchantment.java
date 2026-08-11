package net.firefoxsalesman.dungeonsgear.enchantments.ranged;

import net.firefoxsalesman.dungeonsgear.DungeonsGear;
import net.firefoxsalesman.dungeonsgear.enchantments.ModEnchantmentTypes;
import net.firefoxsalesman.dungeonsgear.enchantments.types.DungeonsEnchantment;
import net.firefoxsalesman.dungeonsgear.registry.EnchantmentInit;
import net.firefoxsalesman.dungeonsgear.registry.MobEffectInit;
import net.firefoxsalesman.dungeonslibs.event.BowEvent;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraftforge.event.entity.EntityJoinLevelEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = DungeonsGear.MOD_ID)
public class RollChargeEnchantment extends DungeonsEnchantment {

	public RollChargeEnchantment() {
		super(Rarity.RARE, ModEnchantmentTypes.RANGED, new EquipmentSlot[] {
				EquipmentSlot.MAINHAND });
	}

	public int getMaxLevel() {
		return 3;
	}

	public static void activateRollCharge(LivingEntity livingEntity) {
		int rollChargeLevel = EnchantmentHelper.getEnchantmentLevel(EnchantmentInit.ROLL_CHARGE.get(),
				livingEntity);

		if (rollChargeLevel > 0) {
			MobEffectInstance effectInstance = new MobEffectInstance(MobEffectInit.BOW_CHARGE.get(),
					30 * rollChargeLevel, 1);
			livingEntity.addEffect(effectInstance);
		}
	}

	@SubscribeEvent
	public static void onBowChargeTime(BowEvent.ChargeTime event) {
		LivingEntity livingEntity = event.getEntity();
		if (livingEntity == null)
			return;
		if (livingEntity.hasEffect(MobEffectInit.BOW_CHARGE.get())
				&& livingEntity.getEffect(MobEffectInit.BOW_CHARGE.get()).getAmplifier() > 0) {
			event.setChargeTime(1);
		}
	}

	@SubscribeEvent
	public static void onArrowJoinWorld(EntityJoinLevelEvent event) {
		if (event.getEntity() instanceof AbstractArrow) {
			AbstractArrow arrowEntity = (AbstractArrow) event.getEntity();
			Entity owner = arrowEntity.getOwner();
			if (owner instanceof LivingEntity) {
				LivingEntity livingEntity = (LivingEntity) owner;
				if (livingEntity.hasEffect(MobEffectInit.BOW_CHARGE.get())) {
					livingEntity.removeEffect(MobEffectInit.BOW_CHARGE.get());
				}
			}
		}
	}
}
