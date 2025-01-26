package net.somfunambulist.thicket.tags;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.somfunambulist.thicket.Thicket;

public class ModItemTags {

    public static final TagKey<Item> POCKET_KNIVES = TagKey.create(Registries.ITEM, Thicket.modPrefix("pocket_knives"));
    public static final TagKey<Item> HAZEL_LOGS = TagKey.create(Registries.ITEM, Thicket.modPrefix("hazel_logs"));

}
