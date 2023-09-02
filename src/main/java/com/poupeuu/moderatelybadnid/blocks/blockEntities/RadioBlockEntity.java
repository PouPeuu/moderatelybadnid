package com.poupeuu.moderatelybadnid.blocks.blockEntities;

import com.poupeuu.moderatelybadnid.ClientHandler;
import com.poupeuu.moderatelybadnid.ModeratelyBadNid;
import com.poupeuu.moderatelybadnid.registers.ModBlockEntities;
import com.poupeuu.moderatelybadnid.registers.ModBlocks;
import com.poupeuu.moderatelybadnid.sounds.RadioSoundInstance;
import com.poupeuu.moderatelybadnid.util.Mathes;
import net.minecraft.client.Minecraft;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.client.resources.sounds.SimpleSoundInstance;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.Connection;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.HitResult;
import org.jetbrains.annotations.Nullable;

import java.util.Random;

public class RadioBlockEntity extends BlockEntity {
    private static boolean selected = false;
    private static float frequency = 0.0f;

    private static final String prefix = "music_disc.";
    private static final String[] songs = {"13","cat","blocks","chirp","far","mall","mellohi","stal","strad","ward","11","wait","otherside","5","pigstep","relic"};
    private static final RadioSoundInstance[] soundInstances = new RadioSoundInstance[songs.length];
    private static final float[] frequencies = new float[songs.length];
    private static boolean initialized = false;


    public RadioBlockEntity(BlockPos blockPos, BlockState blockState) {
        super(ModBlockEntities.RADIO_BLOCK_ENTITY.get(), blockPos, blockState);
        for (int i = 0; i < songs.length; i++){
            soundInstances[i] = new RadioSoundInstance(this, SoundEvent.createVariableRangeEvent(new ResourceLocation("minecraft", prefix+songs[i])), SoundSource.MUSIC);
        }
    }

    protected void saveAdditional(CompoundTag tag) {
        super.saveAdditional(tag);
        tag.putFloat("frequency", frequency);
    }

    public void load(CompoundTag tag){
        super.load(tag);
        frequency = tag.getFloat("frequency");
    }

    public CompoundTag getUpdateTag() {
        CompoundTag tag = super.getUpdateTag();
        tag.putFloat("frequency", frequency);
        return tag;
    }

    @Override
    public void handleUpdateTag(CompoundTag tag){
        frequency = tag.getFloat("frequency");
    }

    @Nullable
    @Override
    public ClientboundBlockEntityDataPacket getUpdatePacket() {
        return ClientboundBlockEntityDataPacket.create(this);
    }

    @Override
    public void onDataPacket(Connection net, ClientboundBlockEntityDataPacket pkt) {
        handleUpdateTag(pkt.getTag());
    }

    // Credits to eriksonn for this code
    private static void initFrequencies(long seed, int songCount)
    {
        Random rand = new Random(seed);
        float spacing = 0.8f;
        float maxFrequency=100;
        for(int i =0;i<songCount;i++)
        {
            frequencies[i]=maxFrequency*(i + (float)rand.nextFloat(1.0f)*spacing + (1-spacing)/2)/songCount;
        }
        for(int i =0;i<songCount;i++)
        {
            int i2 = rand.nextInt(i, songCount);//random number between i and songCount
            float temp = frequencies[i];
            frequencies[i]=frequencies[i2];
            frequencies[i2]=temp;
        }
    }

    private void playSong(float frequency){

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
            if(selected){
                for (int i = 0; i < frequencies.length; i++){
                    ModeratelyBadNid.sendChatMessage(songs[i] + " " + String.valueOf(frequencies[i]));
                }
            }
            ModeratelyBadNid.setCameraLocked(false);
            selected = false;
        }

        if (selected){
            float deltaPitch = -ModeratelyBadNid.getDeltaPitch();
            if(deltaPitch!=0) {
                frequency += deltaPitch;
                frequency = Mathes.clamp(frequency, 0.0f, 100.0f);
                level.sendBlockUpdated(blockPos, blockState, blockState, Block.UPDATE_ALL);
                ModeratelyBadNid.sendChatMessage(String.valueOf(frequency));
            }
        }
    }

    private static RadioSoundInstance getSelectedSong(){
        for (int i = 0; i < frequencies.length; i++){
            if(Math.abs(frequency - frequencies[i])<=3.0){
                return soundInstances[i];
            }
        }
        return null;
    }

    public static void serverTick(Level level, BlockPos blockPos, BlockState blockState, RadioBlockEntity radioBlockEntity) {
        if(!initialized){
            initFrequencies(((ServerLevel)level).getSeed(), songs.length);
            initialized = true;
        }

        RadioSoundInstance song = getSelectedSong();
        for(int i = 0; i < soundInstances.length; i++){
            if(song != null){
                if(soundInstances[i] == song && !song.isPlaying()) {
                    song.setPlaying(true);
                    Minecraft.getInstance().getSoundManager().play(song);
                    continue;
                }
            }
            if ((song == null || soundInstances[i].isPlaying()) && soundInstances[i] != song) {
                Minecraft.getInstance().getSoundManager().stop(soundInstances[i]);
            }
            soundInstances[i].setPlaying(false);
        }
    }
}
