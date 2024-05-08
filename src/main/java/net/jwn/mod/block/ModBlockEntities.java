package net.jwn.mod.block;

import net.jwn.mod.Main;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModBlockEntities {
    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES =
            DeferredRegister.create(ForgeRegistries.BLOCK_ENTITY_TYPES, Main.MOD_ID);

    public static final RegistryObject<BlockEntityType<LuckyBoxBlockEntity>> LUCKY_BOX_BE = BLOCK_ENTITIES.register("lucky_box_be",
            () -> BlockEntityType.Builder.of(LuckyBoxBlockEntity::new, ModBlocks.LUCKY_BOX.get()).build(null));


    public static void register(IEventBus eventBus) {
        BLOCK_ENTITIES.register(eventBus);
    }
}
