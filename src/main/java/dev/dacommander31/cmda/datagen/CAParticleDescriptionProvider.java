package dev.dacommander31.cmda.datagen;

import dev.dacommander31.cmda.CommandersArsenal;
import dev.dacommander31.cmda.particle.CAParticles;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.neoforged.neoforge.common.data.ParticleDescriptionProvider;

public class CAParticleDescriptionProvider extends ParticleDescriptionProvider {
    protected CAParticleDescriptionProvider(PackOutput output, ExistingFileHelper fileHelper) {
        super(output, fileHelper);
    }

    @Override
    protected void addDescriptions() {
        sprite(CAParticles.EMP_STUN.get(), ResourceLocation.fromNamespaceAndPath(CommandersArsenal.MOD_ID, "emp_stun"));
    }
}
