package com.github.stellarwind22.shieldlib.lib.client.render;

import net.minecraft.core.component.DataComponentMap;
import net.minecraft.resources.Identifier;
import org.jspecify.annotations.Nullable;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public final class ShieldTextureOverrides {

    @FunctionalInterface
    public interface Provider {
        /** shape is ShieldModel.shape(), or "vanilla" for the vanilla shield. Return null to leave the shield alone. */
        @Nullable Identifier textureFor(@Nullable DataComponentMap components, String shape);
    }

    private static final List<Provider> PROVIDERS = new CopyOnWriteArrayList<>();

    private ShieldTextureOverrides() { }

    public static void register(Provider provider) { PROVIDERS.add(provider); }

    public static @Nullable Identifier find(@Nullable DataComponentMap components, String shape) {
        for (Provider provider : PROVIDERS) {
            Identifier texture = provider.textureFor(components, shape);
            if (texture != null) return texture;
        }
        return null;
    }
}