package net.somfunambulist.thicket.item.custom;

import net.minecraft.client.Minecraft;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.ShearsItem;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;

import java.util.Objects;

import static java.lang.Math.floor;

public class SickleItem extends ShearsItem {

    public SickleItem(Properties pProperties) {
        super(pProperties);
    }

    @Override
    public boolean isValidRepairItem(ItemStack stack, ItemStack repairStack) {
        return repairStack.is(Items.GOLD_INGOT);
    }


    @Override
    public boolean isFoil(ItemStack pStack) {
        /*
        if(pStack.hasTag()) {
            return pStack.getTag().getBoolean("fullMoon");
        } else {
            return false;
        }
        */
        assert Minecraft.getInstance().level != null;
        if (Minecraft.getInstance().level.isClientSide) {
            return isFullMoon(Minecraft.getInstance().level);
        }
        else {
            return false;
        }
    }

    @Override
    public void onInventoryTick(ItemStack stack, Level level, Player player, int slotIndex, int selectedIndex) {
        CompoundTag tag = new CompoundTag();
        tag.putBoolean("fullMoon",isFullMoon(level));
        stack.setTag(tag);
        super.onInventoryTick(stack, level, player, slotIndex, selectedIndex);
    }

    @Override
    public boolean onEntityItemUpdate(ItemStack stack, ItemEntity entity) {
        CompoundTag tag = new CompoundTag();
        tag.putBoolean("fullMoon",isFullMoon(entity.level()));
        stack.setTag(tag);
        return super.onEntityItemUpdate(stack, entity);
    }

    private boolean isFullMoon(Level level) {
        var current_time = level.getDayTime();
        var current_day = floor(current_time/24000);
        var current_cycle = floor(current_time/192000);
        var moon_phase = floor(current_day-current_cycle*8);
        var relative_time = current_time-current_day*2400;
        return relative_time > 13000 && relative_time < 23000 && moon_phase == 0;
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
