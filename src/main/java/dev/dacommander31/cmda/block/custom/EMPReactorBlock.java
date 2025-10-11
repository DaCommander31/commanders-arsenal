package dev.dacommander31.cmda.block.custom;

import com.lowdragmc.photon.client.fx.BlockEffectExecutor;
import com.lowdragmc.photon.client.fx.FX;
import com.lowdragmc.photon.client.fx.FXHelper;
import com.mojang.serialization.MapCodec;
import dev.dacommander31.cmda.CommandersArsenal;
import dev.dacommander31.cmda.sound.CASounds;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.phys.BlockHitResult;

public class EMPReactorBlock extends Block {
    public static final MapCodec<EMPReactorBlock> CODEC = simpleCodec(EMPReactorBlock::new);
    public static final BooleanProperty LIT = BlockStateProperties.LIT;

    private static final int PULSE_DELAY = 40;

    @Override
    protected MapCodec<? extends Block> codec() {
        return CODEC;
    }

    public EMPReactorBlock(Properties properties) {
        super(properties);
        this.registerDefaultState(this.getStateDefinition().any().setValue(LIT, false));

    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(LIT);
    }

    @Override
    public void onBlockStateChange(LevelReader level, BlockPos pos, BlockState oldState, BlockState newState) {
        if (!level.isClientSide()) {
            schedulePulse((Level) level, pos, newState);
        }
    }

    @Override
    protected void onPlace(BlockState state, Level level, BlockPos pos, BlockState oldState, boolean movedByPiston) {
        if (!level.isClientSide) {
            schedulePulse(level, pos, state);
        }
    }

    @Override
    protected InteractionResult useWithoutItem(BlockState state, Level level, BlockPos pos, Player player, BlockHitResult hitResult) {
        if (!level.isClientSide) {
            level.setBlock(pos, state.cycle(LIT), 3);
        }
        return InteractionResult.PASS;
    }

    @Override
    protected void tick(BlockState state, ServerLevel level, BlockPos pos, RandomSource random) {
        if (state.getValue(LIT)) {
            FX pulse = FXHelper.getFX(ResourceLocation.fromNamespaceAndPath(CommandersArsenal.MOD_ID, "emp_shockwave"));
            if (pulse != null) {
                new BlockEffectExecutor(pulse, level, pos).start();
            }
            level.playSound(
                    null,
                    (double) pos.getX() + 0.5,
                    (double) pos.getY() + 0.5,
                    (double) pos.getZ() + 0.5,
                    CASounds.EMP_REACTOR_PULSE.get(),
                    SoundSource.BLOCKS,
                    1.0F,
                    random.nextIntBetweenInclusive(8, 12) / 10f
            );

        }

        schedulePulse(level, pos, state);

    }

    private void schedulePulse(Level level, BlockPos pos, BlockState state) {
        if (state.getValue(LIT)) {
            level.scheduleTick(pos, this, PULSE_DELAY);
        }
    }
}
