package com.github.stellarwind22.shieldlib.lib.client.render;

import com.github.stellarwind22.shieldlib.init.ShieldLib;
<<<<<<< HEAD
import com.github.stellarwind22.shieldlib.lib.client.model.BucklerShieldModel;
import com.github.stellarwind22.shieldlib.lib.client.model.ShieldModel;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.renderer.special.SpecialModelRenderer;
import net.minecraft.client.resources.model.MaterialSet;
import net.minecraft.resources.Identifier;
import org.jetbrains.annotations.NotNull;

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
=======
import com.github.stellarwind22.shieldlib.lib.client.model.BucklerShieldLibModel;
import com.github.stellarwind22.shieldlib.lib.client.model.ShieldModel;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.model.geom.EntityModelSet;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.renderer.special.SpecialModelRenderer;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.NotNull;

@Environment(EnvType.CLIENT)
public class BucklerShieldModelRenderer implements ShieldModelRenderer {

    private final ResourceLocation baseModel, baseModelNoPat;
    private final BucklerShieldLibModel model;

    public static final ModelLayerLocation BUCKLER_MODEL_LAYER = new ModelLayerLocation(ResourceLocation.fromNamespaceAndPath(ShieldLib.MOD_ID, "buckler_shield"), "main");

    public BucklerShieldModelRenderer(ResourceLocation baseModel, ResourceLocation baseModelNoPat, BucklerShieldLibModel model) {
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

        public static final MapCodec<BucklerShieldModelRenderer.Unbaked> CODEC = RecordCodecBuilder.mapCodec(
                instance -> instance.group(
                        Identifier.CODEC.fieldOf("texture_banner").forGetter(BucklerShieldModelRenderer.Unbaked::baseModel),
                        Identifier.CODEC.fieldOf("texture_default").forGetter(BucklerShieldModelRenderer.Unbaked::baseModelNoPat)
=======
    public record Unbaked(ResourceLocation baseModel, ResourceLocation baseModelNoPat) implements SpecialModelRenderer.Unbaked {

        public static final MapCodec<BucklerShieldModelRenderer.Unbaked> CODEC = RecordCodecBuilder.mapCodec(
                instance -> instance.group(
                        ResourceLocation.CODEC.fieldOf("texture_banner").forGetter(BucklerShieldModelRenderer.Unbaked::baseModel),
                        ResourceLocation.CODEC.fieldOf("texture_default").forGetter(BucklerShieldModelRenderer.Unbaked::baseModelNoPat)
>>>>>>> old/main
                ).apply(instance, BucklerShieldModelRenderer.Unbaked::new)
        );

        @Override
        public @NotNull MapCodec<BucklerShieldModelRenderer.Unbaked> type() {
            return CODEC;
        }

        @Override
<<<<<<< HEAD
        public @NotNull SpecialModelRenderer<?> bake(SpecialModelRenderer.BakingContext context) {
            ModelPart root = context.entityModelSet().bakeLayer(BucklerShieldModel.LOCATION);
            return new BucklerShieldModelRenderer(
                    this.baseModel,
                    this.baseModelNoPat,
                    new BucklerShieldModel(root),
                    context.materials()
=======
        public @NotNull SpecialModelRenderer<?> bake(EntityModelSet entityModelSet) {
            ModelPart root = entityModelSet.bakeLayer(BUCKLER_MODEL_LAYER);
            BucklerShieldLibModel model = new BucklerShieldLibModel(root);
            return new BucklerShieldModelRenderer(
                    this.baseModel,
                    this.baseModelNoPat,
                    model
>>>>>>> old/main
            );
        }
    }
}
