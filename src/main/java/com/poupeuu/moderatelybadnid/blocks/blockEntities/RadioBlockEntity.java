package com.poupeuu.moderatelybadnid.blocks.blockEntities;

import com.ibm.icu.text.Normalizer2;
import com.poupeuu.moderatelybadnid.ClientHandler;
import com.poupeuu.moderatelybadnid.ModeratelyBadNid;
import com.poupeuu.moderatelybadnid.blocks.RadioBlock;
import com.poupeuu.moderatelybadnid.registers.ModBlockEntities;
import com.poupeuu.moderatelybadnid.registers.ModBlocks;
import net.minecraft.client.Camera;
import net.minecraft.client.Minecraft;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.HitResult;

public class RadioBlockEntity extends BlockEntity {
    private static boolean selected = false;
    public RadioBlockEntity(BlockPos blockPos, BlockState blockState) {
        super(ModBlockEntities.RADIO_BLOCK_ENTITY.get(), blockPos, blockState);
    }

    public static void clientTick(Level level, BlockPos blockPos, BlockState blockState, RadioBlockEntity radioBlockEntity) {
        Minecraft minecraft = Minecraft.getInstance();
        LocalPlayer player = minecraft.player;
        if(ClientHandler.isMouseButtonDown(1)) {
            if (!player.isShiftKeyDown()) {
                HitResult result = player.pick(4.6, 0.0f, false);
                if (result.getType() == HitResult.Type.BLOCK && level.getBlockState(((BlockHitResult) result).getBlockPos()).is(ModBlocks.RADIO.get())) {
                    ModeratelyBadNid.setCameraLocked(true);
                    selected = true;
                }
            }
        } else {
            ModeratelyBadNid.setCameraLocked(false);
            selected = false;
        }

        if (selected){
            float deltaPitch = -ModeratelyBadNid.getDeltaPitch();
            if(deltaPitch!=0) {
                ModeratelyBadNid.sendChatMessage(String.valueOf(deltaPitch));
            }
        }
    }
}
