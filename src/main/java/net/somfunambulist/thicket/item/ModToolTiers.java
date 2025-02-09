package net.somfunambulist.thicket.item;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.Tiers;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraftforge.common.ForgeTier;
import net.minecraftforge.common.TierSortingRegistry;
import net.somfunambulist.thicket.Thicket;
import net.somfunambulist.thicket.tags.ModBlockTags;

import java.util.List;

public class ModToolTiers {
    public static final Tier CRUXWOOD = TierSortingRegistry.registerTier(
            new ForgeTier(0,59,2f,0,26,
                    ModBlockTags.NEEDS_CRUXWOOD_TOOL, () -> Ingredient.of(ModItems.ENCHANTED_MISTLETOE.get())),
            new ResourceLocation(Thicket.MOD_ID, "cruxwood"), List.of(Tiers.IRON),List.of());

}
