package net.somfunambulist.thicket.item.custom;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.ShearsItem;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;

import static java.lang.Math.floor;

public class SickleItem extends ShearsItem {
    public static final String FULL_MOON_TAG_KEY = "fullMoon";

    public SickleItem(Properties pProperties) {
        super(pProperties);
    }

    @Override
    public boolean isValidRepairItem(ItemStack stack, ItemStack repairStack) {
        return repairStack.is(Items.GOLD_INGOT);
    }

    @Override
    public boolean isFoil(ItemStack pStack) {
        CompoundTag tag = pStack.getOrCreateTag();
        return tag.contains(FULL_MOON_TAG_KEY) && tag.getBoolean(FULL_MOON_TAG_KEY);
    }

    @Override
    public void onInventoryTick(ItemStack stack, Level level, Player player, int slotIndex, int selectedIndex) {
        tryUpdateFullMoonTag(stack, level);
        super.onInventoryTick(stack, level, player, slotIndex, selectedIndex);
    }

    @Override
    public boolean onEntityItemUpdate(ItemStack stack, ItemEntity entity) {
        tryUpdateFullMoonTag(stack, entity.level());
        return super.onEntityItemUpdate(stack, entity);
    }

    private void tryUpdateFullMoonTag(ItemStack stack, Level level) {
        CompoundTag tag = stack.getOrCreateTag();

        boolean newValue = isFullMoon(level);
        // Only update the tag on the item when we have to
        if (!tag.contains(FULL_MOON_TAG_KEY) || tag.getBoolean(FULL_MOON_TAG_KEY) != newValue) {
            tag.putBoolean(FULL_MOON_TAG_KEY, newValue);
            stack.setTag(tag);
        }
    }

    private boolean isFullMoon(Level level) {
        var phase = level.getMoonPhase();
        if (phase != 0) return false;
        /* getTimeOfDay returns a float between 0.0 and 0.999, where:
            - 0.0 is noon
            - 0.25 is sunset/moonrise
            - 0.5 is midnight
            - 0.75 is sunrise/moonset
         */
        var currentTimeScale = level.getTimeOfDay(0.F /* pPartialTick is unused in this impl */);
        return currentTimeScale >= 0.25 && currentTimeScale <= 0.75;
    }

    @Override
    public InteractionResult useOn(UseOnContext pContext) {
        if (!pContext.getLevel().isClientSide()) {
            Player player = pContext.getPlayer();
            if (isFullMoon(player.level())) {
                player.sendSystemMessage(Component.literal("true"));
            } else {
                player.sendSystemMessage(Component.literal("false"));
            }
        }

        return super.useOn(pContext);
    }
}
