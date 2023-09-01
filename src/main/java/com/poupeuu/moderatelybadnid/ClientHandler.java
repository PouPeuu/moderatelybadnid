package com.poupeuu.moderatelybadnid;

import com.poupeuu.moderatelybadnid.registers.ModBlockEntities;
import com.poupeuu.moderatelybadnid.renderers.RadioBlockEntityRenderer;
import com.poupeuu.moderatelybadnid.renderers.ToasterBlockEntityRenderer;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderers;
import net.minecraftforge.client.event.EntityRenderersEvent;
import org.lwjgl.glfw.GLFW;

public class ClientHandler {
    public static void registerRenderers(EntityRenderersEvent.RegisterRenderers event) {
        BlockEntityRenderers.register(ModBlockEntities.TOASTER_BLOCK_ENTITY.get(), ToasterBlockEntityRenderer::new);
        BlockEntityRenderers.register(ModBlockEntities.RADIO_BLOCK_ENTITY.get(), RadioBlockEntityRenderer::new);
    }

    public static boolean isMouseButtonDown(int button) {
        return GLFW.glfwGetMouseButton(Minecraft.getInstance()
                .getWindow()
                .getWindow(), button) == 1;
    }
}
