package com.electron.taigaexpansion.mobs;

import com.electron.taigaexpansion.TaigaExpansion;
import com.mojang.blaze3d.matrix.MatrixStack;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.entity.IEntityRenderer;
import net.minecraft.client.renderer.entity.layers.LayerRenderer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class BrownBearLayer<T extends BrownBearMob, M extends BrownBearModel<T>> extends LayerRenderer<T, M> {
    private static final ResourceLocation BEAR_LAYER_TEXTURES = new ResourceLocation(TaigaExpansion.MODID + ":textures/entity/brownbear_layer.png");
    private final BrownBearModel<T> layerModel = new BrownBearModel<>(0.55F, true);

    public BrownBearLayer(IEntityRenderer<T, M> p_i50919_1_) {
        super(p_i50919_1_);
    }

    public void render(MatrixStack matrixStackIn, IRenderTypeBuffer bufferIn, int packedLightIn, T entitylivingbaseIn, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch) {
        renderCopyCutoutModel(this.getEntityModel(), this.layerModel, BEAR_LAYER_TEXTURES, matrixStackIn, bufferIn, packedLightIn, entitylivingbaseIn, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, partialTicks, 1.0F, 1.0F, 1.0F);
    }
}
