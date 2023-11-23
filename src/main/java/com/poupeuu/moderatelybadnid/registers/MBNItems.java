package com.poupeuu.moderatelybadnid.registers;

import com.poupeuu.moderatelybadnid.ModeratelyBadNid;
import com.poupeuu.moderatelybadnid.items.GleemBottleItem;
import net.minecraft.world.food.Foods;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class MBNItems {
    public static final DeferredRegister<Item> REGISTER = DeferredRegister.create(ForgeRegistries.ITEMS, ModeratelyBadNid.MODID);
    public static final RegistryObject<Item> RED_BLOCK_ITEM = REGISTER.register("red_block", () -> new BlockItem(MBNBlocks.RED_BLOCK.get(), new Item.Properties()));
    public static final RegistryObject<Item> TOASTER_ITEM = REGISTER.register("toaster", () -> new BlockItem(MBNBlocks.TOASTER.get(), new Item.Properties()));
    public static final RegistryObject<Item> RADIO_ITEM = REGISTER.register("radio", () -> new BlockItem(MBNBlocks.RADIO.get(), new Item.Properties()));
    //public static final RegistryObject<Item> WAND_ITEM = REGISTER.register("wand", () -> new WandItem(new Item.Properties()));
    public static final RegistryObject<Item> GLORT_ITEM = REGISTER.register("glort", () -> new BlockItem(MBNBlocks.GLORT.get(), new Item.Properties()));
    public static final RegistryObject<Item> GLEEM_BOTTLE = REGISTER.register("gleem_bottle", () -> new GleemBottleItem(new Item.Properties().stacksTo(16).food(Foods.HONEY_BOTTLE)));
}
