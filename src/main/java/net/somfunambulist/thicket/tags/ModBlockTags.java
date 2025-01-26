package net.somfunambulist.thicket.tags;

import net.minecraft.core.registries.Registries;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.block.Block;
import net.somfunambulist.thicket.Thicket;

public class ModBlockTags {
    public static final TagKey<Block> CARVING_BLOCKS = TagKey.create(Registries.BLOCK, Thicket.modPrefix("carving_blocks"));
    public static final TagKey<Block> CARVEABLE = TagKey.create(Registries.BLOCK, Thicket.modPrefix("carveable"));
    public static final TagKey<Block> HAZEL_LOGS = TagKey.create(Registries.BLOCK, Thicket.modPrefix("hazel_logs"));
}
