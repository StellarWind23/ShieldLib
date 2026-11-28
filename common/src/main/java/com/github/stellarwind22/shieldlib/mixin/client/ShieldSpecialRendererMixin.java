package com.github.stellarwind22.shieldlib.mixin.client;

import com.github.stellarwind22.shieldlib.init.ShieldLibClient;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.rendertype.RenderType;
import net.minecraft.client.renderer.rendertype.RenderTypes;
import net.minecraft.client.renderer.special.ShieldSpecialRenderer;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.resources.model.Material;
import net.minecraft.client.resources.model.MaterialSet;
import net.minecraft.client.resources.model.ModelBakery;
import net.minecraft.core.component.DataComponentMap;
import net.minecraft.resources.Identifier;
import net.minecraft.world.item.ItemDisplayContext;
import org.jspecify.annotations.Nullable;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ShieldSpecialRenderer.class)
public abstract class ShieldSpecialRendererMixin {

    @Shadow @Final private net.minecraft.client.model.object.equipment.ShieldModel model;
    @Shadow @Final private MaterialSet materials;

    @Inject(method = "submit(Lnet/minecraft/core/component/DataComponentMap;Lnet/minecraft/world/item/ItemDisplayContext;Lcom/mojang/blaze3d/vertex/PoseStack;Lnet/minecraft/client/renderer/SubmitNodeCollector;IIZI)V", at = @At("HEAD"), cancellable = true)
    private void shieldlib$overlay(@Nullable DataComponentMap dataComponentMap, ItemDisplayContext itemDisplayContext, PoseStack poseStack, SubmitNodeCollector submitNodeCollector, int i, int j, boolean bl, int k, CallbackInfo ci) {
        Identifier texture = ShieldLibClient.getOverrideTexture(dataComponentMap, "vanilla");
        if (texture == null) return;

        Material base = ModelBakery.NO_PATTERN_SHIELD;
        RenderType type = model.renderType(base.atlasLocation());
        TextureAtlasSprite sprite = materials.get(base);

        poseStack.pushPose();
        poseStack.scale(1.0F, -1.0F, -1.0F);

        //Handle base model
        submitNodeCollector.submitModelPart(model.handle(), poseStack, type, i, j, sprite, false, bl, -1, null, k);
        submitNodeCollector.submitModelPart(model.plate(), poseStack, type, i, j, sprite, false, bl, -1, null, k);

        //Add overlay
        submitNodeCollector.submitModelPart(model.plate(), poseStack, RenderTypes.entityNoOutline(texture), i, j, sprite, false,false, k, null, k);
        poseStack.popPose();
        ci.cancel();
    }
}
