package net.somfunambulist.thicket.worldgen.feature;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;

public class HugeFiremilkConfiguration implements FeatureConfiguration {
    public static final Codec<HugeFiremilkConfiguration> CODEC = RecordCodecBuilder.create((instance) -> instance.group(
            BlockStateProvider.CODEC.fieldOf("cap_provider").forGetter((getter) -> getter.capProvider),
            BlockStateProvider.CODEC.fieldOf("stem_provider").forGetter((getter) -> getter.stemProvider),
            BlockStateProvider.CODEC.fieldOf("gill_provider").forGetter((getter) -> getter.gillProvider),
            Codec.INT.fieldOf("foliage_radius").orElse(2 /* fallback value */).forGetter((getter) -> getter.foliageRadius)
    ).apply(instance, HugeFiremilkConfiguration::new));

    public final BlockStateProvider capProvider;
    public final BlockStateProvider stemProvider;
    public final BlockStateProvider gillProvider;
    public final int foliageRadius;

    public HugeFiremilkConfiguration(BlockStateProvider capProviderIn, BlockStateProvider stemProviderIn, BlockStateProvider gillProviderIn, int foliageRadiusIn) {
        this.capProvider = capProviderIn;
        this.stemProvider = stemProviderIn;
        this.gillProvider = gillProviderIn;
        this.foliageRadius = foliageRadiusIn;
    }
}