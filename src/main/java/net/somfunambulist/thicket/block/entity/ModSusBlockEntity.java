package net.somfunambulist.thicket.block.entity;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.entity.BrushableBlockEntity;
import net.minecraft.world.level.block.state.BlockState;

public class ModSusBlockEntity extends BrushableBlockEntity {
    public ModSusBlockEntity(BlockPos pos, BlockState state) {
        super(pos, state);
    }
    @Override
    public BlockEntityType<?> getType() {
        return ModBlockEntities.MOD_SUS_BLOCK.get();
    }
}
