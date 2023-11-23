package com.poupeuu.moderatelybadnid;

import com.mojang.logging.LogUtils;
import com.poupeuu.moderatelybadnid.registers.*;
import net.minecraft.client.Minecraft;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraftforge.client.event.RenderGuiOverlayEvent;
import net.minecraftforge.client.event.ViewportEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;
import org.slf4j.Logger;

import java.util.ArrayList;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(ModeratelyBadNid.MODID)
public class ModeratelyBadNid
{
    // Define mod id in a common place for everything to reference
    public static final String MODID = "moderatelybadnid";
    public static final Logger LOGGER = LogUtils.getLogger();
    private float lastCameraPitch = 0;
    private float lastCameraYaw = 0;
    private static float deltaPitch = 0;
    private static float deltaYaw = 0;
    private static boolean lockCamera = false;

    public static void setCameraLocked(boolean value){
        lockCamera = value;
    }

    public static boolean getCameraLocked(){
        return lockCamera;
    }

    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, MODID);
    public static final RegistryObject<CreativeModeTab> TAB = CREATIVE_MODE_TABS.register("moderatelybadnid", () -> CreativeModeTab.builder()
            .withTabsBefore(CreativeModeTabs.COMBAT)
            .icon(() -> MBNItems.TOASTER_ITEM.get().getDefaultInstance())
            .title(Component.translatable("itemGroup.moderatelybadnid"))
            .displayItems((parameters, output) -> {
                output.accept(MBNItems.RED_BLOCK_ITEM.get());
                output.accept(MBNItems.TOASTER_ITEM.get());
                output.accept(MBNItems.RADIO_ITEM.get());
                output.accept(MBNItems.GLORT_ITEM.get());
            }).build());

    public ModeratelyBadNid()
    {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

        modEventBus.addListener(this::clientSetup);

        MBNBlocks.REGISTER.register(modEventBus);
        MBNItems.REGISTER.register(modEventBus);
        MBNBlockEntities.REGISTER.register(modEventBus);
        MBNSounds.REGISTER.register(modEventBus);
        MBNEntities.REGISTER.register(modEventBus);
        CREATIVE_MODE_TABS.register(modEventBus);

        // Register ourselves for server and other game events we are interested in
        MinecraftForge.EVENT_BUS.register(this);

        // Register our mod's ForgeConfigSpec so that Forge can create and load the config file for us
        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, Config.SPEC);
    }

    private void clientSetup(final FMLClientSetupEvent event) {
        event.enqueueWork(() -> ClientHandler.clientInit());
    }

    public static void sendChatMessage(String message){
        Player player = Minecraft.getInstance().player;
        if (player != null) {
            player.sendSystemMessage(Component.literal(message));
        }
    }

    public static float getDeltaPitch() {
        return deltaPitch;
    }

    public static float getDeltaYaw() {
        return deltaYaw;
    }

    @SubscribeEvent
    public void onCameraSetup(ViewportEvent.ComputeCameraAngles event){
        //this.sendChatMessage(String.valueOf(event.getPitch()));
        //this.sendChatMessage(String.valueOf(event.getYaw()));

        float pitch = event.getPitch();
        float yaw = event.getYaw();

        deltaPitch = pitch - this.lastCameraPitch;
        deltaYaw = yaw - this.lastCameraYaw;

        if(this.lockCamera){
            event.setPitch(lastCameraPitch);
            event.setYaw(lastCameraYaw);
            pitch = event.getPitch();
            yaw = event.getYaw();
            LocalPlayer player = Minecraft.getInstance().player;
            player.setYRot(lastCameraYaw);
            player.setXRot(lastCameraPitch);
        }

        this.lastCameraPitch = pitch;
        this.lastCameraYaw = yaw;
    }
    
    public static final ArrayList<String> alrsaid = new ArrayList<String>(256);
    @SubscribeEvent
    public void onRenderGuiOverlay(RenderGuiOverlayEvent.Pre event){
        String name = event.getOverlay().id().getPath();
        if (ModeratelyBadNid.getCameraLocked() && name == "crosshair"){
            event.setCanceled(true);
        }
    }
}
