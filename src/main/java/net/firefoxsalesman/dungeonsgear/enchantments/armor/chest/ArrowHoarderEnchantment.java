package net.firefoxsalesman.dungeonsgear.enchantments.armor.chest;

import net.firefoxsalesman.dungeonsgear.DungeonsGear;
import net.firefoxsalesman.dungeonsgear.enchantments.ModEnchantmentTypes;
import net.firefoxsalesman.dungeonsgear.enchantments.types.JumpingEnchantment;
import net.firefoxsalesman.dungeonsgear.registry.EnchantmentInit;
import net.firefoxsalesman.dungeonsgear.registry.ItemInit;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.monster.Enemy;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.enchantment.EnchantmentCategory;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraftforge.event.entity.living.LivingDropsEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.Collection;

@Mod.EventBusSubscriber(modid = DungeonsGear.MOD_ID)
public class ArrowHoarderEnchantment extends JumpingEnchantment {

	public ArrowHoarderEnchantment() {
		super(Rarity.UNCOMMON, EnchantmentCategory.ARMOR_CHEST, ModEnchantmentTypes.ARMOR_SLOT);
	}

	public int getMaxLevel() {
		return 3;
	}

	@SubscribeEvent
	public static void onArrowDrop(LivingDropsEvent event) {
		if (event.getSource().getEntity() instanceof LivingEntity) {
			LivingEntity attacker = (LivingEntity) event.getSource().getEntity();
			LivingEntity victim = event.getEntity();
			int maxLevel = EnchantmentHelper.getEnchantmentLevel(EnchantmentInit.ARROW_HOARDER.get(),
					attacker);
			int drops = (maxLevel / 4);
			drops += attacker.getRandom().nextFloat() <= (maxLevel % 4) / 4.0F ? 1 : 0;
			Collection<ItemEntity> itemEntities = event.getDrops();
			if (drops > 0 && victim instanceof Enemy && itemEntities.stream()
					.anyMatch(itemEntity -> itemEntity.getItem().getItem().equals(Items.ARROW))) {
				ItemEntity arrowDrop = new ItemEntity(victim.level(), victim.getX(), victim.getY(),
						victim.getZ(), new ItemStack(ItemInit.ARROW_BUNDLE.get(), drops));
				itemEntities.add(arrowDrop);
			}
		}
	}
}
