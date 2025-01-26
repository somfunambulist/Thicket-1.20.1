package net.somfunambulist.thicket.item.custom;

import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.ShearsItem;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.gameevent.GameEvent;
import net.somfunambulist.thicket.block.ModBlocks;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.function.Supplier;

import static net.minecraft.world.level.block.RotatedPillarBlock.AXIS;

public class PocketKnifeItem extends ShearsItem {

    private static final Map<Block, Supplier<Block>> CARVING_BLOCKS = new HashMap<>();

    public static void registerCarvingBlock(Block from, Supplier<Block> to) {
        CARVING_BLOCKS.put(from, to);
    }

    public static Set<Map.Entry<Block, Supplier<Block>>> getCarvingBlocks() {
        return CARVING_BLOCKS.entrySet();
    }

    static {
        registerCarvingBlock(Blocks.STRIPPED_OAK_LOG, ModBlocks.OAK_CARVING);
        registerCarvingBlock(Blocks.STRIPPED_BIRCH_LOG, ModBlocks.BIRCH_CARVING);
        registerCarvingBlock(Blocks.STRIPPED_SPRUCE_LOG, ModBlocks.SPRUCE_CARVING);
        registerCarvingBlock(Blocks.STRIPPED_JUNGLE_LOG, ModBlocks.JUNGLE_CARVING);
        registerCarvingBlock(Blocks.STRIPPED_ACACIA_LOG, ModBlocks.ACACIA_CARVING);
        registerCarvingBlock(Blocks.STRIPPED_DARK_OAK_LOG, ModBlocks.DARK_OAK_CARVING);
        registerCarvingBlock(Blocks.STRIPPED_MANGROVE_LOG, ModBlocks.MANGROVE_CARVING);
        registerCarvingBlock(Blocks.STRIPPED_CHERRY_LOG, ModBlocks.CHERRY_CARVING);
        registerCarvingBlock(Blocks.STRIPPED_CRIMSON_STEM, ModBlocks.CRIMSON_CARVING);
        registerCarvingBlock(Blocks.STRIPPED_WARPED_STEM, ModBlocks.WARPED_CARVING);
        registerCarvingBlock(ModBlocks.STRIPPED_HAZEL_LOG.get(), ModBlocks.HAZEL_CARVING);
    }

    public PocketKnifeItem(Properties properties) {
        super(properties);
    }

    @Override
    public boolean isValidRepairItem(ItemStack stack, ItemStack repairStack) {
        return repairStack.is(Items.IRON_INGOT);
    }

    private InteractionResult replaceBlock(UseOnContext context, BlockState to, boolean particles) {
        var level = context.getLevel();
        var pos = context.getClickedPos();
        var from = level.getBlockState(pos);

        level.setBlockAndUpdate(pos, to.setValue(AXIS, from.getValue(AXIS))); //???
        level.gameEvent(GameEvent.BLOCK_CHANGE, pos, GameEvent.Context.of(context.getPlayer(), from));

        if (particles) {
            level.addDestroyBlockEffect(pos, from);
        }

        if (context.getPlayer() != null) {
            context.getPlayer().playSound(SoundEvents.AXE_STRIP, 1F, 1.5F);

            context.getItemInHand().hurtAndBreak(1, context.getPlayer(), player -> {
                player.broadcastBreakEvent(context.getHand());
            });
        }

        return InteractionResult.sidedSuccess(level.isClientSide);
    }

    @Override
    public InteractionResult useOn(UseOnContext context) {
        var state = context.getLevel().getBlockState(context.getClickedPos());

        var carving = CARVING_BLOCKS.get(state.getBlock());
        if (carving != null) {
            return replaceBlock(context, carving.get().defaultBlockState(), true);
        } else {
            if (context.getPlayer().getOffhandItem().is(Items.FLINT)) {
                Player player = context.getPlayer();
                Level level = context.getLevel();
                BlockPos blockpos = context.getClickedPos();
                BlockState blockstate = level.getBlockState(blockpos);
                if (!CampfireBlock.canLight(blockstate) && !CandleBlock.canLight(blockstate) && !CandleCakeBlock.canLight(blockstate)) {
                    BlockPos blockpos1 = blockpos.relative(context.getClickedFace());
                    if (BaseFireBlock.canBePlacedAt(level, blockpos1, context.getHorizontalDirection())) {
                        level.playSound(player, blockpos1, SoundEvents.FLINTANDSTEEL_USE, SoundSource.BLOCKS, 1.0F, level.getRandom().nextFloat() * 0.4F + 0.8F);
                        BlockState blockstate1 = BaseFireBlock.getState(level, blockpos1);
                        level.setBlock(blockpos1, blockstate1, 11);
                        level.gameEvent(player, GameEvent.BLOCK_PLACE, blockpos);
                        ItemStack itemstack = context.getItemInHand();
                        if (player instanceof ServerPlayer) {
                            CriteriaTriggers.PLACED_BLOCK.trigger((ServerPlayer)player, blockpos1, itemstack);
                            itemstack.hurtAndBreak(1, player, (p_41300_) -> {
                                p_41300_.broadcastBreakEvent(context.getHand());
                            });
                        }

                        return InteractionResult.sidedSuccess(level.isClientSide());

                    } else {
                        return InteractionResult.FAIL;
                    }
                } else {
                    level.playSound(player, blockpos, SoundEvents.FLINTANDSTEEL_USE, SoundSource.BLOCKS, 1.0F, level.getRandom().nextFloat() * 0.4F + 0.8F);
                    level.setBlock(blockpos, blockstate.setValue(BlockStateProperties.LIT, Boolean.valueOf(true)), 11);
                    level.gameEvent(player, GameEvent.BLOCK_CHANGE, blockpos);
                    if (player != null) {
                        context.getItemInHand().hurtAndBreak(1, player, (p_41303_) -> {
                            p_41303_.broadcastBreakEvent(context.getHand());
                        });
                    }

                    return InteractionResult.sidedSuccess(level.isClientSide());
                }
            }
        }

        return super.useOn(context);
    }
}