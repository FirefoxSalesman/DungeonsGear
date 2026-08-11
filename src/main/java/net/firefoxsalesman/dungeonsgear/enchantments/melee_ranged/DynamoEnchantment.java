package net.firefoxsalesman.dungeonsgear.enchantments.melee_ranged;

import net.minecraft.util.Mth;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.DamageEnchantment;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraftforge.event.entity.living.LivingDamageEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import static net.firefoxsalesman.dungeonsgear.config.DungeonsGearConfig.DYNAMO_DAMAGE_MULTIPLIER_PER_STACK;
import static net.firefoxsalesman.dungeonsgear.config.DungeonsGearConfig.DYNAMO_MAX_STACKS;

import net.firefoxsalesman.dungeonsgear.DungeonsGear;
import net.firefoxsalesman.dungeonsgear.enchantments.ModEnchantmentTypes;
import net.firefoxsalesman.dungeonsgear.enchantments.types.AOEDamageEnchantment;
import net.firefoxsalesman.dungeonsgear.enchantments.types.DamageBoostEnchantment;
import net.firefoxsalesman.dungeonsgear.registry.EnchantmentInit;
import net.firefoxsalesman.dungeonsgear.registry.MobEffectInit;
import net.firefoxsalesman.dungeonsgear.utilities.ModEnchantmentHelper;

@Mod.EventBusSubscriber(modid = DungeonsGear.MOD_ID)
public class DynamoEnchantment extends DamageBoostEnchantment {

	public DynamoEnchantment() {
		super(Enchantment.Rarity.RARE, ModEnchantmentTypes.MELEE_RANGED, new EquipmentSlot[] {
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

	public static void handleAddDynamoEnchantment(Player playerEntity) {
		ItemStack mainhand = playerEntity.getMainHandItem();
		if (ModEnchantmentHelper.hasEnchantment(mainhand, EnchantmentInit.DYNAMO.get())) {
			int dynamoLevel = EnchantmentHelper.getItemEnchantmentLevel(EnchantmentInit.DYNAMO.get(),
					mainhand);
			MobEffectInstance currentEffectInstance = playerEntity.getEffect(MobEffectInit.DYNAMO.get());
			int i = dynamoLevel;
			if (currentEffectInstance != null) {
				i += currentEffectInstance.getAmplifier();
			}
			i = Mth.clamp(i, 0, DYNAMO_MAX_STACKS.get());
			MobEffectInstance effectinstance = new MobEffectInstance(MobEffectInit.DYNAMO.get(), 120000,
					i - 1);
			playerEntity.addEffect(effectinstance);
		}
	}

	@SubscribeEvent
	public static void onLivingDamageEvent(LivingDamageEvent event) {
		if (event.getSource().getEntity() instanceof Player) {
			Player playerEntity = (Player) event.getSource().getEntity();
			ItemStack mainhand = playerEntity.getMainHandItem();
			if (ModEnchantmentHelper.hasEnchantment(mainhand, EnchantmentInit.DYNAMO.get())) {
				MobEffectInstance effectinstance = playerEntity.getEffect(MobEffectInit.DYNAMO.get());
				if (effectinstance != null) {
					int dynamoAmplifier = effectinstance.getAmplifier() + 1;
					event.setAmount((float) (event.getAmount() * (1
							+ dynamoAmplifier * DYNAMO_DAMAGE_MULTIPLIER_PER_STACK.get())));
					playerEntity.removeEffect(MobEffectInit.DYNAMO.get());
				}
			}
		}
	}

}
