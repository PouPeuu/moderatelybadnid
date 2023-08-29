package com.poupeuu.moderatelybadnid.blocks.blockEntities;

import com.poupeuu.moderatelybadnid.registers.ModBlockEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;

public class RadioBlockEntity extends BlockEntity {
    public RadioBlockEntity(BlockPos p_155229_, BlockState p_155230_) {
        super(ModBlockEntities.RADIO_BLOCK_ENTITY.get(), p_155229_, p_155230_);
    }
}
