package dev.dacommander31.cmda.datagen;

import dev.dacommander31.cmda.CommandersArsenal;
import dev.dacommander31.cmda.block.CABlocks;
import dev.dacommander31.cmda.block.custom.EMPReactorBlock;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.neoforged.neoforge.client.model.generators.BlockStateProvider;
import net.neoforged.neoforge.client.model.generators.ConfiguredModel;
import net.neoforged.neoforge.client.model.generators.ModelFile;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.neoforged.neoforge.registries.DeferredBlock;

public class CABlockStateProvider extends BlockStateProvider {
    public CABlockStateProvider(PackOutput output, ExistingFileHelper exFileHelper) {
        super(output, CommandersArsenal.MOD_ID, exFileHelper);
    }

    @Override
    protected void registerStatesAndModels() {

        ModelFile offModel = models().cubeBottomTop("emp_reactor",
                ResourceLocation.fromNamespaceAndPath(CommandersArsenal.MOD_ID, "block/emp_reactor_side"),
                ResourceLocation.fromNamespaceAndPath(CommandersArsenal.MOD_ID, "block/emp_reactor_bottom"),
                ResourceLocation.fromNamespaceAndPath(CommandersArsenal.MOD_ID, "block/emp_reactor_top"));
        ModelFile onModel = models().cubeBottomTop("emp_reactor_powered",
                ResourceLocation.fromNamespaceAndPath(CommandersArsenal.MOD_ID, "block/emp_reactor_side_powered"),
                ResourceLocation.fromNamespaceAndPath(CommandersArsenal.MOD_ID, "block/emp_reactor_bottom"),
                ResourceLocation.fromNamespaceAndPath(CommandersArsenal.MOD_ID, "block/emp_reactor_top"));

        getVariantBuilder(CABlocks.EMP_REACTOR.get()).forAllStates(state -> {
            boolean powered = state.getValue(BlockStateProperties.LIT);

            return ConfiguredModel.builder()
                    .modelFile(powered ? onModel : offModel)
                    .build();
        });

        itemModels().simpleBlockItem(CABlocks.EMP_REACTOR.get());
    }

    private void blockWithItem(DeferredBlock<?> deferredBlock) {
        simpleBlockWithItem(deferredBlock.get(), cubeAll(deferredBlock.get()));
    }
}
