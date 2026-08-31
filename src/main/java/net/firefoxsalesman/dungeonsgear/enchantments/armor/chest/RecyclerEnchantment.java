package net.firefoxsalesman.dungeonsgear.enchantments.armor.chest;

import net.firefoxsalesman.dungeonsgear.capabilities.combo.Combo;
import net.firefoxsalesman.dungeonsgear.capabilities.combo.ComboHelper;
import net.firefoxsalesman.dungeonsgear.config.DungeonsGearConfig;
import net.firefoxsalesman.dungeonsgear.enchantments.types.DropsEnchantment;
import net.firefoxsalesman.dungeonsgear.registry.EnchantmentInit;
import net.firefoxsalesman.dungeonsgear.utilities.ModEnchantmentHelper;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentCategory;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraftforge.event.entity.living.LivingDamageEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import static net.firefoxsalesman.dungeonsgear.DungeonsGear.MOD_ID;
import static net.firefoxsalesman.dungeonsgear.enchantments.ModEnchantmentTypes.ARMOR_SLOT;

@Mod.EventBusSubscriber(modid = MOD_ID)
public class RecyclerEnchantment extends DropsEnchantment {

	public RecyclerEnchantment() {
		super(Rarity.RARE, EnchantmentCategory.ARMOR_CHEST, ARMOR_SLOT);
	}

	public int getMaxLevel() {
		return 3;
	}

	@Override
	public boolean checkCompatibility(Enchantment enchantment) {
		return !(enchantment instanceof DropsEnchantment);
	}

	@SubscribeEvent
	public static void onPlayerDamaged(LivingDamageEvent event) {
		if (!(event.getEntity() instanceof Player))
			return;
		Player player = (Player) event.getEntity();
		if (player.isAlive()) {
			if (event.getSource().getDirectEntity() instanceof AbstractArrow) {
				Combo comboCap = ComboHelper.getComboCapability(player);
				if (ModEnchantmentHelper.hasEnchantment(player, EnchantmentInit.RECYCLER.get())) {
					int arrowsInCounter = comboCap.getArrowsInCounter();
					arrowsInCounter++;
					comboCap.setArrowsInCounter(arrowsInCounter);

					int recyclerLevel = EnchantmentHelper
							.getEnchantmentLevel(EnchantmentInit.RECYCLER.get(), player);
					if (comboCap.getArrowsInCounter() >= 40 - 7 * recyclerLevel) {
						ItemEntity arrowDrop = new ItemEntity(player.level(), player.getX(),
								player.getY(), player.getZ(),
								new ItemStack(Items.ARROW, 10));
						player.level().addFreshEntity(arrowDrop);
						comboCap.setArrowsInCounter(0);
					}
				}
			}
		}
	}
}
