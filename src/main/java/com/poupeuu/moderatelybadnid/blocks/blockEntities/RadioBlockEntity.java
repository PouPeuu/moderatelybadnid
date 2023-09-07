package com.poupeuu.moderatelybadnid.blocks.blockEntities;

import com.poupeuu.moderatelybadnid.ClientHandler;
import com.poupeuu.moderatelybadnid.ModeratelyBadNid;
import com.poupeuu.moderatelybadnid.registers.ModBlockEntities;
import com.poupeuu.moderatelybadnid.registers.ModBlocks;
import com.poupeuu.moderatelybadnid.registers.ModSounds;
import com.poupeuu.moderatelybadnid.util.Mathes;
import net.minecraft.client.Minecraft;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.client.resources.sounds.SimpleSoundInstance;
import net.minecraft.client.resources.sounds.SoundInstance;
import net.minecraft.client.sounds.SoundManager;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.Connection;
import net.minecraft.network.chat.Component;
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

    private static SimpleSoundInstance staticSoundInstance = null;
    private static final SimpleSoundInstance[] soundInstances = new SimpleSoundInstance[songs.length];
    private static final float[] frequencies = new float[songs.length];
    private static boolean initialized = false;


    public RadioBlockEntity(BlockPos blockPos, BlockState blockState) {
        super(ModBlockEntities.RADIO_BLOCK_ENTITY.get(), blockPos, blockState);
        staticSoundInstance = new SimpleSoundInstance(ModSounds.STATIC.get().getLocation(), SoundSource.MUSIC, 0.25f, 1.0f, SoundInstance.createUnseededRandom(), true, 0, SoundInstance.Attenuation.NONE, (double)blockPos.getX(), (double)blockPos.getY(), (double)blockPos.getZ(), true);
        for (int i = 0; i < songs.length; i++){
            soundInstances[i] = new SimpleSoundInstance(new ResourceLocation("minecraft",prefix+songs[i]), SoundSource.MUSIC, 0.5f, 1.0f, SoundInstance.createUnseededRandom(), true, 0, SoundInstance.Attenuation.NONE, (double)blockPos.getX(), (double)blockPos.getY(), (double)blockPos.getZ(), true);
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
    private static void initFrequencies(int songCount)
    {
        Random rand = new Random();
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

    public void stopAll(){
        Minecraft.getInstance().getSoundManager().stop(staticSoundInstance);
        for (int i = 0; i < soundInstances.length; i++){
            Minecraft.getInstance().getSoundManager().stop(soundInstances[i]);
        }
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
            /*if(selected){
                for (int i = 0; i < frequencies.length; i++){
                    ModeratelyBadNid.sendChatMessage(songs[i] + " " + String.valueOf(frequencies[i]));
                }
            }*/
            ModeratelyBadNid.setCameraLocked(false);
            selected = false;
        }

        if (selected){
            float deltaPitch = -ModeratelyBadNid.getDeltaPitch();
            if(deltaPitch!=0) {
                frequency += deltaPitch/5;
                frequency = Mathes.clamp(frequency, 0.0f, 100.0f);
                level.sendBlockUpdated(blockPos, blockState, blockState, Block.UPDATE_ALL);
                Minecraft.getInstance().gui.setOverlayMessage(Component.literal(String.valueOf(frequency)), false);
                //ModeratelyBadNid.sendChatMessage(String.valueOf(frequency));
            }
        }

        if(!initialized){
            initFrequencies(songs.length);
            initialized = true;
        }

        SoundManager soundManager = Minecraft.getInstance().getSoundManager();
        SimpleSoundInstance song = getSelectedSong();
        for(int i = 0; i < soundInstances.length; i++){
            if(song != null && soundInstances[i] == song && !soundManager.isActive(song)){
                soundManager.play(song);
                if(soundManager.isActive(staticSoundInstance)) {
                    soundManager.stop(staticSoundInstance);
                }
                continue;
            }

            if (song == null || soundInstances[i] != song) {
                soundManager.stop(soundInstances[i]);
            }
        }

        if(!soundManager.isActive(song) && !soundManager.isActive(staticSoundInstance)){
            soundManager.play(staticSoundInstance);
        }
    }

    private static SimpleSoundInstance getSelectedSong(){
        for (int i = 0; i < frequencies.length; i++){
            if(Math.abs(frequency - frequencies[i])<=2){
                return soundInstances[i];
            }
        }
        return null;
    }

    public static void serverTick(Level level, BlockPos blockPos, BlockState blockState, RadioBlockEntity radioBlockEntity) {

    }
}
