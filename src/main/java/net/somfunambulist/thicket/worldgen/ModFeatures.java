package net.somfunambulist.thicket.worldgen;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.somfunambulist.thicket.Thicket;
import net.somfunambulist.thicket.worldgen.feature.HugeFiremilkConfiguration;
import net.somfunambulist.thicket.worldgen.feature.HugeFiremilkFeature;

public class ModFeatures {
    public static final DeferredRegister<Feature<?>> FeatureRegistry = DeferredRegister.create(ForgeRegistries.FEATURES, Thicket.MOD_ID);

    public static final ResourceKey<ConfiguredFeature<?, ?>> HAZEL_KEY = registerKey("hazel");
    public static final ResourceKey<ConfiguredFeature<?, ?>> SASSAFRAS_KEY = registerKey("sassafras");
    public static final ResourceKey<ConfiguredFeature<?, ?>> HUGE_FIREMILK_KEY = registerKey("huge_firemilk_mushroom");

    public static RegistryObject<Feature<?>> HUGE_FIREMILK_PLACED = FeatureRegistry.register("huge_firemilk_mushroom", () -> new HugeFiremilkFeature(HugeFiremilkConfiguration.CODEC));

    public static void register(IEventBus modEventBus) {
        FeatureRegistry.register(modEventBus);
    }

    public static ResourceKey<ConfiguredFeature<?, ?>> registerKey(String name) {
        return ResourceKey.create(Registries.CONFIGURED_FEATURE, new ResourceLocation(Thicket.MOD_ID, name));
    }
}