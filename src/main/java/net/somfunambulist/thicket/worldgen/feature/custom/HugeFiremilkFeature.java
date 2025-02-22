package net.somfunambulist.thicket.worldgen.feature.custom;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;

public class HugeFiremilkFeature implements FeatureConfiguration {
    public static final Codec<HugeFiremilkFeature> CODEC = RecordCodecBuilder.create((instance) -> {
        return instance.group(BlockStateProvider.CODEC.fieldOf("cap_provider").forGetter((getter) -> {
            return getter.capProvider;
        }), BlockStateProvider.CODEC.fieldOf("stem_provider").forGetter((getter) -> {
            return getter.stemProvider;
        }), BlockStateProvider.CODEC.fieldOf("gill_provider").forGetter((getter) -> {
            return getter.gillProvider;
        }), Codec.INT.fieldOf("foliage_radius").orElse(2 /* fallback value */).forGetter((getter) -> {
            return getter.foliageRadius;
        })).apply(instance, HugeFiremilkFeature::new);
    });

    public final BlockStateProvider capProvider;
    public final BlockStateProvider stemProvider;
    public final BlockStateProvider gillProvider;
    public final int foliageRadius;

    public HugeFiremilkFeature(
            BlockStateProvider capProviderIn,
            BlockStateProvider stemProviderIn,
            BlockStateProvider gillProviderIn,
            int foliageRadiusIn
    ) {
        this.capProvider = capProviderIn;
        this.stemProvider = stemProviderIn;
        this.gillProvider = gillProviderIn;
        this.foliageRadius = foliageRadiusIn;
    }
}