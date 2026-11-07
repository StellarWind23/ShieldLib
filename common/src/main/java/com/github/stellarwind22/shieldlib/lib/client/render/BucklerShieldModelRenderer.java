package com.github.stellarwind22.shieldlib.lib.client.render;

import com.github.stellarwind22.shieldlib.lib.client.model.BucklerShieldModel;
import com.github.stellarwind22.shieldlib.lib.client.model.ShieldModel;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.renderer.special.SpecialModelRenderer;
import net.minecraft.client.resources.model.sprite.SpriteGetter;
import net.minecraft.core.component.DataComponentMap;
import net.minecraft.resources.Identifier;
import org.jspecify.annotations.NonNull;

public class BucklerShieldModelRenderer implements ShieldModelRenderer {

    private final Identifier baseModel, baseModelNoPat;
    private final BucklerShieldModel model;
    private final SpriteGetter sprites;

    public BucklerShieldModelRenderer(Identifier baseModel, Identifier baseModelNoPat, BucklerShieldModel model, SpriteGetter sprites) {
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

        public static final MapCodec<BucklerShieldModelRenderer.Unbaked> CODEC = RecordCodecBuilder.mapCodec(
                instance -> instance.group(
                        Identifier.CODEC.fieldOf("texture_banner").forGetter(BucklerShieldModelRenderer.Unbaked::baseModel),
                        Identifier.CODEC.fieldOf("texture_default").forGetter(BucklerShieldModelRenderer.Unbaked::baseModelNoPat)
                ).apply(instance, BucklerShieldModelRenderer.Unbaked::new)
        );

        @Override
        public @NonNull MapCodec<BucklerShieldModelRenderer.Unbaked> type() { return CODEC; }

        @Override
        public SpecialModelRenderer<DataComponentMap> bake(SpecialModelRenderer.BakingContext context) {
            ModelPart root = context.entityModelSet().bakeLayer(BucklerShieldModel.LOCATION);
            return new BucklerShieldModelRenderer(baseModel, baseModelNoPat,
                    new BucklerShieldModel(root), context.sprites());
        }
    }
}
