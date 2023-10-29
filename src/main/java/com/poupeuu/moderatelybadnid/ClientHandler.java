package com.poupeuu.moderatelybadnid;

import com.poupeuu.moderatelybadnid.models.SopoModel;
import com.poupeuu.moderatelybadnid.registers.MBNBlockEntities;
import com.poupeuu.moderatelybadnid.registers.MBNEntities;
import com.poupeuu.moderatelybadnid.renderers.RadioBlockEntityRenderer;
import com.poupeuu.moderatelybadnid.renderers.SopoEntityRenderer;
import com.poupeuu.moderatelybadnid.renderers.ToasterBlockEntityRenderer;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderers;
import net.minecraft.client.renderer.entity.EntityRenderers;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.ForgeHooksClient;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import org.lwjgl.glfw.GLFW;

@Mod.EventBusSubscriber(modid = ModeratelyBadNid.MODID, value = Dist.CLIENT, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ClientHandler {
    public static void clientInit() {
        BlockEntityRenderers.register(MBNBlockEntities.TOASTER_BLOCK_ENTITY.get(), ToasterBlockEntityRenderer::new);
        BlockEntityRenderers.register(MBNBlockEntities.RADIO_BLOCK_ENTITY.get(), RadioBlockEntityRenderer::new);
        EntityRenderers.register(MBNEntities.SOPO_ENTITY.get(), SopoEntityRenderer::new);
    }

    @SubscribeEvent
    public static void registerLayerDefinition(EntityRenderersEvent.RegisterLayerDefinitions event){
        event.registerLayerDefinition(SopoModel.LAYER_LOCATION, SopoModel::createBodyLayer);
    }

    public static boolean isMouseButtonDown(int button) {
        return GLFW.glfwGetMouseButton(Minecraft.getInstance()
                .getWindow()
                .getWindow(), button) == 1;
    }
}
