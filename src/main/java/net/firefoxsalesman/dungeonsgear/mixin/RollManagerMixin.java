package net.firefoxsalesman.dungeonsgear.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import net.combatroll.CombatRoll;
import net.combatroll.api.EntityAttributes_CombatRoll;
import net.combatroll.api.EntityAttributes_CombatRoll.Type;
import net.combatroll.internals.RollManager;
import net.firefoxsalesman.dungeonsgear.registry.AttributeInit;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.world.entity.ai.attributes.AttributeInstance;

@Mixin(RollManager.class)
public abstract class RollManagerMixin {
	@Shadow
	private int currentCooldownLength;

	@Inject(at = @At(value = "TAIL"), method = "updateCooldownLength", remap = false, cancellable = true)
	private void updateCooldownLength(LocalPlayer player, CallbackInfo ci) {
		AttributeInstance cooldown = player.getAttribute(AttributeInit.ROLL_COOLDOWN.get());
		if (cooldown != null) {
			float duration = CombatRoll.config.roll_cooldown;
			this.currentCooldownLength = (int) Math.round((double) (duration * 20.0F) * ((double) 20.0F
					/ (EntityAttributes_CombatRoll.getAttributeValue(player, Type.RECHARGE)
							- Math.min(19, (cooldown.getValue()
									- cooldown.getBaseValue()) / 20))));
		}
	}
}
