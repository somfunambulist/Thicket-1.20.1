package net.somfunambulist.thicket.item.custom;

import net.minecraft.world.item.BowItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.somfunambulist.thicket.item.ModItems;

import java.util.function.Predicate;

public class CruxwoodBowItem extends BowItem {
    public CruxwoodBowItem(Properties pProperties) {
        super(pProperties);
    }

    public static final Predicate<ItemStack> ARROW_OR_MISTLETOE = ARROW_ONLY.or((MISTLETOE) -> {
        return MISTLETOE.is(ModItems.MISTLETOE.get());
    });

    @Override
    public Predicate<ItemStack> getAllSupportedProjectiles() {
        return ARROW_OR_MISTLETOE;
    }

}
