package com.github.stellarwind22.shieldlib.lib.client.model;

import net.minecraft.client.model.geom.ModelPart;

import net.minecraft.client.model.Model;
import net.minecraft.util.Unit;

public interface ShieldModel {
    ModelPart getRoot();
    ModelPart handle();
    ModelPart plate();
    String shape();

    @SuppressWarnings("unchecked")
    default Model<Unit> asModel() { return (Model<Unit>) this; }
}
