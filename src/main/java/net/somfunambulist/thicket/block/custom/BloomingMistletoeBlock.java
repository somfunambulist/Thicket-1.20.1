package net.somfunambulist.thicket.block.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.somfunambulist.thicket.block.ModBlocks;

public class BloomingMistletoeBlock extends ModLeavesBlock{
    public BloomingMistletoeBlock(Properties pProperties) {
        super(pProperties);
        this.registerDefaultState(this.stateDefinition.any().setValue(DISTANCE, Integer.valueOf(7)).setValue(PERSISTENT, Boolean.valueOf(false)).setValue(WATERLOGGED, Boolean.valueOf(false)));
    }

    @Override
    public boolean isRandomlyTicking(BlockState pState) {
        return true;
    }

    public void randomTick(BlockState pState, ServerLevel pLevel, BlockPos pPos, RandomSource pRandom) {
        if (!pLevel.isAreaLoaded(pPos, 3)) return; // Forge: prevent loading unloaded chunks when checking neighbor's light and spreading

        BlockState blockstate = this.defaultBlockState();
        for(int i = 0; i < 4; ++i) {
            int j = 10;

            for(BlockPos blockpos0 : BlockPos.betweenClosed(pPos.offset(-4, -4, -4), pPos.offset(4, 4, 4))) {
                if (pLevel.getBlockState(blockpos0).is(this)) {
                    --j;
                    if (j <= 0) {
                        return;
                    }
                }
            }
            BlockPos blockpos = pPos.offset(pRandom.nextInt(3) - 1, pRandom.nextInt(5) - 3, pRandom.nextInt(3) - 1);
            if (pLevel.getBlockState(blockpos).is(BlockTags.LEAVES)) {
                pLevel.setBlockAndUpdate(blockpos, blockstate);
            }
        }
    }


}
