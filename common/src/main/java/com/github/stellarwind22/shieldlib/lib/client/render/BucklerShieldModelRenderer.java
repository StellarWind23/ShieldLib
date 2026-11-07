package com.github.stellarwind22.shieldlib.lib.client.render;

import com.github.stellarwind22.shieldlib.init.ShieldLib;
import com.github.stellarwind22.shieldlib.lib.client.model.BucklerShieldModel;
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
public class BucklerShieldModelRenderer implements ShieldModelRenderer {

    private final Identifier baseModel, baseModelNoPat;
    private final BucklerShieldModel model;
    public static final ModelLayerLocation BUCKLER_MODEL_LAYER = new ModelLayerLocation(Identifier.fromNamespaceAndPath(ShieldLib.MOD_ID, "buckler_shield"), "main");
    private final MaterialSet materials;

    public BucklerShieldModelRenderer(Identifier baseModel, Identifier baseModelNoPat, BucklerShieldModel model, MaterialSet materials) {
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

        public static final MapCodec<BucklerShieldModelRenderer.Unbaked> CODEC = RecordCodecBuilder.mapCodec(
                instance -> instance.group(
                        Identifier.CODEC.fieldOf("texture_banner").forGetter(BucklerShieldModelRenderer.Unbaked::baseModel),
                        Identifier.CODEC.fieldOf("texture_default").forGetter(BucklerShieldModelRenderer.Unbaked::baseModelNoPat)
                ).apply(instance, BucklerShieldModelRenderer.Unbaked::new)
        );

        @Override
        public @NotNull MapCodec<BucklerShieldModelRenderer.Unbaked> type() {
            return CODEC;
        }

        @Override
        public @NotNull SpecialModelRenderer<?> bake(SpecialModelRenderer.BakingContext context) {
            ModelPart root = context.entityModelSet().bakeLayer(BucklerShieldModel.LOCATION);
            return new BucklerShieldModelRenderer(
                    this.baseModel,
                    this.baseModelNoPat,
                    new BucklerShieldModel(root),
                    context.materials()
            );
        }
    }
}
