package com.poupeuu.moderatelybadnid.blocks.blockEntities;

import com.poupeuu.moderatelybadnid.ClientHandler;
import com.poupeuu.moderatelybadnid.ModeratelyBadNid;
import com.poupeuu.moderatelybadnid.blocks.RadioBlock;
import com.poupeuu.moderatelybadnid.registers.ModBlockEntities;
import net.minecraft.client.Minecraft;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;

public class RadioBlockEntity extends BlockEntity {
    public RadioBlockEntity(BlockPos blockPos, BlockState blockState) {
        super(ModBlockEntities.RADIO_BLOCK_ENTITY.get(), blockPos, blockState);
    }

    public static void clientTick(Level level, BlockPos blockPos, BlockState blockState, RadioBlockEntity radioBlockEntity){
        if(ClientHandler.isMouseButtonDown(1) && !Minecraft.getInstance().player.isShiftKeyDown()){
            ModeratelyBadNid.sendChatMessage("Right Mouse Down and not shifting");
        }
    }
}
