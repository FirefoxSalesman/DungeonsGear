package net.firefoxsalesman.dungeonsgear.combat;

import com.alrex.parcool.api.Stamina;
import com.alrex.parcool.api.unstable.action.ParCoolActionEvent;
import com.alrex.parcool.common.action.impl.Dodge;
import com.alrex.parcool.common.action.impl.Roll;

import net.firefoxsalesman.dungeonsgear.GlobalEvents;
import net.firefoxsalesman.dungeonsgear.registry.AttributeInit;
import net.firefoxsalesman.dungeonslibs.utils.ModHelper;
import net.minecraft.world.entity.ai.attributes.AttributeInstance;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.SubscribeEvent;

// Borrowed from Extreme Evasion
public class ParcoolEvents {
	public void register() {
		MinecraftForge.EVENT_BUS.register(this);
	}

	@SubscribeEvent
	public void onParcoolAction(ParCoolActionEvent.Start.Post event) {
		if (ModHelper.hasMod("parcool")) {
			Player player = event.getPlayer();
			Object action = event.getAction();
			if (player != null && (action instanceof Roll || action instanceof Dodge)) {
				GlobalEvents.doRollEffects(player);
				AttributeInstance cooldown = player.getAttribute(AttributeInit.ROLL_COOLDOWN.get());
				AttributeInstance count = player.getAttribute(AttributeInit.ROLL_LIMIT.get());
				if (cooldown != null & count != null) {
					double modifier = cooldown.getValue() - (count.getValue() * 2);
					Stamina stamina = Stamina.get(player);
					if (modifier > 180) {
						stamina.consume((int) modifier - 180);
					} else if (modifier < 180) {
						stamina.recover(180 - (int) modifier);
					}
				}
			}
		}
	}
}
