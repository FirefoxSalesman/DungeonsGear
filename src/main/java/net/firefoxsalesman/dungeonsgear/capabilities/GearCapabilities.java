package net.firefoxsalesman.dungeonsgear.capabilities;

import net.minecraft.world.entity.Entity;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.common.capabilities.CapabilityToken;
import net.minecraftforge.common.capabilities.RegisterCapabilitiesEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import static net.firefoxsalesman.dungeonsgear.DungeonsGear.MOD_ID;

import net.firefoxsalesman.dungeonsgear.capabilities.bow.AttacherRangedAbilities;
import net.firefoxsalesman.dungeonsgear.capabilities.bow.RangedAbilities;
import net.firefoxsalesman.dungeonsgear.capabilities.combo.AttacherCombo;
import net.firefoxsalesman.dungeonsgear.capabilities.combo.Combo;
import net.firefoxsalesman.dungeonsgear.capabilities.offhand.AttacherDualWield;
import net.firefoxsalesman.dungeonsgear.capabilities.offhand.DualWield;

@Mod.EventBusSubscriber(modid = MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class GearCapabilities {

	public static final Capability<RangedAbilities> RANGED_ABILITIES_CAPABILITY = CapabilityManager
			.get(new CapabilityToken<>() {
			});
	public static final Capability<Combo> COMBO_CAPABILITY = CapabilityManager.get(new CapabilityToken<>() {
	});
	public static final Capability<DualWield> DUAL_WIELD_CAPABILITY = CapabilityManager
			.get(new CapabilityToken<>() {
			});

	public static void setupCapabilities() {
		IEventBus forgeBus = MinecraftForge.EVENT_BUS;
		forgeBus.addGenericListener(ItemStack.class, AttacherRangedAbilities::attach);
		forgeBus.addGenericListener(Entity.class, AttacherCombo::attach);
		forgeBus.addGenericListener(ItemStack.class, AttacherDualWield::attach);
	}

	@SubscribeEvent
	public static void registerCaps(RegisterCapabilitiesEvent event) {
		event.register(RangedAbilities.class);
		event.register(Combo.class);
		event.register(DualWield.class);
	}
}
