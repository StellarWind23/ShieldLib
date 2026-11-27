package com.github.stellarwind22.shieldlib.lib.client.render;

import com.github.stellarwind22.shieldlib.init.ShieldLib;
import com.github.stellarwind22.shieldlib.lib.client.model.SpikedHeaterShieldModel;
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

public class SpikedHeaterShieldModelRenderer implements ShieldModelRenderer {

    private final Identifier baseModel, baseModelNoPat;
    private final SpikedHeaterShieldModel model;
    public static final ModelLayerLocation SPIKED_HEATER_MODEL_LAYER = new ModelLayerLocation(Identifier.fromNamespaceAndPath(ShieldLib.MOD_ID, "spiked_heater_shield"), "main");
    private final MaterialSet materials;

    public SpikedHeaterShieldModelRenderer(Identifier baseModel, Identifier baseModelNoPat, SpikedHeaterShieldModel model, MaterialSet materials) {
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
public class SpikedHeaterShieldModelRenderer implements ShieldModelRenderer {

    private final ResourceLocation baseModel, baseModelNoPat;
    private final SpikedHeaterShieldModel model;
    public static final ModelLayerLocation SPIKED_HEATER_MODEL_LAYER = new ModelLayerLocation(ResourceLocation.fromNamespaceAndPath(ShieldLib.MOD_ID, "spiked_heater_shield"), "main");

    public SpikedHeaterShieldModelRenderer(ResourceLocation baseModel, ResourceLocation baseModelNoPat, SpikedHeaterShieldModel model) {
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

        public static final MapCodec<SpikedHeaterShieldModelRenderer.Unbaked> CODEC = RecordCodecBuilder.mapCodec(
                instance -> instance.group(
                        Identifier.CODEC.fieldOf("texture_banner").forGetter(SpikedHeaterShieldModelRenderer.Unbaked::baseModel),
                        Identifier.CODEC.fieldOf("texture_default").forGetter(SpikedHeaterShieldModelRenderer.Unbaked::baseModelNoPat)
=======
    public record Unbaked(ResourceLocation baseModel, ResourceLocation baseModelNoPat) implements SpecialModelRenderer.Unbaked {

        public static final MapCodec<SpikedHeaterShieldModelRenderer.Unbaked> CODEC = RecordCodecBuilder.mapCodec(
                instance -> instance.group(
                        ResourceLocation.CODEC.fieldOf("texture_banner").forGetter(SpikedHeaterShieldModelRenderer.Unbaked::baseModel),
                        ResourceLocation.CODEC.fieldOf("texture_default").forGetter(SpikedHeaterShieldModelRenderer.Unbaked::baseModelNoPat)
>>>>>>> old/main
                ).apply(instance, SpikedHeaterShieldModelRenderer.Unbaked::new)
        );

        @Override
        public @NotNull MapCodec<SpikedHeaterShieldModelRenderer.Unbaked> type() {
            return CODEC;
        }

        @Override
<<<<<<< HEAD
        public @NotNull SpecialModelRenderer<?> bake(SpecialModelRenderer.BakingContext context) {
            ModelPart root = context.entityModelSet().bakeLayer(SpikedHeaterShieldModel.LOCATION);
            return new SpikedHeaterShieldModelRenderer(
                    this.baseModel,
                    this.baseModelNoPat,
                    new SpikedHeaterShieldModel(root),
                    context.materials()
=======
        public @NotNull SpecialModelRenderer<?> bake(EntityModelSet entityModelSet) {
            ModelPart root = entityModelSet.bakeLayer(SPIKED_HEATER_MODEL_LAYER);
            SpikedHeaterShieldModel model = new SpikedHeaterShieldModel(root);
            return new SpikedHeaterShieldModelRenderer(
                    this.baseModel,
                    this.baseModelNoPat,
                    model
>>>>>>> old/main
            );
        }
    }
}
