package com.poupeuu.moderatelybadnid.blocks.blockEntities;

import com.poupeuu.moderatelybadnid.ModeratelyBadNid;
import com.poupeuu.moderatelybadnid.blocks.ToasterBlock;
import com.poupeuu.moderatelybadnid.registers.ModBlockEntities;
import com.poupeuu.moderatelybadnid.registers.ModSounds;
import net.minecraft.core.BlockPos;
import net.minecraft.core.NonNullList;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.Connection;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.Container;
import net.minecraft.world.ContainerHelper;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.CampfireCookingRecipe;
import net.minecraft.world.item.crafting.RecipeManager;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.Nullable;

import java.util.Optional;

public class ToasterBlockEntity extends BlockEntity {
    private final NonNullList<ItemStack> items = NonNullList.withSize(2, ItemStack.EMPTY);
    private final RecipeManager.CachedCheck<Container, CampfireCookingRecipe> quickCheck = RecipeManager.createCheck(RecipeType.CAMPFIRE_COOKING);
    private int progress = 0;

    public ToasterBlockEntity(BlockPos blockPos, BlockState blockState) {
        super(ModBlockEntities.TOASTER_BLOCK_ENTITY.get(), blockPos, blockState);
    }

    protected void saveAdditional(CompoundTag tag) {
        super.saveAdditional(tag);
        ContainerHelper.saveAllItems(tag, items);
        tag.putInt("progress", progress);
    }

    public void load(CompoundTag tag){
        super.load(tag);
        this.items.clear();
        ContainerHelper.loadAllItems(tag, items);
        tag.getInt("progress");
    }

    public CompoundTag getUpdateTag() {
        CompoundTag compoundTag = super.getUpdateTag();
        ContainerHelper.saveAllItems(compoundTag, items);
        return compoundTag;
    }

    @Override
    public void handleUpdateTag(CompoundTag tag){
        this.items.clear();
        ContainerHelper.loadAllItems(tag, items);
    }

    @Nullable
    @Override
    public Packet<ClientGamePacketListener> getUpdatePacket() {
        return ClientboundBlockEntityDataPacket.create(this);
    }

    @Override
    public void onDataPacket(Connection net, ClientboundBlockEntityDataPacket pkt) {
        handleUpdateTag(pkt.getTag());
    }

    public static void cookTick(Level level, BlockPos blockPos, BlockState blockState, ToasterBlockEntity toasterBlockEntity){
        // no idea if side check is necessary in this case
        if(!level.isClientSide() && !toasterBlockEntity.items.isEmpty() && blockState.getValue(ToasterBlock.TOASTING)){
            toasterBlockEntity.progress--;
            if(toasterBlockEntity.progress <= 0) {
                for (int i = 0; i < toasterBlockEntity.items.size(); i++){
                    ItemStack itemStack = toasterBlockEntity.items.get(i);
                    if (!itemStack.isEmpty()) {
                        Container container = new SimpleContainer(itemStack);
                        ItemStack result = toasterBlockEntity.quickCheck.getRecipeFor(container, level).map((campfireCookingRecipe) -> {
                            return campfireCookingRecipe.assemble(container, level.registryAccess());
                        }).orElse(itemStack);
                        if (result.isItemEnabled(level.enabledFeatures())) {
                            ItemEntity itementity = new ItemEntity(level, blockPos.getX()+0.5, blockPos.getY()+1, blockPos.getZ()+0.5, result, 0, 0.5, 0);
                            level.addFreshEntity(itementity);
                            toasterBlockEntity.items.set(i, ItemStack.EMPTY);
                        }
                    }
                }

                blockState = blockState.setValue(ToasterBlock.TOASTING, false);
                level.setBlockAndUpdate(blockPos, blockState);
                level.playSound(null, blockPos, ModSounds.TOASTER_END.get(), SoundSource.MASTER);
            }
        }
    }

    public Optional<CampfireCookingRecipe> getCookableRecipe(ItemStack itemStack) {
        return this.items.stream().noneMatch(ItemStack::isEmpty) ? Optional.empty() : this.quickCheck.getRecipeFor(new SimpleContainer(itemStack), this.level);
    }

    public NonNullList<ItemStack> getItems(){
        return this.items;
    }

    public void addToast(ItemStack itemStack, int cookingTime){
        for (int i = 0; i < this.items.size(); i++){
            ItemStack stack = this.items.get(i);
            if (stack.isEmpty()){
                this.items.set(i, itemStack);
                if (cookingTime > this.progress) {
                    this.progress = cookingTime;
                }
                this.level.sendBlockUpdated(this.worldPosition, this.getBlockState(), this.getBlockState(), Block.UPDATE_ALL);
                break;
            }
        }
    }
}
