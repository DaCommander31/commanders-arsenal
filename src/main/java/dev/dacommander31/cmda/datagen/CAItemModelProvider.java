package dev.dacommander31.cmda.datagen;

import dev.dacommander31.cmda.CommandersArsenal;
import dev.dacommander31.cmda.item.CAItems;
import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.client.model.generators.ItemModelProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;

public class CAItemModelProvider extends ItemModelProvider {
    public CAItemModelProvider(PackOutput output, ExistingFileHelper existingFileHelper) {
        super(output, CommandersArsenal.MOD_ID, existingFileHelper);
    }

    @Override
    protected void registerModels() {
        basicItem(CAItems.HEART_OF_THE_WARDEN.get());
    }
}
