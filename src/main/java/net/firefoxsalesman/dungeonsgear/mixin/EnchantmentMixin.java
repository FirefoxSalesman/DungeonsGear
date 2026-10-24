package net.firefoxsalesman.dungeonsgear.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import net.firefoxsalesman.dungeonsgear.config.DungeonsGearConfig;
import net.firefoxsalesman.dungeonsgear.utilities.SoulHelper;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.SoulSpeedEnchantment;

@Mixin(Enchantment.class)
public class EnchantmentMixin {
	private boolean shouldApply(ItemStack stack) {
		return ((Object) this) instanceof SoulSpeedEnchantment && !DungeonsGearConfig.SOUL_SPEED_OVERHAUL.get()
				.equals(DungeonsGearConfig.SoulSpeedType.VANILLA);
	}

	@Inject(at = @At(value = "RETURN"), method = "canEnchant", cancellable = true)
	private void canEnchant(ItemStack stack, CallbackInfoReturnable<Boolean> callback) {
		if (shouldApply(stack))
			callback.setReturnValue(SoulHelper.isSoulItem(stack));
	}

	@Inject(at = @At(value = "RETURN"), method = "canApplyAtEnchantingTable", remap = false, cancellable = true)
	private void canApplyAtEnchantingTable(ItemStack stack, CallbackInfoReturnable<Boolean> callback) {
		if (shouldApply(stack))
			callback.setReturnValue(SoulHelper.isSoulItem(stack));
	}
}
