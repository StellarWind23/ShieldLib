package com.github.stellarwind22.shieldlib.lib.client.render;

import com.github.stellarwind22.shieldlib.init.ShieldLibClient;
import com.github.stellarwind22.shieldlib.lib.client.model.ShieldModel;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.rendertype.RenderTypes;
import net.minecraft.client.renderer.special.SpecialModelRenderer;
import net.minecraft.client.resources.model.Material;
import net.minecraft.core.component.DataComponentMap;
import net.minecraft.core.component.DataComponents;
import net.minecraft.resources.Identifier;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.entity.BannerPatternLayers;
import org.jetbrains.annotations.Nullable;
import org.joml.Vector3fc;
import org.jspecify.annotations.NonNull;

import java.util.Objects;
import java.util.function.Consumer;

public interface ShieldModelRenderer extends SpecialModelRenderer<DataComponentMap> {

    Identifier baseModel();
    Identifier baseModelNoPat();
    ShieldModel model();

    @Override
    default DataComponentMap extractArgument(ItemStack itemStack) {
        return itemStack.getComponents();
    }

    @Override
    default void getExtents(@NonNull Consumer<Vector3fc> consumer) {
        PoseStack poseStack = new PoseStack();
        poseStack.scale(1.0F, -1.0F, -1.0F);
        this.model().getRoot().getExtentsForGui(poseStack, consumer);
    }

    @Override
    default void submit(@Nullable DataComponentMap componentMap, @NonNull ItemDisplayContext itemDisplayContext, PoseStack poseStack, @NonNull SubmitNodeCollector submitNodeCollector, int i, int j, boolean bl, int k) {

        BannerPatternLayers bannerPatternLayers = componentMap == null ? BannerPatternLayers.EMPTY : componentMap.getOrDefault(DataComponents.BANNER_PATTERNS, BannerPatternLayers.EMPTY);

        DyeColor color = componentMap == null ? null : componentMap.get(DataComponents.BASE_COLOR);

        boolean bl2 = !bannerPatternLayers.layers().isEmpty() || color != null;

        poseStack.pushPose();
        poseStack.scale(1.0F, -1.0F, -1.0F);

        try {

            Material spriteMat = bl2 ? new Material(ShieldLibClient.SHIELD_ATLAS_LOCATION, this.baseModel()) : new Material(ShieldLibClient.SHIELD_ATLAS_LOCATION, this.baseModelNoPat());

            VertexConsumer vertexConsumer = spriteMat.buffer();

            this.model().handle().render(poseStack, vertexConsumer, i, j);


            if(bl2) {
                renderPatterns(poseStack, , i, j, this.model().plate(), spriteMat, Objects.requireNonNullElse(color, DyeColor.WHITE), bannerPatternLayers, bl, false);
            } else {
                this.model().plate().render(poseStack, vertexConsumer, i, j);
            }
        } finally {
            poseStack.popPose();
        }
    }

    default void renderPatterns(PoseStack poseStack, MultiBufferSource multiBufferSource, int i, int j, ModelPart modelPart, Material material, DyeColor dyeColor, BannerPatternLayers bannerPatternLayers, boolean bl2, boolean bl3) {
        modelPart.render(poseStack, material.buffer(multiBufferSource, RenderTypes::entitySolid, bl3, bl2), i, j);
        renderPatternLayer(poseStack, multiBufferSource, i, j, modelPart, ShieldLibClient.getShapedBannerMaterial(this.model().shape(), Identifier.withDefaultNamespace("base")), dyeColor);

        for(int k = 0; k < 16 && k < bannerPatternLayers.layers().size(); ++k) {
            BannerPatternLayers.Layer layer = bannerPatternLayers.layers().get(k);
            Material material2 = ShieldLibClient.getShapedBannerMaterial(this.model().shape(), layer.pattern());
            renderPatternLayer(poseStack, multiBufferSource, i, j, modelPart, material2, layer.color());
        }

    }

    default void renderPatternLayer(PoseStack poseStack, MultiBufferSource multiBufferSource, int i, int j, ModelPart modelPart, Material material, DyeColor dyeColor) {
        int k = dyeColor.getTextureDiffuseColor();
        modelPart.render(poseStack, material.buffer(multiBufferSource, RenderTypes::entityNoOutline), i, j, k);
    }
}
