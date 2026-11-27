package com.github.stellarwind22.shieldlib.lib.client.model;

import net.minecraft.client.model.geom.ModelPart;
<<<<<<< HEAD

public interface ShieldModel {
=======
import net.minecraft.client.renderer.RenderType;
import net.minecraft.resources.ResourceLocation;


public interface ShieldModel {

    RenderType getRenderType(ResourceLocation location);
>>>>>>> old/main
    ModelPart getRoot();
    ModelPart handle();
    ModelPart plate();
    String shape();
}
