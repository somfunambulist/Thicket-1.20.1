package net.somfunambulist.thicket.content.items;

import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.EquipmentSlotGroup;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.component.ItemAttributeModifiers;
import net.minecraft.world.item.crafting.RecipeHolder;
import net.minecraft.world.item.crafting.RecipeManager;
import net.minecraft.world.item.crafting.SingleRecipeInput;
import net.minecraft.world.level.Level;
import net.somfunambulist.thicket.registry.ModRecipes;

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

                if (!level.isClientSide()) {
                    player.awardStat(Stats.ITEM_USED.get(this));
                    knifeItem.hurtAndBreak(1, player, LivingEntity.getSlotForHand(usedHand));
                }
                return InteractionResultHolder.sidedSuccess(knifeItem, level.isClientSide());
            }
        }

        return super.use(level, player, usedHand);
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
