package net.somfunambulist.thicket.worldgen.feature;

import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;
import net.somfunambulist.thicket.Thicket;
import net.somfunambulist.thicket.worldgen.feature.custom.HugeFiremilkFeature;

public class ModFeatureTypes {
    public static final DeferredRegister<Feature<?>> FEATURE_TYPES =
            DeferredRegister.create(Registries.FEATURE, Thicket.MOD_ID);

    public static final RegistryObject<Feature<HugeFiremilkFeature>> HUGE_FIREMILK_FEATURE =
            FEATURE_TYPES.register("huge_firemilk_feature", () -> new Feature<>(HugeFiremilkFeature.CODEC) {
                @Override
                public boolean place(FeaturePlaceContext<HugeFiremilkFeature> pContext) {
                    return false;
                }
            });

    public static void register(IEventBus eventBus) {
        FEATURE_TYPES.register(eventBus);
    }
}
