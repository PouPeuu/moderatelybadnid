package com.poupeuu.moderatelybadnid.registers;

import com.poupeuu.moderatelybadnid.ModeratelyBadNid;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModSounds {
    public static final DeferredRegister<SoundEvent> REGISTER = DeferredRegister.create(ForgeRegistries.SOUND_EVENTS, ModeratelyBadNid.MODID);
    public static final RegistryObject<SoundEvent> TOASTER_START = REGISTER.register("block.toaster.toaster_start", () -> SoundEvent.createVariableRangeEvent(new ResourceLocation(ModeratelyBadNid.MODID, "block.toaster.toaster_start")));
    public static final RegistryObject<SoundEvent> TOASTER_END = REGISTER.register("block.toaster.toaster_end", () -> SoundEvent.createVariableRangeEvent(new ResourceLocation(ModeratelyBadNid.MODID, "block.toaster.toaster_end")));

    public static final RegistryObject<SoundEvent> STATIC = REGISTER.register("block.radio.static", () -> SoundEvent.createVariableRangeEvent(new ResourceLocation(ModeratelyBadNid.MODID, "block.radio.static")));
}
