package com.devdyna.justdynathings.registry.builders.goo.energy.diregoo;

import com.devdyna.justdynathings.config.common;
import com.devdyna.justdynathings.registry.builders.goo.energy.FEGoo;
import com.devdyna.justdynathings.registry.types.zBlockEntities;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.state.BlockState;

public class EnergyT1BE extends FEGoo {


    public EnergyT1BE(BlockPos pos, BlockState state) {
        super(zBlockEntities.T1_GOO.get(), pos,state);
    }

    public int getTier() {
        return common.GOO_T1_TIER.get();
    }

    public int counterReducer() {
        return common.GOO_T1_COUNTER_REDUCER.get();
    }

}
