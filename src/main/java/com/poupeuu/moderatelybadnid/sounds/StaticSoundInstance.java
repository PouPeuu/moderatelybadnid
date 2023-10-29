package com.poupeuu.moderatelybadnid.sounds;

import com.poupeuu.moderatelybadnid.registers.MBNSounds;
import net.minecraft.client.resources.sounds.AbstractTickableSoundInstance;
import net.minecraft.client.resources.sounds.SoundInstance;
import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.phys.Vec3;

public class StaticSoundInstance extends AbstractTickableSoundInstance {

    protected StaticSoundInstance(BlockPos position) {
        super(MBNSounds.STATIC.get(), SoundSource.RECORDS, SoundInstance.createUnseededRandom());
        volume = 0.05f;
        looping = true;
        Vec3 v = Vec3.atCenterOf(position);
        x = v.x;
        y = v.y;
        z = v.z;
    }

    @Override
    public void tick() {
        stop();
    }
}
