package net.firefoxsalesman.dungeonsgear.loot;

import net.firefoxsalesman.dungeonsgear.DungeonsGear;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.storage.loot.Serializer;
import net.minecraft.world.level.storage.loot.functions.LootItemFunction;
import net.minecraft.world.level.storage.loot.functions.LootItemFunctionType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

public class ModLootFunctionTypes {
	private static final DeferredRegister<LootItemFunctionType> LOOT_FUNCTIONS = DeferredRegister
			.create(Registries.LOOT_FUNCTION_TYPE, DungeonsGear.MOD_ID);
	public static final RegistryObject<LootItemFunctionType> ADD_POTION = register("add_potion",
			new AddPotionLootFunction.Serializer());

	public static void register(IEventBus bus) {
		LOOT_FUNCTIONS.register(bus);
	}

	private static RegistryObject<LootItemFunctionType> register(final String name,
			final Serializer<? extends LootItemFunction> serializer) {
		return LOOT_FUNCTIONS.register(name, () -> new LootItemFunctionType(serializer));
	}
}
