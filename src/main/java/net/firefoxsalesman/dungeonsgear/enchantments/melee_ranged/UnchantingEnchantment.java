package net.firefoxsalesman.dungeonsgear.enchantments.melee_ranged;

import static net.firefoxsalesman.dungeonsgear.DungeonsGear.MOD_ID;

import baguchan.enchantwithmob.api.IEnchantCap;
import baguchan.enchantwithmob.capability.MobEnchantCapability;
import net.firefoxsalesman.dungeonsgear.enchantments.ModEnchantmentTypes;
import net.firefoxsalesman.dungeonsgear.enchantments.types.AOEDamageEnchantment;
import net.firefoxsalesman.dungeonsgear.enchantments.types.DamageBoostEnchantment;
import net.firefoxsalesman.dungeonsgear.registry.EnchantmentInit;
import net.firefoxsalesman.dungeonsgear.utilities.ModEnchantmentHelper;
import net.firefoxsalesman.dungeonslibs.utils.ModHelper;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.DamageEnchantment;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraftforge.event.entity.living.LivingDamageEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = MOD_ID)
public class UnchantingEnchantment extends DamageBoostEnchantment {
	public UnchantingEnchantment() {
		super(Rarity.RARE, ModEnchantmentTypes.MELEE_RANGED, new EquipmentSlot[] {
				EquipmentSlot.MAINHAND });
	}

	@SubscribeEvent(priority = EventPriority.LOWEST)
	public static void onDamage(LivingDamageEvent event) {
		LivingEntity defender = event.getEntity();
		if (ModHelper.hasMod("enchantwithmob")
				&& event.getSource().getEntity() instanceof LivingEntity attacker) {
			MobEnchantCapability enchantCap = defender instanceof IEnchantCap enchantedEntity
					? enchantedEntity.getEnchantCap()
					: new MobEnchantCapability();
			ItemStack mainhand = attacker.getMainHandItem();
			if (enchantCap.hasEnchant() && ModEnchantmentHelper.hasEnchantment(mainhand,
					EnchantmentInit.UNCHANTING.get()))
				event.setAmount(event.getAmount() + (event.getAmount()
						* (((mainhand.getEnchantmentLevel(EnchantmentInit.UNCHANTING.get())
								- 1)
								* .25F) + .25F)));

		}
	}

	@Override
	public int getMaxLevel() {
		return 3;
	}

	@Override
	public boolean checkCompatibility(Enchantment enchantment) {
		return !(enchantment instanceof DamageEnchantment)
				&& !(enchantment instanceof DamageBoostEnchantment)
				&& !(enchantment instanceof AOEDamageEnchantment);
	}

	@Override
	public boolean canEnchant(ItemStack stack) {
		return super.canEnchant(stack) && !ModHelper.hasMod("enchantwithmob");
	}

	@Override
	public boolean canApplyAtEnchantingTable(ItemStack stack) {
		return super.canApplyAtEnchantingTable(stack) && !ModHelper.hasMod("enchantwithmob");
	}
}
