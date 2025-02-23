package net.somfunambulist.thicket.block.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceKey;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.MushroomBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.somfunambulist.thicket.block.ModBlocks;
import net.somfunambulist.thicket.tags.ModBlockTags;

public class FiremilkMushroomBlock extends MushroomBlock {

    protected static final VoxelShape SHAPE = Block.box(4.0D, 0.0D, 4.0D, 12.0D, 7.0D, 12.0D);

    public VoxelShape getShape(BlockState pState, BlockGetter pLevel, BlockPos pPos, CollisionContext pContext) {
        return SHAPE;
    }

    public void randomTick(BlockState pState, ServerLevel pLevel, BlockPos pPos, RandomSource pRandom) {

        if (pRandom.nextInt(500) == 0) {
            int i = 3;
            int j = 4;

            for(BlockPos blockpos : BlockPos.betweenClosed(pPos.offset(-4, -1, -4), pPos.offset(4, 1, 4))) {
                if (pLevel.getBlockState(blockpos).is(this)) {
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

    public boolean canSurvive(BlockState pState, LevelReader pLevel, BlockPos pPos) {
        BlockPos blockpos = pPos.below();
        BlockState blockstate = pLevel.getBlockState(blockpos);
        if (blockstate.is(ModBlockTags.FIREMILK_GROW_BLOCK)) {
            return true;
        } else {
            return pLevel.getRawBrightness(pPos, 0) < 13 && blockstate.canSustainPlant(pLevel, blockpos, net.minecraft.core.Direction.UP, this);
        }
    }

    public FiremilkMushroomBlock(Properties pProperties, ResourceKey<ConfiguredFeature<?, ?>> pFeature) {
        super(pProperties, pFeature);
    }
}
