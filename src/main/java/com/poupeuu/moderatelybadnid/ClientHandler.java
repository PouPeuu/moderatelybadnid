package com.poupeuu.moderatelybadnid;

import com.poupeuu.moderatelybadnid.registers.ModBlockEntities;
import com.poupeuu.moderatelybadnid.renderers.ToasterBlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderers;
import net.minecraftforge.client.event.EntityRenderersEvent;

public class ClientHandler {
    public static void registerRenderers(EntityRenderersEvent.RegisterRenderers event) {
        BlockEntityRenderers.register(ModBlockEntities.TOASTER_BLOCK_ENTITY.get(), ToasterBlockEntityRenderer::new);
    }
}
