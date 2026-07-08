package net.not_assher.core.item;

import net.acoyt.acornlib.api.item.CritEffectItem;
import net.fabricmc.fabric.api.item.v1.EnchantingContext;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.component.type.AttributeModifierSlot;
import net.minecraft.component.type.AttributeModifiersComponent;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.not_assher.core.Quartermaster;
import net.not_assher.core.cca.entity.RapierComponent;
import net.not_assher.core.index.ModDataComponentTypes;
import net.not_assher.core.index.ModEnchantmentEffects;
import net.not_assher.core.index.tag.ModEnchantmentTags;
import org.jspecify.annotations.Nullable;

public class RapierItem extends Item implements CritEffectItem {
    public RapierItem(Settings settings) {
        super(settings);
    }

    public static AttributeModifiersComponent createAttributes() {
        return AttributeModifiersComponent.builder()
                .add(EntityAttributes.ATTACK_DAMAGE,
                        new EntityAttributeModifier(
                                BASE_ATTACK_DAMAGE_MODIFIER_ID,
                                6.0F,
                                EntityAttributeModifier.Operation.ADD_VALUE
                        ), AttributeModifierSlot.MAINHAND
                )
                .add(EntityAttributes.ATTACK_SPEED,
                        new EntityAttributeModifier(
                                BASE_ATTACK_SPEED_MODIFIER_ID,
                                -2.4F,
                                EntityAttributeModifier.Operation.ADD_VALUE
                        ), AttributeModifierSlot.MAINHAND
                )
                .add(EntityAttributes.ATTACK_KNOCKBACK,
                        new EntityAttributeModifier(
                                Quartermaster.id("attack_knockback"),
                                1.5F,
                                EntityAttributeModifier.Operation.ADD_VALUE
                        ), AttributeModifierSlot.MAINHAND
                )
                .add(EntityAttributes.ENTITY_INTERACTION_RANGE,
                        new EntityAttributeModifier(
                                Quartermaster.id("attack_reach"),
                                0.5F,
                                EntityAttributeModifier.Operation.ADD_VALUE
                        ), AttributeModifierSlot.MAINHAND
                )
                .build();
    }

    public ActionResult use(World world, PlayerEntity user, Hand hand) {
        ItemStack stack = user.getStackInHand(hand);

        if (EnchantmentHelper.hasAnyEnchantmentsWith(stack, ModEnchantmentEffects.JOUST)) {
            double d = -MathHelper.sin(user.getYaw() * ((float)Math.PI / 180F));
            double e = MathHelper.cos(user.getYaw() * ((float)Math.PI / 180F));

            if (world instanceof ServerWorld serverWorld) {
                serverWorld.spawnParticles(
                        ParticleTypes.SWEEP_ATTACK,
                        user.getX() + d,
                        user.getBodyY(0.7F),
                        user.getZ() + e,
                        0,
                        d + 2,
                        0.0F,
                        e + 2,
                        0.0F
                );

                serverWorld.spawnParticles(
                        ParticleTypes.POOF,
                        user.getX() + d,
                        user.getBodyY(0.6F),
                        user.getZ() + e,
                        22,
                        0,
                        0.0F,
                        0,
                        0.5F
                );

                serverWorld.spawnParticles(
                        ParticleTypes.SMOKE,
                        user.getX() + d,
                        user.getBodyY(0.6F),
                        user.getZ() + e,
                        22,
                        0,
                        0.0F,
                        0,
                        0.5F
                );
            }

            user.setVelocity(user.getRotationVec(0).multiply(2));
            user.addVelocity(0, 0.5F, 0);
            user.velocityDirty = true;

            user.swingHand(hand);
        } else {
            if (!EnchantmentHelper.hasAnyEnchantmentsWith(stack, ModEnchantmentEffects.DISARM)) {
                RapierComponent.KEY.get(user).setParryTicks((3 * 20));
            }
        }

        if (!user.isCreative()) {
            user.getItemCooldownManager().set(stack, 90);
        }
        return super.use(world, user, hand);
    }

    public void inventoryTick(ItemStack stack, ServerWorld world, Entity entity, @Nullable EquipmentSlot slot) {
        stack.setHolder(entity);
    }

    public ActionResult useOnBlock(ItemUsageContext context) {
        PlayerEntity player = context.getPlayer();
        ItemStack stack = context.getStack();
        BlockState state = context.getWorld().getBlockState(context.getBlockPos());

        if (player != null) {
            if (state.isOf(Blocks.SMITHING_TABLE)) {
                if (player.isSneaking()) {
                    stack.set(ModDataComponentTypes.IS_CORAL, !stack.getOrDefault(ModDataComponentTypes.IS_CORAL, false));
                    return ActionResult.FAIL;
                }
            }
        }
        return super.useOnBlock(context);
    }

    public boolean canBeEnchantedWith(ItemStack stack, RegistryEntry<Enchantment> enchantment, EnchantingContext context) {
        return enchantment.isIn(ModEnchantmentTags.RAPIER_ENCHANTABLE);
    }

    public void critEffect(PlayerEntity player, ItemStack stack, Entity target) {
        if (EnchantmentHelper.hasAnyEnchantmentsWith(stack, ModEnchantmentEffects.DISARM)) {
            RapierComponent rapier = RapierComponent.KEY.get(player);

            if (rapier.getCrits() < 4) {
                rapier.incCrits();

                if (player.getEntityWorld() instanceof ServerWorld serverWorld) {
                    serverWorld.spawnParticles(
                            ParticleTypes.END_ROD,
                            target.getX(),
                            target.getY(),
                            target.getZ(),
                            9,
                            target.getWidth(),
                            target.getHeight(),
                            target.getWidth(),
                            0.2F
                    );
                }
            } else {
                rapier.setCrits(0);

                if (target instanceof PlayerEntity living) {
                    living.getItemCooldownManager().set(living.getActiveOrMainHandStack(), 140);
                } else {
                    player.addExperience(30);
                }
            }
        }
    }

    public int getItemBarStep(ItemStack stack) {
        Entity hold = stack.getHolder();
        if (hold instanceof PlayerEntity player) {
            return Math.round((float) RapierComponent.KEY.get(player).getCrits() / 4 * 13);
        } else {
            return 0;
        }
    }

    public boolean isItemBarVisible(ItemStack stack) {
        return super.isItemBarVisible(stack) || EnchantmentHelper.hasAnyEnchantmentsWith(stack, ModEnchantmentEffects.DISARM);
    }
}
