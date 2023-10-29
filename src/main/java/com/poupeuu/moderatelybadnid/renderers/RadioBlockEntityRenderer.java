package com.poupeuu.moderatelybadnid.renderers;

import com.mojang.blaze3d.vertex.PoseStack;
import com.poupeuu.moderatelybadnid.ModeratelyBadNid;
import com.poupeuu.moderatelybadnid.blocks.blockEntities.RadioBlockEntity;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.block.BlockRenderDispatcher;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.resources.model.ModelManager;
import net.minecraft.resources.ResourceLocation;

public class RadioBlockEntityRenderer implements BlockEntityRenderer<RadioBlockEntity> {

    private final ResourceLocation KNOB = new ResourceLocation(ModeratelyBadNid.MODID, "block/knob");

    private final ModelManager modelManager;
    private final BlockRenderDispatcher blockRenderDispatcher;

    public RadioBlockEntityRenderer(BlockEntityRendererProvider.Context renderer) {
        this.modelManager = renderer.getBlockRenderDispatcher().getBlockModelShaper().getModelManager();
        this.blockRenderDispatcher = renderer.getBlockRenderDispatcher();
    }
    @Override
    public void render(RadioBlockEntity radioBlockEntity, float partialTick, PoseStack poseStack, MultiBufferSource multiBufferSource, int light, int overlay) {

    }
}
