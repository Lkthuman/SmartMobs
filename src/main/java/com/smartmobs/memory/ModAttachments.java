package com.smartmobs.memory;

import com.smartmobs.SmartMobs;
import java.util.function.Supplier;
import net.neoforged.neoforge.attachment.AttachmentType;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.NeoForgeRegistries;

public final class ModAttachments {
    public static final DeferredRegister<AttachmentType<?>> REGISTER =
            DeferredRegister.create(NeoForgeRegistries.ATTACHMENT_TYPES, SmartMobs.MOD_ID);

    public static final Supplier<AttachmentType<PlayerBehavior>> BEHAVIOR = REGISTER.register("behavior",
            () -> AttachmentType.builder(PlayerBehavior::new)
                    .serialize(PlayerBehavior.CODEC)
                    .copyOnDeath()
                    .build());

    private ModAttachments() {}
}
