package com.github.stellarwind22.shieldlib.lib.registry;

import net.minecraft.core.component.DataComponentMap;
import net.minecraft.resources.Identifier;
import org.jspecify.annotations.Nullable;

@FunctionalInterface
public interface ShieldTextureOverride {
    @Nullable Identifier textureFor(@Nullable DataComponentMap components, String shape);
}
