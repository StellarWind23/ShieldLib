package com.github.stellarwind22.shieldlib.mixin.client;

import com.github.stellarwind22.shieldlib.init.ShieldLibClient;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.rendertype.RenderType;
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

    @Inject(method = "submit(Lnet/minecraft/core/component/DataComponentMap;Lnet/minecraft/world/item/ItemDisplayContext;Lcom/mojang/blaze3d/vertex/PoseStack;Lnet/minecraft/client/renderer/SubmitNodeCollector;IIZI)V", at = @At("HEAD"), cancellable = true)
    private void shieldlib$textureOverride(DataComponentMap dataComponentMap, ItemDisplayContext itemDisplayContext, PoseStack poseStack, SubmitNodeCollector submitNodeCollector, int i, int j, boolean bl, int k, CallbackInfo ci) {
        Identifier texture = ShieldLibClient.getOverrideTexture(dataComponentMap, "vanilla");
        if (texture == null) return;

        RenderType renderType = model.renderType(texture);

        poseStack.pushPose();
        poseStack.scale(1.0F, -1.0F, -1.0F);
        submitNodeCollector.submitModelPart(model.handle(), poseStack, renderType, i, j, null, false, bl, -1, null, k);
        submitNodeCollector.submitModelPart(model.plate(), poseStack, renderType, i, j, null, false, bl, -1, null, k);
        poseStack.popPose();

        ci.cancel();
    }
}
