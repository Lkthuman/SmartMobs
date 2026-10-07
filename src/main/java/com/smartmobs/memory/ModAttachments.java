package com.smartmobs.memory;

import net.neoforged.neoforge.attachment.AttachmentType;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.NeoForgeRegistries;
import net.minecraft.core.registries.Registries;
import com.smartmobs.SmartMobs;
import java.util.function.Supplier;

public class ModAttachments {
    
    public static final DeferredRegister<AttachmentType<?>> ATTACHMENTS = 
        DeferredRegister.create(NeoForgeRegistries.ATTACHMENT_TYPES, SmartMobs.MODID);
    
    public static final Supplier<AttachmentType<PlayerBehavior>> PLAYER_BEHAVIOR =
        ATTACHMENTS.register("player_behavior", () ->
            AttachmentType.builder(() -> new PlayerBehavior())
                .serialize(PlayerBehavior.CODEC)
                .build()
        );
}
