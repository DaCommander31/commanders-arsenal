package dev.dacommander31.cmda.sound;

import dev.dacommander31.cmda.CommandersArsenal;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class CASounds {
    public static final DeferredRegister<SoundEvent> SOUND_EVENTS =
            DeferredRegister.create(Registries.SOUND_EVENT, CommandersArsenal.MOD_ID);

    public static final Supplier<SoundEvent> EMP_REACTOR_PULSE = registerSoundEvent("block.emp_reactor.pulse");
    public static final Supplier<SoundEvent> EMP_STUN = registerSoundEvent("event.mob_effect.emp_stun");

    private static Supplier<SoundEvent> registerSoundEvent(String name) {
        ResourceLocation id = ResourceLocation.fromNamespaceAndPath(CommandersArsenal.MOD_ID, name);
        return SOUND_EVENTS.register(name, () -> SoundEvent.createVariableRangeEvent(id));
    }

    public static void register(IEventBus eventBus) {
        SOUND_EVENTS.register(eventBus);
    }
}
