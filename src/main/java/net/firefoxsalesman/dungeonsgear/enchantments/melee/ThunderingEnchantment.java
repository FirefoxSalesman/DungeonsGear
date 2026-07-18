package net.firefoxsalesman.dungeonsgear.enchantments.melee;

import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraftforge.fml.common.Mod;

import static net.firefoxsalesman.dungeonsgear.DungeonsGear.MOD_ID;
import static net.firefoxsalesman.dungeonsgear.config.DungeonsGearConfig.THUNDERING_BASE_DAMAGE;
import static net.firefoxsalesman.dungeonsgear.config.DungeonsGearConfig.THUNDERING_CHANCE;

import net.firefoxsalesman.dungeonsgear.enchantments.ModEnchantmentTypes;
import net.firefoxsalesman.dungeonsgear.enchantments.types.AOEDamageEnchantment;
import net.firefoxsalesman.dungeonsgear.utilities.AreaOfEffectHelper;
import net.firefoxsalesman.dungeonsgear.utilities.SoundHelper;

@Mod.EventBusSubscriber(modid = MOD_ID)
public class ThunderingEnchantment extends AOEDamageEnchantment {

	public ThunderingEnchantment() {
		super(Rarity.RARE, ModEnchantmentTypes.MELEE, new EquipmentSlot[] {
				EquipmentSlot.MAINHAND });
	}

	public int getMaxLevel() {
		return 1;
	}

	@Override
	public void doPostAttack(LivingEntity user, Entity target, int level) {
		if (!(target instanceof LivingEntity))
			return;
		float chance = user.getRandom().nextFloat();
		if (chance <= THUNDERING_CHANCE.get()) {
			SoundHelper.playLightningStrikeSounds(user);
			AreaOfEffectHelper.electrifyNearbyEnemies(user, 5, THUNDERING_BASE_DAMAGE.get(),
					Integer.MAX_VALUE);
		}
	}
}
