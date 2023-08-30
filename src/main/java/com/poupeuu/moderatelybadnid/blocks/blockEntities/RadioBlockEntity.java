package com.poupeuu.moderatelybadnid.blocks.blockEntities;

import com.poupeuu.moderatelybadnid.registers.ModBlockEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;

public class RadioBlockEntity extends BlockEntity {
    public RadioBlockEntity(BlockPos blockPos, BlockState blockState) {
        super(ModBlockEntities.RADIO_BLOCK_ENTITY.get(), blockPos, blockState);
    }
}
