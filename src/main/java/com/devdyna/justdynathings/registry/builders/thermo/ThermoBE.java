package com.devdyna.justdynathings.registry.builders.thermo;

import com.devdyna.justdynathings.Config;
import com.devdyna.justdynathings.registry.interfaces.be.EnergyGenerator;
import com.devdyna.justdynathings.registry.interfaces.be.FluidMachine;
import com.devdyna.justdynathings.registry.types.zBlockEntities;
import com.devdyna.justdynathings.registry.types.zBlockTags;
import com.devdyna.justdynathings.registry.types.zHandlers;
import com.devdyna.justdynathings.registry.types.zProperties;
import com.devdyna.justdynathings.utils.Actions;
import com.direwolf20.justdirethings.common.blockentities.basebe.BaseMachineBE;
import com.direwolf20.justdirethings.common.blockentities.basebe.FluidContainerData;
import com.direwolf20.justdirethings.common.blockentities.basebe.PoweredMachineContainerData;
import com.direwolf20.justdirethings.common.blockentities.basebe.RedstoneControlledBE;
import com.direwolf20.justdirethings.common.capabilities.MachineEnergyStorage;
import com.direwolf20.justdirethings.setup.Registration;
import com.direwolf20.justdirethings.util.interfacehelpers.RedstoneControlData;

import net.minecraft.core.BlockPos;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.neoforged.neoforge.fluids.capability.templates.FluidTank;

@SuppressWarnings("null")
public class ThermoBE extends BaseMachineBE implements FluidMachine, EnergyGenerator, RedstoneControlledBE {

    public RedstoneControlData redstoneControlData = new RedstoneControlData();
    public final PoweredMachineContainerData poweredMachineData = new PoweredMachineContainerData(this);
    public final FluidContainerData fluidContainerData = new FluidContainerData(this);

    int minFERate = Config.THERMOGEN_FE_ONLY_HEATED.get();
    int maxFERate = Config.THERMOGEN_FE_WITH_COOLANT.get();

    public ThermoBE(BlockEntityType<?> type, BlockPos pos, BlockState b) {
        super(type, pos, b);
    }

    public ThermoBE(BlockPos pos, BlockState b) {
        this(zBlockEntities.THERMOGEN.get(), pos, b);
    }

    @Override
    public void tickServer() {
        updateBlock();

        boolean hasWater = getBlockState().getValue(zProperties.COOLED).booleanValue();
        int ferate = hasWater ? maxFERate : (Config.THERMOGEN_REQUIRE_COOLANT.get() ? 0 : minFERate);

        if (isActiveRedstone()) {
            if (getBlockState().getValue(zProperties.HEATED).booleanValue()) {
                if (hasWater)
                    extractMBWhenPossible();

                increaseFEWhenPossible(ferate);
            }
            if (canExtractFE())
                Actions.providePowerAdjacent(getBlockPos(), level, ferate);
        }
    }

    /**
     * update the blockstate properties
     * Credits: Thanks @S4lvious to optimize block update logic
     */
    public void updateBlock() {

        boolean heated = getHeatBlock().is(zBlockTags.THERMO_HEATER);
        boolean cooled = canExtractMB();

        BlockState currentState = getBlockState();
        if (currentState.getValue(zProperties.HEATED) != heated
                || currentState.getValue(zProperties.COOLED) != cooled) {
            level.setBlockAndUpdate(getBlockPos(), currentState.setValue(zProperties.HEATED, heated)
                    .setValue(zProperties.COOLED, cooled));
        }

    }

    public BlockState getHeatBlock() {
        return level.getBlockState(getBlockPos()
                .relative(getBlockState()
                        .getValue(BlockStateProperties.FACING)));
    }

    @Override
    public ContainerData getFluidContainerData() {
        return fluidContainerData;
    }

    @Override
    public FluidTank getFluidTank() {
        return getData(zHandlers.THERMO_FUELS);
    }

    @Override
    public MachineEnergyStorage getEnergyStorage() {
        return getData(Registration.ENERGYSTORAGE_GENERATORS);
    }

    @Override
    public ContainerData getContainerData() {
        return poweredMachineData;
    }

    @Override
    public BlockEntity getBlockEntity() {
        return this;
    }

    @Override
    public RedstoneControlData getRedstoneControlData() {
        return redstoneControlData;
    }

    @Override
    public int getStandardEnergyCost() {
        return 0;
    }

    @Override
    public int getMaxEnergy() {
        return Config.THERMOGEN_FE_CAPACITY.get();
    }

    @Override
    public int getMaxMB() {
        return Config.THERMOGEN_MB_CAPACITY.get();
    }

    @Override
    public int getStandardFluidCost() {
        return Config.THERMOGEN_MB_COST.get();
    }



}
