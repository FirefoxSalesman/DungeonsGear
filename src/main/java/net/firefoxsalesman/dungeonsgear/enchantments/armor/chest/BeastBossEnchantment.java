package net.firefoxsalesman.dungeonsgear.enchantments.armor.chest;

import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.enchantment.EnchantmentCategory;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraftforge.event.entity.living.LivingDamageEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import static net.firefoxsalesman.dungeonsgear.enchantments.ModEnchantmentTypes.ARMOR_SLOT;

import net.firefoxsalesman.dungeonsgear.DungeonsGear;
import net.firefoxsalesman.dungeonsgear.config.DungeonsGearConfig;
import net.firefoxsalesman.dungeonsgear.enchantments.types.BeastEnchantment;
import net.firefoxsalesman.dungeonsgear.registry.EnchantmentInit;
import net.firefoxsalesman.dungeonslibs.capabilities.minionmaster.Follower;
import net.firefoxsalesman.dungeonslibs.capabilities.minionmaster.FollowerLeaderHelper;

@Mod.EventBusSubscriber(modid = DungeonsGear.MOD_ID)
public class BeastBossEnchantment extends BeastEnchantment {
	public BeastBossEnchantment() {
		super(Rarity.RARE, EnchantmentCategory.ARMOR_CHEST, ARMOR_SLOT);
	}

	@Override
	public int getMaxLevel() {
		return 3;
	}

	@SubscribeEvent
	public static void onBeastDamage(LivingDamageEvent event) {
		LivingEntity target = event.getEntity();
		DamageSource source = event.getSource();
		Entity trueSource = source.getEntity();
		if (trueSource == null)
			return;

		if (trueSource.level() instanceof ServerLevel
				&& FollowerLeaderHelper.isFollower(trueSource)) {
			Follower attackerSummonableCap = FollowerLeaderHelper.getFollowerCapability(trueSource);

			LivingEntity beastOwner = attackerSummonableCap.getLeader();
			if (beastOwner != null) {
				if (!FollowerLeaderHelper.isFollowerOf(target, beastOwner)) {
					int beastBossLevel = EnchantmentHelper.getEnchantmentLevel(
							EnchantmentInit.BEAST_BOSS.get(), beastOwner);
					if (beastBossLevel > 0) {
						float beastBossFactor = (float) (DungeonsGearConfig.BEAST_BOSS_BASE_MULTIPLIER
								.get()
								+ (DungeonsGearConfig.BEAST_BOSS_MULTIPLIER_PER_LEVEL
										.get() * beastBossLevel));
						float currentDamage = event.getAmount();
						float newDamage = currentDamage * beastBossFactor;
						event.setAmount(newDamage);
					}
				}
			}
		}
	}

}
