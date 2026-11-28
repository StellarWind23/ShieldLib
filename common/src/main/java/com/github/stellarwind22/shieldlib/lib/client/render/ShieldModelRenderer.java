package com.github.stellarwind22.shieldlib.lib.client.render;

import com.github.stellarwind22.shieldlib.init.ShieldLibClient;
import com.github.stellarwind22.shieldlib.lib.client.model.ShieldModel;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.model.Model;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.rendertype.RenderTypes;
import net.minecraft.client.renderer.special.SpecialModelRenderer;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.resources.model.sprite.Material;
import net.minecraft.client.resources.model.sprite.SpriteGetter;
import net.minecraft.client.resources.model.sprite.SpriteId;
import net.minecraft.core.component.DataComponentMap;
import net.minecraft.core.component.DataComponents;
import net.minecraft.resources.Identifier;
import net.minecraft.util.Unit;
import net.minecraft.world.item.DyeColor;
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
    SpriteGetter sprites();

    @Override
    default @Nullable DataComponentMap extractArgument(ItemStack stack) {
        return stack.immutableComponents();
    }

    @Override
    default void getExtents(@NonNull Consumer<Vector3fc> output) {
        PoseStack poseStack = new PoseStack();
        poseStack.scale(1.0F, -1.0F, -1.0F);
        this.model().getRoot().getExtentsForGui(poseStack, output);
    }

    @Override
    default void submit(@Nullable DataComponentMap components, @NonNull PoseStack poseStack, @NonNull SubmitNodeCollector collector, int light, int overlay, boolean hasFoil, int outlineColor) {

        Identifier overlayTexture = ShieldLibClient.getOverrideTexture(components, model().shape());

        BannerPatternLayers patterns = components != null ? components.getOrDefault(DataComponents.BANNER_PATTERNS, BannerPatternLayers.EMPTY) : BannerPatternLayers.EMPTY;
        DyeColor baseColor = components != null ? components.get(DataComponents.BASE_COLOR) : null;

        // An override replaces vanilla banner decoration, so it always sits on the plain no-pattern base
        boolean hasPatterns = !patterns.layers().isEmpty() || baseColor != null;

        poseStack.pushPose();
        poseStack.scale(1.0F, -1.0F, -1.0F);

        try {
            Model<Unit> model = model().asModel();
            SpriteId base = new SpriteId(ShieldLibClient.SHIELD_ATLAS_LOCATION, hasPatterns ? baseModel() : baseModelNoPat());

            //Draw base shield model
            collector.submitModel(model, Unit.INSTANCE, poseStack, light, overlay, -1, base, sprites(), outlineColor, null);

            //Handle override
            if (overlayTexture != null) {
                collector.submitModelPart(model().plate(), poseStack, RenderTypes.entitySolid(overlayTexture), light, overlay, sprites().get(overlayTexture), false,false, -1, null, outlineColor);

            } else if (hasPatterns) {   //Handle normal patterns

                submitPatternLayer(model, poseStack, collector, light, overlay, ShieldLibClient.getShapedSprite(model().shape(), Identifier.withDefaultNamespace("base")), Objects.requireNonNullElse(baseColor, DyeColor.WHITE));

                for (int i = 0; i < 16 && i < patterns.layers().size(); i++) {
                    BannerPatternLayers.Layer layer = patterns.layers().get(i);
                    submitPatternLayer(model, poseStack, collector, light, overlay, ShieldLibClient.getShapedSprite(model().shape(), layer.pattern()), layer.color());
                }
            }

            if (hasFoil) {
                collector.submitModel(model, Unit.INSTANCE, poseStack, RenderTypes.entityGlint(), light, overlay, -1, sprites().get(base), 0, null);
            }
        } finally {
            poseStack.popPose();
        }
    }

    default void submitPatternLayer(Model<Unit> model, PoseStack poseStack, SubmitNodeCollector collector, int light, int overlay, SpriteId sprite, DyeColor color) {
        collector.submitModel(model, Unit.INSTANCE, poseStack, sprite.renderType(RenderTypes::bannerPattern), light, overlay, color.getTextureDiffuseColor(), sprites().get(sprite), 0, null);
    }
}
