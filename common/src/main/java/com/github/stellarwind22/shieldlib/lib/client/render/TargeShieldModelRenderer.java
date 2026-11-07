package com.github.stellarwind22.shieldlib.lib.client.render;

import com.github.stellarwind22.shieldlib.lib.client.model.ShieldModel;
import com.github.stellarwind22.shieldlib.lib.client.model.TargeShieldModel;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.renderer.special.SpecialModelRenderer;
import net.minecraft.client.resources.model.MaterialSet;
import net.minecraft.resources.Identifier;
import org.jetbrains.annotations.NotNull;

public class TargeShieldModelRenderer implements ShieldModelRenderer {

    private final Identifier baseModel, baseModelNoPat;
    private final TargeShieldModel model;
    private final MaterialSet materials;

    public TargeShieldModelRenderer(Identifier baseModel, Identifier baseModelNoPat, TargeShieldModel model, MaterialSet materials) {
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

        public static final MapCodec<TargeShieldModelRenderer.Unbaked> CODEC = RecordCodecBuilder.mapCodec(
                instance -> instance.group(
                        Identifier.CODEC.fieldOf("texture_banner").forGetter(TargeShieldModelRenderer.Unbaked::baseModel),
                        Identifier.CODEC.fieldOf("texture_default").forGetter(TargeShieldModelRenderer.Unbaked::baseModelNoPat)
                ).apply(instance, TargeShieldModelRenderer.Unbaked::new)
        );

        @Override
        public @NotNull MapCodec<TargeShieldModelRenderer.Unbaked> type() {
            return CODEC;
        }

        @Override
        public @NotNull SpecialModelRenderer<?> bake(SpecialModelRenderer.BakingContext context) {
            ModelPart root = context.entityModelSet().bakeLayer(TargeShieldModel.LOCATION);
            return new TargeShieldModelRenderer(
                    this.baseModel,
                    this.baseModelNoPat,
                    new TargeShieldModel(root),
                    context.materials()
            );
        }
    }
}
