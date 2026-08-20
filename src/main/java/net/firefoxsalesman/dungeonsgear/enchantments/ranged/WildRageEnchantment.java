package net.firefoxsalesman.dungeonsgear.enchantments.ranged;

import net.firefoxsalesman.dungeonsgear.DungeonsGear;
import net.firefoxsalesman.dungeonsgear.enchantments.ModEnchantmentTypes;
import net.firefoxsalesman.dungeonsgear.enchantments.types.DungeonsEnchantment;
import net.firefoxsalesman.dungeonsgear.entities.goals.WildRageAttackGoal;
import net.firefoxsalesman.dungeonsgear.registry.EnchantmentInit;
import net.firefoxsalesman.dungeonsgear.utilities.ModEnchantmentHelper;
import net.firefoxsalesman.dungeonslibs.utils.ArrowHelper;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.monster.Enemy;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.HitResult;
import net.minecraftforge.event.entity.ProjectileImpactEvent;
import net.minecraftforge.event.entity.living.LivingAttackEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = DungeonsGear.MOD_ID)
public class WildRageEnchantment extends DungeonsEnchantment {

	public static final String INTRINSIC_WILD_RAGE = "IntrinsicWildRage";

	public WildRageEnchantment() {
		super(Rarity.RARE, ModEnchantmentTypes.ARMOR_RANGED, new EquipmentSlot[] {
				EquipmentSlot.MAINHAND,
				EquipmentSlot.HEAD,
				EquipmentSlot.CHEST,
				EquipmentSlot.LEGS,
				EquipmentSlot.FEET });
	}

	public int getMaxLevel() {
		return 3;
	}

	@SubscribeEvent
	public static void onPinkScoundrelImpact(ProjectileImpactEvent event) {
		HitResult rayTraceResult = event.getRayTraceResult();
		if (!ModEnchantmentHelper.arrowHitMob(rayTraceResult))
			return;
		if (event.getProjectile() instanceof AbstractArrow arrow) {
			if (!ModEnchantmentHelper.shooterIsLiving(arrow))
				return;
			LivingEntity shooter = (LivingEntity) arrow.getOwner();
			int wildRageLevel = ArrowHelper.enchantmentTagToLevel(arrow, EnchantmentInit.WILD_RAGE.get());
			Mob victim = (Mob) ((EntityHitResult) rayTraceResult).getEntity();
			if (!(victim instanceof Enemy) || !(victim.canChangeDimensions()))
				return;
			if (wildRageLevel > 0) {
				float wildRageChance = 0.1F;
				wildRageChance += wildRageLevel * 0.1F;

				float chance = shooter.getRandom().nextFloat();
				if (chance <= wildRageChance) {
					sendIntoWildRage(victim);
				}
			}
		}
	}

	@SubscribeEvent
	public static void onWildRageAttack(LivingAttackEvent event) {
		if (!(event.getSource().getEntity() instanceof LivingEntity))
			return;
		LivingEntity attacker = (LivingEntity) event.getSource().getEntity();
		LivingEntity victim = event.getEntity();
		if (!(victim instanceof Enemy) || !(victim.canChangeDimensions()))
			return;
		Mob enemy = (Mob) victim;
		if ((ModEnchantmentHelper.hasEnchantment(attacker, EnchantmentInit.WILD_RAGE.get()))) {
			int wildRageLevel = EnchantmentHelper.getEnchantmentLevel(EnchantmentInit.WILD_RAGE.get(),
					attacker);
			float wildRageChance = 0.1F;
			wildRageChance += wildRageLevel * 0.1F;

			float chance = attacker.getRandom().nextFloat();
			if (chance <= wildRageChance) {
				sendIntoWildRage(enemy);
			}
		}
	}

	public static void sendIntoWildRage(Mob mobEntity) {
		mobEntity.targetSelector.addGoal(0, new WildRageAttackGoal(mobEntity));
		DungeonsGear.PROXY.spawnParticles(mobEntity, ParticleTypes.ANGRY_VILLAGER);
	}
}
