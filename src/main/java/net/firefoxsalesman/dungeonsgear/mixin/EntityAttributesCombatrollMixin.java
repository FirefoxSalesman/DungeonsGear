package net.firefoxsalesman.dungeonsgear.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import net.combatroll.api.Enchantments_CombatRoll;
import net.combatroll.api.EntityAttributes_CombatRoll;
import net.combatroll.api.EntityAttributes_CombatRoll.Type;
import net.firefoxsalesman.dungeonsgear.registry.AttributeInit;
import net.minecraft.world.entity.ai.attributes.AttributeInstance;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.enchantment.EnchantmentHelper;

@Mixin(EntityAttributes_CombatRoll.class)
public class EntityAttributesCombatrollMixin {
	@Inject(at = @At(value = "RETURN"), method = "getAttributeValue", remap = false, cancellable = true)
	private static void getAttributeValue(Player player, Type type, CallbackInfoReturnable<Double> cir) {
		AttributeInstance count = player.getAttribute(AttributeInit.ROLL_LIMIT.get());
		if (type.equals(Type.COUNT) && count != null) {
			double value = player.getAttributeValue(EntityAttributes_CombatRoll.COUNT);
			int level = EnchantmentHelper.getEnchantmentLevel(Enchantments_CombatRoll.COUNT, player);
			value = Enchantments_CombatRoll.COUNT.apply(value, level);
			cir.setReturnValue(value + count.getValue() - 1);
		}
	}
}
