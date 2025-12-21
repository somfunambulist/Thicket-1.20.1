package net.somfunambulist.thicket.tags;

import net.minecraft.core.registries.Registries;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.block.Block;
import net.somfunambulist.thicket.Thicket;

public class ModBlockTags {
    public static final TagKey<Block> CARVING_BLOCKS = TagKey.create(Registries.BLOCK, Thicket.modPrefix("carving_blocks"));
    public static final TagKey<Block> CARVEABLE = TagKey.create(Registries.BLOCK, Thicket.modPrefix("carveable"));
    public static final TagKey<Block> HAZEL_LOGS = TagKey.create(Registries.BLOCK, Thicket.modPrefix("hazel_logs"));
    public static final TagKey<Block> MYRRH_LOGS = TagKey.create(Registries.BLOCK, Thicket.modPrefix("myrrh_logs"));
    public static final TagKey<Block> SASSAFRAS_LOGS = TagKey.create(Registries.BLOCK, Thicket.modPrefix("sassafras_logs"));
    public static final TagKey<Block> FIREMILK_GROW_BLOCK = TagKey.create(Registries.BLOCK, Thicket.modPrefix("firemilk_grow_block"));
    public static final TagKey<Block> MISTLETOE_BLOCK = TagKey.create(Registries.BLOCK, Thicket.modPrefix("mistletoe_block"));
    public static final TagKey<Block> NEEDS_CRUXWOOD_TOOL = TagKey.create(Registries.BLOCK, Thicket.modPrefix("needs_cruxwood_tool"));

}
