package com.github.stellarwind22.shieldlib.lib.client.render;

import com.github.stellarwind22.shieldlib.lib.client.model.TowerShieldModel;
import com.github.stellarwind22.shieldlib.lib.client.model.ShieldModel;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
<<<<<<< HEAD
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.renderer.special.SpecialModelRenderer;
import net.minecraft.client.resources.model.MaterialSet;
import net.minecraft.resources.Identifier;
import org.jetbrains.annotations.NotNull;

public class TowerShieldModelRenderer implements ShieldModelRenderer {

    private final Identifier baseModel, baseModelNoPat;
    private final TowerShieldModel model;
    private final MaterialSet materials;

    public TowerShieldModelRenderer(Identifier baseModel, Identifier baseModelNoPat,
                                    TowerShieldModel model, MaterialSet materials) {
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
=======
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.model.geom.EntityModelSet;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.renderer.special.SpecialModelRenderer;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.NotNull;

@Environment(EnvType.CLIENT)
public class TowerShieldModelRenderer implements ShieldModelRenderer {

    private final ResourceLocation baseModel, baseModelNoPat;
    private final TowerShieldModel model;

    public TowerShieldModelRenderer(ResourceLocation baseModel, ResourceLocation baseModelNoPat, TowerShieldModel model) {
        this.baseModel = baseModel;
        this.baseModelNoPat = baseModelNoPat;
        this.model = model;
    }

    @Override
    public ResourceLocation baseModel() {
>>>>>>> old/main
        return this.baseModel;
    }

    @Override
<<<<<<< HEAD
    public Identifier baseModelNoPat() {
=======
    public ResourceLocation baseModelNoPat() {
>>>>>>> old/main
        return this.baseModelNoPat;
    }

    @Override
    public ShieldModel model() {
        return this.model;
    }

<<<<<<< HEAD
    public record Unbaked(Identifier baseModel, Identifier baseModelNoPat) implements SpecialModelRenderer.Unbaked {

        public static final MapCodec<TowerShieldModelRenderer.Unbaked> CODEC = RecordCodecBuilder.mapCodec(
                instance -> instance.group(
                        Identifier.CODEC.fieldOf("texture_banner").forGetter(TowerShieldModelRenderer.Unbaked::baseModel),
                        Identifier.CODEC.fieldOf("texture_default").forGetter(TowerShieldModelRenderer.Unbaked::baseModelNoPat)
=======
    public record Unbaked(ResourceLocation baseModel, ResourceLocation baseModelNoPat) implements SpecialModelRenderer.Unbaked {

        public static final MapCodec<TowerShieldModelRenderer.Unbaked> CODEC = RecordCodecBuilder.mapCodec(
                instance -> instance.group(
                        ResourceLocation.CODEC.fieldOf("texture_banner").forGetter(TowerShieldModelRenderer.Unbaked::baseModel),
                        ResourceLocation.CODEC.fieldOf("texture_default").forGetter(TowerShieldModelRenderer.Unbaked::baseModelNoPat)
>>>>>>> old/main
                ).apply(instance, TowerShieldModelRenderer.Unbaked::new)
        );

        @Override
        public @NotNull MapCodec<TowerShieldModelRenderer.Unbaked> type() {
            return CODEC;
        }

        @Override
<<<<<<< HEAD
        public @NotNull SpecialModelRenderer<?> bake(SpecialModelRenderer.BakingContext context) {
            ModelPart root = context.entityModelSet().bakeLayer(ModelLayers.SHIELD);
            return new TowerShieldModelRenderer(
                    this.baseModel,
                    this.baseModelNoPat,
                    new TowerShieldModel(root),
                    context.materials()
=======
        public @NotNull SpecialModelRenderer<?> bake(EntityModelSet entityModelSet) {
            ModelPart root = entityModelSet.bakeLayer(ModelLayers.SHIELD);
            TowerShieldModel model = new TowerShieldModel(root);
            return new TowerShieldModelRenderer(
                    this.baseModel,
                    this.baseModelNoPat,
                    model
>>>>>>> old/main
            );
        }
    }
}
