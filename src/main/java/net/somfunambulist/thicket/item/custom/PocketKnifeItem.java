package net.somfunambulist.thicket.item.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.ShearsItem;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentCategory;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
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
        registerCarvingBlock(Blocks.STRIPPED_OAK_LOG, ModBlocks.CARVED_OAK);
        registerCarvingBlock(ModBlocks.STRIPPED_HAZEL_LOG.get(), ModBlocks.CARVED_HAZEL);
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
        }

        return super.useOn(context);
    }
}