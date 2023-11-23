package com.poupeuu.moderatelybadnid.blocks.blockEntities;

import com.poupeuu.moderatelybadnid.blocks.GlortBlock;
import com.poupeuu.moderatelybadnid.registers.MBNBlockEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.Connection;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.world.ContainerHelper;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.DoubleBlockHalf;
import org.jetbrains.annotations.Nullable;

public class GlortBlockEntity extends BlockEntity {

    private float gleemAmount = 100.0f;

    public GlortBlockEntity(BlockPos blockPos, BlockState blockState) {
        super(MBNBlockEntities.GLORT_BLOCK_ENTITY.get(), blockPos, blockState);
        if (blockState.getValue(GlortBlock.HALF) == DoubleBlockHalf.LOWER) {
            gleemAmount = 0.0f;
        }
    }

    public float extractGleem(float amount){
        amount = Math.min(amount, gleemAmount);
        gleemAmount -= amount;
        return amount;
    }

    public float getGleemAmount() {
        return gleemAmount;
    }

    protected void saveAdditional(CompoundTag tag) {
        super.saveAdditional(tag);
        tag.putFloat("gleem", gleemAmount);
    }

    public void load(CompoundTag tag){
        super.load(tag);
        gleemAmount = tag.getFloat("gleem");
    }

    public CompoundTag getUpdateTag() {
        CompoundTag compoundTag = super.getUpdateTag();
        compoundTag.putFloat("gleem", gleemAmount);
        return compoundTag;
    }

    @Override
    public void handleUpdateTag(CompoundTag tag){
        gleemAmount = tag.getFloat("gleem");
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
}
