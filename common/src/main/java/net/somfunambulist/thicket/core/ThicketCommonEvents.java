package net.somfunambulist.thicket.core;

import net.mehvahdjukaar.moonlight.api.misc.EventCalled;
import net.mehvahdjukaar.moonlight.api.platform.RegHelper;
import net.mehvahdjukaar.moonlight.api.set.leaves.LeavesTypeRegistry;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.somfunambulist.thicket.registry.ModBuiltinLootTables;

import static net.somfunambulist.thicket.ThicketHelper.path;

public class ThicketCommonEvents {

    @EventCalled
    public static void injectLootTables(RegHelper.LootInjectEvent event) {
        for (var l : LeavesTypeRegistry.INSTANCE) {
            if (event.getTable().equals(l.leaves.getLootTable().location())) {
                event.addTableReference(path("blocks/additional_sapling_" + l.getAppendableId().replace("/", "_")));
            }
        }
    }

    public static void init() {
        RegHelper.addLootTableInjects(ThicketCommonEvents::injectLootTables);
    }
}
