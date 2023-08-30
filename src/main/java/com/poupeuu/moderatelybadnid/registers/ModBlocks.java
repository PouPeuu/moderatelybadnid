package com.poupeuu.moderatelybadnid.registers;

import com.poupeuu.moderatelybadnid.ModeratelyBadNid;
import com.poupeuu.moderatelybadnid.blocks.RadioBlock;
import com.poupeuu.moderatelybadnid.blocks.RedBlock;
import com.poupeuu.moderatelybadnid.blocks.ToasterBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.MapColor;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModBlocks {
    public static final DeferredRegister<Block> REGISTER = DeferredRegister.create(ForgeRegistries.BLOCKS, ModeratelyBadNid.MODID);
    public static final RegistryObject<Block> RED_BLOCK = REGISTER.register("red_block", () -> new RedBlock(BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_RED)));
    public static final RegistryObject<Block> TOASTER = REGISTER.register("toaster", () -> new ToasterBlock(BlockBehaviour.Properties.of().noOcclusion()));
    public static final RegistryObject<Block> RADIO = REGISTER.register("radio", () -> new RadioBlock(BlockBehaviour.Properties.of().noOcclusion()));
}
