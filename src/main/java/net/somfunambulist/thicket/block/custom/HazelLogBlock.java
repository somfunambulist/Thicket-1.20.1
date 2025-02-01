package net.somfunambulist.thicket.block.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.somfunambulist.thicket.block.ModBlocks;

public class HazelLogBlock extends ModFlammableRotatedPillarBlock{

    @Override
    public boolean isRandomlyTicking(BlockState pState) {
        return true;
    }

    @Override
    public void randomTick(BlockState pState, ServerLevel pLevel, BlockPos pPos, RandomSource pRandom) {
        pState = ModBlocks.FIREMILK_MUSHROOM.get().defaultBlockState();
        if (pRandom.nextInt(20) == 0) {
            int i = 3;
            int j = 4;

            for(BlockPos blockpos : BlockPos.betweenClosed(pPos.offset(-4, -3, -4), pPos.offset(4, 1, 4))) {
                if (pLevel.getBlockState(blockpos).is(ModBlocks.FIREMILK_MUSHROOM.get())) {
                    --i;
                    if (i <= 0) {
                        return;
                    }
                }
            }

            BlockPos blockpos1 = pPos.offset(pRandom.nextInt(3) - 1, pRandom.nextInt(2) - pRandom.nextInt(2), pRandom.nextInt(3) - 1);

            for(int k = 0; k < 4; ++k) {
                if (pLevel.isEmptyBlock(blockpos1) && pState.canSurvive(pLevel, blockpos1)) {
                    pPos = blockpos1;
                }

                blockpos1 = pPos.offset(pRandom.nextInt(3) - 1, pRandom.nextInt(2) - pRandom.nextInt(2), pRandom.nextInt(3) - 1);
            }
            if (pLevel.isEmptyBlock(blockpos1) && pState.canSurvive(pLevel, blockpos1)) {
                pLevel.setBlock(blockpos1, pState, 2);
            }
        }

    }

    public HazelLogBlock(Properties pProperties) {
        super(pProperties);
    }
}
