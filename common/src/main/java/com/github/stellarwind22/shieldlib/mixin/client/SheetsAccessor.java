package com.github.stellarwind22.shieldlib.mixin.client;

import net.minecraft.client.renderer.Sheets;
import net.minecraft.client.resources.model.Material;
import net.minecraft.resources.Identifier;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

import java.util.Map;

@Mixin(Sheets.class)
public interface SheetsAccessor {

    @Accessor(value = "SHIELD_MATERIALS")
    static Map<Identifier, Material> getShieldMaterials() { throw new AssertionError(); }
}
