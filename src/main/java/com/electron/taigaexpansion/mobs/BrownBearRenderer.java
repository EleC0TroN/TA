package com.electron.taigaexpansion.mobs;

import com.electron.taigaexpansion.TaigaExpansion;
import com.mojang.blaze3d.matrix.MatrixStack;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.client.registry.IRenderFactory;

public class BrownBearRenderer extends MobRenderer<BrownBearMob, BrownBearModel<BrownBearMob>> {
    private static final ResourceLocation POLAR_BEAR_TEXTURE = new ResourceLocation(TaigaExpansion.MODID + ":textures/entity/brownbear.png");

    public BrownBearRenderer(EntityRendererManager renderManagerIn) {
        super(renderManagerIn, new BrownBearModel<>(0.0F, true), 0.9F);
        this.addLayer(new BrownBearLayer<>(this));
    }

    public ResourceLocation getEntityTexture(BrownBearMob entity) {
        return POLAR_BEAR_TEXTURE;
    }

    protected void preRenderCallback(BrownBearMob entitylivingbaseIn, MatrixStack matrixStackIn, float partialTickTime) {
        matrixStackIn.scale(1.3F, 1.3F, 1.3F);
        super.preRenderCallback(entitylivingbaseIn, matrixStackIn, partialTickTime);
    }
    public static class RenderFactory implements IRenderFactory<BrownBearMob>
    {
        @Override
        public EntityRenderer<? super BrownBearMob> createRenderFor(EntityRendererManager manager)
        {
            return new BrownBearRenderer(manager);
        }
    }
}
