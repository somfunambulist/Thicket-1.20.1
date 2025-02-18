package net.somfunambulist.thicket.enchantment;

import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.boss.enderdragon.EnderDragon;
import net.minecraft.world.entity.monster.Shulker;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentCategory;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.living.LivingDamageEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class OccultEnchantment extends Enchantment {
    protected OccultEnchantment(Rarity pRarity, EnchantmentCategory pCategory, EquipmentSlot... pApplicableSlots) {
        super(pRarity, pCategory, pApplicableSlots);
        MinecraftForge.EVENT_BUS.register(this);
    }

    @SubscribeEvent
    public void applyEnchantmentDamageModifier(LivingDamageEvent evt) {
        // If we don't have an entity that was attacked (somehow) then we have nothing to do here
        if (evt.getEntity() == null) return;

        /* BEGIN: Check to see if the attacker is a player and was using our enchantment */

        // Let's make sure that the source of the damage was a player wielding our required enchantment
        if (evt.getSource().getDirectEntity() == null) return;
        if (!(evt.getSource().getDirectEntity() instanceof Player player)) return;

        var enchantment = ModEnchantments.OCCULT.get(); /* Replace with your getter from your Registry */
        var enchantmentLevel = EnchantmentHelper.getEnchantmentLevel(enchantment, player);
        // Player doesn't have this enchantment whatsoever :(
        if (enchantmentLevel == 0) return;

        /* END: Check to see if the attacker is a player and was using our enchantment */

        /* BEGIN: Check to see if the enchantment can be applied to the mob we hurt, and apply it */

        // Only apply to the Monster entity type
        // You can also use entity_type tags and use evt.getTarget().is({ENTITY_TAG})
        if (!(evt.getEntity() instanceof EnderDragon)) return;

        float damageModifierBase = 2000.0F; /* So level 1 will have +2.0 damage, 2 -> 4.0, 3 -> 6.0, etc. */
        evt.setAmount(evt.getAmount() + (damageModifierBase * enchantmentLevel));

        /* END: Check to see if the enchantment can be applied to the mob we hurt, and apply it */
    }

}