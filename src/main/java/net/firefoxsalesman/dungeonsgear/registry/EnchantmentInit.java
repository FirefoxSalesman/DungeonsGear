package net.firefoxsalesman.dungeonsgear.registry;

import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import static net.firefoxsalesman.dungeonsgear.DungeonsGear.MOD_ID;

import net.firefoxsalesman.dungeonsgear.enchantments.armor.chest.BagOfSoulsEnchantment;
import net.firefoxsalesman.dungeonsgear.enchantments.armor.chest.BeastBossEnchantment;
import net.firefoxsalesman.dungeonsgear.enchantments.armor.chest.BeehiveEnchantment;
import net.firefoxsalesman.dungeonsgear.enchantments.armor.chest.CowardiceEnchantment;
import net.firefoxsalesman.dungeonsgear.enchantments.armor.chest.DeathBarterEnchantment;
import net.firefoxsalesman.dungeonsgear.enchantments.armor.chest.DeflectEnchantment;
import net.firefoxsalesman.dungeonsgear.enchantments.armor.chest.FinalShoutEnchantment;
import net.firefoxsalesman.dungeonsgear.enchantments.armor.chest.FortuneOfTheSeaEnchantment;
import net.firefoxsalesman.dungeonsgear.enchantments.armor.chest.FrenziedEnchantment;
import net.firefoxsalesman.dungeonsgear.enchantments.armor.chest.HealthSynergyEnchantment;
import net.firefoxsalesman.dungeonsgear.enchantments.armor.chest.OpulentShieldEnchantment;
import net.firefoxsalesman.dungeonsgear.enchantments.armor.chest.RecklessEnchantment;
import net.firefoxsalesman.dungeonsgear.enchantments.armor.feet.DodgeEnchantment;
import net.firefoxsalesman.dungeonsgear.enchantments.armor.feet.RushEnchantment;
import net.firefoxsalesman.dungeonsgear.enchantments.armor.feet.SpeedSynergyEnchantment;
import net.firefoxsalesman.dungeonsgear.enchantments.armor.feet.SwiftfootedEnchantment;
import net.firefoxsalesman.dungeonsgear.enchantments.armor.feet.VoidDodgeEnchantment;
import net.firefoxsalesman.dungeonsgear.enchantments.armor.head.BeastBurstEnchantment;
import net.firefoxsalesman.dungeonsgear.enchantments.armor.head.BeastSurgeEnchantment;
import net.firefoxsalesman.dungeonsgear.enchantments.armor.head.CooldownEnchantment;
import net.firefoxsalesman.dungeonsgear.enchantments.armor.head.FireFocusEnchantment;
import net.firefoxsalesman.dungeonsgear.enchantments.armor.head.LightningFocusEnchantment;
import net.firefoxsalesman.dungeonsgear.enchantments.armor.head.PoisonFocusEnchantment;
import net.firefoxsalesman.dungeonsgear.enchantments.armor.head.PotionBarrierEnchantment;
import net.firefoxsalesman.dungeonsgear.enchantments.armor.head.SoulFocusEnchantment;
import net.firefoxsalesman.dungeonsgear.enchantments.armor.legs.AcrobatEnchantment;
import net.firefoxsalesman.dungeonsgear.enchantments.armor.legs.AltruisticEnchantment;
import net.firefoxsalesman.dungeonsgear.enchantments.armor.legs.BurningEnchantment;
import net.firefoxsalesman.dungeonsgear.enchantments.armor.legs.ChillingEnchantment;
import net.firefoxsalesman.dungeonsgear.enchantments.armor.legs.ElectrifiedEnchantment;
import net.firefoxsalesman.dungeonsgear.enchantments.armor.legs.FireTrailEnchantment;
import net.firefoxsalesman.dungeonsgear.enchantments.armor.legs.GravityPulseEnchantment;
import net.firefoxsalesman.dungeonsgear.enchantments.armor.legs.LifeStealAuraEnchantment;
import net.firefoxsalesman.dungeonsgear.enchantments.armor.legs.MeleeAuraEnchantment;
import net.firefoxsalesman.dungeonsgear.enchantments.armor.legs.MultiRollEnchantment;
import net.firefoxsalesman.dungeonsgear.enchantments.armor.legs.PotionAuraEnchantment;
import net.firefoxsalesman.dungeonsgear.enchantments.armor.legs.SnowballEnchantment;
import net.firefoxsalesman.dungeonsgear.enchantments.armor.legs.SpeedAuraEnchantment;
import net.firefoxsalesman.dungeonsgear.enchantments.armor.legs.TumblebeeEnchantment;
import net.firefoxsalesman.dungeonsgear.enchantments.melee.ArtifactSynergyEnchantment;
import net.firefoxsalesman.dungeonsgear.enchantments.melee.BusyBeeEnchantment;
import net.firefoxsalesman.dungeonsgear.enchantments.melee.ChainsEnchantment;
import net.firefoxsalesman.dungeonsgear.enchantments.melee.CommittedEnchantment;
import net.firefoxsalesman.dungeonsgear.enchantments.melee.CriticalHitEnchantment;
import net.firefoxsalesman.dungeonsgear.enchantments.melee.EchoEnchantment;
import net.firefoxsalesman.dungeonsgear.enchantments.melee.ExplodingEnchantment;
import net.firefoxsalesman.dungeonsgear.enchantments.melee.FreezingEnchantment;
import net.firefoxsalesman.dungeonsgear.enchantments.melee.GuardingStrikeEnchantment;
import net.firefoxsalesman.dungeonsgear.enchantments.melee.IllagersBaneEnchantment;
import net.firefoxsalesman.dungeonsgear.enchantments.melee.LeechingEnchantment;
import net.firefoxsalesman.dungeonsgear.enchantments.melee.PainCycleEnchantment;
import net.firefoxsalesman.dungeonsgear.enchantments.melee.ProspectorEnchantment;
import net.firefoxsalesman.dungeonsgear.enchantments.melee.RadianceEnchantment;
import net.firefoxsalesman.dungeonsgear.enchantments.melee.RampagingEnchantment;
import net.firefoxsalesman.dungeonsgear.enchantments.melee.RushdownEnchantment;
import net.firefoxsalesman.dungeonsgear.enchantments.melee.ShockwaveEnchantment;
import net.firefoxsalesman.dungeonsgear.enchantments.melee.SoulSiphonEnchantment;
import net.firefoxsalesman.dungeonsgear.enchantments.melee.StunningEnchantment;
import net.firefoxsalesman.dungeonsgear.enchantments.melee.SwirlingEnchantment;
import net.firefoxsalesman.dungeonsgear.enchantments.melee.ThunderingEnchantment;
import net.firefoxsalesman.dungeonsgear.enchantments.melee.WeakeningEnchantment;
import net.firefoxsalesman.dungeonsgear.enchantments.melee_ranged.AnimaConduitEnchantment;
import net.firefoxsalesman.dungeonsgear.enchantments.melee_ranged.DynamoEnchantment;
import net.firefoxsalesman.dungeonsgear.enchantments.melee_ranged.EnigmaResonatorEnchantment;
import net.firefoxsalesman.dungeonsgear.enchantments.melee_ranged.GravityEnchantment;
import net.firefoxsalesman.dungeonsgear.enchantments.melee_ranged.MastersCallEnchantment;
import net.firefoxsalesman.dungeonsgear.enchantments.melee_ranged.PoisonCloudEnchantment;
import net.firefoxsalesman.dungeonsgear.enchantments.melee_ranged.RefreshmentEnchantment;
import net.firefoxsalesman.dungeonsgear.enchantments.ranged.AccelerateEnchantment;
import net.firefoxsalesman.dungeonsgear.enchantments.ranged.BonusShotEnchantment;
import net.firefoxsalesman.dungeonsgear.enchantments.ranged.BurstBowstringEnchantment;
import net.firefoxsalesman.dungeonsgear.enchantments.ranged.ChainReactionEnchantment;
import net.firefoxsalesman.dungeonsgear.enchantments.ranged.CooldownShotEnchantment;
import net.firefoxsalesman.dungeonsgear.enchantments.ranged.ExplodingShotEnchantment;
import net.firefoxsalesman.dungeonsgear.enchantments.ranged.FreezingShotEnchantment;
import net.firefoxsalesman.dungeonsgear.enchantments.ranged.FuseShotEnchantment;
import net.firefoxsalesman.dungeonsgear.enchantments.ranged.GaleShotEnchantment;
import net.firefoxsalesman.dungeonsgear.enchantments.ranged.GravityShotEnchantment;
import net.firefoxsalesman.dungeonsgear.enchantments.ranged.GrowingEnchantment;
import net.firefoxsalesman.dungeonsgear.enchantments.ranged.HarpoonShotEnchantment;
import net.firefoxsalesman.dungeonsgear.enchantments.ranged.OverchargeEnchantment;
import net.firefoxsalesman.dungeonsgear.enchantments.ranged.RadianceShotEnchantment;
import net.firefoxsalesman.dungeonsgear.enchantments.ranged.ReplenishEnchantment;
import net.firefoxsalesman.dungeonsgear.enchantments.ranged.RicochetEnchantment;
import net.firefoxsalesman.dungeonsgear.enchantments.ranged.RollChargeEnchantment;
import net.firefoxsalesman.dungeonsgear.enchantments.ranged.SuperchargeEnchantment;
import net.firefoxsalesman.dungeonsgear.enchantments.ranged.TempoTheftEnchantment;
import net.firefoxsalesman.dungeonsgear.enchantments.ranged.VelocityEnchantment;
import net.firefoxsalesman.dungeonsgear.enchantments.ranged.WildRageEnchantment;

public class EnchantmentInit {
	public static final DeferredRegister<Enchantment> ENCHANTMENTS = DeferredRegister
			.create(ForgeRegistries.ENCHANTMENTS, MOD_ID);
	public static RegistryObject<AnimaConduitEnchantment> ANIMA_CONDUIT = ENCHANTMENTS.register("anima_conduit",
			AnimaConduitEnchantment::new);
	public static RegistryObject<EnigmaResonatorEnchantment> ENIGMA_RESONATOR = ENCHANTMENTS
			.register("enigma_resonator", EnigmaResonatorEnchantment::new);
	public static RegistryObject<FuseShotEnchantment> FUSE_SHOT = ENCHANTMENTS.register("fuse_shot",
			FuseShotEnchantment::new);
	public static RegistryObject<GravityEnchantment> GRAVITY = ENCHANTMENTS.register("gravity",
			GravityEnchantment::new);
	public static RegistryObject<MastersCallEnchantment> MASTERS_CALL = ENCHANTMENTS.register("masters_call",
			MastersCallEnchantment::new);
	public static RegistryObject<PoisonCloudEnchantment> POISON_CLOUD = ENCHANTMENTS.register("poison_cloud",
			PoisonCloudEnchantment::new);
	public static RegistryObject<DynamoEnchantment> DYNAMO = ENCHANTMENTS.register("dynamo",
			() -> new DynamoEnchantment());
	public static RegistryObject<RefreshmentEnchantment> REFRESHMENT = ENCHANTMENTS.register("refreshment",
			RefreshmentEnchantment::new);
	public static RegistryObject<ArtifactSynergyEnchantment> ARTIFACT_SYNERGY = ENCHANTMENTS
			.register("artifact_synergy", () -> new ArtifactSynergyEnchantment());
	public static RegistryObject<ExplodingShotEnchantment> EXPLODING_SHOT = ENCHANTMENTS.register("exploding_shot",
			ExplodingShotEnchantment::new);
	public static RegistryObject<HarpoonShotEnchantment> HARPOON_SHOT = ENCHANTMENTS.register("harpoon_shot",
			() -> new HarpoonShotEnchantment());
	public static RegistryObject<FreezingEnchantment> FREEZING = ENCHANTMENTS.register("freezing",
			FreezingEnchantment::new);
	public static RegistryObject<BusyBeeEnchantment> BUSY_BEE = ENCHANTMENTS.register("busy_bee",
			BusyBeeEnchantment::new);
	public static RegistryObject<ChainsEnchantment> CHAINS = ENCHANTMENTS.register("chains",
			ChainsEnchantment::new);
	public static RegistryObject<CommittedEnchantment> COMMITTED = ENCHANTMENTS.register("committed",
			CommittedEnchantment::new);
	public static RegistryObject<CriticalHitEnchantment> CRITICAL_HIT = ENCHANTMENTS.register("critical_hit",
			CriticalHitEnchantment::new);
	public static RegistryObject<EchoEnchantment> ECHO = ENCHANTMENTS.register("echo", EchoEnchantment::new);
	public static RegistryObject<ExplodingEnchantment> EXPLODING = ENCHANTMENTS.register("exploding",
			ExplodingEnchantment::new);
	public static RegistryObject<GuardingStrikeEnchantment> GUARDING_STRIKE = ENCHANTMENTS
			.register("guarding_strike", GuardingStrikeEnchantment::new);
	public static RegistryObject<IllagersBaneEnchantment> ILLAGERS_BANE = ENCHANTMENTS.register("illagers_bane",
			IllagersBaneEnchantment::new);
	public static RegistryObject<LeechingEnchantment> LEECHING = ENCHANTMENTS.register("leeching",
			LeechingEnchantment::new);
	public static RegistryObject<PainCycleEnchantment> PAIN_CYCLE = ENCHANTMENTS.register("pain_cycle",
			PainCycleEnchantment::new);
	public static RegistryObject<ProspectorEnchantment> PROSPECTOR = ENCHANTMENTS.register("prospector",
			ProspectorEnchantment::new);
	public static RegistryObject<RadianceEnchantment> RADIANCE = ENCHANTMENTS.register("radiance",
			RadianceEnchantment::new);
	public static RegistryObject<RampagingEnchantment> RAMPAGING = ENCHANTMENTS.register("rampaging",
			RampagingEnchantment::new);
	public static RegistryObject<RushdownEnchantment> RUSHDOWN = ENCHANTMENTS.register("rushdown",
			RushdownEnchantment::new);
	public static RegistryObject<ShockwaveEnchantment> SHOCKWAVE = ENCHANTMENTS.register("shockwave",
			ShockwaveEnchantment::new);
	public static RegistryObject<SoulSiphonEnchantment> SOUL_SIPHON = ENCHANTMENTS.register("soul_siphon",
			SoulSiphonEnchantment::new);
	public static RegistryObject<StunningEnchantment> STUNNING = ENCHANTMENTS.register("stunning",
			StunningEnchantment::new);
	public static RegistryObject<SwirlingEnchantment> SWIRLING = ENCHANTMENTS.register("swirling",
			SwirlingEnchantment::new);
	public static RegistryObject<ThunderingEnchantment> THUNDERING = ENCHANTMENTS.register("thundering",
			ThunderingEnchantment::new);
	public static RegistryObject<WeakeningEnchantment> WEAKENING = ENCHANTMENTS.register("weakening",
			WeakeningEnchantment::new);
	public static RegistryObject<AccelerateEnchantment> ACCELERATE = ENCHANTMENTS.register("accelerate",
			AccelerateEnchantment::new);
	public static RegistryObject<BonusShotEnchantment> BONUS_SHOT = ENCHANTMENTS.register("bonus_shot",
			BonusShotEnchantment::new);
	public static RegistryObject<BurstBowstringEnchantment> BURST_BOWSTRING = ENCHANTMENTS
			.register("burst_bowstring", () -> new BurstBowstringEnchantment());

	public static RegistryObject<ChainReactionEnchantment> CHAIN_REACTION = ENCHANTMENTS.register("chain_reaction",
			ChainReactionEnchantment::new);
	public static RegistryObject<CooldownShotEnchantment> COOLDOWN_SHOT = ENCHANTMENTS.register("cooldown_shot",
			CooldownShotEnchantment::new);
	public static RegistryObject<FreezingShotEnchantment> FREEZING_SHOT = ENCHANTMENTS.register("freezing_shot",
			FreezingShotEnchantment::new);
	public static RegistryObject<GaleShotEnchantment> GALE_SHOT = ENCHANTMENTS.register("gale_shot",
			GaleShotEnchantment::new);
	public static RegistryObject<GravityShotEnchantment> GRAVITY_SHOT = ENCHANTMENTS.register("gravity_shot",
			GravityShotEnchantment::new);
	public static RegistryObject<GrowingEnchantment> GROWING = ENCHANTMENTS.register("growing",
			GrowingEnchantment::new);
	public static RegistryObject<OverchargeEnchantment> OVERCHARGE = ENCHANTMENTS.register("overcharge",
			OverchargeEnchantment::new);
	public static RegistryObject<RadianceShotEnchantment> RADIANCE_SHOT = ENCHANTMENTS.register("radiance_shot",
			RadianceShotEnchantment::new);
	public static RegistryObject<ReplenishEnchantment> REPLENISH = ENCHANTMENTS.register("replenish",
			ReplenishEnchantment::new);
	public static RegistryObject<RicochetEnchantment> RICOCHET = ENCHANTMENTS.register("ricochet",
			RicochetEnchantment::new);
	public static RegistryObject<RollChargeEnchantment> ROLL_CHARGE = ENCHANTMENTS.register("roll_charge",
			() -> new RollChargeEnchantment());

	public static RegistryObject<SuperchargeEnchantment> SUPERCHARGE = ENCHANTMENTS.register("supercharge",
			SuperchargeEnchantment::new);
	public static RegistryObject<TempoTheftEnchantment> TEMPO_THEFT = ENCHANTMENTS.register("tempo_theft",
			TempoTheftEnchantment::new);
	public static RegistryObject<VelocityEnchantment> VELOCITY = ENCHANTMENTS.register("velocity",
			VelocityEnchantment::new);
	public static RegistryObject<WildRageEnchantment> WILD_RAGE = ENCHANTMENTS.register("wild_rage",
			() -> new WildRageEnchantment());

	public static RegistryObject<AcrobatEnchantment> ACROBAT = ENCHANTMENTS.register("acrobat",
			() -> new AcrobatEnchantment());
	public static RegistryObject<BagOfSoulsEnchantment> BAG_OF_SOULS = ENCHANTMENTS.register("bag_of_souls",
			BagOfSoulsEnchantment::new);
	public static RegistryObject<BeastBossEnchantment> BEAST_BOSS = ENCHANTMENTS.register("beast_boss",
			BeastBossEnchantment::new);
	public static RegistryObject<BeehiveEnchantment> BEEHIVE = ENCHANTMENTS.register("beehive",
			BeehiveEnchantment::new);
	public static RegistryObject<CowardiceEnchantment> COWARDICE = ENCHANTMENTS.register("cowardice",
			CowardiceEnchantment::new);
	public static RegistryObject<DeathBarterEnchantment> DEATH_BARTER = ENCHANTMENTS.register("death_barter",
			DeathBarterEnchantment::new);
	public static RegistryObject<DeflectEnchantment> DEFLECT = ENCHANTMENTS.register("deflect",
			DeflectEnchantment::new);
	public static RegistryObject<ElectrifiedEnchantment> ELECTRIFIED = ENCHANTMENTS.register("electrified",
			() -> new ElectrifiedEnchantment());
	public static RegistryObject<FinalShoutEnchantment> FINAL_SHOUT = ENCHANTMENTS.register("final_shout",
			FinalShoutEnchantment::new);
	public static RegistryObject<FortuneOfTheSeaEnchantment> FORTUNE_OF_THE_SEA = ENCHANTMENTS
			.register("fortune_of_the_sea", FortuneOfTheSeaEnchantment::new);
	public static RegistryObject<FrenziedEnchantment> FRENZIED = ENCHANTMENTS.register("frenzied",
			FrenziedEnchantment::new);
	public static RegistryObject<HealthSynergyEnchantment> HEALTH_SYNERGY = ENCHANTMENTS.register("health_synergy",
			HealthSynergyEnchantment::new);
	public static RegistryObject<OpulentShieldEnchantment> OPULENT_SHIELD = ENCHANTMENTS.register("opulent_shield",
			OpulentShieldEnchantment::new);
	public static RegistryObject<RecklessEnchantment> RECKLESS = ENCHANTMENTS.register("reckless",
			RecklessEnchantment::new);
	public static RegistryObject<DodgeEnchantment> DODGE = ENCHANTMENTS.register("dodge", DodgeEnchantment::new);
	public static RegistryObject<RushEnchantment> RUSH = ENCHANTMENTS.register("rush", RushEnchantment::new);
	public static RegistryObject<SpeedSynergyEnchantment> SPEED_SYNERGY = ENCHANTMENTS.register("speed_synergy",
			SpeedSynergyEnchantment::new);
	public static RegistryObject<VoidDodgeEnchantment> VOID_DODGE = ENCHANTMENTS.register("void_dodge",
			VoidDodgeEnchantment::new);
	public static RegistryObject<BeastBurstEnchantment> BEAST_BURST = ENCHANTMENTS.register("beast_burst",
			BeastBurstEnchantment::new);
	public static RegistryObject<BeastSurgeEnchantment> BEAST_SURGE = ENCHANTMENTS.register("beast_surge",
			BeastSurgeEnchantment::new);
	public static RegistryObject<CooldownEnchantment> COOLDOWN = ENCHANTMENTS.register("cooldown",
			CooldownEnchantment::new);
	public static RegistryObject<FireFocusEnchantment> FIRE_FOCUS = ENCHANTMENTS.register("fire_focus",
			FireFocusEnchantment::new);
	public static RegistryObject<FireTrailEnchantment> FIRE_TRAIL = ENCHANTMENTS.register("fire_trail",
			() -> new FireTrailEnchantment());
	public static RegistryObject<LightningFocusEnchantment> LIGHTNING_FOCUS = ENCHANTMENTS
			.register("lightning_focus", LightningFocusEnchantment::new);
	public static RegistryObject<MultiRollEnchantment> MULTI_ROLL = ENCHANTMENTS.register("multi_roll",
			() -> new MultiRollEnchantment());
	public static RegistryObject<PoisonFocusEnchantment> POISON_FOCUS = ENCHANTMENTS.register("poison_focus",
			PoisonFocusEnchantment::new);
	public static RegistryObject<PotionBarrierEnchantment> POTION_BARRIER = ENCHANTMENTS.register("potion_barrier",
			PotionBarrierEnchantment::new);
	public static RegistryObject<SoulFocusEnchantment> SOUL_FOCUS = ENCHANTMENTS.register("soul_focus",
			SoulFocusEnchantment::new);
	public static RegistryObject<AltruisticEnchantment> ALTRUISTIC = ENCHANTMENTS.register("altruistic",
			AltruisticEnchantment::new);
	public static RegistryObject<BurningEnchantment> BURNING = ENCHANTMENTS.register("burning",
			BurningEnchantment::new);
	public static RegistryObject<ChillingEnchantment> CHILLING = ENCHANTMENTS.register("chilling",
			ChillingEnchantment::new);
	public static RegistryObject<GravityPulseEnchantment> GRAVITY_PULSE = ENCHANTMENTS.register("gravity_pulse",
			GravityPulseEnchantment::new);
	public static RegistryObject<LifeStealAuraEnchantment> LIFE_STEAL_AURA = ENCHANTMENTS
			.register("life_steal_aura", LifeStealAuraEnchantment::new);
	public static RegistryObject<MeleeAuraEnchantment> MELEE_AURA = ENCHANTMENTS.register("melee_aura",
			MeleeAuraEnchantment::new);
	public static RegistryObject<PotionAuraEnchantment> POTION_AURA = ENCHANTMENTS.register("potion_aura",
			PotionAuraEnchantment::new);
	public static RegistryObject<SnowballEnchantment> SNOWBALL = ENCHANTMENTS.register("snowball",
			SnowballEnchantment::new);
	public static RegistryObject<SwiftfootedEnchantment> SWIFTFOOTED = ENCHANTMENTS.register("swiftfooted",
			() -> new SwiftfootedEnchantment());
	public static RegistryObject<TumblebeeEnchantment> TUMBLEBEE = ENCHANTMENTS.register("tumblebee",
			() -> new TumblebeeEnchantment());
	public static RegistryObject<SpeedAuraEnchantment> SPEED_AURA = ENCHANTMENTS.register("speed_aura",
			SpeedAuraEnchantment::new);

	public static void register(IEventBus event) {
		ENCHANTMENTS.register(event);
	}
}
