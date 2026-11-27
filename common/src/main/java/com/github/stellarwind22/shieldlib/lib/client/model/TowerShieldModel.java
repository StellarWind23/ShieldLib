package com.github.stellarwind22.shieldlib.lib.client.model;

<<<<<<< HEAD
import net.minecraft.client.model.Model;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.renderer.rendertype.RenderTypes;
import net.minecraft.util.Unit;

public class TowerShieldModel extends Model<Unit> implements ShieldModel {
=======
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.model.Model;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.resources.ResourceLocation;

@Environment(EnvType.CLIENT)
public class TowerShieldModel extends Model implements ShieldModel {
>>>>>>> old/main

    private final ModelPart plate;
    private final ModelPart handle;

    public TowerShieldModel(ModelPart root) {
<<<<<<< HEAD
        super(root, RenderTypes::entitySolid);
=======
        super(root, RenderType::entitySolid);
>>>>>>> old/main
        this.plate = root.getChild("plate");
        this.handle = root.getChild("handle");
    }

<<<<<<< HEAD
=======
    @Override public RenderType getRenderType(ResourceLocation location) {return this.renderType(location);}
>>>>>>> old/main
    @Override public ModelPart getRoot() {return this.root;}
    @Override public ModelPart handle() { return this.handle; }
    @Override public ModelPart plate() { return this.plate; }
    @Override public String shape() { return "tower"; }
}
