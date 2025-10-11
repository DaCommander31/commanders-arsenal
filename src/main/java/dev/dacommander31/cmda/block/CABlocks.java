package dev.dacommander31.cmda.block;

import dev.dacommander31.cmda.CommandersArsenal;
import dev.dacommander31.cmda.block.custom.EMPReactorBlock;
import dev.dacommander31.cmda.item.CAItems;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.material.MapColor;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;
import java.util.function.ToIntFunction;

public class CABlocks {
    public static final DeferredRegister.Blocks BLOCKS = DeferredRegister.createBlocks(CommandersArsenal.MOD_ID);

    public static final DeferredBlock<EMPReactorBlock> EMP_REACTOR = registerBlock("emp_reactor",
            () -> new EMPReactorBlock(BlockBehaviour.Properties.of()
                    .lightLevel(litBlockEmission(15))
                    .strength(4f)
                    .requiresCorrectToolForDrops()
                    .mapColor(MapColor.COLOR_CYAN)
                    .sound(SoundType.SCULK_CATALYST)
            )
    );

    private static <T extends Block> DeferredBlock<T> registerBlock(String name, Supplier<T> block) {
        DeferredBlock<T> toReturn = BLOCKS.register(name, block);
        registerBlockItem(name, toReturn);
        return toReturn;
    }

    private static <T extends Block> void registerBlockItem(String name, DeferredBlock<T> block) {
        CAItems.ITEMS.register(name, () -> new BlockItem(block.get(), new Item.Properties()));
    }

    public static void register(IEventBus eventBus) {
        BLOCKS.register(eventBus);
    }

    private static ToIntFunction<BlockState> litBlockEmission(int lightValue) {
        return state -> state.getValue(BlockStateProperties.LIT) ? lightValue : 0;
    }
}
