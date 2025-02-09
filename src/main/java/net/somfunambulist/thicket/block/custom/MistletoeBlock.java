package net.somfunambulist.thicket.block.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.block.state.BlockState;
import net.somfunambulist.thicket.block.ModBlocks;
import net.somfunambulist.thicket.tags.ModBlockTags;

public class MistletoeBlock extends ModLeavesBlock{
    public MistletoeBlock(Properties pProperties) {
        super(pProperties);
        this.registerDefaultState(this.stateDefinition.any().setValue(DISTANCE, Integer.valueOf(7)).setValue(PERSISTENT, Boolean.valueOf(false)).setValue(WATERLOGGED, Boolean.valueOf(false)));
    }

    @Override
    public boolean isRandomlyTicking(BlockState pState) {
        return true;
    }

    public void randomTick(BlockState pState, ServerLevel pLevel, BlockPos pPos, RandomSource pRandom) {
        if (this.decaying(pState)) {
            dropResources(pState, pLevel, pPos);
            pLevel.removeBlock(pPos, false);
        }

        if (!pLevel.isAreaLoaded(pPos, 3)) return; // Forge: prevent loading unloaded chunks when checking neighbor's light and spreading

        BlockState blockstate = this.defaultBlockState();
        for(int i = 0; i < 4; ++i) {
            int j = 24;

            for(BlockPos blockpos0 : BlockPos.betweenClosed(pPos.offset(-4, -4, -4), pPos.offset(4, 4, 4))) {
                if (pLevel.getBlockState(blockpos0).is(ModBlockTags.MISTLETOE_BLOCK)) {
                    --j;
                    if (j <= 0) {
                        return;
                    }
                }
            }
            BlockPos blockpos = pPos.offset(pRandom.nextInt(3) - 1, pRandom.nextInt(5) - 3, pRandom.nextInt(3) - 1);
            if (pLevel.getBlockState(blockpos).is(BlockTags.LEAVES) && !pLevel.getBlockState(blockpos).is(ModBlockTags.MISTLETOE_BLOCK)) {
                var bstate = pLevel.getBlockState(blockpos);
                if (pRandom.nextInt(3) == 0) {
                    blockstate = ModBlocks.BLOOMING_MISTLETOE_LEAVES.get().defaultBlockState();
                    pLevel.setBlockAndUpdate(blockpos, blockstate.setValue(PERSISTENT, bstate.getValue(PERSISTENT)).setValue(WATERLOGGED, bstate.getValue(WATERLOGGED)).setValue(DISTANCE, bstate.getValue(DISTANCE)));
                } else {
                    blockstate = ModBlocks.MISTLETOE_LEAVES.get().defaultBlockState();
                    pLevel.setBlockAndUpdate(blockpos, blockstate.setValue(PERSISTENT, bstate.getValue(PERSISTENT)).setValue(WATERLOGGED, bstate.getValue(WATERLOGGED)).setValue(DISTANCE, bstate.getValue(DISTANCE)));
                }
                //pState.setValue(PERSISTENT, false);
                //pState.setValue(DISTANCE, bstate.getValue(DISTANCE));
            }
        }
    }

}
