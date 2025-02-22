package net.somfunambulist.thicket.worldgen.feature.custom;

import com.mojang.serialization.Codec;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.HugeMushroomBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;

public class HugeFiremilkFeature extends Feature<HugeFiremilkConfiguration> {
    public HugeFiremilkFeature(Codec<HugeFiremilkConfiguration> configurationCodec) {
        super(configurationCodec);
    }

    @Override
    public boolean place(FeaturePlaceContext<HugeFiremilkConfiguration> context) {
        WorldGenLevel worldgenlevel = context.level();
        BlockPos blockpos = context.origin();
        RandomSource randomsource = context.random();
        HugeFiremilkConfiguration config = context.config();
        int i = this.getHeight(randomsource);
        BlockPos.MutableBlockPos mutablePos = new BlockPos.MutableBlockPos();
        if (!this.isValidPosition(worldgenlevel, blockpos, i, mutablePos, config)) {
            return false;
        } else {
            this.makeCapAndGills(context, 1, blockpos, mutablePos);
            this.makeTrunk(context, i, blockpos, mutablePos);
            return true;
        }
    }

    protected boolean isValidPosition(LevelAccessor level, BlockPos pos, int height /* I think?? */, BlockPos.MutableBlockPos mutableBlockPos, HugeFiremilkConfiguration config) {
        int i = pos.getY();
        if (i >= level.getMinBuildHeight() + 1 && i + height + 1 < level.getMaxBuildHeight()) {
            BlockState blockstate = level.getBlockState(pos.below());
            if (!isDirt(blockstate) && !blockstate.is(BlockTags.MUSHROOM_GROW_BLOCK)) {
                return false;
            } else {
                for (int j = 0; j <= height; ++j) {
                    int k = this.getRadiusForHeight(config.foliageRadius, j);

                    for (int l = -k; l <= k; ++l) {
                        for (int i1 = -k; i1 <= k; ++i1) {
                            BlockState state = level.getBlockState(mutableBlockPos.setWithOffset(pos, l, j, i1));
                            if (!state.isAir() && !state.is(BlockTags.LEAVES)) {
                                return false;
                            }
                        }
                    }
                }

                return true;
            }
        } else {
            return false;
        }
    }

    private void makeTrunk(FeaturePlaceContext<HugeFiremilkConfiguration> context, int trunkHeight, BlockPos pos, BlockPos.MutableBlockPos mutableBlockPos) {
        for (int i = 0; i < trunkHeight; ++i) {
            mutableBlockPos.set(pos).move(Direction.UP, i);
            if (!context.level().getBlockState(mutableBlockPos).isSolidRender(context.level(), mutableBlockPos)) {
                this.setBlock(context.level(), mutableBlockPos, context.config().stemProvider.getState(context.random(), pos));
            }
        }
    }

    private void makeCapAndGills(FeaturePlaceContext<HugeFiremilkConfiguration> context, int offset, BlockPos pos, BlockPos.MutableBlockPos mutableBlockPos) {
        int folRad = context.config().foliageRadius;

        // TODO: you may need to add a config to change the gill radius too

        // This code below is where the blocks for the cap is actually being placed. You'll want to add in logic to skip
        //   the center 3x3, then add another loop that drops the mutableBlockPos down a block and adds a ring of gills

        for (int j = -folRad; j <= folRad; ++j) {
            for (int k = -folRad; k <= folRad; ++k) {
                boolean flag = j == -folRad;
                boolean flag1 = j == folRad;
                boolean flag2 = k == -folRad;
                boolean flag3 = k == folRad;
                boolean flag4 = flag || flag1;
                boolean flag5 = flag2 || flag3;
                if (!flag4 || !flag5) {
                    mutableBlockPos.setWithOffset(pos, j, offset, k);
                    if (!context.level().getBlockState(mutableBlockPos).isSolidRender(context.level(), mutableBlockPos)) {
                        boolean flag6 = flag || flag5 && j == 1 - folRad;
                        boolean flag7 = flag1 || flag5 && j == folRad - 1;
                        boolean flag8 = flag2 || flag4 && k == 1 - folRad;
                        boolean flag9 = flag3 || flag4 && k == folRad - 1;
                        BlockState blockstate = context.config().capProvider.getState(context.random(), pos);
                        if (blockstate.hasProperty(HugeMushroomBlock.WEST) && blockstate.hasProperty(HugeMushroomBlock.EAST) && blockstate.hasProperty(HugeMushroomBlock.NORTH) && blockstate.hasProperty(HugeMushroomBlock.SOUTH)) {
                            blockstate = blockstate.setValue(HugeMushroomBlock.WEST, Boolean.valueOf(flag6)).setValue(HugeMushroomBlock.EAST, Boolean.valueOf(flag7)).setValue(HugeMushroomBlock.NORTH, Boolean.valueOf(flag8)).setValue(HugeMushroomBlock.SOUTH, Boolean.valueOf(flag9));
                        }

                        this.setBlock(context.level(), mutableBlockPos, blockstate);
                    }
                }
            }
        }
    }


    private int getHeight(RandomSource rand) {
        // TODO: Feel free to change this as much as you need.
        int i = rand.nextInt(3) + 4;
        if (rand.nextInt(12) == 0) {
            i *= 2;
        }
        return i;
    }

    private int getRadiusForHeight(int fallbackRadius, int height) {
        return height <= 3 ? 0 : fallbackRadius;
    }
}
