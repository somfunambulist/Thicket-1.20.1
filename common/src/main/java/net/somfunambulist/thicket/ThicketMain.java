package net.somfunambulist.thicket;

import net.mehvahdjukaar.moonlight.api.platform.RegHelper;
import net.somfunambulist.thicket.registry.ModBlockSets;
import net.somfunambulist.thicket.registry.ModCreativeTabs;
import net.somfunambulist.thicket.registry.ModItems;
import net.somfunambulist.thicket.registry.ModRecipes;
import net.somfunambulist.thicket.registry.dynamicpack.ServerDynamicResourcesHandler;

public class ThicketMain {

    public static void init() {
        ModBlockSets.init();
        ModItems.init();
        ModCreativeTabs.init();
        ModRecipes.init();

        RegHelper.registerDynamicResourceProvider(new ServerDynamicResourcesHandler());
    }
}
