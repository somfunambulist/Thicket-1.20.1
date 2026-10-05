package net.somfunambulist.thicket.content.items;

import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.EquipmentSlotGroup;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.component.ItemAttributeModifiers;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.item.crafting.SingleRecipeInput;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.gameevent.GameEvent;
import net.somfunambulist.thicket.registry.ModRecipes;
import net.somfunambulist.thicket.registry.ModTags;

public class PocketKnife extends Item {

    public PocketKnife(Properties properties) {
        super(properties);
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand usedHand) {
        var offHandItem = player.getItemInHand(InteractionHand.OFF_HAND);
        var knifeItem = player.getMainHandItem();
        if (!offHandItem.isEmpty()) {
            var optional = level.getRecipeManager().getRecipeFor(ModRecipes.POCKET_KNIFE_ITEM.get(), new SingleRecipeInput(offHandItem), level);
            if (optional.isPresent()) {
                var recipe = optional.get().value();

                boolean isShift = player.isShiftKeyDown();
                int processedAmount = isShift ? offHandItem.getCount() : 1;
                offHandItem.consume(processedAmount, player);

                int newCount = recipe.getResultItem(level.registryAccess()).getCount();
                var newStack = recipe.getResultItem(level.registryAccess()).copyWithCount(newCount * processedAmount);
                if (!player.getInventory().add(newStack)) {
                    player.drop(newStack, false);
                }

                return finishKnifeUse(level, player, usedHand, knifeItem);
            }
        }

        return super.use(level, player, usedHand);
    }

    @Override
    public InteractionResult useOn(UseOnContext context) {
        var level = context.getLevel();
        var player = context.getPlayer();
        var clickedPos = context.getClickedPos();
        var clickedState = level.getBlockState(clickedPos);
        ItemStack knife = context.getItemInHand();

        if (player == null) return super.useOn(context);

        if (player.getItemInHand(InteractionHand.OFF_HAND).is(ModTags.Items.FLINT_LIKE)) {
            if (clickedState.hasProperty(BlockStateProperties.LIT)) {
                if (clickedState.hasProperty(BlockStateProperties.WATERLOGGED) && clickedState.getValue(BlockStateProperties.WATERLOGGED)) return super.useOn(context); //If waterlogged
                if (clickedState.getValue(BlockStateProperties.LIT)) return super.useOn(context); //If already lit

                level.playSound(player, clickedPos, SoundEvents.FLINTANDSTEEL_USE, SoundSource.BLOCKS, 1.0F, level.getRandom().nextFloat() * 0.4F + 0.8F);
                level.setBlock(clickedPos, clickedState.setValue(BlockStateProperties.LIT, Boolean.TRUE), 11);
                level.gameEvent(player, GameEvent.BLOCK_CHANGE, clickedPos);

                return finishKnifeUse(level, player, player.getUsedItemHand(), knife).getResult();

            } else {
                BlockPos posOfFace = clickedPos.relative(context.getClickedFace());
                if (BaseFireBlock.canBePlacedAt(level, posOfFace, context.getHorizontalDirection())) {
                    level.playSound(player, posOfFace, SoundEvents.FLINTANDSTEEL_USE, SoundSource.BLOCKS, 1.0F, level.getRandom().nextFloat() * 0.4F + 0.8F);
                    BlockState fireBlock = BaseFireBlock.getState(level, posOfFace);
                    level.setBlock(posOfFace, fireBlock, 11);
                    level.gameEvent(player, GameEvent.BLOCK_PLACE, clickedPos);

                    if (player instanceof ServerPlayer) {
                        CriteriaTriggers.PLACED_BLOCK.trigger((ServerPlayer)player, posOfFace, knife);
                    }

                    return finishKnifeUse(level, player, player.getUsedItemHand(), knife).getResult();

                } else {
                    return super.useOn(context);
                }
            }
        } else {
            //TODO I reallyyyy don't like doing this through item comparison. But the block version was awful too. Figure out a nice way to handle this.
            var stackFromClickedBlock = clickedState.getBlock().asItem().getDefaultInstance();
            var optional = level.getRecipeManager().getRecipeFor(ModRecipes.POCKET_KNIFE_ITEM.get(), new SingleRecipeInput(stackFromClickedBlock), level);

            if (optional.isPresent()) {
                var recipe = optional.get().value();
                var resultItem = recipe.getResultItem(level.registryAccess());
                if (resultItem.getItem() instanceof BlockItem blockItem) {
                    var blockToPlace = blockItem.getBlock();
                    /** TODO
                     *  Two things to do
                     *  1. If to place block has same properties from previous block, copy them over
                     *  2. carvings use facing direction but logs use axis -> add special handling
                     */
                    level.setBlock(clickedPos, blockToPlace.defaultBlockState(), Block.UPDATE_ALL);
                    level.gameEvent(GameEvent.BLOCK_CHANGE, clickedPos, GameEvent.Context.of(context.getPlayer(), clickedState));
                    level.addDestroyBlockEffect(clickedPos, clickedState);
                    player.playSound(SoundEvents.AXE_STRIP, 1F, 1.5F);
                    return finishKnifeUse(level, player, player.getUsedItemHand(), knife).getResult();
                }
            }
        }

        return super.useOn(context);
    }

    private InteractionResultHolder<ItemStack> finishKnifeUse(Level level, Player player, InteractionHand usedHand, ItemStack knife) {
        if (!level.isClientSide()) {
            player.awardStat(Stats.ITEM_USED.get(this));
            knife.hurtAndBreak(1, player, LivingEntity.getSlotForHand(usedHand));
        }
        return InteractionResultHolder.sidedSuccess(knife, level.isClientSide());
    }

    @Override
    public boolean isValidRepairItem(ItemStack stack, ItemStack repairStack) {
        return repairStack.is(Items.IRON_INGOT);
    }

    public static ItemAttributeModifiers createAttributes(float attack_damage, float attack_speed) {
        return ItemAttributeModifiers.builder()
                .add(
                        Attributes.ATTACK_DAMAGE,
                        new AttributeModifier(BASE_ATTACK_DAMAGE_ID, attack_damage, AttributeModifier.Operation.ADD_VALUE),
                        EquipmentSlotGroup.MAINHAND
                )
                .add(
                        Attributes.ATTACK_SPEED,
                        new AttributeModifier(BASE_ATTACK_SPEED_ID, attack_speed, AttributeModifier.Operation.ADD_VALUE),
                        EquipmentSlotGroup.MAINHAND
                ).build();
    }
}
