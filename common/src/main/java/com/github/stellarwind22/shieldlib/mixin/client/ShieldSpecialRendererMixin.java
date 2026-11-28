package com.github.stellarwind22.shieldlib.mixin.client;

import com.github.stellarwind22.shieldlib.init.ShieldLibClient;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.rendertype.RenderType;
import net.minecraft.client.renderer.special.ShieldSpecialRenderer;
import net.minecraft.core.component.DataComponentMap;
import net.minecraft.resources.Identifier;
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

    @Inject(method = "submit(Lnet/minecraft/core/component/DataComponentMap;Lcom/mojang/blaze3d/vertex/PoseStack;Lnet/minecraft/client/renderer/SubmitNodeCollector;IIZI)V", at = @At("HEAD"), cancellable = true)
    private void shieldlib$textureOverride(DataComponentMap components, PoseStack poseStack, SubmitNodeCollector submitNodeCollector, int lightCoords, int overlayCoords, boolean hasFoil, int outlineColor, CallbackInfo ci) {
        Identifier texture = ShieldLibClient.getOverrideTexture(components, "vanilla");
        if (texture == null) return;

        RenderType renderType = model.renderType(texture);

        poseStack.pushPose();
        poseStack.scale(1.0F, -1.0F, -1.0F);
        submitNodeCollector.submitModelPart(model.handle(), poseStack, renderType, lightCoords, overlayCoords, null, false, hasFoil, -1, null, outlineColor);
        submitNodeCollector.submitModelPart(model.plate(), poseStack, renderType, lightCoords, overlayCoords, null, false, hasFoil, -1, null, outlineColor);
        poseStack.popPose();

        ci.cancel();
    }
}
