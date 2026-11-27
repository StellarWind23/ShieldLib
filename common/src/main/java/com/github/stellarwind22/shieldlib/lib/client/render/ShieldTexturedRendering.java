package com.github.stellarwind22.shieldlib.lib.client.render;

import com.github.stellarwind22.shieldlib.lib.client.model.ShieldModel;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.model.Model;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.rendertype.RenderType;
import net.minecraft.resources.Identifier;

public final class ShieldTexturedRendering {

    private ShieldTexturedRendering() { }

    /** Draws a shield from one complete texture sheet. Mirrors the no-banner path of vanilla's ShieldSpecialRenderer. */
    public static void submit(Model<?> model, ModelPart handle, ModelPart plate, Identifier texture, PoseStack ps, SubmitNodeCollector out, int light, int overlay, boolean foil, int outlineColor) {
        RenderType renderType = model.renderType(texture);
        ps.pushPose();
        ps.scale(1.0F, -1.0F, -1.0F);
        out.submitModelPart(handle, ps, renderType, light, overlay, null, false, false, -1, null, outlineColor);
        out.submitModelPart(plate, ps, renderType, light, overlay, null, false, foil, -1, null, outlineColor);
        ps.popPose();
    }

    /** For ShieldLib's own models, which all extend Model<Unit>. */
    public static void submit(ShieldModel model, Identifier texture, PoseStack ps, SubmitNodeCollector out, int light, int overlay, boolean foil, int outlineColor) {
        submit((Model<?>) model, model.handle(), model.plate(), texture, ps, out, light, overlay, foil, outlineColor);
    }
}