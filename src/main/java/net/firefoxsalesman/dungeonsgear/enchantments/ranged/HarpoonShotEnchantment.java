package net.firefoxsalesman.dungeonsgear.enchantments.ranged;

import net.firefoxsalesman.dungeonsgear.DungeonsGear;
import net.firefoxsalesman.dungeonsgear.enchantments.ModEnchantmentTypes;
import net.firefoxsalesman.dungeonsgear.enchantments.types.DungeonsEnchantment;
import net.firefoxsalesman.dungeonsgear.items.artifacts.HarpoonQuiverItem;
import net.firefoxsalesman.dungeonsgear.registry.EnchantmentInit;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraftforge.event.entity.EntityJoinLevelEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = DungeonsGear.MOD_ID)
public class HarpoonShotEnchantment extends DungeonsEnchantment {

	public HarpoonShotEnchantment() {
		super(Rarity.RARE, ModEnchantmentTypes.RANGED, new EquipmentSlot[] {
				EquipmentSlot.MAINHAND });
	}

	@Override
	public int getMaxLevel() {
		return 1;
	}

	@SubscribeEvent
	public static void onArrowJoinWorld(EntityJoinLevelEvent event) {
		if (event.getEntity() instanceof AbstractArrow && !event.getLevel().isClientSide()) {
			AbstractArrow arrowEntity = (AbstractArrow) event.getEntity();
			if (arrowEntity.getOwner() instanceof LivingEntity) {
				LivingEntity livingEntity = (LivingEntity) arrowEntity.getOwner();
				int enchantmentLevel = EnchantmentHelper
						.getEnchantmentLevel(EnchantmentInit.HARPOON_SHOT.get(), livingEntity);
				if (enchantmentLevel > 0) {
					arrowEntity.addTag(HarpoonQuiverItem.HARPOON_QUIVER);
					arrowEntity.setDeltaMovement(arrowEntity.getDeltaMovement().scale(1.5D));
					arrowEntity.setPierceLevel(
							(byte) (arrowEntity.getPierceLevel() + enchantmentLevel));
				}
			}
		}
	}
}
