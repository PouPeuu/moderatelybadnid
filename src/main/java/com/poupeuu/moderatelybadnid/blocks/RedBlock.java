package com.poupeuu.moderatelybadnid.blocks;

import com.poupeuu.moderatelybadnid.ModeratelyBadNid;
import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.sounds.Sound;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.CommandBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.ticks.TickPriority;
import org.joml.Vector3f;

public class RedBlock extends Block {
    public static final IntegerProperty REPEATS = IntegerProperty.create("repeats",0,10);
    public RedBlock(Properties properties) {
        super(properties);
        this.registerDefaultState(this.getStateDefinition().any().setValue(REPEATS, 0));
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder){
        builder.add(REPEATS);
    }

    @Override
    public void tick(BlockState blockState, ServerLevel serverLevel, BlockPos blockPos, RandomSource randomSource){
        //Minecraft.getInstance().player.sendSystemMessage(Component.literal("ticked "+String.valueOf(blockState.getValue(REPEATS))));
        serverLevel.playSound(null, blockPos, SoundEvent.createFixedRangeEvent(new ResourceLocation("minecraft", "entity.player.hurt"), Float.MAX_VALUE), SoundSource.MASTER);
        if (blockState.getValue(REPEATS)>0){
            serverLevel.scheduleTick(blockPos, blockState.getBlock(), 1);
            blockState = blockState.setValue(REPEATS, blockState.getValue(REPEATS)-1);
            serverLevel.setBlockAndUpdate(blockPos,blockState);
        }
    }

    @Override
    public void stepOn(Level level, BlockPos blockPos, BlockState blockState, Entity entity){
        entity.addDeltaMovement(new Vec3(0,100,0));
        blockState = blockState.setValue(REPEATS, 10);
        level.setBlockAndUpdate(blockPos, blockState);
        level.scheduleTick(blockPos, blockState.getBlock(), 1);
        //level.explode(null, blockPos.getX(), blockPos.getY(), blockPos.getZ(), 10, Level.ExplosionInteraction.BLOCK);
    }
}
