package com.poupeuu.moderatelybadnid.renderers;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.poupeuu.moderatelybadnid.ModeratelyBadNid;
import com.poupeuu.moderatelybadnid.entities.SopoEntity;
import com.poupeuu.moderatelybadnid.models.SopoModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.LivingEntityRenderer;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

public class SopoEntityRenderer extends MobRenderer<SopoEntity, SopoModel<SopoEntity>> {

    private static final ResourceLocation textureLoc = new ResourceLocation(ModeratelyBadNid.MODID, "textures/model/sopo_texture.png");

    public SopoEntityRenderer(EntityRendererProvider.Context context, SopoModel<SopoEntity> model) {
        super(context, model, 0.7F);
    }

    public void render(SopoEntity entity, float sopoYaw, float partialTicks, PoseStack poseStack, MultiBufferSource source, int light){
        super.render(entity, sopoYaw, partialTicks, poseStack, source, light);
    }

    @Override
    public ResourceLocation getTextureLocation(SopoEntity entity) {
        return textureLoc;
    }
}
