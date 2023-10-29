package com.poupeuu.moderatelybadnid.registers;

import com.poupeuu.moderatelybadnid.ModeratelyBadNid;
import com.poupeuu.moderatelybadnid.entities.SopoEntity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

@Mod.EventBusSubscriber(modid = ModeratelyBadNid.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class MBNEntities {
    public static final DeferredRegister<EntityType<?>> REGISTER = DeferredRegister.create(ForgeRegistries.ENTITY_TYPES, ModeratelyBadNid.MODID);

    public static final RegistryObject<EntityType<SopoEntity>> SOPO_ENTITY = REGISTER.register("sopo", () -> registerEntity(EntityType.Builder.of(SopoEntity::new, MobCategory.CREATURE)
            .sized(0.5f, 1.0f), "sopo"));

    private static final EntityType registerEntity(EntityType.Builder builder, String entityName) {
        return (EntityType) builder.build(entityName);
    }

    @SubscribeEvent
    public static void initializeAttributes(EntityAttributeCreationEvent event){
        event.put(SOPO_ENTITY.get(), SopoEntity.createAttributes().build());
    }
}
