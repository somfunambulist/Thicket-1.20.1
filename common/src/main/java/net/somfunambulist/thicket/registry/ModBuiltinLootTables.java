package net.somfunambulist.thicket.registry;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.storage.loot.LootTable;

import static net.somfunambulist.thicket.ThicketHelper.path;

public class ModBuiltinLootTables {

    private static ResourceKey<LootTable> register(String location) {
        return ResourceKey.create(Registries.LOOT_TABLE, path(location));
    }
}
