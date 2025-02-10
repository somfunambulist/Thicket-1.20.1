package net.somfunambulist.thicket.item.custom;

import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.ShearsItem;

public class SickleItem extends ShearsItem {

    public SickleItem(Properties pProperties) {
        super(pProperties);
    }

    @Override
    public boolean isValidRepairItem(ItemStack stack, ItemStack repairStack) {
        return repairStack.is(Items.GOLD_INGOT);
    }

}
