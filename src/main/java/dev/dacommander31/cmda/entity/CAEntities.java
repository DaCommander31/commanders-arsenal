package dev.dacommander31.cmda.entity;

import dev.dacommander31.cmda.CommandersArsenal;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class CAEntities {
    public static DeferredRegister<EntityType<?>> ENTITY_TYPES = DeferredRegister.create(Registries.ENTITY_TYPE, CommandersArsenal.MOD_ID);

    public static final Supplier<EntityType<LaserBoltEntity>> LASER_BOLT =
            ENTITY_TYPES.register("laser_bolt", () -> EntityType.Builder.<LaserBoltEntity>of(
                    LaserBoltEntity::new, MobCategory.MISC).sized(0.5f, 0.5f).eyeHeight(0.13F)
                    .clientTrackingRange(4).updateInterval(20).build("laser_bolt"));

    public static void register(IEventBus eventBus) {
        ENTITY_TYPES.register(eventBus);
    }
}
