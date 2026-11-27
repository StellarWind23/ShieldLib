package com.github.stellarwind22.shieldlib.mixin.client;

import com.mojang.serialization.MapCodec;
import net.minecraft.client.renderer.special.SpecialModelRenderer;
import net.minecraft.client.renderer.special.SpecialModelRenderers;
<<<<<<< HEAD
import net.minecraft.resources.Identifier;
=======
import net.minecraft.resources.ResourceLocation;
>>>>>>> old/main
import net.minecraft.util.ExtraCodecs;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(SpecialModelRenderers.class)
public interface SpecialModelRenderersAccessor {

    @Accessor(value = "ID_MAPPER")
<<<<<<< HEAD
    static ExtraCodecs.LateBoundIdMapper<Identifier, MapCodec<? extends SpecialModelRenderer.Unbaked>> getIDMapper() {
=======
    static ExtraCodecs.LateBoundIdMapper<ResourceLocation, MapCodec<? extends SpecialModelRenderer.Unbaked>> getIDMapper() {
>>>>>>> old/main
        throw new AssertionError();
    }
}
