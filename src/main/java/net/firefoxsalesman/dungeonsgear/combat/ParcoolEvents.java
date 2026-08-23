package net.firefoxsalesman.dungeonsgear.combat;

import com.alrex.parcool.api.unstable.action.ParCoolActionEvent;
import com.alrex.parcool.common.action.impl.Dodge;
import com.alrex.parcool.common.action.impl.Roll;

import net.firefoxsalesman.dungeonsgear.GlobalEvents;
import net.firefoxsalesman.dungeonslibs.utils.ModHelper;
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
			if (player != null && (action instanceof Roll || action instanceof Dodge))
				GlobalEvents.doRollEffects(player);
		}
	}
}
