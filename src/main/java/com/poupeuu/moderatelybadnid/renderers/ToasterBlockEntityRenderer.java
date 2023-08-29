package com.poupeuu.moderatelybadnid.renderers;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import com.poupeuu.moderatelybadnid.ModeratelyBadNid;
import com.poupeuu.moderatelybadnid.blocks.ToasterBlock;
import com.poupeuu.moderatelybadnid.blocks.blockEntities.ToasterBlockEntity;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.core.Direction;
import net.minecraft.core.NonNullList;
import net.minecraft.world.ContainerHelper;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.client.model.renderable.BakedModelRenderable;

import java.util.Random;

public class ToasterBlockEntityRenderer implements BlockEntityRenderer<ToasterBlockEntity> {
    public ToasterBlockEntityRenderer(BlockEntityRendererProvider.Context renderer) {

    }

    @Override
    public void render(ToasterBlockEntity toasterBlockEntity, float partialTick, PoseStack poseStack, MultiBufferSource multiBufferSource, int light, int overlay) {
        Direction direction = toasterBlockEntity.getBlockState().getValue(ToasterBlock.FACING);
        double rotation = direction.get2DDataValue()*90;
        NonNullList<ItemStack> items = toasterBlockEntity.getItems();


        for(int i = 0; i < items.size(); i++){
            ItemStack itemStack = items.get(i);
            if(!itemStack.isEmpty()){
                poseStack.pushPose();

                double x = 0, y = 0, z = 0;

                /*switch (direction) {
                    case NORTH:
                        x = 0.5;
                        z = 0.15;
                        break;
                    case SOUTH:
                        x = 0.5;
                        z = 0.85;
                        break;
                    case WEST:
                        x = 0.15;
                        z = 0.5;
                        break;
                    case EAST:
                        x = 0.85;
                        z = 0.5;
                        break;
                }*/

                x = 0.5 + Math.cos(Math.toRadians(rotation))/8 * (i*2-1);
                y = toasterBlockEntity.getBlockState().getValue(ToasterBlock.TOASTING) ? 0.5 : 0.75;
                z = 0.5 + Math.sin(Math.toRadians(rotation))/8 * (i*2-1);

                poseStack.translate(x, y, z);
                float scale = 0.7f;
                poseStack.scale(scale,scale,scale);

                poseStack.mulPose(Axis.YP.rotationDegrees((float)rotation+90));

                Minecraft.getInstance().getItemRenderer().renderStatic(itemStack, ItemDisplayContext.FIXED, light, overlay, poseStack, multiBufferSource, toasterBlockEntity.getLevel(), 0);
                poseStack.popPose();
            }
        }
    }
}
