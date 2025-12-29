package net.somfunambulist.thicket.block.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.block.AmethystClusterBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Fluids;
import net.somfunambulist.thicket.block.ModBlocks;

public class ResinousBlock extends ModFlammableRotatedPillarBlock{
    public static final int GROWTH_CHANCE = 5;
    public static final Direction[] DIRECTIONS = Direction.values();

    public ResinousBlock(Properties pProperties) {
        super(pProperties);
    }

    @Override
    public boolean isRandomlyTicking(BlockState pState) {
        return true;
    }

    @Override
    public void randomTick(BlockState pState, ServerLevel pLevel, BlockPos pPos, RandomSource pRandom) {
        if (pRandom.nextInt(5) == 0) {
            Direction direction = DIRECTIONS[pRandom.nextInt(DIRECTIONS.length)];
            BlockPos blockpos = pPos.relative(direction);
            BlockState blockstate = pLevel.getBlockState(blockpos);
            Block block = ModBlocks.MYRRH_RESIN.get();
            if(pState.is(ModBlocks.STRIPPED_GREEN_MYRRH_LOG.get())||pState.is(ModBlocks.STRIPPED_GREEN_MYRRH_WOOD.get())||pState.is(ModBlocks.GREEN_MYRRH_CARVING.get())) {
                block = ModBlocks.MYRRH_RESIN.get();
            }
            if(pState.is(ModBlocks.STRIPPED_RED_BOSWELLIA_LOG.get())||pState.is(ModBlocks.STRIPPED_RED_BOSWELLIA_WOOD.get())||pState.is(ModBlocks.RED_BOSWELLIA_CARVING.get())) {
                block = ModBlocks.FRANKINCENSE_RESIN.get();
            }
            if (blockstate.isAir()) {
                BlockState blockstate1 = block.defaultBlockState().setValue(AmethystClusterBlock.FACING, direction);
                pLevel.setBlockAndUpdate(blockpos, blockstate1);
            }
        }
    }
}
