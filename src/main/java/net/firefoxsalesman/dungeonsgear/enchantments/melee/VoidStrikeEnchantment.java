package net.firefoxsalesman.dungeonsgear.enchantments.melee;

import net.firefoxsalesman.dungeonsgear.enchantments.types.DamageBoostEnchantment;
import net.firefoxsalesman.dungeonsgear.registry.EnchantmentInit;
import net.firefoxsalesman.dungeonsgear.registry.MobEffectInit;
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
public class VoidStrikeEnchantment extends DamageBoostEnchantment {
	public VoidStrikeEnchantment() {
		super(Enchantment.Rarity.RARE, ModEnchantmentTypes.MELEE, new EquipmentSlot[] {
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
		if (event.getEntity().getEffect(MobEffectInit.VOID_SHOT.get()) != null)
			return;
		MobEffectInstance voidStrike = event.getEntity().getEffect(MobEffectInit.VOID_STRIKE.get());
		if (voidStrike != null) {
			float voidStrikeAmplifier = (float) Math.max(25,
					(voidStrike.getAmplifier() + 1) * (101 - voidStrike.getDuration()) * 2) / 100F;
			event.setAmount((event.getAmount() * voidStrikeAmplifier) + event.getAmount());
			event.getEntity().removeEffect(MobEffectInit.VOID_STRIKE.get());

		} else if (event.getSource().getEntity() instanceof LivingEntity attacker) {
			ItemStack mainhand = attacker.getMainHandItem();
			if (ModEnchantmentHelper.hasEnchantment(mainhand, EnchantmentInit.VOID_STRIKE.get()))
				event.getEntity().addEffect(new MobEffectInstance(MobEffectInit.VOID_STRIKE.get(), 100,
						mainhand.getEnchantmentLevel(EnchantmentInit.VOID_STRIKE.get()) - 1));

		}
	}
}
