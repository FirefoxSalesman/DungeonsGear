package net.firefoxsalesman.dungeonsgear.enchantments.ranged;

import net.firefoxsalesman.dungeonsgear.enchantments.types.DamageBoostEnchantment;
import net.firefoxsalesman.dungeonsgear.registry.EnchantmentInit;
import net.firefoxsalesman.dungeonsgear.registry.MobEffectInit;
import net.firefoxsalesman.dungeonsgear.tags.EntityTags;
import net.firefoxsalesman.dungeonsgear.utilities.ModEnchantmentHelper;
import net.firefoxsalesman.dungeonsgear.enchantments.types.AOEDamageEnchantment;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.DamageEnchantment;
import net.firefoxsalesman.dungeonsgear.enchantments.ModEnchantmentTypes;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraftforge.event.entity.living.LivingDamageEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.firefoxsalesman.dungeonsgear.DungeonsGear;

@Mod.EventBusSubscriber(modid = DungeonsGear.MOD_ID)
public class VoidShotEnchantment extends DamageBoostEnchantment {
	public VoidShotEnchantment() {
		super(Enchantment.Rarity.RARE, ModEnchantmentTypes.RANGED, new EquipmentSlot[] {
				EquipmentSlot.MAINHAND });
	}

	@Override
	public boolean checkCompatibility(Enchantment enchantment) {
		return !(enchantment instanceof DamageEnchantment)
				&& !(enchantment instanceof DamageBoostEnchantment)
				&& !(enchantment instanceof AOEDamageEnchantment);
	}

	public int getMaxLevel() {
		return 3;
	}

	@SubscribeEvent
	public static void onLivingDamageEvent(LivingDamageEvent event) {
		LivingEntity defender = event.getEntity();
		if (defender.getEffect(MobEffectInit.VOID_STRIKE.get()) != null
				|| defender.getType().is(EntityTags.VOID_STRIKE_IMMUNE))
			return;
		MobEffectInstance voidShot = defender.getEffect(MobEffectInit.VOID_SHOT.get());
		if (voidShot != null) {
			float voidShotAmplifier = (float) Math.max(25,
					(voidShot.getAmplifier() + 1) * (101 - voidShot.getDuration())) / 100F;
			event.setAmount((event.getAmount() * voidShotAmplifier) + event.getAmount());
			defender.removeEffect(MobEffectInit.VOID_SHOT.get());

		} else if (event.getSource().getEntity() instanceof LivingEntity attacker) {
			ItemStack mainhand = attacker.getMainHandItem();
			if (ModEnchantmentHelper.hasEnchantment(mainhand, EnchantmentInit.VOID_SHOT.get()))
				defender.addEffect(new MobEffectInstance(MobEffectInit.VOID_SHOT.get(), 100,
						mainhand.getEnchantmentLevel(EnchantmentInit.VOID_SHOT.get()) - 1));

		}
	}
}
