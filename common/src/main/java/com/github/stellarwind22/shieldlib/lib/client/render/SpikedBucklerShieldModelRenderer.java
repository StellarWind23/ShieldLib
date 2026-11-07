package com.github.stellarwind22.shieldlib.lib.client.render;

import com.github.stellarwind22.shieldlib.init.ShieldLib;
import com.github.stellarwind22.shieldlib.lib.client.model.SpikedBucklerShieldModel;
import com.github.stellarwind22.shieldlib.lib.client.model.ShieldModel;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.renderer.special.SpecialModelRenderer;
import net.minecraft.client.resources.model.MaterialSet;
import net.minecraft.resources.Identifier;
import org.jetbrains.annotations.NotNull;

@Environment(EnvType.CLIENT)
public class SpikedBucklerShieldModelRenderer implements ShieldModelRenderer {

    private final Identifier baseModel, baseModelNoPat;
    private final SpikedBucklerShieldModel model;
    public static final ModelLayerLocation SPIKED_BUCKLER_MODEL_LAYER = new ModelLayerLocation(Identifier.fromNamespaceAndPath(ShieldLib.MOD_ID, "spiked_buckler_shield"), "main");
    private final MaterialSet materials;

    public SpikedBucklerShieldModelRenderer(Identifier baseModel, Identifier baseModelNoPat, SpikedBucklerShieldModel model, MaterialSet materials) {
        this.baseModel = baseModel;
        this.baseModelNoPat = baseModelNoPat;
        this.model = model;
        this.materials = materials;
    }

    @Override
    public MaterialSet materials() {
        return this.materials;
    }

    @Override
    public Identifier baseModel() {
        return this.baseModel;
    }

    @Override
    public Identifier baseModelNoPat() {
        return this.baseModelNoPat;
    }

    @Override
    public ShieldModel model() {
        return this.model;
    }

    public record Unbaked(Identifier baseModel, Identifier baseModelNoPat) implements SpecialModelRenderer.Unbaked {

        public static final MapCodec<SpikedBucklerShieldModelRenderer.Unbaked> CODEC = RecordCodecBuilder.mapCodec(
                instance -> instance.group(
                        Identifier.CODEC.fieldOf("texture_banner").forGetter(SpikedBucklerShieldModelRenderer.Unbaked::baseModel),
                        Identifier.CODEC.fieldOf("texture_default").forGetter(SpikedBucklerShieldModelRenderer.Unbaked::baseModelNoPat)
                ).apply(instance, SpikedBucklerShieldModelRenderer.Unbaked::new)
        );

        @Override
        public @NotNull MapCodec<SpikedBucklerShieldModelRenderer.Unbaked> type() {
            return CODEC;
        }

        @Override
        public @NotNull SpecialModelRenderer<?> bake(SpecialModelRenderer.BakingContext context) {
            ModelPart root = context.entityModelSet().bakeLayer(SpikedBucklerShieldModel.LOCATION);
            return new SpikedBucklerShieldModelRenderer(
                    this.baseModel,
                    this.baseModelNoPat,
                    new SpikedBucklerShieldModel(root),
                    context.materials()
            );
        }
    }
}
