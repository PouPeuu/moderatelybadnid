package com.poupeuu.moderatelybadnid.blocks;

import com.poupeuu.moderatelybadnid.blocks.blockEntities.RadioBlockEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.BaseEntityBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.Nullable;

public class RadioBlock extends BaseEntityBlock {
    protected RadioBlock(Properties properties) {
        super(properties);
    }

    @Override
    public BlockEntity newBlockEntity(BlockPos blockPos, BlockState blockState) {
        return new RadioBlockEntity(blockPos, blockState);
    }
}
