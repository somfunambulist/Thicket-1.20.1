package net.somfunambulist.thicket;

import net.somfunambulist.thicket.registry.ModBlockSets;
import net.somfunambulist.thicket.registry.ModCreativeTabs;
import net.somfunambulist.thicket.registry.ModItems;

public class ThicketMain {

    public static void init() {
        ModBlockSets.init();
        ModItems.init();
        ModCreativeTabs.init();

    }
}
