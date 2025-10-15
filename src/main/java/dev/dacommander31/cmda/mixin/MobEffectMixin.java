package dev.dacommander31.cmda.mixin;

import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import org.spongepowered.asm.mixin.*;

import java.util.function.Function;
import java.util.function.Supplier;

@Mixin(MobEffect.class)
public class MobEffectMixin {
    @Mutable
    @Shadow @Final private Function<MobEffectInstance, ParticleOptions> particleFactory;

    @Unique
    public void cmda$setParticle(Supplier<ParticleOptions> particle) {
        this.particleFactory = mobEffectInstance -> particle.get();
    }
}
