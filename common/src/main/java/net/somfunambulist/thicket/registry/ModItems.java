package net.somfunambulist.thicket.registry;

import net.mehvahdjukaar.moonlight.api.misc.RegSupplier;
import net.mehvahdjukaar.moonlight.api.platform.RegHelper;
import net.minecraft.world.item.Item;
import net.somfunambulist.thicket.ThicketHelper;
import net.somfunambulist.thicket.content.items.PocketKnife;

public class ModItems {

    public static final RegSupplier<Item> POCKET_KNIFE = RegHelper.registerItem(ThicketHelper.modPrefix("pocket_knife"), () -> new PocketKnife(new Item.Properties().stacksTo(1).durability(238)));

    public static void init() {}
}
