package dev.dacommander31.cmda.effect;

import dev.dacommander31.cmda.CommandersArsenal;
import dev.dacommander31.cmda.effect.custom.EMPStunMobEffect;
import dev.dacommander31.cmda.particle.CAParticles;
import dev.dacommander31.cmda.sound.CASounds;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;


public class CAMobEffects {
    public static final DeferredRegister<MobEffect> MOB_EFFECTS = DeferredRegister.create(Registries.MOB_EFFECT, CommandersArsenal.MOD_ID);

    public static final Holder<MobEffect> EMP_STUN = MOB_EFFECTS.register("emp_stun",
            () -> new EMPStunMobEffect(MobEffectCategory.HARMFUL, 0x00444d)
                    .withSoundOnAdded(CASounds.EMP_STUN.get())
                    .addAttributeModifier(
                            Attributes.MOVEMENT_SPEED,
                            ResourceLocation.fromNamespaceAndPath(CommandersArsenal.MOD_ID, "emp_stun"),
                            -0.85,
                            AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL
                    ));

    public static void register(IEventBus eventBus) {
        MOB_EFFECTS.register(eventBus);
    }
}
