package com.github.stellarwind22.shieldlib.lib.client.render;

import com.github.stellarwind22.shieldlib.init.ShieldLib;
import com.github.stellarwind22.shieldlib.lib.client.model.HeaterShieldModel;
import com.github.stellarwind22.shieldlib.lib.client.model.ShieldModel;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
<<<<<<< HEAD
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.renderer.special.SpecialModelRenderer;
import net.minecraft.client.resources.model.MaterialSet;
import net.minecraft.resources.Identifier;
import org.jetbrains.annotations.NotNull;

public class HeaterShieldModelRenderer implements ShieldModelRenderer {

    private final Identifier baseModel, baseModelNoPat;
    private final HeaterShieldModel model;
    public static final ModelLayerLocation HEATER_MODEL_LAYER = new ModelLayerLocation(Identifier.fromNamespaceAndPath(ShieldLib.MOD_ID, "heater_shield"), "main");
    private final MaterialSet materials;

    public HeaterShieldModelRenderer(Identifier baseModel, Identifier baseModelNoPat, HeaterShieldModel model, MaterialSet materials) {
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
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.renderer.special.SpecialModelRenderer;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.NotNull;

@Environment(EnvType.CLIENT)
public class HeaterShieldModelRenderer implements ShieldModelRenderer {

    private final ResourceLocation baseModel, baseModelNoPat;
    private final HeaterShieldModel model;
    public static final ModelLayerLocation HEATER_MODEL_LAYER = new ModelLayerLocation(ResourceLocation.fromNamespaceAndPath(ShieldLib.MOD_ID, "heater_shield"), "main");

    public HeaterShieldModelRenderer(ResourceLocation baseModel, ResourceLocation baseModelNoPat, HeaterShieldModel model) {
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

        public static final MapCodec<HeaterShieldModelRenderer.Unbaked> CODEC = RecordCodecBuilder.mapCodec(
                instance -> instance.group(
                        Identifier.CODEC.fieldOf("texture_banner").forGetter(HeaterShieldModelRenderer.Unbaked::baseModel),
                        Identifier.CODEC.fieldOf("texture_default").forGetter(HeaterShieldModelRenderer.Unbaked::baseModelNoPat)
=======
    public record Unbaked(ResourceLocation baseModel, ResourceLocation baseModelNoPat) implements SpecialModelRenderer.Unbaked {

        public static final MapCodec<HeaterShieldModelRenderer.Unbaked> CODEC = RecordCodecBuilder.mapCodec(
                instance -> instance.group(
                        ResourceLocation.CODEC.fieldOf("texture_banner").forGetter(HeaterShieldModelRenderer.Unbaked::baseModel),
                        ResourceLocation.CODEC.fieldOf("texture_default").forGetter(HeaterShieldModelRenderer.Unbaked::baseModelNoPat)
>>>>>>> old/main
                ).apply(instance, HeaterShieldModelRenderer.Unbaked::new)
        );

        @Override
        public @NotNull MapCodec<HeaterShieldModelRenderer.Unbaked> type() {
            return CODEC;
        }

        @Override
<<<<<<< HEAD
        public @NotNull SpecialModelRenderer<?> bake(SpecialModelRenderer.BakingContext context) {
            ModelPart root = context.entityModelSet().bakeLayer(HeaterShieldModel.LOCATION);
            return new HeaterShieldModelRenderer(
                    this.baseModel,
                    this.baseModelNoPat,
                    new HeaterShieldModel(root),
                    context.materials()
=======
        public @NotNull SpecialModelRenderer<?> bake(EntityModelSet entityModelSet) {
            ModelPart root = entityModelSet.bakeLayer(HEATER_MODEL_LAYER);
            HeaterShieldModel model = new HeaterShieldModel(root);
            return new HeaterShieldModelRenderer(
                    this.baseModel,
                    this.baseModelNoPat,
                    model
>>>>>>> old/main
            );
        }
    }
}
