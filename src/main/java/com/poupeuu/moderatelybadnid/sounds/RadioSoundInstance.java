package com.poupeuu.moderatelybadnid.sounds;

import com.poupeuu.moderatelybadnid.blocks.blockEntities.RadioBlockEntity;
import net.minecraft.client.resources.sounds.AbstractTickableSoundInstance;
import net.minecraft.client.resources.sounds.SoundInstance;
import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.RandomSource;

public class RadioSoundInstance extends AbstractTickableSoundInstance {
    protected final RadioBlockEntity radio;
    protected RadioSoundInstance(RadioBlockEntity radio, SoundEvent soundEvent, SoundSource soundSource) {
        super(soundEvent, soundSource, SoundInstance.createUnseededRandom());
        this.radio = radio;
        BlockPos pos = radio.getBlockPos();
        x = pos.getX();
        y = pos.getY();
        z = pos.getZ();
        looping = true;
        delay = 0;
        volume = 0.0F;
    }

    @Override
    public void tick(){

    }
}
