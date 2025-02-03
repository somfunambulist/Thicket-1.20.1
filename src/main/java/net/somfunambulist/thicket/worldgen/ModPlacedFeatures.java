package net.somfunambulist.thicket.worldgen;

import net.somfunambulist.thicket.Thicket;
import net.somfunambulist.thicket.block.ModBlocks;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstapContext;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.data.worldgen.placement.VegetationPlacements;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import net.minecraft.world.level.levelgen.placement.PlacementModifier;

import java.util.List;

public class ModPlacedFeatures {
    public static final ResourceKey<PlacedFeature> HAZEL_PLACED_KEY = registerKey("hazel_placed");
    public static final ResourceKey<PlacedFeature> SASSAFRAS_PLACED_KEY = registerKey("sassafras_placed");
/*
    public static void bootstrap(BootstapContext<PlacedFeature> context) {
        HolderGetter<ConfiguredFeature<?, ?>> configuredFeatures = context.lookup(Registries.CONFIGURED_FEATURE);

        register(context, HAZEL_PLACED_KEY, configuredFeatures.getOrThrow(ModConfiguredFeatures.HAZEL_KEY),
                VegetationPlacements.treePlacement(PlacementUtils.countExtra(0, 0.001f, 1),
                        ModBlocks.HAZEL_SAPLING.get()));

    }
*/

    private static ResourceKey<PlacedFeature> registerKey(String name) {
        return ResourceKey.create(Registries.PLACED_FEATURE, new ResourceLocation(Thicket.MOD_ID, name));
    }

    private static void register(BootstapContext<PlacedFeature> context, ResourceKey<PlacedFeature> key, Holder<ConfiguredFeature<?, ?>> configuration,
                                 List<PlacementModifier> modifiers) {
        context.register(key, new PlacedFeature(configuration, List.copyOf(modifiers)));
    }
}