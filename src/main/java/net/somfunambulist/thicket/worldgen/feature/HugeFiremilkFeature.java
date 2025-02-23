package net.somfunambulist.thicket.worldgen.feature;

import com.mojang.serialization.Codec;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.LevelAccessor;
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
        var worldGenLevel = context.level();
        var pos = context.origin();
        var random = context.random();
        var config = context.config();
        var height = this.getHeight(random);
        BlockPos.MutableBlockPos mutablePos = new BlockPos.MutableBlockPos();
        if (!this.isValidPosition(worldGenLevel, pos, height, mutablePos, config)) {
            return false;
        } else {
            this.placeCapAndGills(worldGenLevel, random, pos, height, mutablePos, config);
            this.placeTrunk(worldGenLevel, random, pos, config, height, mutablePos);
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

    protected void placeTrunk(LevelAccessor level, RandomSource random, BlockPos pos, HugeFiremilkConfiguration config, int maxHeight, BlockPos.MutableBlockPos mutablePos) {
        for (int y = 0; y < maxHeight; ++y) {
            mutablePos.set(pos).move(Direction.UP, y);
            if (!level.getBlockState(mutablePos).isSolidRender(level, mutablePos)) {
                this.setBlock(level, mutablePos, config.stemProvider.getState(random, pos));
            }
        }
    }

    protected void placeCapAndGills(LevelAccessor level, RandomSource random, BlockPos pos, int height, BlockPos.MutableBlockPos mutablePos, HugeFiremilkConfiguration config) {
        int folRad = config.foliageRadius;
        int gillRadius = folRad / 2;

        for (int x = -folRad; x <= folRad; ++x) {
            for (int z = -folRad; z <= folRad; ++z) {

                // Check to see if we fall in the circular gill radius, dropdown and fill it if we do.
                if ((x >= -gillRadius && x <= gillRadius) && (z >= -gillRadius && z <= gillRadius)) {
                    BlockState state = config.gillProvider.getState(random, pos);
                    if (state.hasProperty(HugeMushroomBlock.UP)) {
                        state = state.setValue(HugeMushroomBlock.UP, true);
                    }

                    mutablePos.setWithOffset(pos, x, height, z);
                    mutablePos.move(Direction.DOWN);
                    this.setBlock(level, mutablePos, state);
                    mutablePos.move(Direction.UP);
                } else {
                    boolean isXAtStart = x == -folRad;
                    boolean isXAtEnd = x == folRad;
                    boolean isZAtStart = z == -folRad;
                    boolean isZAtEnd = z == folRad;
                    boolean isXAtStartOrEnd = isXAtStart || isXAtEnd;
                    boolean isZAtStartOrEnd = isZAtStart || isZAtEnd;
                    if (!isXAtStartOrEnd || !isZAtStartOrEnd) {
                        mutablePos.setWithOffset(pos, x, height, z);
                        if (!level.getBlockState(mutablePos).isSolidRender(level, mutablePos)) {
                            BlockState state = config.capProvider.getState(random, pos);
                            this.setBlock(level, mutablePos, state);
                        }
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