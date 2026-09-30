package net.firefoxsalesman.dungeonsgear.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import net.firefoxsalesman.dungeonsgear.config.DungeonsGearConfig;
import net.firefoxsalesman.dungeonsgear.config.DungeonsGearConfig.SoulSpeedType;
import net.minecraft.world.entity.LivingEntity;

@Mixin(LivingEntity.class)
public class LivingEntityMixin {
	@Inject(at = @At("HEAD"), method = "tryAddSoulSpeed", cancellable = true)
	private void dontAddSoulSpeed(CallbackInfo ci) {
		if (DungeonsGearConfig.SOUL_SPEED_OVERHAUL.get().equals(SoulSpeedType.DUNGEONS))
			ci.cancel();
	}
}
