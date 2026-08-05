package net.somfunambulist.thicket;

import net.somfunambulist.thicket.registry.ModCreativeTabs;
import net.somfunambulist.thicket.registry.ModItems;

public class ThicketMain {

    public static void init() {

        ModItems.init();
        ModCreativeTabs.init();
    }
}
