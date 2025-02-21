package net.somfunambulist.thicket.item.custom;

import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.stats.Stats;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.vehicle.Boat;
import net.minecraft.world.item.BoneMealItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ItemUtils;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.ClipContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.biome.Biomes;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.BucketPickup;
import net.minecraft.world.level.block.IceBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.common.BiomeManager;
import net.minecraftforge.common.Tags;

import java.util.List;

import static net.minecraft.world.level.block.RotatedPillarBlock.AXIS;

public class EnchantedMistletoeItem extends Item {

    public EnchantedMistletoeItem(Properties pProperties) {
        super(pProperties);
    }

    @Override
    public boolean isFoil(ItemStack pStack) {
        return true;
    }

    private InteractionResult replaceBlock(UseOnContext context, BlockState to, boolean particles) {
        var level = context.getLevel();
        var pos = context.getClickedPos();
        var from = level.getBlockState(pos);

        level.setBlockAndUpdate(pos, to);
        level.gameEvent(GameEvent.BLOCK_CHANGE, pos, GameEvent.Context.of(context.getPlayer(), from));

        if (particles) {
            level.addDestroyBlockEffect(pos, from);
        }

        if (context.getPlayer() != null) {
            context.getPlayer().playSound(SoundEvents.ENCHANTMENT_TABLE_USE, 0.8F, 2F);
            context.getPlayer().playSound(SoundEvents.ENCHANTMENT_TABLE_USE, 0.4F, 0.8F);

            context.getItemInHand().hurtAndBreak(1, context.getPlayer(), player -> {
                player.broadcastBreakEvent(context.getHand());
            });
        }

        return InteractionResult.sidedSuccess(level.isClientSide);
    }

    private InteractionResult replaceBlockAbove(UseOnContext context, BlockState to, boolean particles) {
        var level = context.getLevel();
        var pos = context.getClickedPos().above();
        var from = level.getBlockState(pos);

        level.setBlockAndUpdate(pos, to);
        level.gameEvent(GameEvent.BLOCK_CHANGE, pos, GameEvent.Context.of(context.getPlayer(), from));

        if (particles) {
            level.addDestroyBlockEffect(pos, from);
        }

        if (context.getPlayer() != null) {
            context.getPlayer().playSound(SoundEvents.ENCHANTMENT_TABLE_USE, 0.8F, 2F);
            context.getPlayer().playSound(SoundEvents.ENCHANTMENT_TABLE_USE, 0.4F, 0.8F);

            context.getItemInHand().hurtAndBreak(1, context.getPlayer(), player -> {
                player.broadcastBreakEvent(context.getHand());
            });
        }

        return InteractionResult.sidedSuccess(level.isClientSide);
    }

    @Override
    public InteractionResult useOn(UseOnContext context) {
        var state = context.getLevel().getBlockState(context.getClickedPos());

        if (state.getBlock() == Blocks.DIRT) {
            if (context.getLevel().isClientSide) {
                addGrowthParticles(context.getLevel(), context.getClickedPos(), 8);
            }
            if (context.getLevel().getBiome(context.getClickedPos()).is(Tags.Biomes.IS_MUSHROOM)) {
                return replaceBlock(context, Blocks.MYCELIUM.defaultBlockState(), false);
            } else {
                return replaceBlock(context, Blocks.GRASS_BLOCK.defaultBlockState(), false);
            }
        } else {
            if (state.getBlock() == Blocks.NETHERRACK) {
                if (context.getLevel().isClientSide) {
                    addGrowthParticles(context.getLevel(), context.getClickedPos(), 15);
                }
                if (context.getLevel().getBiome(context.getClickedPos()).is(Biomes.CRIMSON_FOREST)) {
                    return replaceBlock(context, Blocks.CRIMSON_NYLIUM.defaultBlockState(), false);
                } else {
                    if (context.getLevel().getBiome(context.getClickedPos()).is(Biomes.WARPED_FOREST)) {
                        return replaceBlock(context, Blocks.WARPED_NYLIUM.defaultBlockState(), false);
                    } else {
                        if (Math.random() <0.5) {
                            return replaceBlock(context, Blocks.CRIMSON_NYLIUM.defaultBlockState(), false);
                        } else {
                            return replaceBlock(context, Blocks.WARPED_NYLIUM.defaultBlockState(), false);
                        }
                    }
                }
            }
        }
        return super.useOn(context);
    }

    public static void addGrowthParticles(LevelAccessor pLevel, BlockPos pPos, int pData) {
        if (pData == 0) {
            pData = 15;
        }

        BlockState blockstate = pLevel.getBlockState(pPos);
        if (!blockstate.isAir()) {
            double d0 = 0.5D;
            double d1;
            if (blockstate.is(Blocks.WATER)) {
                pData *= 3;
                d1 = 1.0D;
                d0 = 1.0D;
            } else if (blockstate.isSolidRender(pLevel, pPos)) {
                pPos = pPos.above();
                pData *= 3;
                d0 = 1.0D;
                d1 = 1.0D;
            } else {
                d1 = blockstate.getShape(pLevel, pPos).max(Direction.Axis.Y);
            }

            pLevel.addParticle(ParticleTypes.HAPPY_VILLAGER, (double)pPos.getX() + 0.5D, (double)pPos.getY() + 0.5D, (double)pPos.getZ() + 0.5D, 0.0D, 0.0D, 0.0D);
            RandomSource randomsource = pLevel.getRandom();

            for(int i = 0; i < pData; ++i) {
                double d2 = randomsource.nextGaussian() * 0.02D;
                double d3 = randomsource.nextGaussian() * 0.02D;
                double d4 = randomsource.nextGaussian() * 0.02D;
                double d5 = 0.5D - d0;
                double d6 = (double)pPos.getX() + d5 + randomsource.nextDouble() * d0 * 2.0D;
                double d7 = (double)pPos.getY() + randomsource.nextDouble() * d1;
                double d8 = (double)pPos.getZ() + d5 + randomsource.nextDouble() * d0 * 2.0D;
                if (!pLevel.getBlockState(BlockPos.containing(d6, d7, d8).below()).isAir()) {
                    pLevel.addParticle(ParticleTypes.HAPPY_VILLAGER, d6, d7, d8, d2, d3, d4);
                }
            }

        }
    }
}
