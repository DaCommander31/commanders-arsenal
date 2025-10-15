package dev.dacommander31.cmda.datagen;

import dev.dacommander31.cmda.CATags;
import dev.dacommander31.cmda.CommandersArsenal;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.EntityTypeTagsProvider;
import net.minecraft.world.entity.EntityType;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

public class CAEntityTypeTagsProvider extends EntityTypeTagsProvider {
    public CAEntityTypeTagsProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> provider, @Nullable ExistingFileHelper existingFileHelper) {
        super(output, provider, CommandersArsenal.MOD_ID, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.Provider provider) {
        this.tag(CATags.Entities.AFFECTED_BY_EMP)
                .add(
                        EntityType.IRON_GOLEM
                );
    }
}
