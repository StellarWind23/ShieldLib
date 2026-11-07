package com.github.stellarwind22.shieldlib.lib.client.render;

import com.github.stellarwind22.shieldlib.init.ShieldLibClient;
import com.github.stellarwind22.shieldlib.lib.client.model.ShieldModel;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.rendertype.RenderTypes;
import net.minecraft.client.renderer.special.SpecialModelRenderer;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.resources.model.Material;
import net.minecraft.client.resources.model.MaterialSet;
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
    MaterialSet materials();

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
    default void submit(@Nullable DataComponentMap components, @NonNull ItemDisplayContext ctx,
                        PoseStack poseStack, @NonNull SubmitNodeCollector collector,
                        int light, int overlay, boolean hasFoil, int outlineColor) {

        BannerPatternLayers patterns = components == null ? BannerPatternLayers.EMPTY
                : components.getOrDefault(DataComponents.BANNER_PATTERNS, BannerPatternLayers.EMPTY);
        DyeColor color = components == null ? null : components.get(DataComponents.BASE_COLOR);
        boolean hasPatterns = !patterns.layers().isEmpty() || color != null;

        poseStack.pushPose();
        poseStack.scale(1.0F, -1.0F, -1.0F);
        try {
            Material mat = new Material(ShieldLibClient.SHIELD_ATLAS_LOCATION,
                    hasPatterns ? baseModel() : baseModelNoPat());
            TextureAtlasSprite sprite = materials().get(mat);

            collector.submitModelPart(model().handle(), poseStack, RenderTypes.entitySolid(sprite.atlasLocation()),
                    light, overlay, sprite, false, hasFoil, -1, null, outlineColor);

            collector.submitModelPart(model().plate(), poseStack, RenderTypes.entitySolid(sprite.atlasLocation()),
                    light, overlay, sprite, false, hasFoil, -1, null, outlineColor);
            if(hasPatterns) {
                submitPatternLayer(poseStack, collector, light, overlay, model().plate(),
                        ShieldLibClient.getShapedBannerMaterial(model().shape(), Identifier.withDefaultNamespace("base")),
                        Objects.requireNonNullElse(color, DyeColor.WHITE), outlineColor);
                for (int i = 0; i < 16 && i < patterns.layers().size(); i++) {
                    var layer = patterns.layers().get(i);
                    submitPatternLayer(poseStack, collector, light, overlay, model().plate(),
                            ShieldLibClient.getShapedBannerMaterial(model().shape(), layer.pattern()),
                            layer.color(), outlineColor);
                }
            }
        } finally {
            poseStack.popPose();
        }
    }

    default void submitPatternLayer(PoseStack poseStack, SubmitNodeCollector collector, int light, int overlay,
                                    ModelPart part, Material material, DyeColor dye, int outlineColor) {
        TextureAtlasSprite sprite = materials().get(material);
        collector.submitModelPart(part, poseStack, RenderTypes.entityNoOutline(sprite.atlasLocation()),
                light, overlay, sprite, false, false, dye.getTextureDiffuseColor(), null, outlineColor);
    }
}
