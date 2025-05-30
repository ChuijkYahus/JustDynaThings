package com.devdyna.justdynathings.registry.builders.sculk;

import com.devdyna.justdynathings.registry.builders._core.block.BlockBaseBE;
import com.devdyna.justdynathings.registry.types.zProperties;
import net.minecraft.core.BlockPos;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import javax.annotation.Nullable;

@SuppressWarnings("null")
public class SculkBlock extends BlockBaseBE {


    public SculkBlock() {
        super(zProperties.MachineProp);
    }

    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
        return new SculkBE(pos, state);
    }

    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        return defaultBlockState()
                .setValue(zProperties.ACTIVE, true);
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder
                .add(zProperties.ACTIVE);
    }
}