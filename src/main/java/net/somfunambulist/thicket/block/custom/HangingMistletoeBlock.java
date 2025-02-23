package net.somfunambulist.thicket.block.custom;


import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.HorizontalDirectionalBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.Nullable;

import static net.minecraft.world.level.block.state.properties.BlockStateProperties.HANGING;
import static net.minecraft.world.level.block.state.properties.BlockStateProperties.NORTH;

public class HangingMistletoeBlock extends HorizontalDirectionalBlock {

    private static final VoxelShape EAST_AABB = Block.box(0.0D, 0.0D, 0.0D, 1.0D, 16.0D, 16.0D);
    private static final VoxelShape WEST_AABB = Block.box(15.0D, 0.0D, 0.0D, 16.0D, 16.0D, 16.0D);
    private static final VoxelShape SOUTH_AABB = Block.box(0.0D, 0.0D, 0.0D, 16.0D, 16.0D, 1.0D);
    private static final VoxelShape NORTH_AABB = Block.box(0.0D, 0.0D, 15.0D, 16.0D, 16.0D, 16.0D);
    private static final VoxelShape HANGING_AABB = Block.box(4.0D, 9.0D, 4.0D, 12.0D, 16.0D, 12.0D);

    public HangingMistletoeBlock(Properties properties) {
        super(properties);
    }

    @Override
    public VoxelShape getShape(BlockState pState, BlockGetter pLevel, BlockPos pPos, CollisionContext pContext) {
        if (!pState.getValue(HANGING)) {
            return switch (pState.getValue(FACING)) {
                case NORTH -> NORTH_AABB;
                case SOUTH -> SOUTH_AABB;
                case EAST -> EAST_AABB;
                case WEST -> WEST_AABB;
                default -> NORTH_AABB;
            };
        } else {
            return HANGING_AABB;
        }
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> pBuilder) {
        super.createBlockStateDefinition(pBuilder);
        pBuilder.add(FACING, HANGING);
    }

    @Nullable
    @Override
    public BlockState getStateForPlacement(BlockPlaceContext pContext) {
        Direction direction = pContext.getClickedFace();
        if (direction.getOpposite() != Direction.UP) {
            if (direction.getAxis().isVertical()) {
                for (Direction d : Direction.Plane.HORIZONTAL) {
                    BlockState blockstate = this.defaultBlockState().setValue(FACING, d).setValue(HANGING,false);
                    if (blockstate.canSurvive(pContext.getLevel(), pContext.getClickedPos())) {
                        return blockstate;
                    }
                }
            } else {
                BlockState blockstate = this.defaultBlockState().setValue(FACING, direction).setValue(HANGING,false);
                if (blockstate.canSurvive(pContext.getLevel(), pContext.getClickedPos())) {
                    return blockstate;
                }
            }
        } else {
            return this.defaultBlockState().setValue(HANGING,true);
        }
        return null;
    }

    public boolean canSurvive(BlockState pState, LevelReader pLevel, BlockPos pPos) {
        if (!pState.getValue(HANGING)) {
            Direction direction = pState.getValue(FACING);
            BlockPos blockpos = pPos.relative(direction.getOpposite());
            BlockState blockstate = pLevel.getBlockState(blockpos);
            return blockstate.isFaceSturdy(pLevel, blockpos, direction);
        } else {
            BlockPos blockpos = pPos.above();
            BlockState blockstate = pLevel.getBlockState(blockpos);
            return blockstate.isFaceSturdy(pLevel, blockpos, Direction.DOWN);
        }
    }

    @Override
    public BlockState updateShape(BlockState pState, Direction pFacing, BlockState pFacingState, LevelAccessor pLevel, BlockPos pCurrentPos, BlockPos pFacingPos) {
        if (!pState.getValue(HANGING)) {
            return pState.getValue(FACING).getOpposite() == pFacing && !pState.canSurvive(pLevel, pCurrentPos) ? Blocks.AIR.defaultBlockState() : super.updateShape(pState, pFacing, pFacingState, pLevel, pCurrentPos, pFacingPos);
        } else {
            return !pState.canSurvive(pLevel, pCurrentPos) ? Blocks.AIR.defaultBlockState() : super.updateShape(pState, pFacing, pFacingState, pLevel, pCurrentPos, pFacingPos);
        }
    }
}