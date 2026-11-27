package com.github.stellarwind22.shieldlib.mixin.client;

import com.github.stellarwind22.shieldlib.lib.client.render.ShieldTextureOverrides;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.special.ShieldSpecialRenderer;
import net.minecraft.core.component.DataComponentMap;
import net.minecraft.resources.Identifier;
import net.minecraft.world.item.ItemDisplayContext;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ShieldSpecialRenderer.class)
public abstract class ShieldSpecialRendererMixin {

    @Shadow
    @Final
    private net.minecraft.client.model.object.equipment.ShieldModel model;

    @Inject(method = "submit", at = @At("HEAD"), cancellable = true)
    private void shieldlib$textureOverride(DataComponentMap dataComponentMap, ItemDisplayContext itemDisplayContext, PoseStack poseStack, SubmitNodeCollector submitNodeCollector, int i, int j, boolean bl, int k, CallbackInfo ci) {
        Identifier texture = ShieldTextureOverrides.find(dataComponentMap, "vanilla");
        if (texture == null) return;

        ShieldTexturedRendering.submit(model, model.handle(), model.plate(), texture, poseStack, submitNodeCollector, i, j, bl, k);
        ci.cancel();
    }
}
