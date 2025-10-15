package dev.dacommander31.cmda;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.EntityType;

public class CATags {
    public static class Entities {
        public static final TagKey<EntityType<?>> AFFECTED_BY_EMP = createTag("affected_by_emp");

        private static TagKey<EntityType<?>> createTag(String name) {
            return TagKey.create(Registries.ENTITY_TYPE, ResourceLocation.fromNamespaceAndPath(CommandersArsenal.MOD_ID, name));
        }
    }
}
