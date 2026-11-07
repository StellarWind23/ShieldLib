package com.github.stellarwind22.shieldlib.lib.client.render;

import com.github.stellarwind22.shieldlib.lib.client.model.SpikedBucklerShieldModel;
import com.github.stellarwind22.shieldlib.lib.client.model.ShieldModel;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.renderer.special.SpecialModelRenderer;
import net.minecraft.client.resources.model.sprite.SpriteGetter;
import net.minecraft.core.component.DataComponentMap;
import net.minecraft.resources.Identifier;
import org.jspecify.annotations.NonNull;

public class SpikedBucklerShieldModelRenderer implements ShieldModelRenderer {

    private final Identifier baseModel, baseModelNoPat;
    private final SpikedBucklerShieldModel model;
    private final SpriteGetter sprites;

    public SpikedBucklerShieldModelRenderer(Identifier baseModel, Identifier baseModelNoPat, SpikedBucklerShieldModel model, SpriteGetter sprites) {
        this.baseModel = baseModel;
        this.baseModelNoPat = baseModelNoPat;
        this.model = model;
        this.sprites = sprites;
    }

    @Override
    public SpriteGetter sprites() { return this.sprites; }

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

    public record Unbaked(Identifier baseModel, Identifier baseModelNoPat)
            implements SpecialModelRenderer.Unbaked<DataComponentMap> {

        public static final MapCodec<SpikedBucklerShieldModelRenderer.Unbaked> CODEC = RecordCodecBuilder.mapCodec(
                instance -> instance.group(
                        Identifier.CODEC.fieldOf("texture_banner").forGetter(SpikedBucklerShieldModelRenderer.Unbaked::baseModel),
                        Identifier.CODEC.fieldOf("texture_default").forGetter(SpikedBucklerShieldModelRenderer.Unbaked::baseModelNoPat)
                ).apply(instance, SpikedBucklerShieldModelRenderer.Unbaked::new)
        );

        @Override
        public @NonNull MapCodec<SpikedBucklerShieldModelRenderer.Unbaked> type() { return CODEC; }

        @Override
        public SpecialModelRenderer<DataComponentMap> bake(SpecialModelRenderer.BakingContext context) {
            ModelPart root = context.entityModelSet().bakeLayer(SpikedBucklerShieldModel.LOCATION);
            return new SpikedBucklerShieldModelRenderer(baseModel, baseModelNoPat,
                    new SpikedBucklerShieldModel(root), context.sprites());
        }
    }
}
