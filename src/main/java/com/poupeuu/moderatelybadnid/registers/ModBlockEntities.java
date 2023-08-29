package com.poupeuu.moderatelybadnid.registers;

import com.poupeuu.moderatelybadnid.ModeratelyBadNid;
import com.poupeuu.moderatelybadnid.blocks.blockEntities.ToasterBlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModBlockEntities {
    public static final DeferredRegister<BlockEntityType<?>> REGISTER = DeferredRegister.create(ForgeRegistries.BLOCK_ENTITY_TYPES, ModeratelyBadNid.MODID);

    public static final RegistryObject<BlockEntityType<ToasterBlockEntity>> TOASTER_BLOCK_ENTITY = REGISTER.register("toaster", () -> BlockEntityType.Builder.of(ToasterBlockEntity::new, ModBlocks.TOASTER.get()).build(null));
    public static final RegistryObject<BlockEntityType<ToasterBlockEntity>> RADIO_BLOCK_ENTITY = REGISTER.register("radio", () -> BlockEntityType.Builder.of(ToasterBlockEntity::new, ModBlocks.RADIO.get()).build(null));

}
