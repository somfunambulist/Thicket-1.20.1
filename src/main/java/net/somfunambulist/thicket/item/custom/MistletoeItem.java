package net.somfunambulist.thicket.item.custom;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.somfunambulist.thicket.entity.custom.MistletoeArrow;

public class MistletoeItem extends ArrowBlockItem {

    public MistletoeItem(Properties pProperties, Block block) {
        super(pProperties, block);
    }

    @Override
    public AbstractArrow createArrow(Level worldIn, ItemStack stack, LivingEntity shooter) {
        return new MistletoeArrow(worldIn, shooter);
    }
}
