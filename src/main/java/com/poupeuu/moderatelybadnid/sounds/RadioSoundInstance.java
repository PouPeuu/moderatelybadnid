package com.poupeuu.moderatelybadnid.sounds;

import com.poupeuu.moderatelybadnid.ModeratelyBadNid;
import com.poupeuu.moderatelybadnid.blocks.blockEntities.RadioBlockEntity;
import net.minecraft.client.resources.sounds.AbstractTickableSoundInstance;
import net.minecraft.client.resources.sounds.SoundInstance;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.RandomSource;

public class RadioSoundInstance extends AbstractTickableSoundInstance {
    protected static RadioBlockEntity radio;
    private static boolean playing = true;
    public boolean isPlaying() {
        return playing;
    }

    public void setPlaying(boolean playing) {
        this.playing = playing;
    }

    public RadioSoundInstance(RadioBlockEntity radio, SoundEvent soundEvent, SoundSource soundSource) {
        super(soundEvent, soundSource, SoundInstance.createUnseededRandom());
        this.radio = radio;
        BlockPos pos = radio.getBlockPos();
        x = pos.getX();
        y = pos.getY();
        z = pos.getZ();
        looping = true;
        delay = 0;
    }

    @Override
    public void tick(){
        if(playing){
            volume = 3.0f;
        } else {
            volume = 0.0f;
        }
    }
}
