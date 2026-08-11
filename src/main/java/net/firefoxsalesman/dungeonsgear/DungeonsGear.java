package net.firefoxsalesman.dungeonsgear;

import org.slf4j.Logger;

import com.mojang.logging.LogUtils;

import net.firefoxsalesman.dungeonsgear.client.ClientProxy;
import net.firefoxsalesman.dungeonsgear.config.DungeonsGearConfig;
import net.firefoxsalesman.dungeonsgear.items.GearRangedItemModelProperties;
import net.firefoxsalesman.dungeonsgear.network.NetworkHandler;
import net.firefoxsalesman.dungeonsgear.registry.AttributeInit;
import net.firefoxsalesman.dungeonsgear.registry.EnchantmentInit;
import net.firefoxsalesman.dungeonsgear.registry.EntityTypeInit;
import net.firefoxsalesman.dungeonsgear.registry.ItemInit;
import net.firefoxsalesman.dungeonsgear.registry.MobEffectInit;
import net.firefoxsalesman.dungeonsgear.registry.ParticleInit;
import net.firefoxsalesman.dungeonsgear.registry.SoundEventInit;
import net.firefoxsalesman.dungeonsgear.entities.SoulWizardEntity;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.Item;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.BuildCreativeModeTabContentsEvent;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.event.server.ServerStartingEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.RegistryObject;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(DungeonsGear.MOD_ID)
public class DungeonsGear {
	// Define mod id in a common place for everything to reference
	public static final String MOD_ID = "dungeonsgear";
	// Directly reference a slf4j logger
	public static final Logger LOGGER = LogUtils.getLogger();
	public static CommonProxy PROXY;

	public DungeonsGear() {
		FMLJavaModLoadingContext.get().getModEventBus().addListener(this::setup);
		FMLJavaModLoadingContext.get().getModEventBus().addListener(this::doClientStuff);

		IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
		modEventBus.addListener(this::commonSetup);

		MinecraftForge.EVENT_BUS.register(this);
		modEventBus.addListener(this::addCreative);

		new DungeonsGearConfig();
		PROXY = DistExecutor.safeRunForDist(() -> ClientProxy::new, () -> CommonProxy::new);
		ParticleInit.register(modEventBus);
		AttributeInit.register(modEventBus);
		SoundEventInit.register(modEventBus);
		EntityTypeInit.register(modEventBus);
		FMLJavaModLoadingContext.get().getModEventBus().addListener(this::initEntityTypeAttributes);
		ItemInit.register(modEventBus);
		MobEffectInit.register(modEventBus);
		EnchantmentInit.register(modEventBus);
	}

	private void commonSetup(final FMLCommonSetupEvent event) {
	}

	private void addCreative(BuildCreativeModeTabContentsEvent event) {
		if (event.getTabKey() == CreativeModeTabs.COMBAT)
			ItemInit.getEntries().forEach((RegistryObject<Item> item) -> event.accept(item));
	}

	private void setup(final FMLCommonSetupEvent event) {
		event.enqueueWork(NetworkHandler::init);
	}

	// You can use SubscribeEvent and let the Event Bus discover methods to call
	@SubscribeEvent
	public void onServerStarting(ServerStartingEvent event) {
	}

	private void doClientStuff(final FMLClientSetupEvent event) {
		GearRangedItemModelProperties.init();
	}

	// You can use EventBusSubscriber to automatically register all static methods
	// in the class annotated with @SubscribeEvent
	@Mod.EventBusSubscriber(modid = MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
	public static class ClientModEvents {
		@SubscribeEvent
		public static void onClientSetup(FMLClientSetupEvent event) {
		}
	}

	public void initEntityTypeAttributes(EntityAttributeCreationEvent event) {
		event.put(EntityTypeInit.SOUL_WIZARD.get(), SoulWizardEntity.setCustomAttributes().build());
	}
}
