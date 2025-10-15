package dev.dacommander31.cmda.effect;

import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.effect.MobEffectInstance;

import java.util.function.Function;
import java.util.function.Supplier;

public class CAMobEffect extends MobEffect {
    private final Function<MobEffectInstance, Supplier<SimpleParticleType>> particleFactory;

    protected CAMobEffect(MobEffectCategory category, int color, Supplier<SimpleParticleType> particle) {
        super(category, color);
        this.particleFactory = mobEffectInstance -> particle;
    }

    @Override
    public ParticleOptions createParticleOptions(MobEffectInstance effect) {
        return this.particleFactory.apply(effect).get();
    }
}
