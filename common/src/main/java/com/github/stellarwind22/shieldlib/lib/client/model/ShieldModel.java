package com.github.stellarwind22.shieldlib.lib.client.model;

import net.minecraft.client.model.geom.ModelPart;

public interface ShieldModel {
    ModelPart getRoot();
    ModelPart handle();
    ModelPart plate();
    String shape();
}
