package net.somfunambulist.thicket.block.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.npc.Villager;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class SpikyLeavesBlock extends ModLeavesBlock{
    private static final VoxelShape AABB = box(1f, 0f, 1f, 15f, 15f, 15f);

    public SpikyLeavesBlock(Properties pProperties) {
        super(pProperties);
    }

    @Override
    public VoxelShape getCollisionShape(BlockState state, BlockGetter getter, BlockPos pos, CollisionContext context) {
        return AABB;
    }

    @Override
    public void entityInside(BlockState state, Level level, BlockPos pos, Entity entity) {
        entityInside(1.2f, entity, level);
    }

    @Override
    @OnlyIn(Dist.CLIENT)
    public float getShadeBrightness(BlockState state, BlockGetter getter, BlockPos pos) {
        return .2f;
    }

    public static void entityInside(float damage, Entity entity, Level level) {
        if (!level.isClientSide && (entity.xOld != entity.getX() || entity.zOld != entity.getZ()) && ((entity instanceof Player player && !player.isCrouching()) || entity instanceof Villager)) {
            double d0 = Math.abs(entity.getX() - entity.xOld);
            double d1 = Math.abs(entity.getZ() - entity.zOld);

            if (d0 >= .003d || d1 >= .003d)
                entity.hurt(entity.damageSources().cactus(), damage);
        }
    }

}
