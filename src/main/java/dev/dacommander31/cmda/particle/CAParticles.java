package dev.dacommander31.cmda.particle;

import dev.dacommander31.cmda.CommandersArsenal;
import net.minecraft.core.particles.ParticleType;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.core.registries.BuiltInRegistries;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class CAParticles {
    public static final DeferredRegister<ParticleType<?>> PARTICLE_TYPES =
            DeferredRegister.create(BuiltInRegistries.PARTICLE_TYPE, CommandersArsenal.MOD_ID);

    public static final Supplier<SimpleParticleType> EMP_STUN = PARTICLE_TYPES.register(
            "emp_stun", () -> new SimpleParticleType(false)
    );

    public static void register(IEventBus eventBus) {
        PARTICLE_TYPES.register(eventBus);
    }
}
