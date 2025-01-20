package net.somfunambulist.thicket.item.custom;

import net.minecraft.client.resources.language.I18n;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;

public class DowsingRodItem extends Item {
    public DowsingRodItem(Properties pProperties) {
        super(pProperties);
    }

    @Override
    public InteractionResult useOn(UseOnContext pContext) {
        if(!pContext.getLevel().isClientSide()) {
            BlockPos positionClicked = pContext.getClickedPos();
            Player player = pContext.getPlayer();
            boolean foundWater = false;

            for(int i = 0; i <= positionClicked.getY() + 64; i++) {
                BlockState blockState = pContext.getLevel().getBlockState(positionClicked.below(i));

                if(isWaterSource(blockState) == 8) {
                    outputWaterSourceCoordinates(positionClicked.below(i), player, blockState.getBlock());
                    foundWater = true;

                    break;
                }
            }

            if(!foundWater) {
                outputNoWaterSourceFound(player);
            }

            return InteractionResult.SUCCESS;
        }

        return super.useOn(pContext);
    }

    private void outputNoWaterSourceFound(Player player) {
        player.sendSystemMessage(Component.translatable("item.thicket.dowsing_rod.no_water"));
    }

    private void outputWaterSourceCoordinates(BlockPos below, Player player, Block block) {
        player.sendSystemMessage(Component.literal("Water Found"));
    }

    private int isWaterSource(BlockState blockState) {
        return (blockState.getFluidState()).getAmount();
    }
}
