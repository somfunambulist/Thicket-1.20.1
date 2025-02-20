package net.somfunambulist.thicket.entity.client;

import net.somfunambulist.thicket.Thicket;
import net.somfunambulist.thicket.entity.custom.MistletoeArrow;
import net.minecraft.client.renderer.entity.ArrowRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;

public class MistletoeArrowRenderer extends ArrowRenderer<MistletoeArrow> {
    private static final ResourceLocation MISTLETOE_ARROW = new ResourceLocation(Thicket.MOD_ID, "textures/entity/projectiles/mistletoe_arrow.png");

    public MistletoeArrowRenderer(EntityRendererProvider.Context context) {
        super(context);
    }

    @Override
    public ResourceLocation getTextureLocation(MistletoeArrow entity) {
        return MISTLETOE_ARROW;
    }
}