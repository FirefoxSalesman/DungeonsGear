package net.firefoxsalesman.dungeonsgear.items.artifacts;

import net.firefoxsalesman.dungeonsgear.registry.MobEffectInit;
import net.firefoxsalesman.dungeonslibs.items.artifacts.ArtifactItem;
import net.firefoxsalesman.dungeonslibs.items.artifacts.ArtifactUseContext;
import net.firefoxsalesman.dungeonslibs.network.BreakItemMessage;
import net.firefoxsalesman.dungeonslibs.utils.ModHelper;
import net.firefoxsalesman.dungeonsgear.GlobalEvents;
import net.firefoxsalesman.dungeonsgear.network.NetworkHandler;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;
import net.minecraftforge.network.PacketDistributor;

import java.util.List;

import com.alrex.parcool.common.action.impl.Roll;
import com.alrex.parcool.common.capability.Parkourability;

import static net.firefoxsalesman.dungeonsgear.DungeonsGear.PROXY;
import static net.firefoxsalesman.dungeonslibs.utils.PetHelper.isPetOf;

public class LightFeatherItem extends ArtifactItem {
	public LightFeatherItem(Properties properties) {
		super(properties);
	}

	public InteractionResultHolder<ItemStack> procArtifact(ArtifactUseContext c) {
		Player playerIn = c.getPlayer();
		ItemStack itemstack = c.getItemStack();
		Level worldIn = c.getLevel();

		if (ModHelper.hasMod("parcool")) {
			Parkourability.get(playerIn).get(Roll.class).startRoll(playerIn);
		} else {
			playerIn.jumpFromGround();
			GlobalEvents.doRollEffects(playerIn);
		}

		List<LivingEntity> nearbyEntities = worldIn.getEntitiesOfClass(LivingEntity.class,
				new AABB(playerIn.getX() - 5, playerIn.getY() - 5, playerIn.getZ() - 5,
						playerIn.getX() + 5, playerIn.getY() + 5, playerIn.getZ() + 5),
				(nearbyEntity) -> {
					return nearbyEntity != playerIn && !isPetOf(playerIn, nearbyEntity)
							&& nearbyEntity.isAlive();
				});

		PROXY.spawnParticles(playerIn, ParticleTypes.CLOUD);
		for (LivingEntity nearbyEntity : nearbyEntities) {

			// KNOCKBACK
			float knockbackMultiplier = 1.0F;
			double xRatio = playerIn.getX() - nearbyEntity.getX();
			double zRatio;
			for (zRatio = playerIn.getZ() - nearbyEntity.getZ(); xRatio * xRatio
					+ zRatio * zRatio < 1.0E-4D; zRatio = (Math.random() - Math.random()) * 0.01D) {
				xRatio = (Math.random() - Math.random()) * 0.01D;
			}
			nearbyEntity.knockback(0.4F * knockbackMultiplier, xRatio, zRatio);
			// END OF KNOCKBACK

			PROXY.spawnParticles(nearbyEntity, ParticleTypes.CLOUD);

			MobEffectInstance stunned = new MobEffectInstance(MobEffectInit.STUNNED.get(),
					this.getDurationInSeconds() * 20);
			MobEffectInstance nausea = new MobEffectInstance(MobEffects.CONFUSION,
					this.getDurationInSeconds() * 20);
			MobEffectInstance slowness = new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN,
					this.getDurationInSeconds() * 20, 4);
			nearbyEntity.addEffect(slowness);
			nearbyEntity.addEffect(nausea);
			nearbyEntity.addEffect(stunned);

		}

		itemstack.hurtAndBreak(1, playerIn,
				(entity) -> NetworkHandler.INSTANCE.send(
						PacketDistributor.TRACKING_ENTITY_AND_SELF.with(() -> entity),
						new BreakItemMessage(entity.getId(), itemstack)));
		ArtifactItem.putArtifactOnCooldown(playerIn, itemstack.getItem());
		return new InteractionResultHolder<>(InteractionResult.SUCCESS, itemstack);
	}

	@Override
	public int getCooldownInSeconds() {
		return 3;
	}

	@Override
	public int getDurationInSeconds() {
		return 3;
	}
}
