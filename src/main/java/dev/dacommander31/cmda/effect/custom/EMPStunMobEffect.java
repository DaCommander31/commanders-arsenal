package dev.dacommander31.cmda.effect.custom;

import dev.dacommander31.cmda.effect.CAMobEffect;
import dev.dacommander31.cmda.particle.CAParticles;
import net.minecraft.world.effect.MobEffectCategory;


public class EMPStunMobEffect extends CAMobEffect {
    public EMPStunMobEffect(MobEffectCategory category, int color) {
        super(category, color, CAParticles.EMP_STUN);
    }
}
