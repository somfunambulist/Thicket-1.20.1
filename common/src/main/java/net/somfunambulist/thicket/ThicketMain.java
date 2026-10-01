package net.somfunambulist.thicket;

import net.somfunambulist.thicket.registry.ModBlockSets;
import net.somfunambulist.thicket.registry.ModCreativeTabs;
import net.somfunambulist.thicket.registry.ModItems;
import net.somfunambulist.thicket.registry.ModRecipes;

public class ThicketMain {

    public static void init() {
        ModBlockSets.init();
        ModItems.init();
        ModCreativeTabs.init();
        ModRecipes.init();
    }
}
