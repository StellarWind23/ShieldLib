package com.github.stellarwind22.shieldlib.lib.client.render;

import com.github.stellarwind22.shieldlib.init.ShieldLib;
import com.github.stellarwind22.shieldlib.lib.client.model.SpikedTowerShieldModel;
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
public class SpikedTowerShieldModelRenderer implements ShieldModelRenderer {

    private final Identifier baseModel, baseModelNoPat;
    private final SpikedTowerShieldModel model;
    public static final ModelLayerLocation SPIKED_TOWER_MODEL_LAYER = new ModelLayerLocation(Identifier.fromNamespaceAndPath(ShieldLib.MOD_ID, "spiked_tower_shield"), "main");
    private final MaterialSet materials;

    public SpikedTowerShieldModelRenderer(Identifier baseModel, Identifier baseModelNoPat, SpikedTowerShieldModel model, MaterialSet materials) {
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

        public static final MapCodec<SpikedTowerShieldModelRenderer.Unbaked> CODEC = RecordCodecBuilder.mapCodec(
                instance -> instance.group(
                        Identifier.CODEC.fieldOf("texture_banner").forGetter(SpikedTowerShieldModelRenderer.Unbaked::baseModel),
                        Identifier.CODEC.fieldOf("texture_default").forGetter(SpikedTowerShieldModelRenderer.Unbaked::baseModelNoPat)
                ).apply(instance, SpikedTowerShieldModelRenderer.Unbaked::new)
        );

        @Override
        public @NotNull MapCodec<SpikedTowerShieldModelRenderer.Unbaked> type() {
            return CODEC;
        }

        @Override
        public @NotNull SpecialModelRenderer<?> bake(SpecialModelRenderer.BakingContext context) {
            ModelPart root = context.entityModelSet().bakeLayer(SpikedTowerShieldModel.LOCATION);
            return new SpikedTowerShieldModelRenderer(
                    this.baseModel,
                    this.baseModelNoPat,
                    new SpikedTowerShieldModel(root),
                    context.materials()
            );
        }
    }
}
