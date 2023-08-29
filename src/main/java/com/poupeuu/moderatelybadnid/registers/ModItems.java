package com.poupeuu.moderatelybadnid.registers;

import com.poupeuu.moderatelybadnid.ModeratelyBadNid;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModItems {
    public static final DeferredRegister<Item> REGISTER = DeferredRegister.create(ForgeRegistries.ITEMS, ModeratelyBadNid.MODID);
    public static final RegistryObject<Item> RED_BLOCK_ITEM = REGISTER.register("red_block", () -> new BlockItem(ModBlocks.RED_BLOCK.get(), new Item.Properties()));
    public static final RegistryObject<Item> TOASTER_ITEM = REGISTER.register("toaster", () -> new BlockItem(ModBlocks.TOASTER.get(), new Item.Properties()));
}
