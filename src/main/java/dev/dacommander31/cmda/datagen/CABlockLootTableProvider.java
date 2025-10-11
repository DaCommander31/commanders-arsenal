package dev.dacommander31.cmda.datagen;

import dev.dacommander31.cmda.block.CABlocks;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.level.block.Block;

import java.util.Set;

public class CABlockLootTableProvider extends BlockLootSubProvider {
    protected CABlockLootTableProvider(HolderLookup.Provider registries) {
        super(Set.of(), FeatureFlags.REGISTRY.allFlags(), registries);
    }

    @Override
    protected void generate() {
        dropSelf(CABlocks.EMP_REACTOR.get());
    }

    @Override
    protected Iterable<Block> getKnownBlocks() {
        return CABlocks.BLOCKS.getEntries().stream().map(Holder::value)::iterator;
    }
}
