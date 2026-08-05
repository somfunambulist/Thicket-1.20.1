package net.somfunambulist.thicket.registry;

import net.mehvahdjukaar.moonlight.api.misc.RegSupplier;
import net.mehvahdjukaar.moonlight.api.platform.RegHelper;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.somfunambulist.thicket.ThicketHelper;
import net.somfunambulist.thicket.ThicketStringDefinitions;

public class ModCreativeTabs {
    public static final RegSupplier<CreativeModeTab> THICKET_TAB = RegHelper.registerCreativeModeTab(ThicketHelper.modPrefix("thicket"),
            builder -> builder
                    .title(Component.translatable(ThicketStringDefinitions.TAB_KEY))
                    .icon(() -> ModItems.POCKET_KNIFE.get().getDefaultInstance()) //TODO should be Hazelnut later
                    .build()
    );

    private static void addToTabs(RegHelper.ItemToTabEvent event) { //TODO this can be improved later
        boolean thicketTab = event.getTab() == THICKET_TAB.get();

        if (thicketTab) {
            event.add(THICKET_TAB.getKey(), ModItems.POCKET_KNIFE.get());
        }
    }

    public static void init() {
        RegHelper.addItemsToTabsRegistration(ModCreativeTabs::addToTabs);
    }
}
