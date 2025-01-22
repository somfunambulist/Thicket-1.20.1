package net.somfunambulist.thicket.item.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;

public class DowsingRodItem extends Item {
    public DowsingRodItem(Properties pProperties) {
        super(pProperties);
    }

    @Override
    public void onInventoryTick(ItemStack stack, Level level, Player player, int slotIndex, int selectedIndex) {
        if((slotIndex == selectedIndex)||(player.getOffhandItem() == stack)) {
            if (!level.isClientSide()) {
                BlockPos playerPosition = player.getOnPos();
                boolean foundWater = false;
                for (int i = 0; i <= playerPosition.getY() + 64; i++) {
                    BlockState blockState = level.getBlockState(playerPosition.below(i));

                    if (isWaterSource(blockState) == 8) {
                        foundWater = true;
                        break;
                    }
                }
                if (foundWater) {
                    CompoundTag tag = new CompoundTag();
                    tag.putBoolean("foundWater", true);
                    stack.setTag(tag);
                } else {
                    stack.setTag(new CompoundTag());
                }
            }
        } else {
            stack.setTag(new CompoundTag());
        }
        super.onInventoryTick(stack, level, player, slotIndex, selectedIndex);
    }

    @Override
    public boolean onDroppedByPlayer(ItemStack item, Player player) {
        item.setTag(new CompoundTag());
        return super.onDroppedByPlayer(item, player);
    }

    @Override
    public boolean isFoil(ItemStack pStack) {
        return pStack.hasTag();
    }

    private int isWaterSource(BlockState blockState) {
        return (blockState.getFluidState()).getAmount();
    }
}