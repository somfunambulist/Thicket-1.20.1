package net.somfunambulist.thicket.core;

import net.mehvahdjukaar.moonlight.api.misc.EventCalled;
import net.mehvahdjukaar.moonlight.api.platform.RegHelper;
import net.somfunambulist.thicket.registry.dynamicpack.ServerDynamicResourcesHandler;

public class ThicketCommonEvents {

    @EventCalled
    public static void injectLootTables(RegHelper.LootInjectEvent event) {
        ServerDynamicResourcesHandler.LEAVES_TO_SAPLING_MAP.forEach((leavesLoc, additionalLoc) -> {
            if (event.getTable().equals(leavesLoc)) {
                event.addTableReference(additionalLoc);
            }
        });
    }

    public static void init() {
        RegHelper.addLootTableInjects(ThicketCommonEvents::injectLootTables);
    }
}
