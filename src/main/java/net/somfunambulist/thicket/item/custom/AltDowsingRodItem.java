package net.somfunambulist.thicket.item.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;

public class AltDowsingRodItem extends Item {

    public AltDowsingRodItem(Properties pProperties) {
        super(pProperties);
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level pLevel, Player pPlayer, InteractionHand pUsedHand) {
        ItemStack itemstack = pPlayer.getItemInHand(pUsedHand);
        if(!pLevel.isClientSide()) {
            BlockPos playerPosition = pPlayer.getOnPos();
            boolean foundWater = false;

            for(int i = 0; i <= playerPosition.getY() + 64; i++) {
                BlockState blockState = pLevel.getBlockState(playerPosition.below(i));

                if(isWaterSource(blockState) == 8) {
                    foundWater = true;
                    break;
                }
            }
            if (foundWater) {
                pPlayer.sendSystemMessage(Component.literal("Water Found"));
            }
            else {
                pPlayer.sendSystemMessage(Component.literal("No Water Found"));
            }
        }
        return super.use(pLevel, pPlayer, pUsedHand);
    }

    private int isWaterSource(BlockState blockState) {
        return (blockState.getFluidState()).getAmount();
    }
}
