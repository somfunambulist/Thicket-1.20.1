package net.somfunambulist.thicket.core;

import net.mehvahdjukaar.moonlight.api.misc.EventCalled;
import net.mehvahdjukaar.moonlight.api.platform.RegHelper;
import net.mehvahdjukaar.moonlight.api.set.leaves.LeavesTypeRegistry;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.somfunambulist.thicket.registry.ModBuiltinLootTables;
import net.somfunambulist.thicket.registry.dynamicpack.ServerDynamicResourcesHandler;

import static net.somfunambulist.thicket.ThicketHelper.path;

public class ThicketCommonEvents {

    @EventCalled
    public static void injectLootTables(RegHelper.LootInjectEvent event) {
        ServerDynamicResourcesHandler.LEAVES_TO_SAPLING_MAP.forEach((leavesLoc, additionalLoc) -> {
            if (event.getTable().equals(leavesLoc)) {
                event.addTableReference(additionalLoc);
                System.out.println(leavesLoc);
            }
        });
    }

    public static void init() {
        RegHelper.addLootTableInjects(ThicketCommonEvents::injectLootTables);
    }
}
